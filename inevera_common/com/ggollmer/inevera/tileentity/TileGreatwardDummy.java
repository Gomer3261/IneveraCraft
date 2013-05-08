package com.ggollmer.inevera.tileentity;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;

import com.ggollmer.inevera.lib.TileStrings;

/**
 * IneveraCraft
 *
 * TileGreatwardDummy.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class TileGreatwardDummy extends TileInevera
{
	/* NBT data */
	private int imitationId = 1;
	private int imitationMetadata = 0;
	private int coreX;
	private int coreY;
	private int coreZ;
	
	/* non NBT data */
	private TileGreatwardCore core = null;
	
	public void setImitationBlock(int newId, int newMetadata)
	{
		if(Block.isNormalCube(newId))
		{
			Block newBlock = Block.blocksList[newId];
			if(!newBlock.hasTileEntity(imitationMetadata) && (newBlock.getBlockHardness(null, 0, 0, 0) > 0.6))
			{
				imitationId = newId;
				imitationMetadata = newMetadata;
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, newMetadata, 3);
			}
		}
	}
	
	public int getImitationId()
	{
		return imitationId;
	}
	
	public int getImitationMetadata()
	{
		return imitationMetadata;
	}

	public void setCoreTile(TileGreatwardCore newCore)
	{
		
		core = newCore;
	}
	
	public TileGreatwardCore getCoreTile()
	{
		if(core == null)
		{
			core = (TileGreatwardCore)worldObj.getBlockTileEntity(coreX, coreY, coreZ);
		}
		return core;
	}
	
	@Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey(TileStrings.NBT_TE_GW_DUMMY_CORE_X)) {
            coreX = nbtTagCompound.getInteger(TileStrings.NBT_TE_GW_DUMMY_CORE_X);
        }

        if (nbtTagCompound.hasKey(TileStrings.NBT_TE_GW_DUMMY_CORE_Y)) {
            coreY = nbtTagCompound.getInteger(TileStrings.NBT_TE_GW_DUMMY_CORE_Y);
        }
        
        if (nbtTagCompound.hasKey(TileStrings.NBT_TE_GW_DUMMY_CORE_Z)) {
            coreZ = nbtTagCompound.getInteger(TileStrings.NBT_TE_GW_DUMMY_CORE_Z);
        }
        
        if (nbtTagCompound.hasKey(TileStrings.NBT_TE_GW_DUMMY_ID)) {
            imitationId = nbtTagCompound.getInteger(TileStrings.NBT_TE_GW_DUMMY_ID);
        }
        
        if (nbtTagCompound.hasKey(TileStrings.NBT_TE_GW_DUMMY_META)) {
        	imitationMetadata = nbtTagCompound.getInteger(TileStrings.NBT_TE_GW_DUMMY_META);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);
        
        nbtTagCompound.setInteger(TileStrings.NBT_TE_GW_DUMMY_CORE_X, coreX);
        nbtTagCompound.setInteger(TileStrings.NBT_TE_GW_DUMMY_CORE_Y, coreY);
        nbtTagCompound.setInteger(TileStrings.NBT_TE_GW_DUMMY_CORE_Z, coreZ);
        nbtTagCompound.setInteger(TileStrings.NBT_TE_GW_DUMMY_ID, imitationId);
		nbtTagCompound.setInteger(TileStrings.NBT_TE_GW_DUMMY_META, imitationMetadata);
    }
}
