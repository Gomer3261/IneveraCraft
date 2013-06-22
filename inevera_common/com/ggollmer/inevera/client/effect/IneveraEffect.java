package com.ggollmer.inevera.client.effect;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
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
public abstract class IneveraEffect extends EntityFX
{
	EffectRenderer renderer;
	double posX;
	double posY;
	double posZ;
	int ageMax;
	int age;
	
	public IneveraEffect(World world, EffectRenderer effectRenderer, double px, double py, double pz, String args)
	{
		super(world, px, py, pz);
		this.renderer = effectRenderer;
		this.posX = px;
		this.posY = py;
		this.posZ = pz;
		this.age = 0;
		this.ageMax = 120;
	}
	
	@Override
	public void onUpdate()
	{
		if (age >= ageMax)
		{
			setDead();
		}
		age++;
	}
	
	@Override
	public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {}
}
