package com.ggollmer.inevera.tileentity;

import java.util.ArrayList;
import java.util.List;

import com.ggollmer.inevera.block.BlockGreatwardComponent;
import com.ggollmer.inevera.block.IneveraBlocks;
import com.ggollmer.inevera.core.helper.NBTHelper;
import com.ggollmer.inevera.greatward.Greatward;
import com.ggollmer.inevera.greatward.GreatwardHelper;
import com.ggollmer.inevera.greatward.GreatwardManager;
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
	protected boolean validGreatward;
	protected List<ChunkCoordinates> piecePosList;
	protected ForgeDirection wardDirection;
	protected Greatward greatward;
	
	public TileGreatwardCore()
	{
		super();
		validGreatward = false;
		piecePosList = new ArrayList<ChunkCoordinates>();
		wardDirection = ForgeDirection.UNKNOWN;
		greatward = null;
	}
	
	public void invalidateGreatward()
	{
		boolean changed = validGreatward;
		setValidGreatward(false);
		wardDirection = ForgeDirection.UNKNOWN;
		
		revertDummyList();
		
		if(changed && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
		{
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	public void setValidGreatward(boolean valid)
	{
		validGreatward = valid;
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
		return validGreatward;
	}
	
	public void setPiecesCoordList(List<ChunkCoordinates> pieces)
	{
		piecePosList = pieces;
	}
	
	public ForgeDirection getWardDirection()
	{
		return wardDirection;
	}
	
	public void setWardDirection(ForgeDirection direction)
	{
		wardDirection = direction;
	}
	
	public void onNeighborChange(World world, int x, int y, int z)
	{
		checkValidGreatward(world, x, y, z);
	}
	
	public void onBlockPlaced(World world, int x, int y, int z)
	{
		checkValidGreatward(world, x, y, z);
	}
	
	private void checkValidGreatward(World world, int x, int y, int z)
	{
		boolean changed = !validGreatward;
		if(validGreatward)
		{
			return;
		}
		
		// TODO: this is temporary code to make sure that the dummy actually works.
		if(worldObj.getBlockId(x,y+1, z) != 0)
		{
			if(GreatwardHelper.isValidGreatwardPiece(worldObj.getBlockId(x,  y+1, z)) && ((TileGreatwardPiece)worldObj.getBlockTileEntity(x, y+1, z)).getCoreTile() == null)
			{
				wardDirection = ForgeDirection.UP;
				greatward = GreatwardManager.generateGreatward(world, x, y+1, z, worldObj.getBlockId(x,y+1, z), worldObj.getBlockMetadata(x,y+1, z), "", wardDirection);
				
				if(greatward != null)
				{
					convertDummyList(world, greatward.getGreatwardBlocks());
					setValidGreatward(true);
				}
			}
		}
		
		if(changed == validGreatward && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
		{
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	private void convertDummy(World world, int x, int y, int z)
	{
		TileGreatwardPiece pieceTE = (TileGreatwardPiece) world.getBlockTileEntity(x, y, z);
		pieceTE.setCoreTile(this);
		
		piecePosList.add(new ChunkCoordinates(x, y, z));
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
		for(ChunkCoordinates dummyPos: piecePosList)
		{
			this.revertDummy(worldObj, dummyPos.posX, dummyPos.posY, dummyPos.posZ);
		}
		piecePosList.clear();
	}
	
	@Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey(Strings.NBT_TE_GW_DUMMYLIST_KEY)) {
            piecePosList = NBTHelper.getChunkCoordinatesListFromNBTTagList(nbtTagCompound.getTagList(Strings.NBT_TE_GW_DUMMYLIST_KEY));
            if(piecePosList.isEmpty())
            {
            	this.setValidGreatward(false);
            }
            else
            {
            	this.setValidGreatward(true);
            }
        }
        
        this.wardDirection = ForgeDirection.getOrientation(nbtTagCompound.getByte(Strings.NBT_TE_WARDIDRECTION_KEY));
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setTag(Strings.NBT_TE_GW_DUMMYLIST_KEY, NBTHelper.createChunkCoordinatesNBTTagList(piecePosList));
        nbtTagCompound.setByte(Strings.NBT_TE_WARDIDRECTION_KEY, (byte)this.wardDirection.ordinal());
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
    	return PacketTypeHandler.populatePacket(new PacketGreatwardCoreUpdate(xCoord, yCoord, zCoord, validGreatward, (byte)wardDirection.ordinal(), piecePosList));
    }
}
