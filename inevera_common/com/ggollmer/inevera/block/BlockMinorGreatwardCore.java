package com.ggollmer.inevera.block;

import com.ggollmer.inevera.Inevera;
import com.ggollmer.inevera.lib.BlockNames;
import com.ggollmer.inevera.tileentity.TileGreatwardCoreMinor;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
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
public class BlockMinorGreatwardCore extends BlockInevera
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
		return new TileGreatwardCoreMinor();
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		TileGreatwardCoreMinor tile = (TileGreatwardCoreMinor) world.getBlockTileEntity(x, y, z);
		tile.invalidate();
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int id)
	{
		TileGreatwardCoreMinor tile = (TileGreatwardCoreMinor) world.getBlockTileEntity(x, y, z);
		tile.onNeighborChange(world, x, y, z);
	}
}
