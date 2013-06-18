package com.ggollmer.inevera.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.network.INetworkManager;
import net.minecraft.util.Vec3;

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
	public int dimension_id;
	public boolean target_entities;
	public List<Integer> target_ids;
	public List<Vec3> target_positions;
	
	public PacketGreatwardAction()
	{
		super(PacketTypeHandler.GWACTION, true);
	}
	
	public PacketGreatwardAction(String type, int dimension_id, boolean target_entity, List<Integer> target_ids, List<Vec3> target_positions, String args)
	{
        super(PacketTypeHandler.GWACTION, true);
        this.type = type;
        this.dimension_id = dimension_id;
        this.target_entities = target_entity;
        this.target_ids = target_ids;
        this.target_positions = target_positions;
        this.args = args;
    }
	
	@Override
    public void writeData(DataOutputStream data) throws IOException
    {
        data.writeUTF(type);
        data.writeInt(dimension_id);
        data.writeBoolean(target_entities);
        data.writeInt(target_ids.size());
        for(int i=0; i<target_ids.size(); i++)
        {
        	data.writeInt(target_ids.get(i));
        	data.writeDouble(target_positions.get(i).xCoord);
        	data.writeDouble(target_positions.get(i).yCoord);
        	data.writeDouble(target_positions.get(i).zCoord);
        }
        data.writeUTF(args);
    }
	
	@Override
    public void readData(DataInputStream data) throws IOException
    {
        type = data.readUTF();
        dimension_id = data.readInt();
        target_entities = data.readBoolean();
        target_ids = new ArrayList<Integer>();
        target_positions = new ArrayList<Vec3>();
        int size = data.readInt();
        for(int i=0; i<size; i++)
        {
        	target_ids.add(data.readInt());
        	target_positions.add(Vec3.fakePool.getVecFromPool(data.readDouble(), data.readDouble(), data.readDouble()));
        }
        args = data.readUTF();
    }
	
	@Override
    public void execute(INetworkManager manager, Player player)
	{
        LogHelper.debugLog("PacketGreatwardAction - Execute");
        Inevera.proxy.handleGreatwardActionPacket(type, dimension_id, target_entities, target_ids, target_positions, args);
    }
}
