package com.ggollmer.inevera.client.effect;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.world.World;

import com.ggollmer.inevera.client.particle.GreatwardFX;

public class IneveraEffectGroundEmanate extends IneveraEffect
{
	float effectMultiplier=0;
	
	public IneveraEffectGroundEmanate(World world, EffectRenderer effectRenderer, double px, double py, double pz, String args)
	{
		super(world, effectRenderer, px, py, pz, args);
		ageMax = 1;
		age = 0;
		
		effectMultiplier=Float.valueOf(args);
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		int particleSetting = Minecraft.getMinecraft().gameSettings.particleSetting;
		int maxParticles =  ( (Math.abs(rand.nextInt()) + 7 ) %11 - 4*particleSetting );
		for(int i=0; i<maxParticles; i++)
		{
			double angle = rand.nextDouble()*Math.PI*2;
			
			int life = (Math.abs(rand.nextInt())+8)%12;
			
			double offset = rand.nextDouble()*1.7f;
			
			double mx = 0.0D;
			double my = (0.1f + (rand.nextDouble()/8f)) * effectMultiplier;
			double mz = 0.0D;
			
			double offx = offset * Math.cos(angle);
			double offy = (my >= 0) ? 0 : life*my;
			double offz = offset * Math.sin(angle);
			
			double px = posX + offx;
			double py = posY + offy;
			double pz = posZ + offz;

			renderer.addEffect(new GreatwardFX(worldObj, life, px, py, pz, mx, my, mz));
		}
	}
}
