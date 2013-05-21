package com.ggollmer.inevera.greatward;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.ggollmer.inevera.block.BlockGreatwardComponent;
import com.ggollmer.inevera.core.helper.LogHelper;

/**
 * IneveraCraft
 *
 * GreatwardComponent.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public abstract class GreatwardComponent
{
	private static Map<String, GreatwardMap> loadedMaps = new HashMap<String, GreatwardMap>();
	
	protected Map<String, GreatwardMap> patterns;
	protected String name;
	
	/**
	 * Constructs a new greatward component.
	 * @param name The name of the greatward component. Primarily for error print purposes.
	 */
	public GreatwardComponent(String name)
	{
		patterns = new HashMap<String, GreatwardMap>();
		this.name = name;
	}
	
	/**
	 * Loads a greatward map from a file and stores the information statically for reference.
	 * @param dimx The number of columns in the map.
	 * @param dimy The number of rows in the map.
	 * @param path The path to the map.
	 * @return A greatward map object holding the data from the specified text file.
	 */
	protected GreatwardMap loadGreatwardMap(String path, int dimx, int dimy)
	{
		char[][] charMap = new char[dimx][dimy];
		
		if(!GreatwardComponent.loadedMaps.containsKey(path))
		{
			try {
				path = Minecraft.class.getResource(path).getFile();
				
				BufferedReader reader = new BufferedReader(new FileReader(path));
			    String line = null;
			    int x;
			    int y = 0;
			    
			    while ((line = reader.readLine()) != null) {
			        for(x=0; x < dimx; x++)
			        {
			        	 char in = line.charAt(x*2);
			        	 switch(in)
			        	 {
			        		 case GreatwardMap.GW_NEGATIVE_TILE:
			        			 charMap[x][y] = GreatwardMap.GW_NEGATIVE_TILE;
			        			 break;
			        		 case GreatwardMap.GW_POSITIVE_TILE:
			        			 charMap[x][y] = GreatwardMap.GW_POSITIVE_TILE;
			        			 break;
			        		 default:
			        			 charMap[x][y] = GreatwardMap.GW_EMPTY_TILE;
			        			 break;
			        	 }
			        	 
			        }
			        y++;
			    }
			    reader.close();
			    
			    GreatwardComponent.loadedMaps.put(path, new GreatwardMap(dimx, dimy, charMap));
			    
			} catch (Exception e) {
			    LogHelper.log(Level.SEVERE, "IneveraCraft: Greatward map: " + path + " could not be found.");
			    e.printStackTrace();
			}
		}
		
		return GreatwardComponent.loadedMaps.get(path);
	}
	
	/**
	 * Used to check if the world matches the components loaded GreatwardMap.
	 * @param world The world object to work with.
	 * @param type The string representing the type of greatward. Used to determine the correct pattern.
	 * @param posId The id that positive tiles should look for.
	 * @param posMeta The metadata that positive tiles should look for.
	 * @param sx The x position in starting point of the check (Top left corner of the patter)
	 * @param sy The y position in starting point of the check (Top left corner of the patter)
	 * @param sz The z position in starting point of the check (Top left corner of the patter)
	 * @param ex The direction to move in the world when we move right 1 column in the map.
	 * @param ey The direction to move in the world when we move down 1 column in the map.
	 * @param ez The direction to 
	 * @param metaMatters
	 * @return
	 */
	protected boolean areaMatchesPattern(World world, String type, int posId, int posMeta, int sx, int sy, int sz, ForgeDirection ex, ForgeDirection ey, ForgeDirection ez, List<ChunkCoordinates> greatwardBlocks, boolean metaMatters)
	{
		List<ChunkCoordinates> newCoords = new ArrayList<ChunkCoordinates>();
		ChunkCoordinates coord;
		GreatwardMap pattern = patterns.get(type);
		if(pattern == null)
		{
			LogHelper.debugLog("No pattern exists for type: " + type);
			return false;
		}
		
		for(int i=0; i<pattern.getWidth(); i++)
		{
			for(int j=0; j<pattern.getHeight(); j++)
			{
				/* Empty tiles can be anything as far as we are concerned */
				if(pattern.getValue(i, j) != GreatwardMap.GW_EMPTY_TILE)
				{
					coord = new ChunkCoordinates(sx + i*ex.offsetX + j*ey.offsetX,
							sy + i*ex.offsetY + j*ey.offsetY,
							sz + i*ex.offsetZ + j*ey.offsetZ);
					int id = world.getBlockId(coord.posX, coord.posY, coord.posZ);	
					int meta = world.getBlockMetadata(coord.posX, coord.posY, coord.posZ);
					
					/* Active ward components break the ward calculation. */
					if(id == posId && (meta & BlockGreatwardComponent.ACTIVE_BIT) != 0)
					{
						return false;
					}
					
					/* Do we match the pattern? */
					if(pattern.getValue(i, j) == GreatwardMap.GW_POSITIVE_TILE)
					{
						if(posId != id 
								|| (posMeta != meta && metaMatters)
								|| !GreatwardHelper.isClearBlock(world.getBlockId(
										coord.posX + ez.offsetX,
										coord.posY + ez.offsetY,
										coord.posZ + ez.offsetZ)))
						{
							return false;
						}
						else
						{
							newCoords.add(coord);
						}
					}
					
					if (pattern.getValue(i, j) == GreatwardMap.GW_NEGATIVE_TILE && (posId == id && (posMeta == meta || !metaMatters)))
					{
						return false;
					}
				}
			}
		}
		greatwardBlocks.addAll(newCoords);
		return true;
	}
	
	/**
	 * Used to load a new greatward map into the component's map hashmap.
	 * @param type The type of map, doubles as the map key.
	 * @param path The path to the greatward map resource.
	 * @param dimx The width of the map.
	 * @param dimy The height of the map.
	 */
	public void addGreatwardMap(String type, String path, int dimx, int dimy)
	{
		patterns.put(type, this.loadGreatwardMap(path, dimx, dimy));
	}
	
	/**
	 * Used to insert an existing greatward map into the component's map hashmap.
	 * @param type The type of the map, doubles as the map key.
	 * @param map The GreatwardMap object to be loaded into the hashmap.
	 */
	public void addGreatwardMap(String type, GreatwardMap map)
	{
		patterns.put(type, map);
	}
	
	/**
	 * Used to check if the component's greatward map hashmap contains the key type.
	 * @param type The key to look for.
	 * @return True if an entry exists for the key type.
	 */
	public boolean hasGreatwardMap(String type)
	{
		return patterns.containsKey(type);
	}
	
	protected GreatwardMap getGreatwardMap(String type)
	{
		return patterns.get(type);
	}
	
	/**
	 * Used to get the name of the greatward component.
	 * @return The name of the greatward component.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Prints the greatward component as a string.
	 */
	@Override
	public String toString()
	{
		return "GreatwardComponent: " + getName();
	}
}
