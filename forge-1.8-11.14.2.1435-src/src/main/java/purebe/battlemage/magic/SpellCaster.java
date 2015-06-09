package purebe.battlemage.magic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ResourceLocation;
import purebe.battlemage.battlemage.BattleMage;
import purebe.battlemage.network.CastSpellMsg;

public class SpellCaster {
	public enum SpellSymbol {
		White, Black, Chaos, Nature, Power;
	}
	
	public enum SpellName {
		None, Cancel, Illuminate, ArtilleryCattle, Teleport, MagicalWall, Gust, Fly, EndFlight
	}
	
	public final int MAX_SYMBOLS = 5;
	public final List<SpellSymbol> currentIncantation;
	public final List<Integer> symbolTimeAlive;
	public int tickCount = 0;
	public int coolDownTicks = 20;
	public boolean cooldown = false;
	public SpellName currentIncantationSpell = SpellName.None;
	public Map<SpellName, Spell> spellRegistry;
	
	public SpellCaster() {
		currentIncantation = new ArrayList<SpellSymbol>(5);
		symbolTimeAlive    = new ArrayList<Integer>(5);
		spellRegistry = new HashMap<SpellName, Spell>();
		
		registerSpell(SpellName.Illuminate, new IlluminateSpell(new ResourceLocation("battlemage:textures/gui/illuminate_icon.png")));
		registerSpell(SpellName.ArtilleryCattle, new ArtilleryCattleSpell(new ResourceLocation("battlemage:textures/gui/artillerycattle_icon.png")));
		registerSpell(SpellName.Teleport, new TeleportSpell(new ResourceLocation("battlemage:textures/gui/teleport_icon.png")));
		registerSpell(SpellName.MagicalWall, new MagicalWallSpell(new ResourceLocation("battlemage:textures/gui/magicalwall_icon.png")));
		registerSpell(SpellName.Gust, new GustSpell(new ResourceLocation("battlemage:textures/gui/gust_icon.png")));
		registerSpell(SpellName.Fly, new FlySpell(new ResourceLocation("battlemage:textures/gui/fly_icon.png")));
	}
	
	public void registerSpell(SpellName spellName, Spell spell) {
		spellRegistry.put(spellName, spell);
	}
	
	public boolean addSymbol(SpellSymbol symbol) {
		if (!cooldown) {
			currentIncantation.add(symbol);
			symbolTimeAlive.add(0);
			return checkIncantation();
		}
		return true;
	}
	
	// Returns true if there is a completed spell incantation in the list,
	// or if it has reached the max number of symbols. Returns false otherwise.
	private boolean checkIncantation() {		
		for (Spell spell : spellRegistry.values()) {
			if (currentIncantation.equals(spell.getIncantation())) {
				if (spell.cdTick == 0) {
					Minecraft.getMinecraft().thePlayer.sendChatMessage("You cast: " + spell.getName().toString());
					BattleMage.network.sendToServer(new CastSpellMsg(spell.getName(), Minecraft.getMinecraft().thePlayer.getLookVec()));
					spell.setOnCooldown();
				}
				else {
					Minecraft.getMinecraft().thePlayer.sendChatMessage("You tried to cast: " + spell.getName().toString() + " but it was on cooldown!");
				}
				cooldown = true;
				coolDownTicks = 20;
				currentIncantationSpell = spell.getName();
				return true;
			}
		}
		if (currentIncantation.size() >= MAX_SYMBOLS) {
			System.out.println(currentIncantation);
			cooldown = true;
			System.out.println("Your spell fizzled out and nothing happened");
			Minecraft.getMinecraft().thePlayer.sendChatMessage("Your spell fizzled out and nothing happened");
			coolDownTicks = 20;
			currentIncantationSpell = SpellName.Cancel;
			return true;
		}
		return false;
	}
	
	public void clearSymbols() {
		if (!currentIncantation.isEmpty()) {
			System.out.println(currentIncantation);
			currentIncantation.clear();
			System.out.println("You wave your wand around and begin to cast a new spell!");
			Minecraft.getMinecraft().thePlayer.sendChatMessage("You wave your wand around and begin to cast a new spell!");
			cooldown = true;
			coolDownTicks = 5;
			currentIncantationSpell = SpellName.Cancel;
		}
	}
	
	public void updateTick() {
		if (cooldown) {
			if (++tickCount >= coolDownTicks) {
				cooldown = false;
				tickCount = 0;
				currentIncantation.clear();
				symbolTimeAlive.clear();
				currentIncantationSpell = SpellName.None;
			}
		}
		for (int i = 0; i < symbolTimeAlive.size(); ++i) {
			symbolTimeAlive.set(i, symbolTimeAlive.get(i) + 1);
		}
		for (Spell spell : spellRegistry.values()) {
			spell.update();
		}
	}
}
