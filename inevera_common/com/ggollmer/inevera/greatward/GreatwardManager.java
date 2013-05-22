package com.ggollmer.inevera.greatward;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.ggollmer.inevera.core.helper.LogHelper;
import com.ggollmer.inevera.greatward.attribute.GreatwardAttribute;
import com.ggollmer.inevera.greatward.attribute.GreatwardAttributeHealth;
import com.ggollmer.inevera.greatward.augment.GreatwardAugment;
import com.ggollmer.inevera.greatward.effect.GreatwardEffect;
import com.ggollmer.inevera.greatward.effect.GreatwardEffectPositive;
import com.ggollmer.inevera.greatward.target.GreatwardTarget;
import com.ggollmer.inevera.greatward.target.GreatwardTargetAll;
import com.ggollmer.inevera.lib.GreatwardConstants;

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
	private static Map<String, GreatwardTarget> targetMap;
	private static Map<String, GreatwardAttribute> attributeMap;
	private static Map<String, GreatwardEffect> effectMap;
	private static Map<String, GreatwardAugment> augmentMap;
	
	private static List<String> typeList;
	
	/**
	 * Used to initialize the greatward manager.
	 */
	public static void init()
	{
		targetMap = new HashMap<String, GreatwardTarget>();
		attributeMap = new HashMap<String, GreatwardAttribute>();
		effectMap = new HashMap<String, GreatwardEffect>();
		augmentMap = new HashMap<String, GreatwardAugment>();
		
		typeList = new ArrayList<String>();
		
		registerTarget(GreatwardConstants.GW_TARGET_ALL_NAME, new GreatwardTargetAll(GreatwardConstants.GW_TARGET_ALL_NAME));
		
		registerAttribute(GreatwardConstants.GW_ATTRIBUTE_HEALTH_NAME, new GreatwardAttributeHealth(GreatwardConstants.GW_ATTRIBUTE_HEALTH_NAME));
		
		registerEffect(GreatwardConstants.GW_EFFECT_POSITIVE_NAME, new GreatwardEffectPositive(GreatwardConstants.GW_EFFECT_POSITIVE_NAME));
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
		for(GreatwardTarget t : targetMap.values())
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
		for(GreatwardAttribute a: attributeMap.values())
		{
			if(a.findPattern(world, wardType, coreX, coreY, coreZ, wardDirection, dir, id, meta, greatwardBlocks))
			{
				LogHelper.debugLog("WardAttribute found: " + a.getName());
				attribute = a;
				break;
			}
		}
		if(attribute == null) return null;
		
		GreatwardEffect effect = null;
		for(GreatwardEffect e: effectMap.values())
		{
			if(e.findPattern(world, wardType, coreX, coreY, coreZ, wardDirection, dir, id, meta, greatwardBlocks))
			{
				effect = e;
				break;
			}
		}
		if(effect == null) return null;
		
		List<GreatwardAugment> augments = new ArrayList<GreatwardAugment>();
		
		return new Greatward(target, attribute, effect, augments, greatwardBlocks);
	}
	
	/**
	 * Used to register a greatward target class with the manager.
	 * @param target the target class to register.
	 */
	public static void registerTarget(String name, GreatwardTarget target)
	{
		targetMap.put(name, target);
	}
	
	/**
	 * Used during loading to get a target object from the managers maps.
	 * @param name The name of the target object.
	 * @return The target object. Null if it dosn't exist.
	 */
	public static GreatwardTarget getTargetByName(String name)
	{
		return targetMap.get(name);
	}
	
	/**
	 * Used to register a greatward attribute class with the manager.
	 * @param attribute the attribute class to register.
	 */
	public static void registerAttribute(String name, GreatwardAttribute attribute)
	{
		attributeMap.put(name, attribute);
	}
	
	/**
	 * Used during loading to get an attribute object from the managers maps.
	 * @param name The name of the attribute object.
	 * @return The attribute object. Null if it dosn't exist.
	 */
	public static GreatwardAttribute getAttributeByName(String name)
	{
		return attributeMap.get(name);
	}
	
	/**
	 * Used to register a greatward effect class with the manager.
	 * @param effect the effect class to register.
	 */
	public static void registerEffect(String name, GreatwardEffectPositive effect)
	{
		effectMap.put(name, effect);
	}
	
	/**
	 * Used during loading to get an effect object from the managers maps.
	 * @param name The name of the effect object.
	 * @return The effect object. Null if it dosn't exist.
	 */
	public static GreatwardEffect getEffectByName(String name)
	{
		return effectMap.get(name);
	}
	
	/**
	 * Used to register a greatward augment class with the manager.
	 * @param augment the augment class to register.
	 */
	public static void registerAugment(String name, GreatwardAugment augment)
	{
		augmentMap.put(name, augment);
	}
	
	/**
	 * Used during loading to get an augment object from the managers maps.
	 * @param name The name of the augment object.
	 * @return The augment object. Null if it dosn't exist.
	 */
	public static GreatwardAugment getAugmentByName(String name)
	{
		return augmentMap.get(name);
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
