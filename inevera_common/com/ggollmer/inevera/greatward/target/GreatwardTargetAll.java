package com.ggollmer.inevera.greatward.target;

import java.util.List;

import com.ggollmer.inevera.lib.GreatwardConstants;

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
	 * @param name
	 */ 
	public GreatwardTargetAll(String name)
	{
		super(GreatwardConstants.GW_TARGET_ALL_LOCATION, name);
	}

	@Override
	public List<Entity> findValidEntityTargets(int px, int py, int pz,
			String type, ForgeDirection dir)
	{
		return null;
	}

}
