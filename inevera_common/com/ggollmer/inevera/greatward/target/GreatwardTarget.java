package com.ggollmer.inevera.greatward.target;

import java.util.ArrayList;
import java.util.List;

import com.ggollmer.inevera.greatward.Greatward;
import com.ggollmer.inevera.greatward.GreatwardComponent;
import com.ggollmer.inevera.greatward.GreatwardDimensions;
import com.ggollmer.inevera.lib.GreatwardConstants;

import net.minecraft.entity.Entity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * IneveraCraft
 *
 * IGreatwardTarget.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public abstract class GreatwardTarget extends GreatwardComponent
{
	
	/**
	 * Base constructor for targets.
	 * @param patternPath The path to the target pattern.
	 * @param name The unique name of the target component.
	 */
	public GreatwardTarget(String patternPath, String name)
	{
		super(name);
		addGreatwardMap(GreatwardConstants.GW_MINOR_TYPE, patternPath, GreatwardConstants.GW_TARGET_WIDTH, GreatwardConstants.GW_TARGET_HEIGHT);
		addGreatwardMap(GreatwardConstants.GW_NORMAL_TYPE, patternPath, GreatwardConstants.GW_TARGET_WIDTH, GreatwardConstants.GW_TARGET_HEIGHT);
		addGreatwardMap(GreatwardConstants.GW_MAJOR_TYPE, patternPath, GreatwardConstants.GW_TARGET_WIDTH, GreatwardConstants.GW_TARGET_HEIGHT);
	}
	
	/**
	 * Used to check for the saved pattern at the given location, and return the direction of the greatward.
	 * @param world The world the ward could exist in.
	 * @param coreX The x location of the core block.
	 * @param coreY The y location of the core block.
	 * @param coreZ The z location of the core block.
	 * @param ez The direction that the greatward faces (normal vector).
	 * @param id The id of the block the greatward is made up of.
	 * @param meta The metadata value of the block the greatward is made up of.
	 * @param greatwardBlocks The list to store discovered blocks in.
	 * @return The direction the ward is facing.
	 */
	public ForgeDirection findPatternAndDirection(World world, int coreX, int coreY, int coreZ, ForgeDirection ez, int id, int meta, List<ChunkCoordinates> greatwardBlocks)
	{
		int px = coreX + ez.offsetX;
		int py = coreY + ez.offsetY;
		int pz = coreZ + ez.offsetZ;
		
		for(ForgeDirection ey : ForgeDirection.VALID_DIRECTIONS)
		{
			if(ey != ez && ey != ez.getOpposite())
			{
				ForgeDirection ex = ForgeDirection.getOrientation(ForgeDirection.ROTATION_MATRIX[ey.ordinal()][ez.ordinal()]);
				
				int sx = px + ex.offsetX*(GreatwardConstants.GW_TARGET_WIDTH/2) + ey.offsetX*(GreatwardConstants.GW_TARGET_HEIGHT/2);
				int sy = py + ex.offsetY*(GreatwardConstants.GW_TARGET_WIDTH/2) + ey.offsetY*(GreatwardConstants.GW_TARGET_HEIGHT/2);
				int sz = pz + ex.offsetZ*(GreatwardConstants.GW_TARGET_WIDTH/2) + ey.offsetZ*(GreatwardConstants.GW_TARGET_HEIGHT/2);
				
				if(areaMatchesPattern(world, GreatwardConstants.GW_MINOR_TYPE, id, meta, sx, sy, sz, ex, ey, ez, greatwardBlocks, true))
				{
					return ey;
				}
			}
		}
		
		return ForgeDirection.UNKNOWN;
	}
	
	/**
	 * Used to find the target-able entities within the greatward.
	 * @param world The world that the ward exists in.
	 * @param greatward The greatward that is calling the function.
	 * @return A list of entities within the greatward.
	 */
	public List<Entity> findValidEntityTargets(World world, Greatward greatward)
	{
		List<Entity> targetEntities = new ArrayList<Entity>();
		List<?> worldEntities = world.getEntitiesWithinAABB(Entity.class, greatward.bounds);
		
		for(Object obj : worldEntities)
		{
			if(obj instanceof Entity)
			{
				if( greatward.isValidEntityTarget((Entity)obj) && (this.isValidEntityTarget((Entity)obj)) )
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
	
	/**
	 * Used to find a list of target-able blocks for a given greatward.
	 * @param World The world the greatward exists in.
	 * @param Greatward The greatward calling this function.
	 * @return A list of blocks that the greatward can target.
	 */
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
						if(greatward.isValidBlockTarget(world.getBlockId(i, j, k)) && (this.isValidBlockTarget(world.getBlockId(i, j, k), world.getBlockMetadata(i, j, k))) )
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
	
	/**
	 * Used to check restrictions on target-able entities for this target type.
	 * @param entity The entity that is being considered for targeting.
	 * @return true if the entity can be targeted.
	 */
	public abstract boolean isValidEntityTarget(Entity entity);
	
	/**
	 * Used to check restrictions on target-able blocks for this target type.
	 * @param id The id of the block that is being considered for targeting.
	 * @param metadata The metadata of the block that is being considered for targeting.
	 * @return true if the block can be targeted.
	 */
	public abstract boolean isValidBlockTarget(int id, int metadata);
}
