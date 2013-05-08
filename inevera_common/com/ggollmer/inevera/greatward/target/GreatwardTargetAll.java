package com.ggollmer.inevera.greatward.target;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraftforge.common.ForgeDirection;

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
	 * @param dimx
	 * @param dimy
	 * @param patternPath
	 */
	public GreatwardTargetAll(int dimx, int dimy, String patternPath)
	{
		super(dimx, dimy, patternPath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Entity> findValidEntityTargets(int px, int py, int pz,
			String type, ForgeDirection dir)
	{
		return null;
	}

}
