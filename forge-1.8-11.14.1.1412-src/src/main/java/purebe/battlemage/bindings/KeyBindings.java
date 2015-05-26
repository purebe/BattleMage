package purebe.battlemage.bindings;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import org.lwjgl.input.Keyboard;

public class KeyBindings {
    public static KeyBinding white, black, chaos, nature, power, clear;

    public static void init() {
    	white = new KeyBinding("key.white", Keyboard.KEY_F, "key.categories.battlemage");
    	black = new KeyBinding("key.black", Keyboard.KEY_B, "key.categories.battlemage");
    	chaos = new KeyBinding("key.chaos", Keyboard.KEY_C, "key.categories.battlemage");
    	nature = new KeyBinding("key.nature", Keyboard.KEY_V, "key.categories.battlemage");
    	power = new KeyBinding("key.power", Keyboard.KEY_G, "key.categories.battlemage");
    	clear = new KeyBinding("key.clear", Keyboard.KEY_X, "key.categories.battlemage");
        
        ClientRegistry.registerKeyBinding(white);
        ClientRegistry.registerKeyBinding(black);
        ClientRegistry.registerKeyBinding(chaos);
        ClientRegistry.registerKeyBinding(nature);
        ClientRegistry.registerKeyBinding(power);
        ClientRegistry.registerKeyBinding(clear);
    }
}