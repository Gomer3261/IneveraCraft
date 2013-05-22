package com.ggollmer.inevera.greatward.effect;

import com.ggollmer.inevera.lib.GreatwardConstants;

/**
 * IneveraCraft
 *
 * IGreatwardEffect.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GreatwardEffectPositive extends GreatwardEffect
{
	public GreatwardEffectPositive(String name)
	{
		super(name);
		addGreatwardMap(GreatwardConstants.GW_MINOR_TYPE, GreatwardConstants.GW_EFFECT_POSITIVE_MINOR_LOCATION, GreatwardConstants.GW_MINOR_WIDTH, GreatwardConstants.GW_MINOR_HEIGHT);
	}

}
