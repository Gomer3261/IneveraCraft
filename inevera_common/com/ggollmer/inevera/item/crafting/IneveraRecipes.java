package com.ggollmer.inevera.item.crafting;

import com.ggollmer.inevera.block.IneveraBlocks;
import com.ggollmer.inevera.item.IneveraItems;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * IneveraCraft
 *
 * IneveraRecipies.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class IneveraRecipes
{
	public static void init()
	{
		/* Greatward Core Recipes */
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.minorGreatwardCore, 1), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.planks, 1), Character.valueOf('y'), IneveraItems.demonBone });
		
		/* Greatward Piece Recipes */
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardWoodPiece, 1, 0), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.planks, 1, 0), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardWoodPiece, 1, 1), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.planks, 1, 1), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardWoodPiece, 1, 2), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.planks, 1, 2), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardWoodPiece, 1, 3), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.planks, 1, 3), Character.valueOf('y'), IneveraItems.demonFlesh });
		
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardStonePiece, 1, 0), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.brick, 1), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardStonePiece, 1, 1), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.stoneBrick, 1, 0), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardStonePiece, 1, 2), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.stoneBrick, 1, 2), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardStonePiece, 1, 3), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.stoneBrick, 1, 3), Character.valueOf('y'), IneveraItems.demonFlesh });
		
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardSandPiece, 1, 0), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.sandStone, 1, 0), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardSandPiece, 1, 1), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.sandStone, 1, 1), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardSandPiece, 1, 2), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.sandStone, 1, 2), Character.valueOf('y'), IneveraItems.demonFlesh });
		
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardNetherPiece, 1, 0), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.netherBrick, 1), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardNetherPiece, 1, 1), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.blockNetherQuartz, 1, 0), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardNetherPiece, 1, 2), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.blockNetherQuartz, 1, 1), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardNetherPiece, 1, 3), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.blockNetherQuartz, 1, 2), Character.valueOf('y'), IneveraItems.demonFlesh });
		
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardMetalPiece, 1, 0), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.blockIron, 1), Character.valueOf('y'), IneveraItems.demonFlesh });
		
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardPreciousPiece, 1, 0), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.blockGold, 1), Character.valueOf('y'), IneveraItems.demonFlesh });
		
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardGemPiece, 1, 0), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.blockDiamond, 1), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardGemPiece, 1, 1), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.blockEmerald, 1), Character.valueOf('y'), IneveraItems.demonFlesh });
	}
}
