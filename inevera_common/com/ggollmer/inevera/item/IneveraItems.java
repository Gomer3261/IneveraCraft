package com.ggollmer.inevera.item;

import com.ggollmer.inevera.lib.ItemIds;

import net.minecraft.item.Item;

/**
 * IneveraCraft
 *
 * IneveraItems
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class IneveraItems
{
	/* Inevera Item Instances */
	public static Item demonFlesh;
	
	public static void init()
	{
		/* Item Instantiation */
		demonFlesh = new ItemDemonFlesh(ItemIds.DEMON_FLESH_DEFAULT);
		
		/* Recipes */
		// I want to split these into another class hopefully. Like IneveraRecipes.
	}
}
