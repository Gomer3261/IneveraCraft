package com.ggollmer.inevera.greatward;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.ggollmer.inevera.greatward.attribute.GreatwardAttribute;
import com.ggollmer.inevera.greatward.augment.GreatwardAugment;
import com.ggollmer.inevera.greatward.effect.GreatwardEffect;
import com.ggollmer.inevera.greatward.target.GreatwardTarget;

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
	
	/**
	 * Used to initialize the greatward manager.
	 */
	public static void init()
	{
		targetList = new ArrayList<GreatwardTarget>();
		attributeList = new ArrayList<GreatwardAttribute>();
		effectList = new ArrayList<GreatwardEffect>();
		augmentList = new ArrayList<GreatwardAugment>();
		
		
	}
	
	/**
	 * Used to check for a valid greatward given the provided arguments.
	 * @param px The x position of the center of target portion of the greatward.
	 * @param py The y position of the center of target portion of the greatward. 
	 * @param pz The z position of the center of target portion of the greatward.
	 * @param id The block id to check for while looking for a ward.
	 * @param wardType The type(size) of ward to look for.
	 * @param dir The direction the final ward should be facing.
	 * @param world The world the ward will exist in.
	 * @return null if no valid ward is found.
	 */
	public static Greatward generateGreatward(int px, int py, int pz, int id, String wardType, ForgeDirection dir, World world)
	{
		GreatwardTarget target = null;
		for(GreatwardTarget t : targetList)
		{
			if(t.findPattern(px, py, pz, dir, world))
			{
				target = t;
				break;
			}
		}
		if(target == null) return null;
		
		GreatwardAttribute attribute = null;
		for(GreatwardAttribute a: attributeList)
		{
			if(a.findPattern(px, py, pz, dir, world))
			{
				attribute = a;
				break;
			}
		}
		if(attribute == null) return null;
		
		GreatwardEffect effect = null;
		for(GreatwardEffect e: effectList)
		{
			if(e.findPattern(px, py, px, dir, world))
			{
				effect = e;
				break;
			}
		}
		if(effect == null) return null;
		
		List<GreatwardAugment> augments = new ArrayList<GreatwardAugment>();
		
		return new Greatward(target, attribute, effect, augments);
	}
	
	/**
	 * Used to register a greatward target class with the manager.
	 * @param target the target class to register.
	 */
	public void registerTarget(GreatwardTarget target)
	{
		targetList.add(target);
	}
	
	/**
	 * Used to register a greatward attribute class with the manager.
	 * @param attribute the attribute class to register.
	 */
	public void registerAttribute(GreatwardAttribute attribute)
	{
		attributeList.add(attribute);
	}
	
	/**
	 * Used to register a greatward effect class with the manager.
	 * @param effect the effect class to register.
	 */
	public void registerEffect(GreatwardEffect effect)
	{
		effectList.add(effect);
	}
	
	/**
	 * Used to register a greatward augment class with the manager.
	 * @param augment the augment class to register.
	 */
	public void registerAugment(GreatwardAugment augment)
	{
		augmentList.add(augment);
	}
}
