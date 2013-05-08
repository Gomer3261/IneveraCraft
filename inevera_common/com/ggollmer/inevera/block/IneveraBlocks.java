package com.ggollmer.inevera.block;

import com.ggollmer.inevera.lib.BlockIds;
import com.ggollmer.inevera.lib.BlockNames;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;

/**
 * IneveraCraft
 *
 * IneveraBlocks.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class IneveraBlocks
{
	/* Block instances */
	public static Block minorGreatwardCore;
	public static Block greatwardDummy;
	
	public static void init()
	{
		minorGreatwardCore = new BlockMinorGreatwardCore(BlockIds.MINOR_WARD_CORE);
		greatwardDummy = new BlockGreatwardDummy(BlockIds.GREATWARD_DUMMY);
		
		GameRegistry.registerBlock(minorGreatwardCore, BlockNames.MINOR_WARD_CORE_NAME);
		GameRegistry.registerBlock(greatwardDummy, BlockNames.GREATWARD_DUMMY_NAME);
	}
}
