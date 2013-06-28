package com.ggollmer.inevera.client.particle;

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
public class GreatwardAccelerationFX extends GreatwardFX
{
	protected double mx;
	protected double my;
	protected double mz;
	protected double accelX;
	protected double accelY;
	protected double accelZ;
	
	/**
	 * Used to create a basic greatward particle with a set position and motion.
	 * @param par1World The world to create the particle in.
	 * @param x The x position of the particle.
	 * @param y The y position of the particle.
	 * @param z The z position of the particle.
	 * @param mx The x motion of the particle.
	 * @param my The y motion of the particle.
	 * @param mz The z motion of the particle.
	 * @param ax The x acceleration of the particle.
	 * @param ay The y acceleration of the particle.
	 * @param az The z acceleration of the particle.
	 */
	public GreatwardAccelerationFX(World world, int life, double x, double y, double z, double mx, double my, double mz, double ax, double ay, double az)
	{
		super(world, life, x, y, z, mx, my, mz);
		this.mx = mx;
		this.my = my;
		this.mz = mz;
		accelX = ax;
		accelY = ay;
		accelZ = az;
		this.noClip = true;
	}
	
	public void onUpdate()
	{
		this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }
        
        this.mx += accelX;
        this.my += accelY;
        this.mz += accelZ;
        this.moveEntity(this.mx, this.my, this.mz);
	}
}
