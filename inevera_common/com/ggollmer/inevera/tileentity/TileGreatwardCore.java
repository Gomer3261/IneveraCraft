package com.ggollmer.inevera.tileentity;

import java.util.List;

import com.ggollmer.inevera.block.IneveraBlocks;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * IneveraCraft
 *
 * ITileGreatwardCore.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public abstract class TileGreatwardCore extends TileInevera
{
	protected boolean validGreatward = false;
	protected List<ChunkCoordinates> dummies;
	
	public void invalidateGreatward()
	{
		validGreatward = false;
	}
	
	public boolean isValidGreatward()
	{
		return validGreatward;
	}
	
	public void onNeighborChange()
	{
		if(validGreatward)
		{
			return;
		}
		
		
	}
	
	public void convertDummy(World world, int x, int y, int z)
	{
		int oldId = world.getBlockId(x, y, z);
		int oldMetadata = world.getBlockMetadata(x, y, z);
		
		world.setBlock(x, y, z, IneveraBlocks.greatwardDummy.blockID);
		world.markBlockForUpdate(x, y, z);
		
		TileGreatwardDummy dummyTE = (TileGreatwardDummy) world.getBlockTileEntity(x, y, z);
		dummyTE.setImitationBlock(oldId, oldMetadata);
		dummyTE.setCoreTile(this);
	}
	
	public void revertDummy(World world, int x, int y, int z)
	{
		if(world.getBlockId(x, y, z) != IneveraBlocks.greatwardDummy.blockID)
		{
			return;
		}
		
		TileGreatwardDummy dummyTE = (TileGreatwardDummy) world.getBlockTileEntity(x, y, z);
		
		int newId = dummyTE.getImitationId();
		int newMetadata = dummyTE.getImitationMetadata();
		
		world.setBlock(x, y, z, newId);
		world.setBlockMetadataWithNotify(x, y, z, newMetadata, 0);
	}
	
	public abstract ForgeDirection getWardDirection();
}
