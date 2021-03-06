package purebe.battlemage.magic;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import purebe.battlemage.magic.SpellCaster.SpellName;
import purebe.battlemage.magic.SpellCaster.SpellSymbol;

public class TeleportSpell extends Spell {
	final EntityPlayer     player;
	final Vec3             playerLookat;
	final int              max_distance;
	final int              min_distance;
	final int[]            checkPattern = { 0, 1, -1, 2, -2 };
	
	public TeleportSpell(ResourceLocation icon) {
		player = null;
		playerLookat = null;
		max_distance = 0;
		min_distance = 0;
		this.icon = icon;
	}

	public TeleportSpell(EntityPlayer player, Vec3 playerLookat) {
		this.player = player;
		this.playerLookat = playerLookat;
		this.intensity = 1;
		this.max_distance = 40;
		this.min_distance = 15;
	}
	
	@Override
	public void cast() {
		Vec3 telePosition = new Vec3(player.posX + playerLookat.xCoord,
                player.posY,
                player.posZ + playerLookat.zCoord);
		
		if (teleportThruWall(telePosition)) {
			return;
		}
		if (teleport(telePosition)) {
			return;
		}
		
		System.out.println(player.getDisplayName().getFormattedText() + "'s teleport spell fizzled out.");
	}
	
	protected boolean hasHeadroom(Vec3 position) {
		BlockPos pos = new BlockPos(Math.floor(position.xCoord), Math.floor(position.yCoord), Math.floor(position.zCoord));
		if (!player.worldObj.getBlockState(pos).getBlock().isFullCube() ||
		    !player.worldObj.getBlockState(pos).getBlock().isOpaqueCube()) {
			if (!player.worldObj.getBlockState(pos.add(0, 1, 0)).getBlock().isFullCube() ||
			    !player.worldObj.getBlockState(pos.add(0, 1, 0)).getBlock().isOpaqueCube()) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean teleportThruWall(Vec3 telePosition) {
		boolean isSolidWall = !hasHeadroom(telePosition) && !hasHeadroom(telePosition.addVector(0, 1, 0));
		boolean isWindowedWall = !hasHeadroom(telePosition) && !hasHeadroom(telePosition.addVector(0, 2, 0));
		
		if (isSolidWall || isWindowedWall) {
			for (int k = 0; k < checkPattern.length; ++k) {
				for (int i = 1; i <= min_distance; ++i) {
					telePosition = new Vec3(player.posX + playerLookat.xCoord * i,
					                       player.posY + checkPattern[k],
					                       player.posZ + playerLookat.zCoord * i);
					if (hasHeadroom(telePosition)) {
						player.setPositionAndUpdate(telePosition.xCoord, telePosition.yCoord, telePosition.zCoord);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	protected boolean teleport(Vec3 telePosition) {
		int randomMaxDistance = player.getRNG().nextInt(max_distance) + min_distance;
		for (int k = 0; k < checkPattern.length; ++k) {
			for (int i = randomMaxDistance; i >= min_distance; --i) {
				telePosition = new Vec3(player.posX + playerLookat.xCoord * i,
				                       player.posY + checkPattern[k],
				                       player.posZ + playerLookat.zCoord * i);
				if (hasHeadroom(telePosition)) {
					player.setPositionAndUpdate(telePosition.xCoord, telePosition.yCoord, telePosition.zCoord);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List<SpellSymbol> getIncantation() {
		return Arrays.asList(SpellSymbol.Chaos, SpellSymbol.White, SpellSymbol.Chaos, SpellSymbol.White, SpellSymbol.Chaos);
	}
	
	@Override
	public SpellName getName() {
		return SpellName.Teleport;
	}
}
