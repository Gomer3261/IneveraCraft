package com.ggollmer.inevera.item;

import com.ggollmer.inevera.Inevera;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * IneveraCraft
 *
 * ItemGreatwardPiece.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ItemIneveraMultiblock extends ItemBlock
{
	private final String[] subNames;
	
	public ItemIneveraMultiblock(int id, String name, String[] subNames)
	{
		super(id);
		setHasSubtypes(true);
		setUnlocalizedName(name);
		setCreativeTab(Inevera.tabsInevera);
		this.subNames = subNames;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		return super.getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
	}
	
	@Override
	public int getMetadata (int damageValue)
	{
		return damageValue;
	}
}