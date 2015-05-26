package purebe.battlemage.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class WoodenWand extends Item {
	public WoodenWand(String unlocalizedName) {
		super();
		
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}
}