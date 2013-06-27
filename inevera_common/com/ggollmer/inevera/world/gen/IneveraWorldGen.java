package com.ggollmer.inevera.world.gen;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * IneveraCraft
 *
 * IneveraWorldGen.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class IneveraWorldGen
{
	public static void init()
	{
		GameRegistry.registerWorldGenerator(new WorldGenDemonFossil());
	}
}
