package purebe.battlemage.magic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import purebe.battlemage.algorithms.Vectors;
import purebe.battlemage.blocks.BattleMageBlocks;
import purebe.battlemage.magic.SpellCaster.SpellName;
import purebe.battlemage.magic.SpellCaster.SpellSymbol;

public class IlluminateSpell extends Spell {
	final static int MAX_SIZE = 12;
	
	final EntityPlayer player;
	
	public IlluminateSpell(ResourceLocation icon) {
		player = null;
		this.icon = icon;
	}
	
	public IlluminateSpell(EntityPlayer player) {
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

	@Override
	public List<SpellSymbol> getIncantation() {
		return Arrays.asList(SpellSymbol.White, SpellSymbol.Nature, SpellSymbol.Chaos);
	}

	@Override
	public SpellName getName() {
		return SpellName.Illuminate;
	}
}
