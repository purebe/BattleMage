package purebe.battlemage.magic;

import java.util.LinkedList;
import java.util.Queue;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import purebe.battlemage.algorithms.Vectors;
import purebe.battlemage.blocks.BattleMageBlocks;

public class Illuminate implements ISpell {
	final static int MAX_SIZE = 12;
	
	final EntityPlayer player;
	final int          intensity;
	
	public Illuminate(EntityPlayer player) {
		this.player = player;
		this.intensity = 1;
	}

	@Override
	public void cast() {
		System.out.println("You cast Illuminate!");
		
		int count = 0;
		Queue<BlockPos> storage = new LinkedList<BlockPos>();
		BlockPos checkPos = player.getPosition().add(0, 1, 0);
		storage.add(checkPos);
		
		while (!storage.isEmpty()) {
			if (count >= MAX_SIZE) {
				break;
			}
			checkPos = storage.remove();
			if (player.worldObj.getBlockState(checkPos).getBlock() == Blocks.air) {
				System.out.println(checkPos);
				if (player.getRNG().nextInt(10) == 0) {
					++count;
					player.worldObj.setBlockState(checkPos, BattleMageBlocks.illuminatedAir.getDefaultState());
				}
				Vectors.Enqueue2DDirections(storage, checkPos);
			}
		}
	}
}