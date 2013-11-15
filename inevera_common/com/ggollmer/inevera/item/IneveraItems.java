package com.ggollmer.inevera.item;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import com.ggollmer.inevera.lib.ItemIds;
import com.ggollmer.inevera.lib.ItemNames;

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
	public static Item demonBone;
	public static Item grimoire;
	
	public static void init()
	{
		/* Item Instantiation */
		demonFlesh = new DemonItemInevera(ItemIds.DEMON_FLESH, ItemNames.DEMON_FLESH_NAME);
		demonBone = new ItemDemonBone(ItemIds.DEMON_BONE);
		grimoire = new ItemGrimoire(ItemIds.GRIMOIRE);
	}
	
	public static void registerThaumcraftAspects()
	{
		ThaumcraftApi.registerObjectTag(demonFlesh.itemID, new int[]{0}, (new AspectList()).add(Aspect.FLESH, 2).add(Aspect.HUNGER, 2).add(Aspect.MAGIC, 1));
		ThaumcraftApi.registerObjectTag(demonBone.itemID, new int[]{0}, (new AspectList()).add(Aspect.DEATH, 3).add(Aspect.HUNGER, 2).add(Aspect.MAGIC, 4));
	}
}
