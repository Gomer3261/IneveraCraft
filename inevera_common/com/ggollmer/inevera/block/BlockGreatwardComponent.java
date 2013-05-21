package com.ggollmer.inevera.block;

import java.util.Random;

import com.ggollmer.inevera.client.particle.GreatwardDummyDamageFX;
import com.ggollmer.inevera.lib.BlockNames;
import com.ggollmer.inevera.lib.Reference;
import com.ggollmer.inevera.lib.RenderIds;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public abstract class BlockGreatwardComponent extends BlockInevera
{
	public static final int ACTIVE_BIT = 1<<3;
	
	protected Icon[] iconFXArray;
	protected Icon iconCoreActive;
	protected Icon iconCoreInactive;
	protected Random rand;
	
	protected BlockGreatwardComponent(int id, Material material)
	{
		super(id, material);
		this.rand = new Random();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.iconFXArray = new Icon[5];
	
	    for (int i = 0; i < this.iconFXArray.length; i++)
	    {        	
	        this.iconFXArray[i] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + BlockNames.GREATWARD_COMPONENT_FX_NAME + "_" + i);
	    }
	    
	    iconCoreActive = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + BlockNames.GREATWARD_COMPONENT_ACTIVE_NAME);
	    iconCoreInactive = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + BlockNames.GREATWARD_COMPONENT_INACTIVE_NAME);
	}
	
	public Icon getEffectIcon(int par15, int par16)
	{
		return iconFXArray[rand.nextInt(5)];
	}
	
	public Icon getCoreIcon(int metadata)
	{
		if((metadata & ACTIVE_BIT) != 0)
		{
			return iconCoreActive;
		}
		else
		{
			return iconCoreInactive;
		}
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public int getRenderType()
	{
		return RenderIds.greatwardCoreRenderer;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return true;
	}
	
	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}
	
	@Override
	public boolean canRenderInPass(int pass)
	{
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean addBlockHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
    {
		if(rand.nextFloat() > 0.78)
        {
	        float f = 0.1F;
	        double d0 = target.hitVec.xCoord + (this.rand.nextDouble() - 0.5D)*1.4D;
	        double d1 = target.hitVec.yCoord + (this.rand.nextDouble() - 0.5D)*1.4D;
	        double d2 = target.hitVec.zCoord + (this.rand.nextDouble() - 0.5D)*1.4D;
	
	        if (target.sideHit == 0)
	        {
	            d1 = target.hitVec.yCoord - (double)f;
	        }
	
	        if (target.sideHit == 1)
	        {
	            d1 = target.hitVec.yCoord + (double)f;
	        }
	
	        if (target.sideHit == 2)
	        {
	            d2 = target.hitVec.zCoord - (double)f;
	        }
	
	        if (target.sideHit == 3)
	        {
	            d2 = target.hitVec.zCoord + (double)f;
	        }
	
	        if (target.sideHit == 4)
	        {
	            d0 = target.hitVec.xCoord - (double)f;
	        }
	
	        if (target.sideHit == 5)
	        {
	            d0 = target.hitVec.xCoord + (double)f;
	        }

        	effectRenderer.addEffect((new GreatwardDummyDamageFX(worldObj, d0, d1, d2, 0.0D, 0.0D, 0.0D, this, target.sideHit, worldObj.getBlockMetadata(target.blockX, target.blockY, target.blockZ), null)).func_70596_a(target.blockX, target.blockY, target.blockZ).multiplyVelocity(0.1F).multipleParticleScaleBy(1.8F));
        }
        
        return false;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean addBlockDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {	
		byte b0 = 4;

        for (int j1 = 0; j1 < b0; ++j1)
        {
            for (int k1 = 0; k1 < b0; ++k1)
            {
                for (int l1 = 0; l1 < b0; ++l1)
                {
                	if(rand.nextFloat() > 0.86F)
                	{
	                    double d0 = (double)x + ((double)j1 + 0.5D) / (double)b0;
	                    double d1 = (double)y + ((double)k1 + 0.5D) / (double)b0;
	                    double d2 = (double)z + ((double)l1 + 0.5D) / (double)b0;
	                    int i2 = this.rand.nextInt(6);
	                    effectRenderer.addEffect((new GreatwardDummyDamageFX(world, d0, d1, d2, d0 - (double)x - 0.5D, d1 - (double)y - 0.5D, d2 - (double)z - 0.5D, this, i2, meta, null)).func_70596_a(x, y, z).multiplyVelocity(0.75F).multipleParticleScaleBy(3.0F));
                	}
                }
            }
        }
        
        return false;
    }
}
