package com.ggollmer.inevera.greatward.target;

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
		// TODO Auto-generated method stub
	}

	@Override
	public List<Entity> findValidEntityTargets(World world, Greatward greatward)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChunkCoordinates> findValidBlockTargets(World world, Greatward greatward)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
