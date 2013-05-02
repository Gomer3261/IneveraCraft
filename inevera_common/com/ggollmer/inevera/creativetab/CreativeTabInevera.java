package com.ggollmer.inevera.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Inevera Craft
 * 
 * CreativeTabInevera
 * 
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class CreativeTabInevera extends CreativeTabs
{

	/**
	 * Used to construct the IneveraCraft creative tab.
	 * 
	 * @param par1
	 * @param modid
	 */
	public CreativeTabInevera(int par1, String modid)
	{
		super(par1, modid);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex()
	{
        return 2; //ItemIds.HORA;
    }
}
