package com.ggollmer.inevera.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;

import com.ggollmer.inevera.Inevera;
import com.ggollmer.inevera.core.helper.LogHelper;
import com.ggollmer.inevera.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

public class PacketGreatwardActivation extends PacketInevera
{
	public int dimId, coreX, coreY, coreZ;
	
	public PacketGreatwardActivation()
	{
		super(PacketTypeHandler.GWACTIVE, true);
	}
	
	public PacketGreatwardActivation(int dimId, int coreX, int coreY, int coreZ)
	{
        super(PacketTypeHandler.GWACTIVE, true);
        this.dimId = dimId;
        this.coreX = coreX;
        this.coreY = coreY;
        this.coreZ = coreZ;
    }
	
	@Override
    public void writeData(DataOutputStream data) throws IOException
    {
		data.writeInt(dimId);
        data.writeInt(coreX);
        data.writeInt(coreY);
        data.writeInt(coreZ);
    }
	
	@Override
    public void readData(DataInputStream data) throws IOException
    {
		dimId = data.readInt();
        coreX = data.readInt();
        coreY = data.readInt();
        coreZ = data.readInt();
    }
	
	@Override
    public void execute(INetworkManager manager, Player player)
	{
        LogHelper.debugLog("PacketGreatwardActivation - Execute");
        Inevera.proxy.handleGreatwardActivationPacket(dimId, coreX, coreY, coreZ);
    }
}
