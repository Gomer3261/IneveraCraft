package com.ggollmer.inevera.tileentity;

import net.minecraftforge.common.ForgeDirection;

/**
 * IneveraCraft
 *
 * ITileGreatwardCore.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public abstract class TileGreatwardCore extends TileInevera
{
	public abstract void invalidateGreatward();

	public abstract ForgeDirection getWardDirection();
}
