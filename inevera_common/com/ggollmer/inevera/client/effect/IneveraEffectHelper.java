package com.ggollmer.inevera.client.effect;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import com.ggollmer.inevera.lib.EffectConstants;

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
public class IneveraEffectHelper
{
	private static Map<String, Class<? extends IneveraEffect>> effects;
	
	/**
	 * Used to initialize the effect helper, and register default inevera effects.
	 */
	public static void init()
	{
		effects = new HashMap<String, Class<? extends IneveraEffect>>(); 
		
		registerEffect(EffectConstants.EFFECT_ABSORB_NAME, IneveraEffectAbsorb.class);
		registerEffect(EffectConstants.EFFECT_GROUND_EMANATE_NAME, IneveraEffectGroundEmanate.class);
		registerEffect(EffectConstants.EFFECT_DIRECTIONAL_BURST_NAME, IneveraEffectDirectionalBurst.class);
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
				Class<?> par_types[] = new Class[]{World.class, EffectRenderer.class, double.class, double.class, double.class, String.class};
				Constructor<?> constructor = effects.get(type).getConstructor(par_types);
				Object arguments[] = new Object[]{world, effectRenderer, (Double)px, (Double)py, (Double)pz, args};
				
				IneveraEffect effect = (IneveraEffect) constructor.newInstance(arguments);
				
				effectRenderer.addEffect(effect);
				
				return effect;
			}
			catch (Exception e)
			{
				e.printStackTrace(System.err);
			}
		}
		return null;
	}
}
