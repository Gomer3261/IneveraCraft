package com.ggollmer.inevera.client.particle;

import com.ggollmer.inevera.block.BlockGreatwardComponent;
import com.ggollmer.inevera.lib.BlockIds;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
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
	 * Used to create a basic greatward particle with a set position and motion.
	 * @param par1World The world to create the particle in.
	 * @param x The x position of the particle.
	 * @param y The y position of the particle.
	 * @param z The z position of the particle.
	 * @param mx The x motion of the particle.
	 * @param my The y motion of the particle.
	 * @param mz The z motion of the particle.
	 */
	public GreatwardFX(World world, int life, double x, double y, double z, double mx, double my, double mz)
	{
		super(world, x, y, z, mx, my, mz);
		this.setParticleIcon(Minecraft.getMinecraft().renderEngine, ( (BlockGreatwardComponent)Block.blocksList[BlockIds.GREATWARD_WOOD_PIECE] ).getEffectIcon(0, 0));
		this.particleMaxAge = life;
		this.motionX = mx;
		this.motionY = my;
		this.motionZ = mz;
	}
	
	public GreatwardFX(World world, int life, double x, double y, double z)
	{
		this(world, life, x, y, z, 0d, 0d, 0d);
	}
	
	@Override
	public int getFXLayer()
    {
        return 1;
    }
}
