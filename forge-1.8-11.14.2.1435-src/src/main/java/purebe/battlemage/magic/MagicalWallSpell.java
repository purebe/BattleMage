package purebe.battlemage.magic;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import purebe.battlemage.blocks.BattleMageBlocks;
import purebe.battlemage.magic.SpellCaster.SpellName;
import purebe.battlemage.magic.SpellCaster.SpellSymbol;

public class MagicalWallSpell extends Spell {
	final EntityPlayer     player;
	final Vec3             playerLookat;
	
	public MagicalWallSpell(ResourceLocation icon) {
		player = null;
		playerLookat = null;
		this.icon = icon;
	}

	public MagicalWallSpell(EntityPlayer player, Vec3 playerLookat) {
		this.player = player;
		this.playerLookat = playerLookat;
		this.intensity = 1;
	}
	
	@Override
	public void cast() {
		System.out.println("playerPosition: " + player.getPositionVector());
		Vec3 wallPosition = new Vec3(player.posX + playerLookat.xCoord,
                                     player.posY,
                                     player.posZ + playerLookat.zCoord);
		System.out.println("wallPosition: " + wallPosition);
		Vec3 facingDirection = wallPosition.subtract(player.getPositionVector()).normalize();
		Vec3 facingDirectionPlus = new Vec3(facingDirection.xCoord * 3, 0, facingDirection.zCoord * 3);
		wallPosition = player.getPositionVector().add(facingDirectionPlus);
		System.out.println("facingDirection: " + facingDirection);
		Vec3 upVector = new Vec3(0, 1, 0);
		Vec3 wallDirection = upVector.crossProduct(facingDirection);
		System.out.println("wallDirection: " + wallDirection);
		
		BlockPos wallPos;
		for (int i = 0; i < 3; ++i) {
			for (int k = -2; k < 3; ++k) {
				Vec3 blockPosition = wallPosition.addVector(k * wallDirection.xCoord, i , k * wallDirection.zCoord);
				wallPos = new BlockPos(blockPosition);
				//System.out.println(blockPosition);
				if (player.worldObj.getBlockState(wallPos) == Blocks.air.getDefaultState()) {
					player.worldObj.setBlockState(wallPos, BattleMageBlocks.magicalWallBlock.getDefaultState());
					player.worldObj.scheduleUpdate(wallPos, BattleMageBlocks.magicalWallBlock, 200);
				}
			}
		}
	}

	@Override
	public List<SpellSymbol> getIncantation() {
		return Arrays.asList(SpellSymbol.White, SpellSymbol.Nature, SpellSymbol.White, SpellSymbol.Nature, SpellSymbol.Power);
	}

	@Override
	public SpellName getName() {
		return SpellName.MagicalWall;
	}
}
