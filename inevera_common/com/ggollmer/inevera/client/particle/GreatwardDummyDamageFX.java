package com.ggollmer.inevera.client.particle;

import com.ggollmer.inevera.block.BlockGreatwardPiece;

import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.world.World;

public class GreatwardDummyDamageFX extends EntityDiggingFX
{

	public GreatwardDummyDamageFX(World par1World, double par2, double par4,
			double par6, double par8, double par10, double par12,
			BlockGreatwardPiece block, int par15, int par16,
			RenderEngine par17RenderEngine)
	{
		super(par1World, par2, par4, par6, par8, par10, par12, block, par15,
				par16, par17RenderEngine);
		
		this.setParticleIcon(par17RenderEngine, block.getEffectIcon(par15, par16));
	}

}
