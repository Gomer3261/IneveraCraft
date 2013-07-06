package com.ggollmer.inevera.client.effect;

import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.world.World;

import com.ggollmer.inevera.client.particle.GreatwardFX;
import com.ggollmer.inevera.lib.EffectConstants;

public class IneveraEffectDirectionalBurst extends IneveraEffect
{
	double vx=0;
	double vy=0;
	double vz=0;
	double oAngle=0;
	
	public IneveraEffectDirectionalBurst(World world, EffectRenderer effectRenderer, double px, double py, double pz, String args)
	{
		super(world, effectRenderer, px, py, pz, args);
		ageMax = 1;
		age = 0;
		
		String[] indvargs = args.split(EffectConstants.EFFECT_ARG_SEPARATOR);
		
		vx=Double.valueOf(indvargs[0]);
		vy=Double.valueOf(indvargs[1]);
		vz=Double.valueOf(indvargs[2]);
		oAngle=Double.valueOf(indvargs[3]);
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		int maxParticles =  ( (Math.abs(rand.nextInt()%3) + 4 ));
		for(int i=0; i<maxParticles; i++)
		{
			//TODO; Works when entering from Z, not from X
			
			double angleY = (vz < 0) ? Math.PI - Math.asin(vy) : Math.asin(vy); 
			double angleX = (vz < 0) ? Math.acos(vx)*-1 : Math.acos(vx);
			
			angleY += oAngle*rand.nextDouble()*( (rand.nextBoolean()) ? -1 : 1 );
			angleX += oAngle*rand.nextDouble()*( (rand.nextBoolean()) ? -1 : 1 );
			
			double velocity = Math.sqrt((vx*vx) + (vy*vy) + (vz*vz))*( 1 + (rand.nextDouble()/3 * ( (rand.nextBoolean()) ? -1 : 1 )) );
			double horizVelocity = Math.cos(angleY)*velocity;
			
			int life = (Math.abs(rand.nextInt()%6)+4);
			
			double mx = Math.cos(angleX)*horizVelocity;
			double my = Math.sin(angleY)*velocity;
			double mz = Math.sin(angleX)*horizVelocity;

			renderer.addEffect(new GreatwardFX(worldObj, life, posX, posY, posZ, mx/life, my/life, mz/life));
		}
	}
}
