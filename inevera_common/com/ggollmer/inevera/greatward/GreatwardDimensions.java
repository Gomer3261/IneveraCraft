package com.ggollmer.inevera.greatward;

import net.minecraftforge.common.ForgeDirection;

/**
 * IneveraCraft
 *
 * WardDimensions.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GreatwardDimensions
{
	private int width;
	private int cornerOffsetX;
	private int cornerOffsetY;
	private int projectionLength;
	
	public GreatwardDimensions(int width, int offX, int offY, int proj)
	{
		this.width = width;
		this.cornerOffsetX = offX;
		this.cornerOffsetY = offY;
		this.projectionLength = proj;
	}
	
	public float getRadius()
	{
		return ((float)width)/2.0f;
	}
	
	public int getHeight()
	{
		return projectionLength;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getStartX(int coreX, ForgeDirection wardDir, ForgeDirection wardOri, ForgeDirection ex)
	{
		return coreX + ex.offsetX*cornerOffsetX + wardOri.offsetX*cornerOffsetY + wardDir.offsetX;
	}
	
	public int getStartY(int coreY, ForgeDirection wardDir, ForgeDirection wardOri, ForgeDirection ex)
	{
		return coreY + ex.offsetY*cornerOffsetX + wardOri.offsetY*cornerOffsetY + wardDir.offsetY;
	}
	
	public int getStartZ(int coreZ, ForgeDirection wardDir, ForgeDirection wardOri, ForgeDirection ex)
	{
		return coreZ + ex.offsetZ*cornerOffsetX + wardOri.offsetZ*cornerOffsetY + wardDir.offsetZ;
	}
}
