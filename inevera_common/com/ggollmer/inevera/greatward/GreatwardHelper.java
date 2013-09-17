package com.ggollmer.inevera.greatward;

import java.util.ArrayList;
import java.util.List;

import com.ggollmer.inevera.block.IneveraBlocks;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class GreatwardHelper
{
	private static boolean[] clearBlocks = new boolean[4096];
	private static List<Integer> validGreatwardPieces;
	
	public static void init()
	{
		validGreatwardPieces = new ArrayList<Integer>();
		
		for(int i=0; i<clearBlocks.length; i++)
		{
			clearBlocks[i] = false;
		}
		
		addValidGreatwardPiece(IneveraBlocks.greatwardWoodPiece.blockID);
		addValidGreatwardPiece(IneveraBlocks.greatwardStonePiece.blockID);
		addValidGreatwardPiece(IneveraBlocks.greatwardSandPiece.blockID);
		addValidGreatwardPiece(IneveraBlocks.greatwardNetherPiece.blockID);
		addValidGreatwardPiece(IneveraBlocks.greatwardMetalPiece.blockID);
		addValidGreatwardPiece(IneveraBlocks.greatwardPreciousPiece.blockID);
		addValidGreatwardPiece(IneveraBlocks.greatwardGemPiece.blockID);
		
		addClearBlock(0);
		addClearBlock(Block.glass.blockID);
	}
	
	/**
	 * Used to check if a block is a valid greatward piece.
	 * @param id The id of the block to be checked.
	 * @return True of the block is a valid greatward piece.
	 */
	public static boolean isValidGreatwardPiece(int id)
	{
		return validGreatwardPieces.contains(id);
	}
	
	/**
	 * Used to register new valid greatward pieces.
	 * @param id The id to register as a valid greatward piece.
	 */
	public static void addValidGreatwardPiece(int id)
	{
		validGreatwardPieces.add(id);
	}
	
	/**
	 * Used to check if a block will prevent a greatward from working.
	 * @param blockId The id of the block above a greatward piece.
	 * @return true if the block will allow the greatward to work.
	 */
	public static boolean isClearBlock(int blockId)
	{
		return clearBlocks[blockId];
	}
	
	/**
	 * Used to check if a block in the world is clear, takes into account blocks that should be recognized as air.
	 * @param world The world that the block exists within.
	 * @param x The x coordinate of the block.
	 * @param y The y coordinate of the block.
	 * @param z The z coordinate of the block.
	 * @return True if the block will allow the ward to be active.
	 */
	public static boolean isClearBlock(World world, int x, int y, int z)
	{
		int id = world.getBlockId(x, y, z);
		if(GreatwardHelper.isClearBlock(id) || Block.blocksList[id].isAirBlock(world, x, y, z))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Used to register a block as something that doesn't prevent greatwards from operating.
	 * @param id The id to register as a clear block.
	 */
	public static void addClearBlock(int id)
	{
		clearBlocks[id] = true;
	}
}
