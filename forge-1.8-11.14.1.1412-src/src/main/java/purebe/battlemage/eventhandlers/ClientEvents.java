package purebe.battlemage.eventhandlers;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import purebe.battlemage.main.ClientProxy;
import purebe.battlemage.main.Main;

public class ClientEvents {
	@SubscribeEvent
	public void ClientTick(ClientTickEvent event) {
		((ClientProxy)Main.proxy).spellCaster.updateTick();
	}
}