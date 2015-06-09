package purebe.battlemage.battlemage;

import net.minecraft.client.model.ModelCow;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import purebe.battlemage.bindings.KeyBindings;
import purebe.battlemage.bindings.KeyInputHandler;
import purebe.battlemage.blocks.clientrender.BlockRenderRegister;
import purebe.battlemage.entities.ArtilleryCattleEntity;
import purebe.battlemage.entities.RenderArtilleryCattle;
import purebe.battlemage.eventhandlers.ClientEvents;
import purebe.battlemage.eventhandlers.GuiEvents;
import purebe.battlemage.items.clientrender.ItemRenderRegister;
import purebe.battlemage.magic.SpellCaster;
import purebe.battlemage.magic.SpellCaster.SpellSymbol;

public class ClientProxy extends CommonProxy {
	public SpellCaster spellCaster = new SpellCaster();
	
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		FMLCommonHandler.instance().bus().register(new KeyInputHandler());
		FMLCommonHandler.instance().bus().register(new ClientEvents());
		MinecraftForge.EVENT_BUS.register(new GuiEvents());
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		KeyBindings.init();
		ItemRenderRegister.registerItemRenderer();
		BlockRenderRegister.registerBlockRenderer();
		
		RenderingRegistry.registerEntityRenderingHandler(ArtilleryCattleEntity.class, 
                new RenderArtilleryCattle(new ModelCow(), 0.7F));
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}
	
	public void castSymbol(SpellSymbol symbol) {
		spellCaster.addSymbol(symbol);
	}
	
	public void clearSymbols() {
		spellCaster.clearSymbols();
	}
}
