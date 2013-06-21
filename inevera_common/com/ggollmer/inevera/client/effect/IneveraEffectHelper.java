package com.ggollmer.inevera.client.effect;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.world.World;

/**
 * IneveraCraft
 *
 * IneveraEffectHelper.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
@SideOnly(Side.CLIENT)
public class IneveraEffectHelper
{
	private static Map<String, Class<? extends IneveraEffect>> effects;
	
	/**
	 * Used to initialize the effect helper, and register default inevera effects.
	 */
	public static void init()
	{
		effects = new HashMap<String, Class<? extends IneveraEffect>>(); 
	}
	
	/**
	 * Registers an effect with the helper.
	 * @param type The string representing the type of effect.
	 * @param effect The class for the new effect.
	 * @return false if the type already exists.
	 */
	public static boolean registerEffect(String type, Class<? extends IneveraEffect> effect)
	{
		if(effects.containsKey(type))
		{
			return false;
		}
		effects.put(type, effect);
		return true;
	}
	
	/**
	 * Used to register an effect regardless of whether the type is taken or not.
	 * @param type The string representing the type of effect.
	 * @param effect The class for the new effect.
	 */
	public static void registerEffectNonBlocking(String type, Class<? extends IneveraEffect> effect)
	{
		effects.put(type, effect);
	}
	
	/**
	 * Used to instantiate a new effect.
	 * @param type The type of effect to create.
	 * @param world The world to create the effect in.
	 * @param effectRenderer The renderer that should control the effect.
	 * @param px The starting x location of the effect.
	 * @param py The starting y location of the effect.
	 * @param pz The starting z location of the effect.
	 * @param args The effect specific arguments.
	 * @return A reference to the spawned effect.
	 */
	public static IneveraEffect spawnEffect(String type, World world, EffectRenderer effectRenderer, double px, double py, double pz, String args)
	{
		if(effects.containsKey(type))
		{
			try
			{
				Class<?> par_types[] = new Class[]{world.getClass(), effectRenderer.getClass(), Double.class, Double.class, Double.class, args.getClass()};
				Constructor<?> constructor = effects.get(type).getConstructor(par_types);
				Object arguments = new Object[]{world, effectRenderer, px, py, pz, args};
				
				return (IneveraEffect)constructor.newInstance(arguments);
			}
			catch (Exception e)
			{
				e.printStackTrace(System.err);
			}
		}
		return null;
	}
}