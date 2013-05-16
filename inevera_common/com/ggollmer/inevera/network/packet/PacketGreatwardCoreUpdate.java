package com.ggollmer.inevera.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.network.INetworkManager;
import net.minecraft.util.ChunkCoordinates;

import com.ggollmer.inevera.Inevera;
import com.ggollmer.inevera.core.helper.LogHelper;
import com.ggollmer.inevera.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

/**
 * IneveraCraft
 *
 * PacketGreatwardCoreUpdate.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class PacketGreatwardCoreUpdate extends PacketInevera
{
	public int x, y, z;
	public boolean valid;
	public List<ChunkCoordinates> pieces;
	
	public PacketGreatwardCoreUpdate()
	{
		super(PacketTypeHandler.GWCORE, true);
	}
	
	public PacketGreatwardCoreUpdate(int x, int y, int z, boolean valid, List<ChunkCoordinates> pieces)
	{
        super(PacketTypeHandler.GWCORE, true);
        this.x = x;
        this.y = y;
        this.z = z;
        this.valid = valid;
        this.pieces = pieces;
    }
	
	@Override
    public void writeData(DataOutputStream data) throws IOException
    {
        data.writeInt(x);
        data.writeInt(y);
        data.writeInt(z);
        data.writeBoolean(valid);
        if(valid == true)
        {
        	data.writeInt(pieces.size());
        	for(ChunkCoordinates coord: pieces)
        	{
        		data.writeInt(coord.posX);
        		data.writeInt(coord.posY);
        		data.writeInt(coord.posZ);
        	}
        }
    }
	
	@Override
    public void readData(DataInputStream data) throws IOException
    {
        x = data.readInt();
        y = data.readInt();
        z = data.readInt();
        valid = data.readBoolean();
        pieces = null;
        if(valid == true)
        {
        	pieces = new ArrayList<ChunkCoordinates>();
        	int size = data.readInt();
        	
        	for(int i=0; i<size; i++)
        	{
        		ChunkCoordinates piece = new ChunkCoordinates();
        		piece.posX = data.readInt();
        		piece.posY = data.readInt();
        		piece.posZ = data.readInt();
        		pieces.add(piece);
        	}
        }
    }
	
	@Override
    public void execute(INetworkManager manager, Player player)
	{
        LogHelper.debugLog("PacketGreatwardCoreUpdate - Execute");
        Inevera.proxy.handleGreatwardCorePacket(x, y, z, valid, pieces);
    }
}
