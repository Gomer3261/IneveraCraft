package com.ggollmer.inevera.greatward.effect;

import net.minecraft.world.World;

import com.ggollmer.inevera.greatward.Greatward;
import com.ggollmer.inevera.greatward.GreatwardComponent;

/**
 * IneveraCraft
 *
 * GreatwardEffect.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public abstract class GreatwardEffect extends GreatwardComponent
{
	/**
	 * @param name The unique name of the greatward component
	 */
	public GreatwardEffect(String name)
	{
		super(name);
	}
	
	/**
	 * Used to get the multiplier that should be applied to the greatward attribute.
	 * @param world The world the greatward exists in.
	 * @param greatward The greatward itself.
	 * @return The multiplier to be applied to the greatward attribute.
	 */
	public abstract float getEffectMultiplier(World world, Greatward greatward);
}
