package com.ggollmer.inevera.greatward.attribute;

import net.minecraft.world.World;

/**
 * IneveraCraft
 *
 * IGWHealableBlock.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public interface IGWHealableBlock
{
	public void onGreatwardHeal(World world, int x, int y, int z);
}
