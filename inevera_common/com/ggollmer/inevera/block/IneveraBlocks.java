package com.ggollmer.inevera.block;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import com.ggollmer.inevera.item.ItemGreatwardGemPiece;
import com.ggollmer.inevera.item.ItemGreatwardMetalPiece;
import com.ggollmer.inevera.item.ItemGreatwardNetherPiece;
import com.ggollmer.inevera.item.ItemGreatwardPreciousPiece;
import com.ggollmer.inevera.item.ItemGreatwardSandPiece;
import com.ggollmer.inevera.item.ItemGreatwardStonePiece;
import com.ggollmer.inevera.item.ItemGreatwardWoodPiece;
import com.ggollmer.inevera.lib.BlockIds;
import com.ggollmer.inevera.lib.BlockNames;
import com.ggollmer.inevera.lib.GreatwardConstants;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;

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
	public static Block greatwardStonePiece;
	public static Block greatwardSandPiece;
	public static Block greatwardNetherPiece;
	public static Block greatwardMetalPiece;
	public static Block greatwardPreciousPiece;
	public static Block greatwardGemPiece;
	
	public static Block minorGreatwardCore;
	
	public static Block demonFossil;
	
	public static void init()
	{
		greatwardWoodPiece = new BlockGreatwardPiece(BlockIds.GREATWARD_WOOD_PIECE, Material.wood, BlockNames.GREATWARD_WOOD_PIECE_NAME, BlockNames.GREATWARD_WOOD_PIECE_SUBNAMES, GreatwardConstants.GREATWARD_WOOD_PIECE_VALUE).setHardness(2.0F).setResistance(5.0F);
		greatwardStonePiece = new BlockGreatwardPiece(BlockIds.GREATWARD_STONE_PIECE, Material.rock, BlockNames.GREATWARD_STONE_PIECE_NAME, BlockNames.GREATWARD_STONE_PIECE_SUBNAMES, GreatwardConstants.GREATWARD_STONE_PIECE_VALUE).setHardness(1.5F).setResistance(10.0F);
		greatwardSandPiece = new BlockGreatwardPiece(BlockIds.GREATWARD_SAND_PIECE, Material.rock, BlockNames.GREATWARD_SAND_PIECE_NAME, BlockNames.GREATWARD_SAND_PIECE_SUBNAMES, GreatwardConstants.GREATWARD_SAND_PIECE_VALUE).setHardness(0.8F).setResistance(6.0F);
		greatwardNetherPiece = new BlockGreatwardPiece(BlockIds.GREATWARD_NETHER_PIECE, Material.rock, BlockNames.GREATWARD_NETHER_PIECE_NAME, BlockNames.GREATWARD_NETHER_PIECE_SUBNAMES, GreatwardConstants.GREATWARD_NETHER_PIECE_VALUE).setHardness(2.0F).setResistance(10.0F);
		greatwardMetalPiece = new BlockGreatwardPiece(BlockIds.GREATWARD_METAL_PIECE, Material.iron, BlockNames.GREATWARD_METAL_PIECE_NAME, BlockNames.GREATWARD_METAL_PIECE_SUBNAMES, GreatwardConstants.GREATWARD_METAL_PIECE_VALUE).setHardness(5.0F).setResistance(10.0F);
		greatwardPreciousPiece = new BlockGreatwardPiece(BlockIds.GREATWARD_PRECIOUS_PIECE, Material.iron, BlockNames.GREATWARD_PRECIOUS_PIECE_NAME, BlockNames.GREATWARD_PRECIOUS_PIECE_SUBNAMES, GreatwardConstants.GREATWARD_PRECIOUS_PIECE_VALUE).setHardness(3.0F).setResistance(10.0F);
		greatwardGemPiece = new BlockGreatwardPiece(BlockIds.GREATWARD_GEM_PIECE, Material.iron, BlockNames.GREATWARD_GEM_PIECE_NAME, BlockNames.GREATWARD_GEM_PIECE_SUBNAMES, GreatwardConstants.GREATWARD_GEM_PIECE_VALUE).setHardness(5.0F).setResistance(10.0F);
		
		minorGreatwardCore = new BlockMinorGreatwardCore(BlockIds.MINOR_WARD_CORE);
		
		demonFossil = new BlockDemonFossil(BlockIds.DEMON_FOSSIL);
		
		GameRegistry.registerBlock(greatwardWoodPiece, ItemGreatwardWoodPiece.class, BlockNames.GREATWARD_WOOD_PIECE_NAME);
		GameRegistry.registerBlock(greatwardStonePiece, ItemGreatwardStonePiece.class, BlockNames.GREATWARD_STONE_PIECE_NAME);
		GameRegistry.registerBlock(greatwardSandPiece, ItemGreatwardSandPiece.class, BlockNames.GREATWARD_SAND_PIECE_NAME);
		GameRegistry.registerBlock(greatwardNetherPiece, ItemGreatwardNetherPiece.class, BlockNames.GREATWARD_NETHER_PIECE_NAME);
		GameRegistry.registerBlock(greatwardMetalPiece, ItemGreatwardMetalPiece.class, BlockNames.GREATWARD_METAL_PIECE_NAME);
		GameRegistry.registerBlock(greatwardPreciousPiece, ItemGreatwardPreciousPiece.class, BlockNames.GREATWARD_PRECIOUS_PIECE_NAME);
		GameRegistry.registerBlock(greatwardGemPiece, ItemGreatwardGemPiece.class, BlockNames.GREATWARD_GEM_PIECE_NAME);
		
		GameRegistry.registerBlock(minorGreatwardCore, BlockNames.MINOR_WARD_CORE_NAME);
		
		GameRegistry.registerBlock(demonFossil, BlockNames.DEMON_FOSSIL_NAME);
		
		MinecraftForge.setBlockHarvestLevel(greatwardNetherPiece, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(greatwardMetalPiece, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(greatwardMetalPiece, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(greatwardGemPiece, "pickaxe", 2);
		
		MinecraftForge.setBlockHarvestLevel(demonFossil, "pickaxe", 1);
	}
	
	public static void registerThaumcraftAspects()
	{
		ThaumcraftApi.registerObjectTag(greatwardWoodPiece.blockID, new int[]{0,8}, (new AspectList()).add(Aspect.TREE, 2).add(Aspect.MAGIC, 1));
		ThaumcraftApi.registerObjectTag(greatwardWoodPiece.blockID, new int[]{1,9}, (new AspectList()).add(Aspect.TREE, 2).add(Aspect.MAGIC, 1));
		ThaumcraftApi.registerObjectTag(greatwardWoodPiece.blockID, new int[]{2,10}, (new AspectList()).add(Aspect.TREE, 2).add(Aspect.MAGIC, 1));
		ThaumcraftApi.registerObjectTag(greatwardWoodPiece.blockID, new int[]{3,11}, (new AspectList()).add(Aspect.TREE, 2).add(Aspect.MAGIC, 1));
		
		ThaumcraftApi.registerObjectTag(greatwardStonePiece.blockID, new int[]{0,8}, (new AspectList()).add(Aspect.EARTH, 4).add(Aspect.FIRE, 4).add(Aspect.MAGIC, 2));
		ThaumcraftApi.registerObjectTag(greatwardStonePiece.blockID, new int[]{1,9}, (new AspectList()).add(Aspect.STONE, 4).add(Aspect.MAGIC, 1));
		ThaumcraftApi.registerObjectTag(greatwardStonePiece.blockID, new int[]{2,10}, (new AspectList()).add(Aspect.STONE, 2).add(Aspect.ORDER, 2).add(Aspect.MAGIC, 1));
		ThaumcraftApi.registerObjectTag(greatwardStonePiece.blockID, new int[]{3,11}, (new AspectList()).add(Aspect.STONE, 2).add(Aspect.PLANT, 2).add(Aspect.MAGIC, 1));
		
		ThaumcraftApi.registerObjectTag(greatwardSandPiece.blockID, new int[]{0,8}, (new AspectList()).add(Aspect.STONE, 1).add(Aspect.ENTROPY, 4).add(Aspect.EARTH, 4).add(Aspect.MAGIC, 1));
		ThaumcraftApi.registerObjectTag(greatwardSandPiece.blockID, new int[]{1,9}, (new AspectList()).add(Aspect.STONE, 1).add(Aspect.ENTROPY, 4).add(Aspect.EARTH, 3).add(Aspect.MAGIC, 2));
		ThaumcraftApi.registerObjectTag(greatwardSandPiece.blockID, new int[]{2,10}, (new AspectList()).add(Aspect.STONE, 1).add(Aspect.ENTROPY, 4).add(Aspect.EARTH, 3).add(Aspect.ORDER, 1).add(Aspect.MAGIC, 1));
		
		ThaumcraftApi.registerObjectTag(greatwardNetherPiece.blockID, new int[]{0,8}, (new AspectList()).add(Aspect.STONE, 3).add(Aspect.FIRE, 2).add(Aspect.MAGIC, 2));
		ThaumcraftApi.registerObjectTag(greatwardNetherPiece.blockID, new int[]{1,9}, (new AspectList()).add(Aspect.CRYSTAL, 5).add(Aspect.ENERGY, 5).add(Aspect.MAGIC, 2));
		ThaumcraftApi.registerObjectTag(greatwardNetherPiece.blockID, new int[]{2,10}, (new AspectList()).add(Aspect.CRYSTAL, 5).add(Aspect.ENERGY, 5).add(Aspect.MAGIC, 2));
		ThaumcraftApi.registerObjectTag(greatwardNetherPiece.blockID, new int[]{3,11}, (new AspectList()).add(Aspect.CRYSTAL, 5).add(Aspect.ENERGY, 5).add(Aspect.MAGIC, 2));
		
		ThaumcraftApi.registerObjectTag(greatwardMetalPiece.blockID, new int[]{0,8}, (new AspectList()).add(Aspect.METAL, 10).add(Aspect.MAGIC, 3));
		ThaumcraftApi.registerObjectTag(greatwardPreciousPiece.blockID, new int[]{0,8}, (new AspectList()).add(Aspect.METAL, 10).add(Aspect.GREED, 10).add(Aspect.MAGIC, 3));
		ThaumcraftApi.registerObjectTag(greatwardGemPiece.blockID, new int[]{0,8}, (new AspectList()).add(Aspect.CRYSTAL, 12).add(Aspect.GREED, 12).add(Aspect.MAGIC, 4));
		ThaumcraftApi.registerObjectTag(greatwardGemPiece.blockID, new int[]{1,9}, (new AspectList()).add(Aspect.CRYSTAL, 12).add(Aspect.EARTH, 13).add(Aspect.MAGIC, 4));
		
		ThaumcraftApi.registerObjectTag(minorGreatwardCore.blockID, new int[]{0,8}, (new AspectList()).add(Aspect.MECHANISM, 3).add(Aspect.TREE, 2).add(Aspect.MAGIC, 4));
		
		ThaumcraftApi.registerObjectTag(demonFossil.blockID, new int[]{0}, (new AspectList()).add(Aspect.TAINT, 1).add(Aspect.HUNGER, 1).add(Aspect.MAGIC, 1));
	}
}
