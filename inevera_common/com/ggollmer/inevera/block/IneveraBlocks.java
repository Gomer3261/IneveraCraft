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
	public static Block greatwardDummyGround;
	public static Block greatwardDummyWood;
	public static Block greatwardDummyRock;
	public static Block greatwardDummyIron;
	public static Block greatwardDummyCloth;
	public static Block greatwardDummySand;
	public static Block greatwardDummyGlass;
	
	public static Block minorGreatwardCore;
	
	public static void init()
	{
		greatwardDummyGround = new BlockGreatwardDummy(BlockIds.GREATWARD_DUMMY_GROUND, Material.ground, BlockNames.GREATWARD_DUMMY_GROUND_NAME);
		greatwardDummyWood = new BlockGreatwardDummy(BlockIds.GREATWARD_DUMMY_WOOD, Material.wood, BlockNames.GREATWARD_DUMMY_WOOD_NAME);
		greatwardDummyRock = new BlockGreatwardDummy(BlockIds.GREATWARD_DUMMY_ROCK, Material.rock, BlockNames.GREATWARD_DUMMY_ROCK_NAME);
		greatwardDummyIron = new BlockGreatwardDummy(BlockIds.GREATWARD_DUMMY_IRON, Material.iron, BlockNames.GREATWARD_DUMMY_IRON_NAME);
		greatwardDummyCloth = new BlockGreatwardDummy(BlockIds.GREATWARD_DUMMY_CLOTH, Material.cloth, BlockNames.GREATWARD_DUMMY_CLOTH_NAME);
		greatwardDummySand = new BlockGreatwardDummy(BlockIds.GREATWARD_DUMMY_SAND, Material.sand, BlockNames.GREATWARD_DUMMY_SAND_NAME);
		greatwardDummyGlass = new BlockGreatwardDummy(BlockIds.GREATWARD_DUMMY_GLASS, Material.glass, BlockNames.GREATWARD_DUMMY_GLASS_NAME);
		
		minorGreatwardCore = new BlockMinorGreatwardCore(BlockIds.MINOR_WARD_CORE);
		
		GameRegistry.registerBlock(greatwardDummyGround, BlockNames.GREATWARD_DUMMY_GROUND_NAME);
		GameRegistry.registerBlock(greatwardDummyWood, BlockNames.GREATWARD_DUMMY_WOOD_NAME);
		GameRegistry.registerBlock(greatwardDummyRock, BlockNames.GREATWARD_DUMMY_ROCK_NAME);
		GameRegistry.registerBlock(greatwardDummyIron, BlockNames.GREATWARD_DUMMY_IRON_NAME);
		GameRegistry.registerBlock(greatwardDummyCloth, BlockNames.GREATWARD_DUMMY_CLOTH_NAME);
		GameRegistry.registerBlock(greatwardDummySand, BlockNames.GREATWARD_DUMMY_SAND_NAME);
		GameRegistry.registerBlock(greatwardDummyGlass, BlockNames.GREATWARD_DUMMY_GLASS_NAME);
		
		GameRegistry.registerBlock(minorGreatwardCore, BlockNames.MINOR_WARD_CORE_NAME);
	}
}
