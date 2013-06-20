package com.ggollmer.inevera.greatward.target;

import java.util.List;

import com.ggollmer.inevera.greatward.Greatward;
import com.ggollmer.inevera.greatward.GreatwardComponent;
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
	 * 
	 * @param patternPath
	 * @param name
	 */
	public GreatwardTarget(String patternPath, String name)
	{
		super(name);
		addGreatwardMap(GreatwardConstants.GW_MINOR_TYPE, patternPath, GreatwardConstants.GW_TARGET_WIDTH, GreatwardConstants.GW_TARGET_HEIGHT);
		addGreatwardMap(GreatwardConstants.GW_NORMAL_TYPE, patternPath, GreatwardConstants.GW_TARGET_WIDTH, GreatwardConstants.GW_TARGET_HEIGHT);
		addGreatwardMap(GreatwardConstants.GW_MAJOR_TYPE, patternPath, GreatwardConstants.GW_TARGET_WIDTH, GreatwardConstants.GW_TARGET_HEIGHT);
	}
	
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

	public abstract List<Entity> findValidEntityTargets(World world, Greatward ward);
	public abstract List<ChunkCoordinates> findValidBlockTargets(World world, Greatward ward);

}
