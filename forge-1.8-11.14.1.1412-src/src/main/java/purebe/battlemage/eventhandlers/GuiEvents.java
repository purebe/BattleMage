package purebe.battlemage.eventhandlers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.lwjgl.opengl.GL11;

import purebe.battlemage.magic.SpellCaster.SpellSymbol;
import purebe.battlemage.magic.Spells.Spell;
import purebe.battlemage.main.ClientProxy;
import purebe.battlemage.main.Main;

public class GuiEvents {
	@SubscribeEvent
	public void RenderGameOverlayEvent(RenderGameOverlayEvent event) {
		if(event.isCancelable() || event.type != ElementType.EXPERIENCE)
	    {      
	      return;
	    }
		
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = Tessellator.getInstance().getWorldRenderer();
		
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		if (((ClientProxy)Main.proxy).spellCaster.cooldown) {
			double height = 48;
			double width = 112;
			double widthPadding = (width / 2);
			double heightPadding = 70 + (height/2);
			double posX = (event.resolution.getScaledWidth() / 2) - widthPadding;
			double posY = (event.resolution.getScaledHeight() / 2) - heightPadding;
			
			DrawAura(((ClientProxy)Main.proxy).spellCaster.tickCount, posX, posY, width, height,
			         tessellator, worldrenderer, ((ClientProxy)Main.proxy).spellCaster.coolDownTicks,
			         ((ClientProxy)Main.proxy).spellCaster.currentSpell);
		}
		
		int size = ((ClientProxy)Main.proxy).spellCaster.currentIncantation.size();
		for (int i = 0; i < size; ++i) {
			SpellSymbol symbol = ((ClientProxy)Main.proxy).spellCaster.currentIncantation.get(i);
			
			double height = 16;
			double width = 16;
			double widthPadding = 16 * ((size / 2.0f) - i);
			double heightPadding = 80;
			double posX = (event.resolution.getScaledWidth() / 2) - widthPadding;
			double posY = (event.resolution.getScaledHeight() / 2) - heightPadding;
			
			DrawSymbol(symbol, ((ClientProxy)Main.proxy).spellCaster.symbolTimeAlive.get(i), posX, posY, width, height, 
			           tessellator, worldrenderer, ((ClientProxy)Main.proxy).spellCaster.coolDownTicks,
			           ((ClientProxy)Main.proxy).spellCaster.cooldown);
		}
		
		GlStateManager.disableBlend();
	}
	
	private void DrawSymbol(SpellSymbol symbol, int ticksAlive, double posX, double posY, double width, double height,
	                        Tessellator tessellator, WorldRenderer worldRenderer, int maxTicks, boolean onCooldown) {
		switch (symbol) {
		case White:
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("battlemage:textures/gui/white_symbol.png"));
			break;
		case Black:
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("battlemage:textures/gui/black_symbol.png"));
			break;
		case Chaos:
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("battlemage:textures/gui/chaos_symbol.png"));
			break;
		case Nature:
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("battlemage:textures/gui/nature_symbol.png"));
			break;
		case Power:
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("battlemage:textures/gui/power_symbol.png"));
			break;
		}
		float alpha = ticksAlive / 10f;
		if (alpha >= 1.0f) {
			alpha = 1.0f;
		}
		
		GlStateManager.color(1f, 1f, 1f, alpha);
		
		worldRenderer.startDrawingQuads();
		worldRenderer.addVertexWithUV(posX, posY + height, 0, 0, 1);
		worldRenderer.addVertexWithUV(posX + width, posY + height, 0, 1, 1);
		worldRenderer.addVertexWithUV(posX + width, posY, 0, 1, 0);
		worldRenderer.addVertexWithUV(posX, posY, 0, 0, 0);
		tessellator.draw();
	}
	
	private void DrawAura(int ticksAlive, double posX, double posY, double width, double height,
	                      Tessellator tessellator, WorldRenderer worldRenderer, int maxTicks, Spell spell) {
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("battlemage:textures/gui/spell_cast_aura.png"));
		
		float alpha = ticksAlive / 10f;
		if (alpha >= 1.0f) {
			alpha = 1.0f;
		}
		
		switch (spell) {
		case Illuminate:
			GlStateManager.color(0f, 0.4f, 0.8f, alpha);
			break;
		case ArtilleryCattle:
			GlStateManager.color(0.39f, 0.03f, 0.64f, alpha);
			break;
		case Cancel:
			GlStateManager.color(1f, 1f, 1f, alpha);
			break;
		case None:
			GlStateManager.color(1f, 0f, 0f, alpha);
			break;
		case Teleport:
			GlStateManager.color(0.81f, 0.56f, 0.94f, alpha);
			break;
		default:
			break;
		}
		
		worldRenderer.startDrawingQuads();
		worldRenderer.addVertexWithUV(posX, posY + height, 0, 0, 1);
		worldRenderer.addVertexWithUV(posX + width, posY + height, 0, 1, 1);
		worldRenderer.addVertexWithUV(posX + width, posY, 0, 1, 0);
		worldRenderer.addVertexWithUV(posX, posY, 0, 0, 0);
		tessellator.draw();
	}
}