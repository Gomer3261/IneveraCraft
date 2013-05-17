package com.ggollmer.inevera.block;

import com.ggollmer.inevera.Inevera;
import com.ggollmer.inevera.lib.BlockNames;
import com.ggollmer.inevera.lib.Reference;
import com.ggollmer.inevera.tileentity.TileGreatwardCore;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * IneveraCraft
 *
 * BlockMinorGreatwardCore.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class BlockMinorGreatwardCore extends BlockGreatwardComponent
{

	/**
	 * @param id
	 * @param material
	 */
	protected BlockMinorGreatwardCore(int id)
	{
		super(id, Material.wood);
		this.setUnlocalizedName(BlockNames.MINOR_WARD_CORE_NAME);
		this.setCreativeTab(Inevera.tabsInevera);
		this.setHardness(4F);
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileGreatwardCore();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		blockIcon = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + this.getUnlocalizedName2());
		
		super.registerIcons(iconRegister);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		TileGreatwardCore tile = (TileGreatwardCore) world.getBlockTileEntity(x, y, z);
		tile.invalidateGreatward();
		
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int id)
	{
		TileGreatwardCore tile = (TileGreatwardCore) world.getBlockTileEntity(x, y, z);
		tile.onNeighborChange(world, x, y, z);
		
		super.onNeighborBlockChange(world, x, y, z, id);
	}
	
	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int par5)
	{
		TileGreatwardCore tile = (TileGreatwardCore) world.getBlockTileEntity(x, y, z);
		tile.onBlockPlaced(world, x, y, z);
		
		super.onPostBlockPlaced(world, x, y, z, par5);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean addBlockHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
    {
		if(((TileGreatwardCore)worldObj.getBlockTileEntity(target.blockX, target.blockY, target.blockZ)).isValidGreatward())
		{
			super.addBlockHitEffects(worldObj, target, effectRenderer);
		}
        
        return false;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean addBlockDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {	
		if(((TileGreatwardCore)world.getBlockTileEntity(x, y, z)).isValidGreatward())
		{
			super.addBlockDestroyEffects(world, x, y, z, meta, effectRenderer);
		}
        
        return false;
    }
}
