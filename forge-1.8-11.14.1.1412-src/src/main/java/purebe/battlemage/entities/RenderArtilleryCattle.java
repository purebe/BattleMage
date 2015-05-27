package purebe.battlemage.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderArtilleryCattle extends RenderLiving {
    protected ResourceLocation artilleryCattleTexture;

    public RenderArtilleryCattle(ModelBase par1ModelBase, float parShadowSize)
    {
        super(Minecraft.getMinecraft().getRenderManager(), par1ModelBase, parShadowSize);
        setEntityTexture();        
    }
    
    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
    	switch (entity.ticksExisted) {
    	case 35:
    		GL11.glScalef(1.15f, 1.15f, 1.15f);
    		break;
    	case 36:
    		GL11.glScalef(1.25f, 1.25f, 1.25f);
    		break;
    	case 37:
    		GL11.glScalef(1.15f, 1.15f, 1.15f);
    		break;
    	case 38:
    		//GL11.glScalef(1f, 1f, 1f);
    		break;
    	case 39:
    		GL11.glScalef(1.15f, 1.15f, 1.15f);
    		break;
    	case 40:
    		GL11.glScalef(1.25f, 1.25f, 1.25f);
    		break;
    	case 41:
    		GL11.glScalef(1.15f, 1.15f, 1.15f);
    		break;
    	case 42:
    		//GL11.glScalef(1f, 1f, 1f);
    		break;
    	case 43:
    		GL11.glScalef(1.15f, 1.15f, 1.15f);
    		break;
    	case 44:
    		GL11.glScalef(1.25f, 1.25f, 1.25f);
    		break;
    	case 45:
    		GL11.glScalef(1.15f, 1.15f, 1.15f);
    		break;
    	case 46:
    		//GL11.glScalef(1f, 1f, 1f);
    		break;
    	case 47:
    		GL11.glScalef(1.15f, 1.15f, 1.15f);
    		break;
    	case 48:
    		GL11.glScalef(1.25f, 1.25f, 1.25f);
    		break;
    	case 49:
    		GL11.glScalef(1.15f, 1.15f, 1.15f);
    		break;
    	case 50:
    		//GL11.glScalef(1f, 1f, 1f);
    		break;
    	case 51:
    		GL11.glScalef(1.15f, 1.15f, 1.15f);
    		break;
    	case 52:
    		GL11.glScalef(1.25f, 1.25f, 1.25f);
    		break;
    	case 53:
    		GL11.glScalef(1.35f, 1.35f, 1.35f);
    		break;
    	case 54:
    		GL11.glScalef(1.25f, 1.25f, 1.25f);
    		break;
    	case 55:
    		GL11.glScalef(1.15f, 1.15f, 1.15f);
    		break;
    	case 56:
    		GL11.glScalef(1.25f, 1.25f, 1.25f);
    		break;
    	case 57:
    		GL11.glScalef(1.35f, 1.35f, 1.35f);
    		break;
    	case 58:
    		GL11.glScalef(1.25f, 1.25f, 1.25f);
    		break;
    	case 59:
    		GL11.glScalef(1.25f, 1.25f, 1.25f);
    		break;
    	case 60:
    		GL11.glScalef(1.35f, 1.35f, 1.35f);
    		break;
    	case 61:
    	case 62:
    	case 63:
    	case 64:
    	case 65:
    	case 66:
    	case 67:
    	case 68:
    	case 69:
    	case 70:
    		GL11.glScalef(1.45f, 1.45f, 1.45f);
    		break;
    	}
    }

    protected void setEntityTexture()
    {
    	artilleryCattleTexture = new ResourceLocation("textures/entity/cow/cow.png");
    }

    /**
    * Returns the location of an entity's texture. Doesn't seem to be called 
    * unless you call Render.bindEntityTexture.
    */
    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return artilleryCattleTexture;
    }
}