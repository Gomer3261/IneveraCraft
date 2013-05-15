package com.ggollmer.inevera.block;

import com.ggollmer.inevera.item.ItemGreatwardWoodPiece;
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
	public static Block greatwardWoodPiece;
	
	public static Block minorGreatwardCore;
	
	public static void init()
	{
		greatwardWoodPiece = new BlockGreatwardPiece(BlockIds.GREATWARD_WOOD_PIECE, Material.ground, BlockNames.GREATWARD_WOOD_PIECE_NAME, BlockNames.GREATWARD_WOOD_PIECE_SUBNAMES);
		
		minorGreatwardCore = new BlockMinorGreatwardCore(BlockIds.MINOR_WARD_CORE);
		
		GameRegistry.registerBlock(greatwardWoodPiece, ItemGreatwardWoodPiece.class, BlockNames.GREATWARD_WOOD_PIECE_NAME);
		GameRegistry.registerBlock(minorGreatwardCore, BlockNames.MINOR_WARD_CORE_NAME);
	}
}
