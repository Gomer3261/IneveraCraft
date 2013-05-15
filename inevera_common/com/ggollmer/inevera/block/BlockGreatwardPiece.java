package com.ggollmer.inevera.block;

import java.util.List;
import java.util.Random;

import com.ggollmer.inevera.Inevera;
import com.ggollmer.inevera.client.particle.GreatwardDummyDamageFX;
import com.ggollmer.inevera.lib.BlockNames;
import com.ggollmer.inevera.lib.Reference;
import com.ggollmer.inevera.tileentity.TileGreatwardPiece;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockGreatwardPiece extends BlockContainer
{

	private Random rand;
	private String[] subNames;
	private Icon[] iconFXArray;
	private Icon[] iconArray;

	/**
	 * @param id The id of the new block
	 */
	public BlockGreatwardPiece(int id, Material material, String name, String[] subNames)
	{
		super(id, material);
		this.setUnlocalizedName(name);
		this.setCreativeTab(Inevera.tabsInevera);
		this.rand = new Random();
		this.subNames = subNames;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.iconArray = new Icon[subNames.length];
		
		for(int i=0; i < subNames.length; ++i)
		{
			this.iconArray[i] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + this.getUnlocalizedName2() + "_" + subNames[i]);
		}
		
		this.iconFXArray = new Icon[5];

        for (int i = 0; i < this.iconFXArray.length; i++)
        {        	
            this.iconFXArray[i] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + BlockNames.GREATWARD_COMPONENT_FX_NAME + "_" + i);
        }
	}
	
	@Override
	public Icon getIcon(int side, int metadata)
	{
		return iconArray[metadata];
	}
	
	public Icon getEffectIcon(int par15, int par16)
	{
		return iconFXArray[rand.nextInt(5)];
	}
	
	@Override
	public int damageDropped(int metadata)
	{
		return metadata;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int id, CreativeTabs tab, List subItems)
	{
		for(int i=0; i<subNames.length; i++)
		{
			subItems.add(new ItemStack(id, 1, i));
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return (TileEntity)new TileGreatwardPiece();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		TileGreatwardPiece dummy = (TileGreatwardPiece)world.getBlockTileEntity(x, y, z);
		
		if(dummy != null && dummy.getCoreTile() != null)
		{
			dummy.getCoreTile().invalidateGreatward();
		}
		
		world.removeBlockTileEntity(x, y, z);
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int id)
	{
		TileGreatwardPiece dummy = (TileGreatwardPiece)world.getBlockTileEntity(x, y, z);
		
		if(dummy != null)
		{
			if(dummy.getCoreTile() != null)
			{
				ForgeDirection gwDir = dummy.getCoreTile().getWardDirection();
				if(world.getBlockId(x+gwDir.offsetX, y+gwDir.offsetY, z+gwDir.offsetZ) != 0)
				{
					dummy.invalidate();
				}
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean addBlockHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
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

        
        effectRenderer.addEffect((new GreatwardDummyDamageFX(worldObj, d0, d1, d2, 0.0D, 0.0D, 0.0D, this, target.sideHit, worldObj.getBlockMetadata(target.blockX, target.blockY, target.blockZ), null)).func_70596_a(target.blockX, target.blockY, target.blockZ).multiplyVelocity(0.1F));
        
        return true;
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
                    double d0 = (double)x + ((double)j1 + 0.5D) / (double)b0;
                    double d1 = (double)y + ((double)k1 + 0.5D) / (double)b0;
                    double d2 = (double)z + ((double)l1 + 0.5D) / (double)b0;
                    int i2 = this.rand.nextInt(6);
                    effectRenderer.addEffect((new GreatwardDummyDamageFX(world, d0, d1, d2, d0 - (double)x - 0.5D, d1 - (double)y - 0.5D, d2 - (double)z - 0.5D, this, i2, meta, null)).func_70596_a(x, y, z).multiplyVelocity(2.0F).multipleParticleScaleBy(1.5F));
                }
            }
        }
        
        return true;
    }
}
