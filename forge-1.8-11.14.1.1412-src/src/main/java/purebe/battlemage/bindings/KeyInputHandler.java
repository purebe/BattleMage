package purebe.battlemage.bindings;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import purebe.battlemage.magic.SpellCaster.SpellSymbol;
import purebe.battlemage.main.ClientProxy;
import purebe.battlemage.main.Main;

public class KeyInputHandler {
	
	public KeyInputHandler() {
	}
	
	@SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (KeyBindings.white.isPressed()) {
			((ClientProxy)Main.proxy).castSymbol(SpellSymbol.White);
        }
		if (KeyBindings.black.isPressed()) {
			((ClientProxy)Main.proxy).castSymbol(SpellSymbol.Black);
        }
		if (KeyBindings.chaos.isPressed()) {
			((ClientProxy)Main.proxy).castSymbol(SpellSymbol.Chaos);
        }
		if (KeyBindings.nature.isPressed()) {
			((ClientProxy)Main.proxy).castSymbol(SpellSymbol.Nature);
        }
		if (KeyBindings.power.isPressed()) {
			((ClientProxy)Main.proxy).castSymbol(SpellSymbol.Power);
        }
		if (KeyBindings.clear.isPressed()) {
			((ClientProxy)Main.proxy).clearSymbols();
		}
    }
}