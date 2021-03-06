package purebe.battlemage.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class IlluminatedAir extends Block {	
	public IlluminatedAir() {
		super(Material.air);
		this.setLightLevel(1.0f);
		this.setTickRandomly(true);
	}
	
	@Override
	public int getRenderType()
    {
        return -1;
    }
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        return null;
    }

    @Override
	public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid)
    {
        return false;
    }
    
    /**
     * Whether this Block can be replaced directly by other blocks (true for e.g. tall grass)
     */
    @Override
	public boolean isReplaceable(World worldIn, BlockPos pos){ return true; }
    
    @Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
    	// Lights fade out randomly over time
    	//if (rand.nextInt(10) == 0) {
    		worldIn.setBlockToAir(pos);
    	//}
    }
    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
	public int quantityDropped(Random random)
    {
        return 0;
    }
}