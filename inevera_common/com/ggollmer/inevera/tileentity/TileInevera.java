package com.ggollmer.inevera.tileentity;

import com.ggollmer.inevera.lib.Strings;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

/**
 * IneveraCraft
 *
 * TileInevera.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class TileInevera extends TileEntity
{
	private ForgeDirection orientation;
	private String customName;
	
	public TileInevera()
	{
		orientation = ForgeDirection.SOUTH;
		customName = "";
	}
	
	public ForgeDirection getOrientation()
	{
        return orientation;
    }

    public void setOrientation(ForgeDirection orientation)
    {
        this.orientation = orientation;
    }

    public void setOrientation(int orientation)
    {
        this.orientation = ForgeDirection.getOrientation(orientation);
    }
    
    public boolean hasCustomName()
    {
        return customName != null && customName.length() > 0;
    }

    public String getCustomName()
    {
        return customName;
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey(Strings.NBT_TE_DIRECTION_KEY)) {
            orientation = ForgeDirection.getOrientation(nbtTagCompound.getByte(Strings.NBT_TE_DIRECTION_KEY));
        }

        if (nbtTagCompound.hasKey(Strings.NBT_TE_CUSTOM_NAME)) {
            customName = nbtTagCompound.getString(Strings.NBT_TE_CUSTOM_NAME);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setByte(Strings.NBT_TE_DIRECTION_KEY, (byte) orientation.ordinal());

        if (this.hasCustomName()) {
            nbtTagCompound.setString(Strings.NBT_TE_CUSTOM_NAME, customName);
        }
    }
}
