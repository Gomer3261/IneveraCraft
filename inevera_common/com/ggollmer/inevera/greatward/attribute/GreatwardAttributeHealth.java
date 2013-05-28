package com.ggollmer.inevera.greatward.attribute;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.ggollmer.inevera.core.helper.LogHelper;
import com.ggollmer.inevera.greatward.Greatward;
import com.ggollmer.inevera.lib.GreatwardConstants;

/**
 * IneveraCraft
 *
 * GreatwardAttributeHealth.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GreatwardAttributeHealth extends GreatwardAttribute
{
	private static final int OPERATION_COST = 30;	
	/**
	 * @param name The unique name of the greatward component.
	 */
	public GreatwardAttributeHealth(String name)
	{
		super(name);
		addGreatwardMap(GreatwardConstants.GW_MINOR_TYPE, GreatwardConstants.GW_ATTRIBUTE_HEALTH_MINOR_LOCATION, GreatwardConstants.GW_MINOR_DIMENSION, GreatwardConstants.GW_MINOR_DIMENSION);
		//TODO: add constructors for normal and major maps.
	}

	@Override
	public void onGreatwardInit(World world, Greatward greatward)
	{
	}

	@Override
	public boolean canPerformOperation(World world, Greatward greatward)
	{
		if(greatward.currentCoreEnergy >= OPERATION_COST)
		{
			return true;
		}
		return false;
	}

	@Override
	public void performGreatwardEffects(World world, Greatward greatward, float effectMultiplier)
	{
		if(!greatward.entityTargets.isEmpty())
		{
			List<EntityLiving> healableEntities = new ArrayList<EntityLiving>();
			
			for(Entity e : greatward.entityTargets)
			{
				if(e instanceof EntityLiving)
				{
					healableEntities.add((EntityLiving)e);
				}
			}
			
			if(!healableEntities.isEmpty())
			{
				int index = 0;
				
				if(healableEntities.size() > 1)
				{
					index = rand.nextInt((greatward.entityTargets.size()-1));
				}
				
				LogHelper.debugLog("Healing: " + healableEntities.get(index).getEntityName());
				if(effectMultiplier < 0)
				{
					healableEntities.get(index).attackEntityFrom(DamageSource.magic, (int)(-1*effectMultiplier));
				}
				else
				{
					healableEntities.get(index).heal((int)(1*effectMultiplier));
				}
				greatward.currentCoreEnergy -= OPERATION_COST;
			}
		}
	}
}
