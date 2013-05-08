package com.ggollmer.inevera.greatward.target;

import java.util.List;

import com.ggollmer.inevera.greatward.GreatwardComponent;

import net.minecraft.entity.Entity;
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

	List<Entity> findValidEntityTargets(int px, int py, int pz, String type, ForgeDirection dir)
	{
		return null;
	}

}
