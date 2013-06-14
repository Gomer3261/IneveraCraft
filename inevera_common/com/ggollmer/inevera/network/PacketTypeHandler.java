package com.ggollmer.inevera.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import com.ggollmer.inevera.lib.Reference;
import com.ggollmer.inevera.network.packet.PacketGreatwardCoreUpdate;
import com.ggollmer.inevera.network.packet.PacketGreatwardPieceUpdate;
import com.ggollmer.inevera.network.packet.PacketInevera;
import com.ggollmer.inevera.network.packet.PacketIneveraEffect;
import com.ggollmer.inevera.network.packet.PacketTileUpdate;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;


/**
 * IneveraCraft
 *
 * PacketTypeHandler
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public enum PacketTypeHandler
{
	TILE(PacketTileUpdate.class),
	EFFECT(PacketIneveraEffect.class),
	GWPIECE(PacketGreatwardPieceUpdate.class),
	GWCORE(PacketGreatwardCoreUpdate.class);
	
	private Class<? extends PacketInevera> clazz;

    PacketTypeHandler(Class<? extends PacketInevera> clazz)
    {
        this.clazz = clazz;
    }
    
    public static PacketInevera buildPacket(byte[] data)
    {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        int selector = bis.read();
        DataInputStream dis = new DataInputStream(bis);

        PacketInevera packet = null;

        try {
            packet = values()[selector].clazz.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }

        packet.readPopulate(dis);

        return packet;
    }
    
    public static PacketInevera buildPacket(PacketTypeHandler type)
    {
        PacketInevera packet = null;

        try {
            packet = values()[type.ordinal()].clazz.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return packet;
    }
    
    public static Packet populatePacket(PacketInevera packetInevera)
    {
        byte[] data = packetInevera.populate();

        Packet250CustomPayload packet250 = new Packet250CustomPayload();
        packet250.channel = Reference.CHANNEL_NAME;
        packet250.data = data;
        packet250.length = data.length;
        packet250.isChunkDataPacket = packetInevera.isChunkDataPacket;

        return packet250;
    }
}
