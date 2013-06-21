package com.ggollmer.inevera.greatward.target;

import java.util.ArrayList;
import java.util.List;

import com.ggollmer.inevera.greatward.Greatward;
import com.ggollmer.inevera.greatward.GreatwardDimensions;
import com.ggollmer.inevera.lib.GreatwardConstants;

import net.minecraft.entity.Entity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

/**
 * IneveraCraft
 *
 * GreatwardTargetAll.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GreatwardTargetAll extends GreatwardTarget
{

	/**
	 * @param name The name of the unique greatward component
	 */ 
	public GreatwardTargetAll(String name)
	{
		super(GreatwardConstants.GW_TARGET_ALL_LOCATION, name);
	}
	
	@Override
	public void onGreatwardInit(World world, Greatward greatward)
	{
	}
	
	@Override
	public void renderAmbientParticles(World world, Greatward greatward)
	{
	}

	@Override
	public List<Entity> findValidEntityTargets(World world, Greatward greatward)
	{
		List<Entity> targetEntities = new ArrayList<Entity>();
		List<?> worldEntities = world.getEntitiesWithinAABB(Entity.class, greatward.bounds);
		
		for(Object obj : worldEntities)
		{
			if(obj instanceof Entity)
			{
				if(greatward.isValidEntityTarget((Entity)obj))
				{
					double dist;
					switch(greatward.getWardDirection())
					{
						case EAST:
						case WEST:
							dist = ((Entity)obj).getDistance( ((Entity)obj).posY, greatward.centerY, greatward.centerZ );
							break;
						case NORTH:
						case SOUTH:
							dist  = ((Entity)obj).getDistance( greatward.centerX, greatward.centerY, ((Entity)obj).posZ );
							break;
						case UP:
						case DOWN:
						default:
							dist = ((Entity)obj).getDistance( greatward.centerX, ((Entity)obj).posY, greatward.centerZ );
							break;
					}
					
					if(dist <= greatward.radius)
					{
						targetEntities.add((Entity)obj);
					}
				}
			}
		}
		
		return targetEntities;
	}

	@Override
	public List<ChunkCoordinates> findValidBlockTargets(World world, Greatward greatward)
	{
		List<ChunkCoordinates> targetBlocks = new ArrayList<ChunkCoordinates>();
		
		//TODO: Not tested
		
		if(!greatward.canTargetBlocks())
		{
			for(int i=(int)Math.floor(greatward.bounds.minX); i<(int)Math.floor(greatward.bounds.maxX-1); i++)
			{
				for(int j=(int)Math.floor(greatward.bounds.minY); j<(int)Math.floor(greatward.bounds.maxY-1); j++)
				{
					for(int k=(int)Math.floor(greatward.bounds.minZ); k<(int)Math.floor(greatward.bounds.maxZ-1); k++)
					{
						if(greatward.isValidBlockTarget(world.getBlockId(i, j, k)))
						{
							ChunkCoordinates coords = new ChunkCoordinates(i, j, k);
							double dist;
							switch(greatward.getWardDirection())
							{
								case EAST:
								case WEST:
									dist = GreatwardDimensions.getDistance(coords.posX, coords.posY, coords.posZ, coords.posY, greatward.centerY, greatward.centerZ);
									break;
								case NORTH:
								case SOUTH:
									dist  = GreatwardDimensions.getDistance(coords.posX, coords.posY, coords.posZ, greatward.centerX, greatward.centerY, coords.posZ );
									break;
								case UP:
								case DOWN:
								default:
									dist = GreatwardDimensions.getDistance(coords.posX, coords.posY, coords.posZ, greatward.centerX, coords.posY, greatward.centerZ );
									break;
							}
							
							if(dist <= greatward.radius)
							{
								targetBlocks.add(new ChunkCoordinates(i, j, k));
							}
						}
					}
				}
			}
		}
		
		return targetBlocks;
	}
}
