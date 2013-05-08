package com.ggollmer.inevera.greatward;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Level;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.ggollmer.inevera.core.helper.LogHelper;

/**
 * IneveraCraft
 *
 * GreatwardComponent.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public abstract class GreatwardComponent
{
	protected GreatwardMap pattern;
	
	public GreatwardComponent(int dimx, int dimy, String patternPath)
	{
		pattern = this.loadGreatwardMap(dimx, dimy, patternPath);
	}
	
	protected GreatwardMap loadGreatwardMap(int dimx, int dimy, String path)
	{		
		char[][] charMap = new char[dimx][dimy];
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
		    String line = null;
		    int x;
		    int y = 0;
		    while ((line = reader.readLine()) != null) {
		        for(x=0; x < dimx; x++)
		        {
		        	 char in = line.charAt(x+2);
		        	 switch(in)
		        	 {
		        		 case GreatwardMap.GW_NEGATIVE_TILE:
		        			 charMap[x][y] = GreatwardMap.GW_NEGATIVE_TILE;
		        			 break;
		        		 case GreatwardMap.GW_POSITIVE_TILE:
		        			 charMap[x][y] = GreatwardMap.GW_POSITIVE_TILE;
		        			 break;
		        		 default:
		        			 charMap[x][y] = GreatwardMap.GW_EMPTY_TILE;
		        			 break;
		        	 }
		        	 
		        }
		        y++;
		    }
		    reader.close();
		} catch (Exception x) {
		    LogHelper.log(Level.SEVERE, "IneveraCraft: Greatward map: " + path + " could not be found.");
		}
		
		return new GreatwardMap(dimx, dimy, charMap);
	}
	
	public boolean findPattern(int px, int py, int pz, ForgeDirection dir, World world)
	{
		
		
		return false;
	}
}
