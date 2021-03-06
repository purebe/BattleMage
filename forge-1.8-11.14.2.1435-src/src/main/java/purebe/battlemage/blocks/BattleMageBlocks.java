package purebe.battlemage.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class BattleMageBlocks {
	public static Block illuminatedAir, magicalWallBlock;

	public static void createBlocks() {
		GameRegistry.registerBlock(illuminatedAir = new IlluminatedAir(), "illuminated_air");
		GameRegistry.registerBlock(magicalWallBlock = new MagicalWallBlock(Material.glass), "magical_wall_block");
	}
}