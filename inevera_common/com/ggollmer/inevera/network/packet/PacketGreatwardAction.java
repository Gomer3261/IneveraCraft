package com.ggollmer.inevera.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;

import com.ggollmer.inevera.Inevera;
import com.ggollmer.inevera.core.helper.LogHelper;
import com.ggollmer.inevera.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

/**
 * IneveraCraft
 *
 * PacketIneveraEffect.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class PacketGreatwardAction extends PacketInevera
{
	public String type, args;
	public boolean target_entity;
	public int target_id;
	public double posX, posY, posZ;
	
	public PacketGreatwardAction()
	{
		super(PacketTypeHandler.EFFECT, true);
	}
	
	public PacketGreatwardAction(String type, boolean target_entity, int id, double posX, double posY, double posZ, String args)
	{
        super(PacketTypeHandler.EFFECT, true);
        this.type = type;
        this.target_entity = target_entity;
        this.target_id = id;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.args = args;
    }
	
	@Override
    public void writeData(DataOutputStream data) throws IOException
    {
        data.writeUTF(type);
        data.writeBoolean(target_entity);
        data.writeInt(target_id);
        data.writeDouble(posX);
        data.writeDouble(posY);
        data.writeDouble(posZ);
        data.writeUTF(args);
    }
	
	@Override
    public void readData(DataInputStream data) throws IOException
    {
        type = data.readUTF();
        target_entity = data.readBoolean();
        target_id = data.readInt();
        posX = data.readDouble();
        posY = data.readDouble();
        posZ = data.readDouble();
        args = data.readUTF();
    }
	
	@Override
    public void execute(INetworkManager manager, Player player)
	{
        LogHelper.debugLog("PacketIneveraEffect - Execute");
        Inevera.proxy.handleGreatwardActionPacket(type, target_entity, target_id, posX, posY, posZ, args);
    }
}
