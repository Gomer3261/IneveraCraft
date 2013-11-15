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
	protected double mx;
	protected double my;
	protected double mz;
	protected double accelX;
	protected double accelY;
	protected double accelZ;
	
	protected boolean trail;
	
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
	 * @param trail Should the particle leave a trail?
	 */
	public GreatwardFX(World world, int life, double x, double y, double z, double mx, double my, double mz, double ax, double ay, double az, boolean trail)
	{
		super(world, x, y, z, mx, my, mz);
		this.setParticleIcon(( (BlockGreatwardComponent)Block.blocksList[BlockIds.GREATWARD_WOOD_PIECE] ).getEffectIcon(0, 0));
		this.particleMaxAge = life;
		this.mx = mx;
		this.my = my;
		this.mz = mz;
		accelX = ax;
		accelY = ay;
		accelZ = az;
		this.trail = trail;
		this.noClip = true;
	}
	
	public GreatwardFX(World world, int life, double x, double y, double z, double mx, double my, double mz, boolean trail)
	{
		this(world, life, x, y, z, mx, my, mz, 0.0D, 0.0D, 0.0D, trail);
	}
	
	public GreatwardFX(World world, int life, double x, double y, double z, double mx, double my, double mz, double ax, double ay, double az)
	{
		this(world, life, x, y, z, mx, my, mz, ax, ay, az, false);
	}
	
	public GreatwardFX(World world, int life, double x, double y, double z, double mx, double my, double mz)
	{
		this(world, life, x, y, z, mx, my, mz, 0.0D, 0.0D, 0.0D);
	}
	
	public GreatwardFX(World world, int life, double x, double y, double z)
	{
		this(world, life, x, y, z, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
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
        
        if(trail) 
        {
        	EntityFX fx = new IneveraSparkFX(worldObj, 15, prevPosX, prevPosY, prevPosZ);
        	fx.setRBGColorF(1.0f, 0.0f, 1.0f);
        	Minecraft.getMinecraft().effectRenderer.addEffect(fx);
        }
        
        this.mx += accelX;
        this.my += accelY;
        this.mz += accelZ;
        this.moveEntity(this.mx, this.my, this.mz);
	}
	
	@Override
	public int getFXLayer()
    {
        return 1;
    }
}
