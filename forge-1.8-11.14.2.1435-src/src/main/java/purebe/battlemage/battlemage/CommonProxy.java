package purebe.battlemage.battlemage;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import purebe.battlemage.blocks.BattleMageBlocks;
import purebe.battlemage.entities.ArtilleryCattleEntity;
import purebe.battlemage.items.BattleMageItems;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent e) {
		BattleMageItems.createItems();
		BattleMageBlocks.createBlocks();
	}

	public void init(FMLInitializationEvent e) {
		EntityRegistry.registerModEntity(ArtilleryCattleEntity.class, "ArtilleryCattle", BattleMage.uniqueEntityId++,
		          BattleMage.instance, 150, 1, true);
	}

	public void postInit(FMLPostInitializationEvent e) {
	}
}
