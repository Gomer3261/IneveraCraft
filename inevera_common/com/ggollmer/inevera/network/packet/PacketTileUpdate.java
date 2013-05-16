package com.ggollmer.inevera.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import net.minecraftforge.common.ForgeDirection;

import com.ggollmer.inevera.Inevera;
import com.ggollmer.inevera.core.helper.LogHelper;
import com.ggollmer.inevera.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

/**
 * IneveraCraft
 *
 * PacketTypeHandler
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class PacketTileUpdate extends PacketInevera
{
	public int x, y, z;
    public byte orientation;
    public String customName;
	
	public PacketTileUpdate()
	{
		super(PacketTypeHandler.TILE, true);
	}

	public PacketTileUpdate(int x, int y, int z, ForgeDirection orientation, String customName)
	{
        super(PacketTypeHandler.TILE, true);
        this.x = x;
        this.y = y;
        this.z = z;
        this.orientation = (byte) orientation.ordinal();
        this.customName = customName;
    }
	
	@Override
    public void writeData(DataOutputStream data) throws IOException
    {
        data.writeInt(x);
        data.writeInt(y);
        data.writeInt(z);
        data.writeByte(orientation);
        data.writeUTF(customName);
    }
	
	@Override
    public void readData(DataInputStream data) throws IOException
    {
        x = data.readInt();
        y = data.readInt();
        z = data.readInt();
        orientation = data.readByte();
        customName = data.readUTF();
    }
	
	@Override
    public void execute(INetworkManager manager, Player player)
	{
        LogHelper.debugLog("PacketTileUpdate - Execute");
        Inevera.proxy.handleTileEntityPacket(x, y, z, ForgeDirection.getOrientation(orientation), customName);
    }
}
