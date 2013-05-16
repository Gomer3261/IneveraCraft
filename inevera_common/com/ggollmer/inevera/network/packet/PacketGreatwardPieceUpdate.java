package com.ggollmer.inevera.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;

import com.ggollmer.inevera.Inevera;
import com.ggollmer.inevera.core.helper.LogHelper;
import com.ggollmer.inevera.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

public class PacketGreatwardPieceUpdate extends PacketInevera
{
	public int x, y, z, coreX, coreY, coreZ;
	
	public PacketGreatwardPieceUpdate()
	{
		super(PacketTypeHandler.GWPIECE, true);
	}
	
	public PacketGreatwardPieceUpdate(int x, int y, int z, int coreX, int coreY, int coreZ)
	{
        super(PacketTypeHandler.GWPIECE, true);
        this.x = x;
        this.y = y;
        this.z = z;
        this.coreX = coreX;
        this.coreY = coreY;
        this.coreZ = coreZ;
    }
	
	@Override
    public void writeData(DataOutputStream data) throws IOException
    {
        data.writeInt(x);
        data.writeInt(y);
        data.writeInt(z);
        data.writeInt(coreX);
        data.writeInt(coreY);
        data.writeInt(coreZ);
    }
	
	@Override
    public void readData(DataInputStream data) throws IOException
    {
        x = data.readInt();
        y = data.readInt();
        z = data.readInt();
        coreX = data.readInt();
        coreY = data.readInt();
        coreZ = data.readInt();
    }
	
	@Override
    public void execute(INetworkManager manager, Player player)
	{
        LogHelper.debugLog("PacketGreatwardUpdate - Execute");
        Inevera.proxy.handleGreatwardPiecePacket(x, y, z, coreX, coreY, coreZ);
    }
}
