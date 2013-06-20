package com.ggollmer.inevera.client.particle;

import com.ggollmer.inevera.block.BlockGreatwardComponent;
import com.ggollmer.inevera.lib.BlockIds;

import net.minecraft.block.Block;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

/**
 * IneveraCraft
 *
 * GreatwardFX.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GreatwardFX extends EntityFX
{

	/**
	 * @param par1World
	 * @param x
	 * @param y
	 * @param z
	 */
	public GreatwardFX(World world, double x, double y, double z, double mx, double my, double mz)
	{
		super(world, x, y, z, mx, my, mz);
		this.particleIcon = ( (BlockGreatwardComponent)Block.blocksList[BlockIds.GREATWARD_WOOD_PIECE] ).getEffectIcon(0, 0);
		this.particleMaxAge = 60;
	}
	
	public GreatwardFX(World world, double x, double y, double z)
	{
		this(world, x, y, z, 0d, 0d, 0d);
	}
}
