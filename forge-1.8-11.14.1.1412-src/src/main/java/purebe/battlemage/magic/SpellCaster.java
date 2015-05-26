package purebe.battlemage.magic;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import purebe.battlemage.magic.Spells.Spell;
import purebe.battlemage.main.Main;
import purebe.battlemage.network.CastSpellMsg;

public class SpellCaster {
	public enum SpellSymbol {
		White, Black, Chaos, Nature, Power;
	}
	
	public final int MAX_SYMBOLS = 5;
	public final List<SpellSymbol> currentIncantation;
	public final List<Integer> symbolTimeAlive;
	public int tickCount = 0;
	public int coolDownTicks = 20;
	public boolean cooldown = false;
	public Spell currentSpell = Spell.None;
	
	public SpellCaster() {
		currentIncantation = new ArrayList<SpellSymbol>(5);
		symbolTimeAlive    = new ArrayList<Integer>(5);
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
		for (Spell spell : Spell.values()) {
			if (currentIncantation.equals(Spell.getIncantation(spell))) {
				Minecraft.getMinecraft().thePlayer.sendChatMessage("You cast: " + spell.toString());
				Main.network.sendToServer(new CastSpellMsg(spell, Minecraft.getMinecraft().thePlayer.getLookVec()));
				cooldown = true;
				coolDownTicks = 20;
				currentSpell = spell;
				return true;
			}
		}
		if (currentIncantation.size() >= MAX_SYMBOLS) {
			System.out.println(currentIncantation);
			cooldown = true;
			System.out.println("Your spell fizzled out and nothing happened");
			Minecraft.getMinecraft().thePlayer.sendChatMessage("Your spell fizzled out and nothing happened");
			coolDownTicks = 20;
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
			currentSpell = Spell.Cancel;
		}
	}
	
	public void updateTick() {
		if (cooldown) {
			if (++tickCount >= coolDownTicks) {
				cooldown = false;
				tickCount = 0;
				currentIncantation.clear();
				symbolTimeAlive.clear();
				currentSpell = Spell.None;
			}
		}
		for (int i = 0; i < symbolTimeAlive.size(); ++i) {
			symbolTimeAlive.set(i, symbolTimeAlive.get(i) + 1);
		}
	}
}
