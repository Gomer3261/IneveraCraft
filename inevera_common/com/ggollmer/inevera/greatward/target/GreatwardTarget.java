package com.ggollmer.inevera.greatward.target;

import java.util.List;

import com.ggollmer.inevera.greatward.GreatwardComponent;

import net.minecraft.entity.Entity;
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
	 * @param dimx
	 * @param dimy
	 * @param patternPath
	 */
	public GreatwardTarget(int dimx, int dimy, String patternPath)
	{
		super(dimx, dimy, patternPath);
	}
	
	public ForgeDirection findPatternAndDirection(World world, int px, int py, int pz, ForgeDirection dir, int id)
	{		
		for(ForgeDirection ex : ForgeDirection.VALID_DIRECTIONS)
		{
			if(ex != dir && ex != dir.getOpposite())
			{
				ForgeDirection ey = ForgeDirection.getOrientation(ForgeDirection.ROTATION_MATRIX[dir.ordinal()][ex.ordinal()]);
				int sx = px + ex.offsetX + ey.offsetX;
				int sy = px + ex.offsetY + ey.offsetY;
				int sz = px + ex.offsetZ + ey.offsetZ;
			}
		}
		
		return ForgeDirection.UNKNOWN;
	}

	List<Entity> findValidEntityTargets(int px, int py, int pz, String type, ForgeDirection dir)
	{
		return null;
	}

}
