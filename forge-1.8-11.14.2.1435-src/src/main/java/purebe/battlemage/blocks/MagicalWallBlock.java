package purebe.battlemage.blocks;

import java.util.Random;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MagicalWallBlock extends BlockBreakable
{
    public MagicalWallBlock(Material material)
    {
        super(material, false);
        this.setHardness(2.5F);
        this.setHarvestLevel("pickaxe", 0);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
	public int quantityDropped(Random random)
    {
        return 0;
    }

    @Override
	@SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }

    @Override
	public boolean isFullCube()
    {
        return false;
    }

    @Override
	protected boolean canSilkHarvest()
    {
        return false;
    }
    
    @Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    	worldIn.scheduleUpdate(pos, state.getBlock(), 5);
    	if (rand.nextInt(25) == 0) {
    		worldIn.setBlockToAir(pos);
    	}
    }
}