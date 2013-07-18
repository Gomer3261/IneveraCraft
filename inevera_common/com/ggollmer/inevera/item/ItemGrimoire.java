package com.ggollmer.inevera.item;

import com.ggollmer.inevera.Inevera;
import com.ggollmer.inevera.lib.ItemNames;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * IneveraCraft
 *
 * ItemGrimoire.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ItemGrimoire extends ItemInevera
{
	public ItemGrimoire(int itemId)
    {
        super(itemId);
        this.setMaxStackSize(1);
        this.setUnlocalizedName(ItemNames.GRIMOIRE_NAME);
        this.setCreativeTab(Inevera.tabsInevera);
    }
	
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
        player.displayGUIBook(item);
        return item;
    }
	
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }
}
