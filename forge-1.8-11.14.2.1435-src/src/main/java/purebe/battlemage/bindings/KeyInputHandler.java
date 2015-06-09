package purebe.battlemage.bindings;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import purebe.battlemage.magic.SpellCaster.SpellSymbol;
import purebe.battlemage.battlemage.ClientProxy;
import purebe.battlemage.battlemage.BattleMage;

public class KeyInputHandler {
	
	public KeyInputHandler() {
	}
	
	@SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (KeyBindings.white.isPressed()) {
			((ClientProxy) BattleMage.proxy).castSymbol(SpellSymbol.White);
        }
		if (KeyBindings.black.isPressed()) {
			((ClientProxy) BattleMage.proxy).castSymbol(SpellSymbol.Black);
        }
		if (KeyBindings.chaos.isPressed()) {
			((ClientProxy) BattleMage.proxy).castSymbol(SpellSymbol.Chaos);
        }
		if (KeyBindings.nature.isPressed()) {
			((ClientProxy) BattleMage.proxy).castSymbol(SpellSymbol.Nature);
        }
		if (KeyBindings.power.isPressed()) {
			((ClientProxy) BattleMage.proxy).castSymbol(SpellSymbol.Power);
        }
		if (KeyBindings.clear.isPressed()) {
			((ClientProxy) BattleMage.proxy).clearSymbols();
		}
    }
}
