package com.ggollmer.inevera.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.network.INetworkManager;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.common.ForgeDirection;

import com.ggollmer.inevera.Inevera;
import com.ggollmer.inevera.core.helper.LogHelper;
import com.ggollmer.inevera.greatward.Greatward;
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
	public byte direction;
	public List<ChunkCoordinates> blocks;
	public String target;
	public String attribute;
	public String effect;
	public List<String> augments;
	
	public PacketGreatwardCoreUpdate()
	{
		super(PacketTypeHandler.GWCORE, true);
	}
	
	public PacketGreatwardCoreUpdate(int x, int y, int z, Greatward greatward)
	{
        super(PacketTypeHandler.GWCORE, true);
        this.x = x;
        this.y = y;
        this.z = z;
        this.valid = greatward != null;
        if(valid)
        {
        	this.direction = (byte)greatward.getWardDirection().ordinal();
        	this.blocks = greatward.getGreatwardBlocks();
        	this.target = greatward.getTargetName();
        	this.attribute = greatward.getAttributeName();
        	this.effect = greatward.getEffectName();
        	this.augments = greatward.getAugmentNames();
        }
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
        	data.writeByte(direction);
        	data.writeInt(blocks.size());
        	for(ChunkCoordinates coord: blocks)
        	{
        		data.writeInt(coord.posX);
        		data.writeInt(coord.posY);
        		data.writeInt(coord.posZ);
        	}
        	data.writeUTF(target);
        	data.writeUTF(attribute);
        	data.writeUTF(effect);
        	data.writeInt(augments.size());
        	for(String augment : augments)
        	{
        		data.writeUTF(augment);
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
        if(valid == true)
        {
        	direction = data.readByte();
        	blocks = new ArrayList<ChunkCoordinates>();
        	int size = data.readInt();
        	
        	for(int i=0; i<size; i++)
        	{
        		ChunkCoordinates piece = new ChunkCoordinates();
        		piece.posX = data.readInt();
        		piece.posY = data.readInt();
        		piece.posZ = data.readInt();
        		blocks.add(piece);
        	}
        	
        	target = data.readUTF();
        	attribute = data.readUTF();
        	effect = data.readUTF();
        	
        	augments = new ArrayList<String>();
        	size = data.readInt();
        	for(int i=0; i<size; i++)
        	{
        		String str = data.readUTF();
        		augments.add(str);
        	}
        }
        else
        {
        	direction = (byte)ForgeDirection.UNKNOWN.ordinal();
            blocks = null;
            target = null;
            attribute = null;
            effect = null;
            augments = null;
        }
    }
	
	@Override
    public void execute(INetworkManager manager, Player player)
	{
        LogHelper.debugLog("PacketGreatwardCoreUpdate - Execute");
        Inevera.proxy.handleGreatwardCorePacket(x, y, z, valid, direction, blocks, target, attribute, effect, augments);
    }
}
