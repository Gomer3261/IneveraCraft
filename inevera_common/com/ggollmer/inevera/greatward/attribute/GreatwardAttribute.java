package com.ggollmer.inevera.greatward.attribute;

import net.minecraft.entity.Entity;
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
	
	protected boolean[] validIds = new boolean[4096];
	protected boolean canTargetBlocks;
	
	/**
	 * @param name
	 */
	public GreatwardAttribute(String name)
	{
		super(name);
		for(int i=0; i<4096; i++)
		{
			validIds[i] = false;
		}
		canTargetBlocks = false;
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
	
	public abstract void performGreatwardAction(World world, boolean target_entity, int id, double posX, double posY, double posZ);

	/**
	 * Used to check which blocks should be targeted based on block id.
	 * @param id The id to check for.
	 * @return true if a block can be targeted.
	 */
	public boolean isValidBlockTarget(int id)
	{
		return validIds[id];
	}
	
	/**
	 * Used to check if an entity can be targeted.
	 * @param target The entity to be targeted.
	 * @return True if the entity is a valid target.
	 */
	public boolean isValidEntityTarget(Entity target)
	{
		return true;
	}
	
	/**
	 * Used to register valid target ids with the greatward attribute.
	 * @param id The id to register with the attribute.
	 */
	public void registerValidId(int id)
	{
		validIds[id] = true;
		canTargetBlocks = true;
	}
	
	/**
	 * Used to check if the attribute can target blocks.
	 * @return True if the attribute can target blocks.
	 */
	public boolean canTargetBlocks()
	{
		return canTargetBlocks;
	}
}
