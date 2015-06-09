package purebe.battlemage.magic;

import java.util.List;

import net.minecraft.util.ResourceLocation;
import purebe.battlemage.magic.SpellCaster.SpellName;
import purebe.battlemage.magic.SpellCaster.SpellSymbol;

public abstract class Spell {
	protected int cdTick = 0;
	protected int cdDuration = 5 * 20;
	protected int intensity = 0;
	protected boolean clientInit = false;
	protected boolean hasCast = false;
	protected ResourceLocation icon = null;

	public abstract void cast();
	public abstract List<SpellSymbol> getIncantation();
	public abstract SpellName getName();
	
	public int getCoolDownTick() {
		return cdTick;
	}
	
	public int getCoolDownDuration() {
		return cdDuration;
	}
	
	public void setOnCooldown() {
		cdTick = cdDuration;
		hasCast = true;
	}

	public void initClient() {
		clientInit = true;
	}

	public void update() {
		if (cdTick > 0) {
			--cdTick;
			if (cdTick == 0) {
				clientInit = false;
				hasCast = false;
			}
		}
	}
	
	public ResourceLocation getIcon() {
		return icon;
	}
}
