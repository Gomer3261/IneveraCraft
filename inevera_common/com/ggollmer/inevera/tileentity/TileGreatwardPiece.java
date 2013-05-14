package com.ggollmer.inevera.tileentity;

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
public class TileGreatwardPiece extends TileInevera
{
	/* NBT data */
	private int coreX;
	private int coreY;
	private int coreZ;
	
	/* non NBT data */
	private TileGreatwardCore core = null;
	
	public TileGreatwardPiece()
	{
		this(null);
	}
	
	public TileGreatwardPiece(TileGreatwardCore core)
	{
		this.setCoreTile(core);
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
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);
        
        nbtTagCompound.setInteger(TileStrings.NBT_TE_GW_DUMMY_CORE_X, coreX);
        nbtTagCompound.setInteger(TileStrings.NBT_TE_GW_DUMMY_CORE_Y, coreY);
        nbtTagCompound.setInteger(TileStrings.NBT_TE_GW_DUMMY_CORE_Z, coreZ);
    }
}
