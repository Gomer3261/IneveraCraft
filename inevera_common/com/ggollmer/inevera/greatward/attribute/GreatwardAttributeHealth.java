package com.ggollmer.inevera.greatward.attribute;

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
	/**
	 * @param name
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
		if(greatward.currentCoreEnergy >= 100)
		{
			return true;
		}
		return false;
	}

	@Override
	public void performGreatwardEffects(World world, Greatward greatward, float effectMutliplier)
	{
		if(!greatward.entityTargets.isEmpty())
		{
			if(greatward.entityTargets.size() > 1)
			{
				LogHelper.debugLog("Healing: " + greatward.entityTargets.get(rand.nextInt((greatward.entityTargets.size()-1))).getEntityName());
			}
			else
			{
				LogHelper.debugLog("Healing: " + greatward.entityTargets.get(0).getEntityName());
			}
		}
	}
}
