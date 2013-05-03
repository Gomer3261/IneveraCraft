package com.ggollmer.inevera.item;

import com.ggollmer.inevera.Inevera;
import com.ggollmer.inevera.lib.ItemNames;

/**
 * IneveraCraft
 *
 * ItemDemonFlesh
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ItemDemonFlesh extends ItemInevera
{
	public ItemDemonFlesh(int id)
	{
		super(id);
		this.setUnlocalizedName(ItemNames.DEMON_FLESH_NAME);
		this.setCreativeTab(Inevera.tabsInevera);
		this.setMaxStackSize(64);
	}

}
