package purebe.battlemage.magic;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import purebe.battlemage.battlemage.BattleMage;
import purebe.battlemage.magic.SpellCaster.SpellName;
import purebe.battlemage.magic.SpellCaster.SpellSymbol;
import purebe.battlemage.network.CastSpellMsg;

public class FlySpell extends Spell {
	final EntityPlayer player;
	final boolean initSpell;
	
	public FlySpell(ResourceLocation icon) {
		player = null;
		this.icon = icon;
		super.cdDuration = 10 * 20;
		this.initSpell = false;
	}
	
	public FlySpell(EntityPlayer player, boolean initSpell) {
		this.player = player;
		this.intensity = 1;
		this.initSpell = initSpell;
	}
	
	@Override
	public void cast() {
		if (initSpell) {
			player.capabilities.allowFlying = true;
			player.motionY = 8.0f;
			player.capabilities.isFlying = true;
		}
		else {
			if (!player.capabilities.isCreativeMode) {
				player.fallDistance = 0f;
				player.capabilities.allowFlying = false;
				player.capabilities.isFlying = false;
			}
		}
	}

	@Override
	public void initClient() {
		super.initClient();
		EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
		player.capabilities.allowFlying = true;
		player.motionY = 8.0f;
		player.capabilities.isFlying = true;
	}

	@Override
	public void update() {
		if (!this.clientInit && this.hasCast) {
			this.initClient();
		}
		// Check if cdTick is 1 as we decrement the tick in the super.update() call at the bottom of this function
		if (cdTick == 1 && this.hasCast) {
			EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
			if (!player.capabilities.isCreativeMode) {
				player.fallDistance = 0f;
				player.capabilities.allowFlying = false;
				player.capabilities.isFlying = false;
				BattleMage.network.sendToServer(new CastSpellMsg(SpellName.EndFlight, player.getLookVec()));
			}
		}
		super.update();
	}

	@Override
	public List<SpellSymbol> getIncantation() {
		return Arrays.asList(SpellSymbol.Chaos, SpellSymbol.Chaos, SpellSymbol.Chaos, SpellSymbol.White, SpellSymbol.Power);
	}

	@Override
	public SpellName getName() {
		return SpellName.Fly;
	}
}