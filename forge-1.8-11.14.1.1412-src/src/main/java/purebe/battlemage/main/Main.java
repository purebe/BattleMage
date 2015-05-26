package purebe.battlemage.main;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import purebe.battlemage.network.CastSpellMsg;
import purebe.battlemage.network.CastSpellMsg.CastSpellMsgHandler;

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.VERSION)
public class Main {
    public static final String MODID = "BattleMage";
    public static final String MODNAME = "Battle Mage";
    public static final String VERSION = "0.0.1";
    
    public static SimpleNetworkWrapper network;
    public static int networkDiscriminatorId = 0;
    public static int uniqueEntityId = 0;
        
    @Instance
    public static Main instance = new Main();
    
    @SidedProxy(clientSide="purebe.battlemage.main.ClientProxy", serverSide="purebe.battlemage.main.ServerProxy")
	public static CommonProxy proxy;
     
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    	this.proxy.preInit(e);
    	network = NetworkRegistry.INSTANCE.newSimpleChannel("battlemage");
		network.registerMessage(CastSpellMsgHandler.class, CastSpellMsg.class, networkDiscriminatorId++, Side.SERVER);
    }
        
    @EventHandler
    public void init(FMLInitializationEvent e) {
    	this.proxy.init(e);
    }
        
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    	this.proxy.postInit(e);
    }
}