package purebe.battlemage.eventhandlers;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import purebe.battlemage.battlemage.ClientProxy;
import purebe.battlemage.battlemage.BattleMage;

public class ClientEvents {
	@SubscribeEvent
	public void PlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			((ClientProxy) BattleMage.proxy).spellCaster.updateTick();
		}
	}
}