package purebe.battlemage.magic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import purebe.battlemage.entities.ArtilleryCattleEntity;

public class ArtilleryCattle implements ISpell {
	EntityPlayer           player;
	ArtilleryCattleEntity  babyCow;
	Vec3                   playerLookat;
	int                    intensity;
	
	
	public ArtilleryCattle(EntityPlayer player, Vec3 playerLookat) {
		this.player = player;
		this.playerLookat = playerLookat;
		this.intensity = 1;
	}
	
	@Override
	public void cast() {
		System.out.println("You cast Artillery Cattle!");
		
		Vec3 cowPosition;
		Vec3 upVector = new Vec3(0, 1, 0);
		Vec3 lookAtY = playerLookat.subtract(playerLookat.xCoord, 0, playerLookat.zCoord);
		double lookAtAngleY = upVector.dotProduct(lookAtY);
		
		cowPosition = new Vec3(player.posX + playerLookat.xCoord * 2,
		                       player.posY + lookAtAngleY * 3 + 1,
		                       player.posZ + playerLookat.zCoord * 2);

		
		babyCow = new ArtilleryCattleEntity(player.worldObj);
		babyCow.setGrowingAge(-100);
		babyCow.setLocationAndAngles(cowPosition.xCoord, cowPosition.yCoord, cowPosition.zCoord, player.rotationYaw, 0.0f);
		babyCow.motionX = playerLookat.xCoord * 3;
		babyCow.motionY = lookAtAngleY * 3;
		babyCow.motionZ = playerLookat.zCoord * 3;
		player.worldObj.spawnEntityInWorld(babyCow);
	}
}
