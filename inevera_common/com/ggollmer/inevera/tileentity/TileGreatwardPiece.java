package com.ggollmer.inevera.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;

import com.ggollmer.inevera.lib.TileStrings;
import com.ggollmer.inevera.network.PacketTypeHandler;
import com.ggollmer.inevera.network.packet.PacketGreatwardPieceUpdate;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

/**
 * IneveraCraft
 *
 * TileGreatwardDummy.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class TileGreatwardPiece extends TileEntity
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
		
		int oldX = coreX;
		int oldY = coreY;
		int oldZ = coreZ;
		
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
		
		if((oldX != coreX || oldY != coreY || oldZ != coreZ) && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
		{
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	public void setCorePosition(int x, int y, int z)
	{
		coreX = x;
		coreY = y;
		coreZ = z;
		
		this.setCoreTile((TileGreatwardCore)worldObj.getBlockTileEntity(coreX, coreY, coreZ));
	}
	
	public TileGreatwardCore getCoreTile()
	{
		if(core == null)
		{
			this.setCoreTile((TileGreatwardCore)worldObj.getBlockTileEntity(coreX, coreY, coreZ));
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
    
    @Override
    public Packet getDescriptionPacket()
    {
    	return PacketTypeHandler.populatePacket(new PacketGreatwardPieceUpdate(xCoord, yCoord, zCoord, coreX, coreY, coreZ));
    }
}
