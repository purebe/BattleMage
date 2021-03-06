package purebe.battlemage.entities;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ArtilleryCattleEntity extends EntityCow {
	protected int explosionPower = 1;
	public ArtilleryCattleEntity(World worldIn) {
		super(worldIn);
	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.80000000298023224D);
    }
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		
		if (ticksExisted % 5 == 0) { 
			worldObj.spawnParticle(EnumParticleTypes.HEART, posX, posY, posZ, 0.0, 0.0, 0.0);
		}
		
		if (ticksExisted > 60) {
			if (ticksExisted % 2 == 0) {
				worldObj.createExplosion(null, posX, posY, posZ, explosionPower, false);
			}
			if (ticksExisted % 3 == 2) {
				//++explosionPower;
			}
		}
	}
	
	@Override
	public void onDeath(DamageSource cause) {
		this.worldObj.createExplosion(null, posX, posY, posZ, 6, false);
		
		if (net.minecraftforge.common.ForgeHooks.onLivingDeath(this, cause)) return;
		this.dead = true;
		
		this.worldObj.setEntityState(this, (byte)3);
	}
}
