package purebe.battlemage.magic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldServer;
import purebe.battlemage.magic.SpellCaster.SpellName;
import purebe.battlemage.magic.SpellCaster.SpellSymbol;

public class GustSpell extends Spell {
	final EntityPlayer     player;
	final Vec3             playerLookat;
	
	public GustSpell(ResourceLocation icon) {
		player = null;
		playerLookat = null;
		this.icon = icon;
	}

	public GustSpell(EntityPlayer player, Vec3 playerLookat) {
		this.player = player;
		this.playerLookat = playerLookat;
		this.intensity = 1;
	}
	
	@Override
	public void cast() {
		List<Entity> entityList = new ArrayList<Entity>();
		// TODO: Send a packet to nearby/all clients handling
		// the spawning of the particles (since this is packet-spammy)
		for (int i = 0; i < 18; ++i) {
			double offsetX = player.posX + (i * playerLookat.xCoord);
			double offsetY = player.posY + (i * playerLookat.yCoord);
			double offsetZ = player.posZ + (i * playerLookat.zCoord);
		    ((WorldServer)player.worldObj).spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL,
		                                                 offsetX,
		                                                 offsetY,
		                                                 offsetZ,
		                                                 5,
		                                                 player.getRNG().nextDouble() * (i/5.0f),
		                                                 player.getRNG().nextDouble(),
		                                                 player.getRNG().nextDouble() * (i/5.0f),
		                                                 0, new int[0]);
		    
		    AxisAlignedBB aabb = new AxisAlignedBB(offsetX - (i/2), offsetY - 2, offsetZ - (i/2), offsetX + (i/2), offsetY + 2, offsetZ + (i/2));
			entityList.addAll(player.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)null, aabb));
		}
		
		for (Entity e : entityList) {
			System.out.println(e.getName());
			Vec3 direction = e.getPositionVector().subtract(player.getPositionVector()).normalize();
			e.motionX = direction.xCoord * 5;
			e.motionY = direction.yCoord * 2.5f;
			e.motionZ = direction.zCoord * 5;
			EntityLivingBase livingEntity = (EntityLivingBase)e;
			if (livingEntity != null && livingEntity != player) {
				livingEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), 1.0f);
			}
		}
	}
	
	@Override
	public List<SpellSymbol> getIncantation() {
		return Arrays.asList(SpellSymbol.Black, SpellSymbol.Power, SpellSymbol.Black, SpellSymbol.Chaos, SpellSymbol.Black);
	}

	@Override
	public SpellName getName() {
		return SpellName.Gust;
	}
}
