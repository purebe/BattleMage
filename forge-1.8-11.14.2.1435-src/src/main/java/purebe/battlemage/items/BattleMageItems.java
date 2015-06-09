package purebe.battlemage.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class BattleMageItems {
	public static Item woodenWand;
	
	public static void createItems() {
		GameRegistry.registerItem(woodenWand = new WoodenWand("wooden_wand"), "wooden_wand");
	}
}