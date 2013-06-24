package com.ggollmer.inevera.greatward.target;

import com.ggollmer.inevera.greatward.Greatward;
import com.ggollmer.inevera.lib.GreatwardConstants;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

/**
 * IneveraCraft
 *
 * GreatwardTargetEntity.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GreatwardTargetEntity extends GreatwardTarget
{

	/**
	 * @param name The name of the unique greatward component
	 */ 
	public GreatwardTargetEntity(String name)
	{
		super(GreatwardConstants.GW_TARGET_ENTITY_LOCATION, name);
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
	public boolean isValidEntityTarget(Entity entity)
	{
		return true;
	}

	@Override
	public boolean isValidBlockTarget(int id, int metadata)
	{
		return false;
	}
}
