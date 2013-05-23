/**
 * 
 */
package com.ggollmer.inevera.greatward.attribute;

import net.minecraft.world.World;

import com.ggollmer.inevera.greatward.Greatward;
import com.ggollmer.inevera.greatward.GreatwardFullComponent;

/**
 * IneveraCraft
 *
 * GreatwardAttribute.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public abstract class GreatwardAttribute extends GreatwardFullComponent
{

	/**
	 * @param name
	 */
	public GreatwardAttribute(String name)
	{
		super(name);
	}
	
	/**
	 * Used to check if the greatward has enough charge to perform and operation.
	 * @param world The world the greatward exists in.
	 * @param greatward The greatward object itself.
	 */
	public abstract boolean canPerformOperation(World world, Greatward greatward);
	
	/**
	 * Used to perform an actual greatward operation on all targets.
	 * @param world The world the greatward exists in.
	 * @param greatward The greatward itself.
	 */
	public abstract void performGreatwardEffects(World world, Greatward greatward, float effectMultiplier);
}
