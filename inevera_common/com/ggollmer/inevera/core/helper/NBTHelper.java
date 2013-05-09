package com.ggollmer.inevera.core.helper;

import java.util.ArrayList;
import java.util.List;

import com.ggollmer.inevera.lib.Strings;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChunkCoordinates;

/**
 * IneveraCraft
 *
 * NBTHelper.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class NBTHelper
{
	/**
	 * Used to create an NBTTag containing the provided ChunkCoordinates data.
	 * @param coord The ChunkCoordinate object to be stored.
	 * @return An NBTTag containing the x, y and z components of the coordinates object.
	 */
	public static NBTTagCompound createChunkCoordinateCompound(ChunkCoordinates coord)
	{
		if(coord == null)
		{
			return null;
		}
		
		NBTTagCompound tag = new NBTTagCompound(Strings.NBT_COORDINATES_KEY);
		tag.setInteger(Strings.NBT_COORDINATES_KEY_X, coord.posX);
		tag.setInteger(Strings.NBT_COORDINATES_KEY_Y, coord.posY);
		tag.setInteger(Strings.NBT_COORDINATES_KEY_Z, coord.posZ);
		
		return tag;
	}
	
	/**
	 * Used to created a ChunkCoordinates object from an NBTTag.
	 * @param compound The compound to read.
	 * @return A ChunkCoordinates object containing the x, y, and z values stored in the tag.
	 */
	public static ChunkCoordinates getChunkCoordinatesFromCompound(NBTBase compoundBase)
	{
		if(compoundBase == null || !(compoundBase instanceof NBTTagCompound))
		{
			return null;
		}
		
		NBTTagCompound compound = (NBTTagCompound) compoundBase;
		ChunkCoordinates coords = new ChunkCoordinates();
		
		if(compound.hasKey(Strings.NBT_COORDINATES_KEY_X)) {
			coords.posX = compound.getInteger(Strings.NBT_COORDINATES_KEY_X);
		}
		
		if(compound.hasKey(Strings.NBT_COORDINATES_KEY_Y)) {
			coords.posY = compound.getInteger(Strings.NBT_COORDINATES_KEY_Y);
		}
		
		if(compound.hasKey(Strings.NBT_COORDINATES_KEY_Z)) {
			coords.posZ = compound.getInteger(Strings.NBT_COORDINATES_KEY_Z);
		}
		
		return coords;
	}
	
	/**
	 * Used to create an NBTTagList from a list of chunk coordinates.
	 * @param coordList The list of chunk coordinates to work with.
	 * @return The NBTTag object.
	 */
	public static NBTTagList createChunkCoordinatesNBTTagList(List<ChunkCoordinates> coordList)
	{
		if(coordList  == null) {
			return null;
		}
		
		NBTTagList tagList = new NBTTagList(Strings.NBT_COORDINATES_LIST_KEY);
		for(ChunkCoordinates coord : coordList)
		{
			tagList.appendTag(NBTHelper.createChunkCoordinateCompound(coord));
		}
		
		return tagList;
	}
	
	/**
	 * Used to convert from an NBT tag object to a list of chunk coordinates.
	 * @param tagList The NBT tag to read.
	 * @return A list of ChunkCoordinates objects.
	 */
	public static List<ChunkCoordinates> getChunkCoordinatesListFromNBTTagList(NBTTagList tagList)
	{
		if(tagList == null) {
			return null;
		}
		
		List<ChunkCoordinates> coordList = new ArrayList<ChunkCoordinates>();
		
		for(int i=0; i<tagList.tagCount(); i++)
		{
			coordList.add(NBTHelper.getChunkCoordinatesFromCompound(tagList.tagAt(i)));
		}
		
		return coordList;
	}
}
