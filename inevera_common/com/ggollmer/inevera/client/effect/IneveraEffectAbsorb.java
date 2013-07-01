package com.ggollmer.inevera.client.effect;

import com.ggollmer.inevera.client.particle.GreatwardFX;

import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.world.World;

/**
 * IneveraCraft
 *
 * IneveraHealEffect.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class IneveraEffectAbsorb extends IneveraEffect
{
	float effectMultiplier=0;
	
	public IneveraEffectAbsorb(World world, EffectRenderer effectRenderer, double px, double py, double pz, String args)
	{
		super(world, effectRenderer, px, py, pz, args);
		ageMax = 1;
		age = 0;
		effectMultiplier = Float.parseFloat(args);
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		if(effectMultiplier==0) return;
		
		int maxParticles =  ( (Math.abs(rand.nextInt()) + 9 ) %14 );
		for(int i=0; i<maxParticles; i++)
		{
			double angle1 = rand.nextDouble()*Math.PI*2;
			double angle2 = rand.nextDouble()*Math.PI*2;
			
			int life = (Math.abs(rand.nextInt())+8)%12;
			
			double speed = (rand.nextDouble()+.48f)/5f;
			
			double my = speed*Math.sin(angle1);
			double remainder = speed*Math.cos(angle1);
			double mx = remainder*Math.cos(angle2);
			double mz = remainder*Math.sin(angle2);
			
			double offx = (effectMultiplier < 0) ? 0 : mx*life*-1;
			double offy = (effectMultiplier < 0) ? 0 : my*life*-1;
			double offz = (effectMultiplier < 0) ? 0 : mz*life*-1;
			
			double px = posX + offx;
			double py = posY + offy;
			double pz = posZ + offz;

			renderer.addEffect(new GreatwardFX(worldObj, life, px, py, pz, mx, my, mz));
		}
	}
}
