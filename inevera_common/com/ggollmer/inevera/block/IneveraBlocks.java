package com.ggollmer.inevera.block;

import com.ggollmer.inevera.lib.BlockIds;
import com.ggollmer.inevera.lib.BlockNames;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

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
	public static Block greatwardPiece;
	
	public static Block minorGreatwardCore;
	
	public static void init()
	{
		greatwardPiece = new BlockGreatwardPiece(BlockIds.GREATWARD_PIECE, Material.ground, BlockNames.GREATWARD_PIECE_NAME);
		
		minorGreatwardCore = new BlockMinorGreatwardCore(BlockIds.MINOR_WARD_CORE);
		
		GameRegistry.registerBlock(greatwardPiece, BlockNames.GREATWARD_PIECE_NAME);
		
		GameRegistry.registerBlock(minorGreatwardCore, BlockNames.MINOR_WARD_CORE_NAME);
	}
}
