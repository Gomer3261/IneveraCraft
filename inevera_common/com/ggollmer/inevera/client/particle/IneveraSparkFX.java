/**
 * 
 */
package com.ggollmer.inevera.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

/**
 * IneveraCraft
 *
 * IneveraSparkFX.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class IneveraSparkFX extends EntityFX
{
	double mx;
	double my;
	double mz;

	/**
	 * @param par1World
	 * @param par2
	 * @param par4
	 * @param par6
	 * @param par8
	 * @param par10
	 * @param par12
	 */
	public IneveraSparkFX(World world, int life, double px, double py, double pz, double mx, double my, double mz)
	{
		super(world, px, py, pz, mx, my, mz);
		this.mx = mx;
		this.my = my;
		this.mz = mz;
		this.particleMaxAge = life;
	}
	
	public IneveraSparkFX(World world, int life, double px, double py, double pz)
	{
		this(world, life, px, py, pz, 0.0D, 0.0D, 0.0D);
	}
	
	@Override
	public void onUpdate()
	{
		this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }
        
        if (this.particleAge > this.particleMaxAge / 2)
        {
            this.setAlphaF(1.0F - ((float)this.particleAge - (float)(this.particleMaxAge / 2)) / (float)this.particleMaxAge);
            this.particleScale /= 1.1;
        }
        
        this.moveEntity(this.mx, this.my, this.mz);
	}
}
