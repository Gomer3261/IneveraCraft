package com.ggollmer.inevera.greatward.effect;

import net.minecraft.world.World;

import com.ggollmer.inevera.greatward.Greatward;
import com.ggollmer.inevera.lib.GreatwardConstants;

/**
 * IneveraCraft
 *
 * GreatwardEffectNegative.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GreatwardEffectNegative extends GreatwardEffect
{
	public GreatwardEffectNegative(String name)
	{
		super(name);
		addGreatwardMap(GreatwardConstants.GW_MINOR_TYPE, GreatwardConstants.GW_EFFECT_NEGATIVE_MINOR_LOCATION, GreatwardConstants.GW_MINOR_DIMENSION, GreatwardConstants.GW_MINOR_DIMENSION);
		//TODO: Add normal and major types.
	}

	@Override
	public float getEffectMultiplier(World world, Greatward greatward)
	{
		return -1;
	}

	@Override
	public void onGreatwardInit(World world, Greatward greatward)
	{
	}
	
	@Override
	public void renderAmbientParticles(World world, Greatward greatward)
	{
	}
}
