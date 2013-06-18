package com.ggollmer.inevera.client.effect;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * IneveraCraft
 *
 * IneveraEffect.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
@SideOnly(Side.CLIENT)
public abstract class IneveraEffect extends Entity
{
	EffectRenderer renderer;
	double posX;
	double posY;
	double posZ;
	int ageMax;
	int age;
	
	public IneveraEffect(World world, EffectRenderer effectRenderer, double px, double py, double pz, String args)
	{
		super(world);
		this.renderer = effectRenderer;
		this.posX = px;
		this.posY = py;
		this.posZ = pz;
		this.age = 0;
		this.ageMax = 120;
		this.processArguments(args);
	}
	
	@Override
	protected void entityInit() {}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (this.age++ >= this.ageMax)
		{
			this.setDead();
		}
	}
	
	protected abstract void processArguments(String args);
	
	@Override
	protected boolean canTriggerWalking()
    {
        return false;
    }
	
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}
	
	@Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}
	
	@Override
	public boolean canAttackWithItem()
    {
        return false;
    }
}
