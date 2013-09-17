package com.ggollmer.inevera.item;


import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import com.ggollmer.inevera.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * IneveraCraft
 *
 * ItemInevera
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ItemInevera extends Item
{

	public ItemInevera(int id)
	{
		super(id - Reference.SHIFTED_ID_RANGE_CORRECTION);
		maxStackSize = 1;
		setNoRepair();
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
	{
        itemIcon = iconRegister.registerIcon(new ResourceLocation(Reference.MOD_ID.toLowerCase(), getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1)).toString());
    }
}
