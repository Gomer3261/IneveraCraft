package com.ggollmer.inevera.greatward;

import java.util.List;
import java.util.ArrayList;

import com.ggollmer.inevera.block.IneveraBlocks;

public class GreatwardPieceHelper
{
	private static List<Integer> validGreatwardPieces;
	
	public static void init()
	{
		validGreatwardPieces = new ArrayList<Integer>();
		
		validGreatwardPieces.add(IneveraBlocks.greatwardWoodPiece.blockID);
	}
	
	public static boolean isValidGreatwardPiece(int id)
	{
		return validGreatwardPieces.contains(id);
	}
}
