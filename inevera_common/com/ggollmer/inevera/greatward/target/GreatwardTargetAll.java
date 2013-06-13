package com.ggollmer.inevera.greatward.target;

import java.util.ArrayList;
import java.util.List;

import com.ggollmer.inevera.greatward.Greatward;
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
					double dist = ((Entity)obj).getDistance(greatward.centerX, ((Entity)obj).posY, greatward.centerZ);
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
		
		if(!greatward.canTargetBlocks())
		{
			for(int i=(int)Math.floor(greatward.bounds.minX); i<(int)Math.floor(greatward.bounds.maxX); i++)
			{
				for(int j=(int)Math.floor(greatward.bounds.minY); j<(int)Math.floor(greatward.bounds.maxY); j++)
				{
					for(int k=(int)Math.floor(greatward.bounds.minX); k<(int)Math.floor(greatward.bounds.maxX); k++)
					{
						if(greatward.isValidBlockTarget(world.getBlockId(i, j, k)))
						{
							targetBlocks.add(new ChunkCoordinates(i, j, k));
						}
					}
				}
			}
		}
		
		return targetBlocks;
	}
}
