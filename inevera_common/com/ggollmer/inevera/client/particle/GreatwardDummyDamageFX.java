package com.ggollmer.inevera.client.particle;

import com.ggollmer.inevera.block.BlockGreatwardComponent;

import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class GreatwardDummyDamageFX extends EntityDiggingFX
{

	public GreatwardDummyDamageFX(World par1World, double par2, double par4,
			double par6, double par8, double par10, double par12,
			BlockGreatwardComponent block, int par15, int par16,
			RenderEngine par17RenderEngine)
	{
		super(par1World, par2, par4, par6, par8, par10, par12, block, par15,
				par16, par17RenderEngine);
		
		this.setParticleIcon(par17RenderEngine, block.getEffectIcon(par15, par16));
		this.particleGravity = 0.0F;
		this.particleTextureJitterX = 0;
		this.particleTextureJitterY = 0;
	}
	
	public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        float f6 = (float)this.particleTextureIndexX / 16.0F;
        float f7 = f6 + 0.015609375F;
        float f8 = (float)this.particleTextureIndexY / 16.0F;
        float f9 = f8 + 0.015609375F;
        float f10 = 0.1F * this.particleScale;

        if (this.particleTextureIndex != null)
        {
            f6 = this.particleTextureIndex.getInterpolatedU((double)(0F));
            f7 = this.particleTextureIndex.getInterpolatedU((double)(16.0F));
            f8 = this.particleTextureIndex.getInterpolatedV((double)(0F));
            f9 = this.particleTextureIndex.getInterpolatedV((double)(16.0F));
        }

        float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)par2 - interpPosX);
        float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)par2 - interpPosY);
        float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)par2 - interpPosZ);
        float f14 = 1.0F;
        par1Tessellator.setColorOpaque_F(f14 * this.particleRed, f14 * this.particleGreen, f14 * this.particleBlue);
        par1Tessellator.addVertexWithUV((double)(f11 - par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 - par5 * f10 - par7 * f10), (double)f6, (double)f9);
        par1Tessellator.addVertexWithUV((double)(f11 - par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 - par5 * f10 + par7 * f10), (double)f6, (double)f8);
        par1Tessellator.addVertexWithUV((double)(f11 + par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 + par5 * f10 + par7 * f10), (double)f7, (double)f8);
        par1Tessellator.addVertexWithUV((double)(f11 + par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 + par5 * f10 - par7 * f10), (double)f7, (double)f9);
    }
}
