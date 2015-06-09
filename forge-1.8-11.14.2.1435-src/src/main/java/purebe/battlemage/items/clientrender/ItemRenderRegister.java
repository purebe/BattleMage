package purebe.battlemage.items.clientrender;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import purebe.battlemage.items.BattleMageItems;
import purebe.battlemage.battlemage.BattleMage;

public final class ItemRenderRegister {
	public static void registerItemRenderer() {
		registerItem(BattleMageItems.woodenWand);
	}
	
	public static void registerItem(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
				.register(item, 0, new ModelResourceLocation(BattleMage.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}