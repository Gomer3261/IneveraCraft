package com.ggollmer.inevera.greatward;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
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
	protected GreatwardMap pattern;
	protected String name;
	protected int dimX;
	protected int dimY;
	
	public GreatwardComponent(int dimX, int dimY, String patternPath, String name)
	{
		pattern = this.loadGreatwardMap(dimX, dimY, patternPath);
		this.name = name;
		this.dimX = dimX;
		this.dimY = dimY;
	}
	
	/**
	 * Loads a greatward map from a file.
	 * @param dimx The number of columns in the map.
	 * @param dimy The number of rows in the map.
	 * @param path The path to the map.
	 * @return A greatward map object holding the data from the specified text file.
	 */
	protected GreatwardMap loadGreatwardMap(int dimx, int dimy, String path)
	{		
		char[][] charMap = new char[dimx][dimy];
		
		try {
			path = Minecraft.class.getResource(path).getFile();
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
		    String line = null;
		    int x;
		    int y = 0;
		    while ((line = reader.readLine()) != null) {
		        for(x=0; x < dimx; x++)
		        {
		        	 char in = line.charAt(x+2);
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
		} catch (Exception x) {
		    LogHelper.log(Level.SEVERE, "IneveraCraft: Greatward map: " + path + " could not be found.");
		}
		
		return new GreatwardMap(dimx, dimy, charMap);
	}
	
	/**
	 * Used to check if the world matches the components loaded GreatwardMap.
	 * @param world The world object to work with.
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
	protected boolean areaMatchesPattern(World world, int posId, int posMeta, int sx, int sy, int sz, ForgeDirection ex, ForgeDirection ey, ForgeDirection ez, List<ChunkCoordinates> greatwardBlocks, boolean metaMatters)
	{
		List<ChunkCoordinates> newCoords = new ArrayList<ChunkCoordinates>();
		ChunkCoordinates coord;
		
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
						if((posId != id 
								|| (posMeta != meta && metaMatters))
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
	
	public String getName()
	{
		return name;
	}
	
	@Override
	public String toString()
	{
		return "GreatwardComponent: " + getName();
	}
}
