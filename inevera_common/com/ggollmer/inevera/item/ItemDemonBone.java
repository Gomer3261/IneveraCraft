/**
 * 
 */
package com.ggollmer.inevera.item;

import com.ggollmer.inevera.Inevera;
import com.ggollmer.inevera.lib.ItemNames;

/**
 * IneveraCraft
 *
 * ItemDemonBone.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ItemDemonBone extends DemonItemInevera
{
	public ItemDemonBone(int id)
	{
		super(id);
		this.setUnlocalizedName(ItemNames.DEMON_BONE_NAME);
		this.setCreativeTab(Inevera.tabsInevera);
		this.setMaxStackSize(64);
	}

}
