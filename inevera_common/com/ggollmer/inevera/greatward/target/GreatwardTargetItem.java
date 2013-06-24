package com.ggollmer.inevera.greatward.target;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.world.World;

import com.ggollmer.inevera.greatward.Greatward;
import com.ggollmer.inevera.lib.GreatwardConstants;

/**
 * IneveraCraft
 *
 * GreatwardTargetItem.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GreatwardTargetItem extends GreatwardTarget
{

	/**
	 * @param name the unique name for this particular target type.
	 */
	public GreatwardTargetItem(String name)
	{
		super(GreatwardConstants.GW_TARGET_ITEM_LOCATION, name);
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
		return (entity instanceof EntityItem);
	}

	@Override
	public boolean isValidBlockTarget(int id, int metadata)
	{
		return false;
	}
}
