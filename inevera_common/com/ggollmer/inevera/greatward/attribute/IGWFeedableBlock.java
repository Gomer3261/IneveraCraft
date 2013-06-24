package com.ggollmer.inevera.greatward.attribute;

import net.minecraft.world.World;

/**
 * IneveraCraft
 *
 * IGWFeedableBlock.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public interface IGWFeedableBlock
{

	/**
	 * Feeds a block from a greatward.
	 * @param world The world the block exists in.
	 * @param posX The x position of the block.
	 * @param posY The y position of the block.
	 * @param posZ The z position of the block.
	 * @param amount The amount that the block is fed from the greatward.
	 */
	public void onGreatwardFeed(World world, int posX, int posY, int posZ, int amount);
}
