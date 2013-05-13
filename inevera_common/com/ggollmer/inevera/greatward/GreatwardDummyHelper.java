package com.ggollmer.inevera.greatward;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.ggollmer.inevera.block.BlockGreatwardDummy;
import com.ggollmer.inevera.block.IneveraBlocks;

/**
 * IneveraCraft
 *
 * GreatwardDummyHelper.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GreatwardDummyHelper
{
	private static Map<Material, BlockGreatwardDummy> materialMap = new HashMap<Material, BlockGreatwardDummy>();
	private static Map<Integer, BlockGreatwardDummy> specialIds = new HashMap<Integer, BlockGreatwardDummy>();
	
	public static void init()
	{
		GreatwardDummyHelper.addDummyBlock(Material.ground, (BlockGreatwardDummy)IneveraBlocks.greatwardDummyGround);
		GreatwardDummyHelper.addDummyBlock(Material.wood, (BlockGreatwardDummy)IneveraBlocks.greatwardDummyWood);
		GreatwardDummyHelper.addDummyBlock(Material.rock, (BlockGreatwardDummy)IneveraBlocks.greatwardDummyRock);
		GreatwardDummyHelper.addDummyBlock(Material.iron, (BlockGreatwardDummy)IneveraBlocks.greatwardDummyIron);
		GreatwardDummyHelper.addDummyBlock(Material.cloth, (BlockGreatwardDummy)IneveraBlocks.greatwardDummyCloth);
		GreatwardDummyHelper.addDummyBlock(Material.sand, (BlockGreatwardDummy)IneveraBlocks.greatwardDummySand);
		GreatwardDummyHelper.addDummyBlock(Material.glass, (BlockGreatwardDummy)IneveraBlocks.greatwardDummyGlass);
		
		GreatwardDummyHelper.addSpecialGreatwardComponent(Block.glass.blockID, (BlockGreatwardDummy)IneveraBlocks.greatwardDummyGlass);
	}
	
	/**
	 * Used to add a new dummy block type based on material.
	 * @param material The material defining the dummy.
	 * @param dummy The dummy block to be used when a block of the provided material is replaced.
	 */
	public static void addDummyBlock(Material material, BlockGreatwardDummy dummy)
	{
		materialMap.put(material, dummy);
	}
	
	/**
	 * Used to add special cases for dummiable blocks.
	 * @param id The Id to be
	 */
	public static void addSpecialGreatwardComponent(int id, BlockGreatwardDummy dummy)
	{
		specialIds.put(id, dummy);
	}
	
	/**
	 * Used to check if a block id/meta can be replicated by a dummy block.
	 * @param Id the block to check.
	 * @param Metadata The metadata of the id being checked.
	 * @return True if the block can be replicated by a dummy.
	 */
	public static boolean isBlockDummiable(int id, int metadata)
	{
		if(specialIds.get(id) != null)
		{
			return true;
		}
		
		Block testBlock = Block.blocksList[id];
		
		if(testBlock == null)
		{
			return false;
		}
		
		if(testBlock.hasTileEntity(metadata))
		{
			return false;
		}
		
		if(!testBlock.renderAsNormalBlock() || testBlock.canProvidePower())
		{
			return false;
		}
		
		if(materialMap.get(testBlock.blockMaterial) == null)
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * Used to get the correct dummy class to use
	 * @param imitatingId the id of the block to be imitated.
	 * @return the correct instance of BlockGreatwardDummy to use.
	 */
	public static BlockGreatwardDummy getProperDummy(int imitatingId)
	{
		if(specialIds.get(imitatingId) != null)
		{
			return specialIds.get(imitatingId);
		}
		else
		{
			Block testBlock = Block.blocksList[imitatingId];
			return materialMap.get(testBlock.blockMaterial);
		}
	}
}
