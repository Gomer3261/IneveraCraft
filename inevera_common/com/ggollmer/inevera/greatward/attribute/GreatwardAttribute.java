package com.ggollmer.inevera.greatward.attribute;

import java.util.ArrayList;
import java.util.List;

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
	
	private static List<Integer> validIds;
	
	/**
	 * @param name
	 */
	public GreatwardAttribute(String name)
	{
		super(name);
		validIds = new ArrayList<Integer>();
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

	/**
	 * Used to check which blocks should be targetted based on block id.
	 * @return A list of valid block Ids for the greatward to target.
	 */
	public List<Integer> getValidBlockTargets()
	{
		return validIds;
	}
	
	/**
	 * Used to register valid target ids with the greatward attribute.
	 * @param id The id to register with the attribute.
	 */
	public void registerValidId(int id)
	{
		validIds.add(id);
	}
}
