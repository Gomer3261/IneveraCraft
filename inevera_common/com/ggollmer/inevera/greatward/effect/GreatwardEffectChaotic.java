package com.ggollmer.inevera.greatward.effect;

import net.minecraft.world.World;

import com.ggollmer.inevera.greatward.Greatward;
import com.ggollmer.inevera.lib.GreatwardConstants;

/**
 * IneveraCraft
 *
 * GreatwardEffectChaotic.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GreatwardEffectChaotic extends GreatwardEffect
{
	public GreatwardEffectChaotic(String name)
	{
		super(name);
		addGreatwardMap(GreatwardConstants.GW_MINOR_TYPE, GreatwardConstants.GW_EFFECT_CHAOTIC_MINOR_LOCATION, GreatwardConstants.GW_MINOR_DIMENSION, GreatwardConstants.GW_MINOR_DIMENSION);
		//TODO: Add normal and major types.
	}

	@Override
	public float getEffectMultiplier(World world, Greatward greatward)
	{
		return rand.nextInt()%3;
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
