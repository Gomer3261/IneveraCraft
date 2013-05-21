package com.ggollmer.inevera.greatward;

/**
 * IneveraCraft
 *
 * GreatwardMap.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GreatwardMap
{
	public static final char GW_EMPTY_TILE = '-';
	public static final char GW_NEGATIVE_TILE = 'x';
	public static final char GW_POSITIVE_TILE = 'o';
	
	
	private char[][] map;
	private int dimx;
	private int dimy;
	
	public GreatwardMap(int dimx, int dimy, char[][] map)
	{
		this.dimx = dimx;
		this.dimy = dimy;
		this.map = map;
	}
	
	public int getWidth()
	{
		return dimx;
	}
	
	public int getHeight()
	{
		return dimy;
	}
	
	public char getValue(int x, int y)
	{
		return map[x][y];
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append('\n');
		
		for(int j=0; j<dimy; j++)
		{
			for(int i=0; i<dimx; i++)
			{
				builder.append(map[i][j]);
				builder.append(' ');
			}
			builder.append('\n');
		}
		
		return builder.toString();
	}
}
