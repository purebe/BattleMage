package purebe.battlemage.blocks.clientrender;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import purebe.battlemage.blocks.BattleMageBlocks;
import purebe.battlemage.battlemage.BattleMage;


public final class BlockRenderRegister {
	public static void registerBlockRenderer() {
		registerBlock(BattleMageBlocks.illuminatedAir);
		registerBlock(BattleMageBlocks.magicalWallBlock);
	}
	
	public static void registerBlock(Block block) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
				Item.getItemFromBlock(block), 
				0, 
				new ModelResourceLocation(BattleMage.MODID + ":" + block.getUnlocalizedName().substring(5),
				"inventory"));
	}
}