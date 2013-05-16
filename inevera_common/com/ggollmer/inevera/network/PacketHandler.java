package com.ggollmer.inevera.network;

import com.ggollmer.inevera.network.packet.PacketInevera;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

/**
 * IneveraCraft
 *
 * PacketHandler
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class PacketHandler implements IPacketHandler
{
	/***
     * Handles Packet250CustomPayload packets that are registered to an
     * IneveraCraft network channel
     * 
     * @param manager
     *            The NetworkManager associated with the current platform
     *            (client/server)
     * @param packet
     *            The Packet250CustomPayload that was received
     * @param player
     *            The Player associated with the packet
     */
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		PacketInevera packetInevera = PacketTypeHandler.buildPacket(packet.data);
		
		packetInevera.execute(manager, player);
	}
}
