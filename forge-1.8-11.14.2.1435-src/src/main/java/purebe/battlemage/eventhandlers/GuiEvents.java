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

import purebe.battlemage.magic.Spell;
import purebe.battlemage.magic.SpellCaster.SpellName;
import purebe.battlemage.magic.SpellCaster.SpellSymbol;
import purebe.battlemage.battlemage.ClientProxy;
import purebe.battlemage.battlemage.BattleMage;

public class GuiEvents {
	private int cooldownIcons = 0;
	private int cooldownsDrawn = 0;
	//private final float[] squareCoordsX = { 1f, 1f, 1f, 1f, 0.5f, 0f, -0.5f, -1f, -1f, -1f, -1f, -1f, -0.5f, 0f, 0.5f, 1f, 1f, 1f };
	//private final float[] squareCoordsY = { 0.5f, 0f, -0.5f, -1f, -1f, -1f, -1f, -1f, -0.5f, 0f, 0.5f, 1f, 1f, 1f, 1f, 1f, 0.5f, 0f };
	private final float[] squareCoordsX = { 0f, -0.5f, -1f, -1f, -1f, -1f, -1f, -0.5f, 0f, 0.5f, 1f, 1f, 1f, 1f, 1f, 0.5f, 0f };
	private final float[] squareCoordsY = { -1f, -1f, -1f, -0.5f, 0f, 0.5f, 1f, 1f, 1f, 1f, 1f, 0.5f, 0f, -0.5f, -1f, -1f, -1f };

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
		
		cooldownIcons = 0;
		int ticks = 0;
		for (Spell spell : ((ClientProxy) BattleMage.proxy).spellCaster.spellRegistry.values()) {
			ticks = spell.getCoolDownTick();
			if (ticks > 0) {
				++cooldownIcons;
				
				DrawIcon(ticks, spell.getCoolDownDuration(), spell.getIcon(),
				         event.resolution.getScaledWidth(), event.resolution.getScaledHeight(),
				         tessellator, worldrenderer);
			}
		}
		
		if (((ClientProxy) BattleMage.proxy).spellCaster.cooldown) {
			double height = 48;
			double width = 112;
			double widthPadding = (width / 2);
			double heightPadding = 70 + (height/2);
			double posX = (event.resolution.getScaledWidth() / 2) - widthPadding;
			double posY = (event.resolution.getScaledHeight() / 2) - heightPadding;
			
			DrawAura(((ClientProxy) BattleMage.proxy).spellCaster.tickCount, posX, posY, width, height,
			         tessellator, worldrenderer, ((ClientProxy) BattleMage.proxy).spellCaster.coolDownTicks,
			         ((ClientProxy) BattleMage.proxy).spellCaster.currentIncantationSpell);
		}
		
		int size = ((ClientProxy) BattleMage.proxy).spellCaster.currentIncantation.size();
		for (int i = 0; i < size; ++i) {
			if (i >= ((ClientProxy) BattleMage.proxy).spellCaster.currentIncantation.size()) {
				break;
			}
			SpellSymbol symbol = ((ClientProxy) BattleMage.proxy).spellCaster.currentIncantation.get(i);
			
			double height = 16;
			double width = 16;
			double widthPadding = 16 * ((size / 2.0f) - i);
			double heightPadding = 80;
			double posX = (event.resolution.getScaledWidth() / 2) - widthPadding;
			double posY = (event.resolution.getScaledHeight() / 2) - heightPadding;
			
			DrawSymbol(symbol, ((ClientProxy) BattleMage.proxy).spellCaster.symbolTimeAlive.get(i), posX, posY, width, height,
			           tessellator, worldrenderer, ((ClientProxy) BattleMage.proxy).spellCaster.coolDownTicks,
			           ((ClientProxy) BattleMage.proxy).spellCaster.cooldown);
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
	                      Tessellator tessellator, WorldRenderer worldRenderer, int maxTicks, SpellName spell) {
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
		case MagicalWall:
			GlStateManager.color(0.54f, 0.95f, 0.76f, alpha);
			break;
		case Gust:
			GlStateManager.color(0.09f, 0.035f, 0.125f, alpha);
			break;
		case Fly:
			GlStateManager.color(0.255f, 0.012f, 0.475f, alpha);
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
	
	private void DrawIcon(int ticksLeft, int ticksStart, ResourceLocation icon, int screenWidth, int screenHeight,
		                  Tessellator tessellator, WorldRenderer worldRenderer) {
		float centerX = screenWidth / 2.0f + screenWidth / 4.0f - 8;
		float centerY = (screenHeight / 2.0f - 8) - cooldownIcons * 20 - (cooldownsDrawn * 20);
		int width = 16;
		int height = 16;
		
		GlStateManager.color(1f, 1f, 1f, 0.9f);
		Minecraft.getMinecraft().renderEngine.bindTexture(icon);
		
		worldRenderer.startDrawingQuads();
		worldRenderer.addVertexWithUV(centerX, centerY + height, 0, 0, 1);
		worldRenderer.addVertexWithUV(centerX + width, centerY + height, 0, 1, 1);
		worldRenderer.addVertexWithUV(centerX + width, centerY, 0, 1, 0);
		worldRenderer.addVertexWithUV(centerX, centerY, 0, 0, 0);
		tessellator.draw();
		
		GlStateManager.color(0f, 0f, 0f, 0.5f);
		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		//for (float i = (float)(Math.PI * 2) * (1 - (float)ticksLeft / ticksStart); i > 0; i -= Math.PI / 4) {
		//	GL11.glVertex2f(centerX + (width / 2.0f), centerY + (height / 2.0f));
		//	GL11.glVertex2f((float)((width / 2.0f) + centerX + ((width / 2.0f) * Math.cos(i))), (float)((height / 2.0f) + centerY + (height / 2.0f) * Math.sin(i)));
		//}
		
		float vertexCenterX = centerX + (width / 2.0f);
		float vertexCenterY = centerY + (height / 2.0f);
		int skipper = Math.round((squareCoordsX.length - 1) * ((1 - (float)ticksLeft / ticksStart)));
		GL11.glVertex2f(vertexCenterX, vertexCenterY);
		GL11.glVertex2f(vertexCenterX + ((width / 2.0f) * squareCoordsX[0]), vertexCenterY + ((height / 2.0f) * squareCoordsY[0]));
		for (int i = 1; i < squareCoordsX.length - skipper; ++i) {
			GL11.glVertex2f(vertexCenterX + ((width / 2.0f) * squareCoordsX[i]), vertexCenterY + ((height / 2.0f) * squareCoordsY[i]));
		}
		GL11.glEnd();
	}
}
