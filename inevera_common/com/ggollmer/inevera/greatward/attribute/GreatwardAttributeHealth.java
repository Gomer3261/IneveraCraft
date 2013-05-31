package com.ggollmer.inevera.greatward.attribute;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

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
	private static final int TARGETS_PER_OPERATION = 5;
	private static final int AMOUNT_PER_OPERATION = 1;
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
	public boolean isValidEntityTarget(Entity target)
	{
		return ((target instanceof EntityLiving) || (target instanceof IGWHealableEntity));
	}
	
	@Override
	public void registerValidId(int id)
	{
		if(Block.blocksList[id] instanceof IGWHealableBlock)
		{
			super.registerValidId(id);
		}
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
		/* Entities */
		if(!greatward.entityTargets.isEmpty())
		{
			int targetCount = (greatward.entityTargets.size() < TARGETS_PER_OPERATION) ? greatward.entityTargets.size() : TARGETS_PER_OPERATION;
			int startIndex = (greatward.entityTargets.size()>1) ? rand.nextInt(greatward.entityTargets.size()-1) : 0;
			for(int i=0; i<targetCount; i++)
			{
				Entity target = greatward.entityTargets.get((startIndex + i)%greatward.entityTargets.size());
				if(target instanceof IGWHealableEntity)
				{
					((IGWHealableEntity)target).onGreatwardHeal((int)(AMOUNT_PER_OPERATION*effectMultiplier));
				}
				else
				{
					if(effectMultiplier < 0)
					{
						((EntityLiving)target).attackEntityFrom(DamageSource.magic, (int)(-1*effectMultiplier));
					}
					else
					{
						((EntityLiving)target).heal((int)(1*effectMultiplier));
					}
				}
			}
			greatward.currentCoreEnergy -= OPERATION_COST;
		}
		
		/* Blocks */
		if(!greatward.blockTargets.isEmpty())
		{
			for(ChunkCoordinates coord : greatward.blockTargets)
			{
				((IGWHealableBlock)Block.blocksList[world.getBlockId(coord.posX, coord.posY, coord.posZ)]).onGreatwardHeal(world, coord.posX, coord.posY, coord.posZ);
			}
		}
	}
}
