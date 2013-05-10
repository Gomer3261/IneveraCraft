package com.ggollmer.inevera.tileentity;

import java.util.ArrayList;
import java.util.List;

import com.ggollmer.inevera.block.BlockGreatwardDummy;
import com.ggollmer.inevera.block.IneveraBlocks;
import com.ggollmer.inevera.core.helper.LogHelper;
import com.ggollmer.inevera.core.helper.NBTHelper;
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
	protected List<ChunkCoordinates> dummyPosList;
	
	public TileGreatwardCore()
	{
		super();
		dummyPosList = new ArrayList<ChunkCoordinates>();
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
			if(TileGreatwardDummy.isBlockDummiable(worldObj.getBlockId(xCoord,  yCoord+1, zCoord), worldObj.getBlockMetadata(xCoord, yCoord+1, zCoord)))
			{
				convertDummy(world, x, y+1, z);
				validGreatward = true;
			}
		}
	}
	
	public void convertDummy(World world, int x, int y, int z)
	{
		int oldId = world.getBlockId(x, y, z);
		int oldMetadata = world.getBlockMetadata(x, y, z);
		
		BlockGreatwardDummy dummyBlock = (BlockGreatwardDummy) IneveraBlocks.greatwardDummy;
		dummyBlock.setDummyValues(oldId, oldMetadata, this);
		
		world.setBlock(x, y, z, dummyBlock.blockID);
		world.markBlockForUpdate(x, y, z);
		
		/*TileGreatwardDummy dummyTE = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		dummyTE.setImitationBlock(oldId, oldMetadata);
		dummyTE.setCoreTile(this);*/
		
		dummyPosList.add(new ChunkCoordinates(x, y, z));
		LogHelper.debugLog(String.format("Dummy block added: id: %d, meta: %d, pos: %d, %d, %d", oldId, oldMetadata, x, y, z));
		world.markBlockForUpdate(x, y, z);
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
		world.markBlockForUpdate(x, y, z);
	}
	
	public void revertDummyList()
	{
		for(ChunkCoordinates dummyPos: dummyPosList)
		{
			this.revertDummy(worldObj, dummyPos.posX, dummyPos.posY, dummyPos.posZ);
		}
		dummyPosList.clear();
	}
	
	public ForgeDirection getWardDirection()
	{
		return ForgeDirection.NORTH;
	}
	
	@Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey(Strings.NBT_TE_GW_DUMMYLIST_KEY)) {
            dummyPosList = NBTHelper.getChunkCoordinatesListFromNBTTagList(nbtTagCompound.getTagList(Strings.NBT_TE_GW_DUMMYLIST_KEY));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setTag(Strings.NBT_TE_GW_DUMMYLIST_KEY, NBTHelper.createChunkCoordinatesNBTTagList(dummyPosList));
    }
}
