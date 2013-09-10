package com.ggollmer.inevera.item.crafting;

import com.ggollmer.inevera.block.IneveraBlocks;
import com.ggollmer.inevera.item.IneveraItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
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
		/* Item Recipes */
		GameRegistry.addShapelessRecipe(new ItemStack(IneveraItems.grimoire), IneveraItems.demonBone, Item.book);
		
		/* Greatward Core Recipes */		
		GameRegistry.addRecipe( new ShapedOreRecipe(new ItemStack(IneveraBlocks.minorGreatwardCore), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), Block.planks, Character.valueOf('y'), IneveraItems.demonBone }) );
		
		/* Greatward Piece Recipes */
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardWoodPiece, 8, 0), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.planks, 1, 0), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardWoodPiece, 8, 1), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.planks, 1, 1), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardWoodPiece, 8, 2), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.planks, 1, 2), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardWoodPiece, 8, 3), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.planks, 1, 3), Character.valueOf('y'), IneveraItems.demonFlesh });
		
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardStonePiece, 8, 0), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.brick, 1), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardStonePiece, 8, 1), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.stoneBrick, 1, 0), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardStonePiece, 8, 2), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.stoneBrick, 1, 3), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardStonePiece, 8, 3), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.stoneBrick, 1, 1), Character.valueOf('y'), IneveraItems.demonFlesh });
		
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardSandPiece, 8, 0), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.sandStone, 1, 0), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardSandPiece, 8, 1), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.sandStone, 1, 1), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardSandPiece, 8, 2), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.sandStone, 1, 2), Character.valueOf('y'), IneveraItems.demonFlesh });
		
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardNetherPiece, 8, 0), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.netherBrick, 1), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardNetherPiece, 8, 1), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.blockNetherQuartz, 1, 0), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardNetherPiece, 8, 2), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.blockNetherQuartz, 1, 1), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardNetherPiece, 8, 3), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Block.blockNetherQuartz, 1, 2), Character.valueOf('y'), IneveraItems.demonFlesh });
		
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardMetalPiece, 8, 0), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Item.ingotIron, 1), Character.valueOf('y'), IneveraItems.demonFlesh });
		
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardPreciousPiece, 8, 0), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Item.ingotGold, 1), Character.valueOf('y'), IneveraItems.demonFlesh });
		
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardGemPiece, 8, 0), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Item.diamond, 1), Character.valueOf('y'), IneveraItems.demonFlesh });
		GameRegistry.addRecipe(new ItemStack(IneveraBlocks.greatwardGemPiece, 8, 1), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'),  new ItemStack(Item.emerald, 1), Character.valueOf('y'), IneveraItems.demonFlesh });
	}
}
