package com.ggollmer.inevera.block;

import java.util.List;

import com.ggollmer.inevera.Inevera;
import com.ggollmer.inevera.lib.Reference;
import com.ggollmer.inevera.tileentity.TileGreatwardPiece;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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

public class BlockGreatwardPiece extends BlockGreatwardComponent
{
	private String[] subNames;
	private Icon[] iconArray;

	/**
	 * @param id The id of the new block
	 */
	public BlockGreatwardPiece(int id, Material material, String name, String[] subNames)
	{
		super(id, material);
		this.setUnlocalizedName(name);
		this.setCreativeTab(Inevera.tabsInevera);
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
		
		super.registerIcons(iconRegister);
	}
	
	@Override
	public Icon getIcon(int side, int metadata)
	{
		return iconArray[metadata & (~BlockGreatwardComponent.ACTIVE_BIT)];
	}
	
	@Override
	public int damageDropped(int metadata)
	{
		return metadata & (~BlockGreatwardComponent.ACTIVE_BIT);
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
		
		super.breakBlock(world, x, y, z, par5, par6);
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
					dummy.getCoreTile().invalidateGreatward();
				}
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean addBlockHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
    {
		if(((TileGreatwardPiece)worldObj.getBlockTileEntity(target.blockX, target.blockY, target.blockZ)).getCoreTile() != null)
		{
			super.addBlockHitEffects(worldObj, target, effectRenderer);
		}
        
        return false;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean addBlockDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {	
		if(((TileGreatwardPiece)world.getBlockTileEntity(x, y, z)).getCoreTile() != null)
		{
			super.addBlockDestroyEffects(world, x, y, z, meta, effectRenderer);
		}
        
        return false;
    }
}
