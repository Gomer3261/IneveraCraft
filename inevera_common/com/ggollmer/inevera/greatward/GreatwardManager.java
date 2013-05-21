package com.ggollmer.inevera.greatward;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.ggollmer.inevera.core.helper.LogHelper;
import com.ggollmer.inevera.greatward.attribute.GreatwardAttribute;
import com.ggollmer.inevera.greatward.augment.GreatwardAugment;
import com.ggollmer.inevera.greatward.effect.GreatwardEffect;
import com.ggollmer.inevera.greatward.target.GreatwardTarget;
import com.ggollmer.inevera.greatward.target.GreatwardTargetAll;

/**
 * IneveraCraft
 *
 * GreatwardManager.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GreatwardManager
{
	private static List<GreatwardTarget> targetList;
	private static List<GreatwardAttribute> attributeList;
	private static List<GreatwardEffect> effectList;
	private static List<GreatwardAugment> augmentList;
	private static List<String> typeList;
	
	/**
	 * Used to initialize the greatward manager.
	 */
	public static void init()
	{
		targetList = new ArrayList<GreatwardTarget>();
		attributeList = new ArrayList<GreatwardAttribute>();
		effectList = new ArrayList<GreatwardEffect>();
		augmentList = new ArrayList<GreatwardAugment>();
		typeList = new ArrayList<String>();
		
		registerTarget(new GreatwardTargetAll());
	}
	
	/**
	 * Used to check for a valid greatward given the provided arguments.
	 * @param coreX The x position of the greatwarcd core.
	 * @param coreY The y position of the greatwarcd core.
	 * @param coreZ The z position of the greatwarcd core.
	 * @param id The block id to check for while looking for a ward.
	 * @param wardType The type(size) of ward to look for.
	 * @param dir The direction the final ward should be facing.
	 * @param world The world the ward will exist in.
	 * @return null if no valid ward is found.
	 */
	public static Greatward generateGreatward(World world, int coreX, int coreY, int coreZ, int id, int meta, String wardType, ForgeDirection dir)
	{
		List<ChunkCoordinates> greatwardBlocks = new ArrayList<ChunkCoordinates>();
		ForgeDirection wardDirection = null;
		
		GreatwardTarget target = null;
		for(GreatwardTarget t : targetList)
		{
			wardDirection = t.findPatternAndDirection(world, coreX, coreY, coreZ, dir, id, meta, greatwardBlocks);
			if(wardDirection != ForgeDirection.UNKNOWN)
			{
				LogHelper.debugLog("WardTarget found: " + t.getName());
				target = t;
				break;
			}
		}
		if(target == null) return null;
		
		GreatwardAttribute attribute = null;
		/*for(GreatwardAttribute a: attributeList)
		{
			if(a.findPattern(px, py, pz, dir, world))
			{
				attribute = a;
				break;
			}
		}
		if(attribute == null) return null;*/
		
		GreatwardEffect effect = null;
		/*for(GreatwardEffect e: effectList)
		{
			if(e.findPattern(px, py, px, dir, world))
			{
				effect = e;
				break;
			}
		}
		if(effect == null) return null;*/
		
		List<GreatwardAugment> augments = new ArrayList<GreatwardAugment>();
		
		return new Greatward(target, attribute, effect, augments, greatwardBlocks);
	}
	
	/**
	 * Used to register a greatward target class with the manager.
	 * @param target the target class to register.
	 */
	public static void registerTarget(GreatwardTarget target)
	{
		targetList.add(target);
	}
	
	/**
	 * Used to register a greatward attribute class with the manager.
	 * @param attribute the attribute class to register.
	 */
	public static void registerAttribute(GreatwardAttribute attribute)
	{
		attributeList.add(attribute);
	}
	
	/**
	 * Used to register a greatward effect class with the manager.
	 * @param effect the effect class to register.
	 */
	public static void registerEffect(GreatwardEffect effect)
	{
		effectList.add(effect);
	}
	
	/**
	 * Used to register a greatward augment class with the manager.
	 * @param augment the augment class to register.
	 */
	public static void registerAugment(GreatwardAugment augment)
	{
		augmentList.add(augment);
	}
	
	/**
	 * Used to register additional greatward types with the manager.
	 * @param type a string representing a unique type of greatward.
	 */
	public static void registerType(String type)
	{
		typeList.add(type);
	}
}
