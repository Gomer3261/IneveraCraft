package com.ggollmer.inevera.greatward.target;

import java.util.List;

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
		super(GreatwardConstants.GW_TARGET_WIDTH, GreatwardConstants.GW_TARGET_HEIGHT, patternPath, name);
	}
	
	public ForgeDirection findPatternAndDirection(World world, int coreX, int coreY, int coreZ, ForgeDirection dir, int id, int meta, List<ChunkCoordinates> greatwardBlocks)
	{
		int px = coreX + dir.offsetX;
		int py = coreY + dir.offsetY;
		int pz = coreZ + dir.offsetZ;
		
		for(ForgeDirection ex : ForgeDirection.VALID_DIRECTIONS)
		{
			if(ex != dir && ex != dir.getOpposite())
			{
				ForgeDirection ey = ForgeDirection.getOrientation(ForgeDirection.ROTATION_MATRIX[dir.ordinal()][ex.ordinal()]);
				
				int sx = px + ex.offsetX*(dimX/-2) + ey.offsetX*(dimY/-2);
				int sy = py + ex.offsetY*(dimX/-2) + ey.offsetY*(dimY/-2);
				int sz = pz + ex.offsetZ*(dimX/-2) + ey.offsetZ*(dimY/-2);
				
				if(areaMatchesPattern(world, id, meta, sx, sy, sz, ex, ey, dir, greatwardBlocks, true))
				{
					return ex;
				}
			}
		}
		
		return ForgeDirection.UNKNOWN;
	}

	List<Entity> findValidEntityTargets(int px, int py, int pz, String type, ForgeDirection dir)
	{
		return null;
	}

}
