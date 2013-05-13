package com.ggollmer.inevera.tileentity;

import net.minecraft.nbt.NBTTagCompound;

import com.ggollmer.inevera.core.helper.LogHelper;
import com.ggollmer.inevera.greatward.GreatwardDummyHelper;
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
	private int imitationId;
	private int imitationMetadata;
	private int coreX;
	private int coreY;
	private int coreZ;
	
	/* non NBT data */
	private TileGreatwardCore core = null;
	
	public TileGreatwardDummy()
	{
		this(2, 0, null);
	}
	
	public TileGreatwardDummy(int id, int metadata, TileGreatwardCore core)
	{
		imitationId = id;
		imitationMetadata = metadata;
		this.setCoreTile(core);
	}
	
	public void setImitationBlock(int newId, int newMetadata)
	{
		if(GreatwardDummyHelper.isBlockDummiable(newId, newMetadata))
		{
			LogHelper.debugLog(String.format("id %d and meta %d set!", newId, newMetadata));
			imitationId = newId;
			imitationMetadata = newMetadata;
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, newMetadata, 2);
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
		
		if(core != null)
		{
			coreX = core.xCoord;
			coreY = core.yCoord;
			coreZ = core.zCoord;
		}
		else
		{
			coreX = 0;
			coreY = 0;
			coreZ = 0;
		}
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
