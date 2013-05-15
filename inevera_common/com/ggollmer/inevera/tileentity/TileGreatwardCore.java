package com.ggollmer.inevera.tileentity;

import java.util.ArrayList;
import java.util.List;

import com.ggollmer.inevera.block.IneveraBlocks;
import com.ggollmer.inevera.core.helper.LogHelper;
import com.ggollmer.inevera.core.helper.NBTHelper;
import com.ggollmer.inevera.greatward.GreatwardPieceHelper;
import com.ggollmer.inevera.lib.Strings;

import net.minecraft.nbt.NBTTagCompound;
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
	protected List<ChunkCoordinates> piecePosList;
	
	public TileGreatwardCore()
	{
		super();
		piecePosList = new ArrayList<ChunkCoordinates>();
	}
	
	public void invalidateGreatward()
	{
		validGreatward = false;
		
		revertDummyList();
		LogHelper.debugLog("Greatward Invalidated!");
	}
	
	public boolean isValidGreatward()
	{
		return validGreatward;
	}
	
	public void onNeighborChange(World world, int x, int y, int z)
	{
		if(validGreatward)
		{
			return;
		}
		
		// TODO: this is temporary code to make sure that the dummy actually works.
		if(worldObj.getBlockId(x,y+1, z) != 0)
		{
			if(GreatwardPieceHelper.isValidGreatwardPiece(worldObj.getBlockId(xCoord,  yCoord+1, zCoord)))
			{
				convertDummy(world, x, y+1, z);
				validGreatward = true;
			}
		}
	}
	
	public void convertDummy(World world, int x, int y, int z)
	{
		TileGreatwardPiece pieceTE = (TileGreatwardPiece) world.getBlockTileEntity(x, y, z);
		pieceTE.setCoreTile(this);
		
		piecePosList.add(new ChunkCoordinates(x, y, z));
	}
	
	public void revertDummy(World world, int x, int y, int z)
	{
		if(world.getBlockId(x, y, z) != IneveraBlocks.greatwardWoodPiece.blockID)
		{
			return;
		}
		
		TileGreatwardPiece pieceTE = (TileGreatwardPiece) world.getBlockTileEntity(x, y, z);
		pieceTE.setCoreTile(null);
	}
	
	public void revertDummyList()
	{
		for(ChunkCoordinates dummyPos: piecePosList)
		{
			this.revertDummy(worldObj, dummyPos.posX, dummyPos.posY, dummyPos.posZ);
		}
		piecePosList.clear();
	}
	
	public ForgeDirection getWardDirection()
	{
		return ForgeDirection.NORTH;
	}
	
	@Override
	public void invalidate()
	{
		invalidateGreatward();
		super.invalidate();
	}
	
	@Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey(Strings.NBT_TE_GW_DUMMYLIST_KEY)) {
            piecePosList = NBTHelper.getChunkCoordinatesListFromNBTTagList(nbtTagCompound.getTagList(Strings.NBT_TE_GW_DUMMYLIST_KEY));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setTag(Strings.NBT_TE_GW_DUMMYLIST_KEY, NBTHelper.createChunkCoordinatesNBTTagList(piecePosList));
    }
}
