package com.ggollmer.inevera.greatward;

import java.util.ArrayList;
import java.util.List;

import com.ggollmer.inevera.block.IneveraBlocks;

import net.minecraft.block.Block;

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