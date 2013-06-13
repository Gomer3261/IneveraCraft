package com.ggollmer.inevera.tileentity;

import java.util.List;

import com.ggollmer.inevera.block.BlockGreatwardComponent;
import com.ggollmer.inevera.block.IneveraBlocks;
import com.ggollmer.inevera.greatward.Greatward;
import com.ggollmer.inevera.greatward.GreatwardHelper;
import com.ggollmer.inevera.greatward.GreatwardManager;
import com.ggollmer.inevera.lib.GreatwardConstants;
import com.ggollmer.inevera.lib.Strings;
import com.ggollmer.inevera.network.PacketTypeHandler;
import com.ggollmer.inevera.network.packet.PacketGreatwardCoreUpdate;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * IneveraCraft
 *
 * ITileGreatwardCore.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class TileGreatwardCore extends TileEntity
{
	protected Greatward greatward;
	
	public TileGreatwardCore()
	{
		super();
		greatward = null;
	}
	
	public void invalidateGreatward()
	{
		boolean changed = greatward != null;
		
		if(greatward != null)
		{
			revertDummyList();
			setValidGreatward(false);
		}
		
		if(changed && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
		{
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	private void setValidGreatward(boolean valid)
	{
		if(worldObj != null)
		{
			if(valid)
			{
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, (worldObj.getBlockMetadata(xCoord, yCoord, zCoord) | BlockGreatwardComponent.ACTIVE_BIT), 2);
			}
			else
			{
				greatward = null;
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, (worldObj.getBlockMetadata(xCoord, yCoord, zCoord) & (~BlockGreatwardComponent.ACTIVE_BIT)), 2);
			}
		}
	}
	
	public boolean isValidGreatward()
	{
		return greatward != null;
	}
	
	public ForgeDirection getWardDirection()
	{
		if(greatward != null)
		{
			return greatward.getWardDirection();
		}
		else
		{
			return ForgeDirection.UNKNOWN;
		}
	}
	
	public void onNeighborChange(World world, int x, int y, int z)
	{
		checkValidGreatward(world, x, y, z);
	}
	
	public void onBlockPlaced(World world, int x, int y, int z)
	{
		checkValidGreatward(world, x, y, z);
	}
	
	@Override
	public void updateEntity()
	{
		if(greatward != null)
		{
			if(!worldObj.isRemote) greatward.update(worldObj, xCoord, yCoord, zCoord);
			greatward.renderAmbientParticles(worldObj);
		}
	}
	
	private void checkValidGreatward(World world, int x, int y, int z)
	{
		
		if(greatward != null || world.isRemote)
		{
			return;
		}
		
		boolean changed = greatward == null;
		
		for(ForgeDirection wardDirection : ForgeDirection.VALID_DIRECTIONS)
		{                                     
			if(GreatwardHelper.isValidGreatwardPiece(worldObj.getBlockId(x+wardDirection.offsetX,  y+wardDirection.offsetY, z+wardDirection.offsetZ)))
			{
				if(((TileGreatwardPiece)worldObj.getBlockTileEntity(x+wardDirection.offsetX, y+wardDirection.offsetY, z+wardDirection.offsetZ)).getCoreTile() == null)
				{
					greatward = GreatwardManager.generateGreatward(world, x, y, z, worldObj.getBlockId(x+wardDirection.offsetX, y+wardDirection.offsetY, z+wardDirection.offsetZ), worldObj.getBlockMetadata(x+wardDirection.offsetX, y+wardDirection.offsetY, z+wardDirection.offsetZ), GreatwardConstants.GW_MINOR_TYPE, wardDirection);
					
					if(greatward != null)
					{
						convertDummyList(world, greatward.getGreatwardBlocks());
						setValidGreatward(true);
					}
				}
			}
		}
		
		if(changed == (greatward != null) && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
		{
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	private void convertDummy(World world, int x, int y, int z)
	{
		if(world.getBlockId(x, y, z) != IneveraBlocks.greatwardWoodPiece.blockID)
		{
			return;
		}
		
		TileGreatwardPiece pieceTE = (TileGreatwardPiece) world.getBlockTileEntity(x, y, z);
		pieceTE.setCoreTile(this);
	}
	
	private void revertDummy(World world, int x, int y, int z)
	{
		if(world.getBlockId(x, y, z) != IneveraBlocks.greatwardWoodPiece.blockID)
		{
			return;
		}
		
		TileGreatwardPiece pieceTE = (TileGreatwardPiece) world.getBlockTileEntity(x, y, z);
		pieceTE.setCoreTile(null);
	}
	
	private void convertDummyList(World world, List<ChunkCoordinates> coords)
	{
		for(ChunkCoordinates c : coords)
		{
			convertDummy(world, c.posX, c.posY, c.posZ);
		}
	}
	
	private void revertDummyList()
	{	
		for(ChunkCoordinates dummyPos: greatward.getGreatwardBlocks())
		{
			this.revertDummy(worldObj, dummyPos.posX, dummyPos.posY, dummyPos.posZ);
		}
	}
	
	@Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);
        
        if(nbtTagCompound.hasKey(Strings.NBT_TE_GW_VALID_KEY))
        {
        	if(nbtTagCompound.getBoolean(Strings.NBT_TE_GW_VALID_KEY))
        	{
        		greatward = new Greatward();
        		greatward.readFromNBT(nbtTagCompound);
        		
        		if(!greatward.isValidGreatward())
        		{
        			invalidateGreatward();
        		}
        		else
        		{
        			setValidGreatward(true);
        		}
        		return;
        	}
        }
        setValidGreatward(false);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);
        
        nbtTagCompound.setBoolean(Strings.NBT_TE_GW_VALID_KEY, greatward != null);
        
        if(greatward != null)
        {
        	greatward.writeToNBT(nbtTagCompound);
        }
    }
    
    public void updateWardFromPacket(boolean valid, byte direction, byte orientation, List<ChunkCoordinates> blocks, String type, String target, String attribute, String effect, List<String> augments)
	{
		if(valid)
		{
			if(greatward == null)
			{
				greatward = new Greatward();
			}
			greatward.updateWardFromPacket(direction, orientation, blocks, type, target, attribute, effect, augments);
			
			if(greatward.isValidGreatward())
			{
				setValidGreatward(true);
			}
			else
			{
				invalidateGreatward();
			}
		}
		else
		{
			setValidGreatward(false);
		}
	}
    
    @Override
    public Packet getDescriptionPacket()
    {
    	return PacketTypeHandler.populatePacket(new PacketGreatwardCoreUpdate(xCoord, yCoord, zCoord, greatward));
    }
}
