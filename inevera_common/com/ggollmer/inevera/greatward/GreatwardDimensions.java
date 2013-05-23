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
	
	public double getCenterX(int coreX, ForgeDirection wardDir, ForgeDirection wardOri)
	{
		return getStartX(coreX, wardDir, wardOri) + (width/2 + 1)*(1 - wardOri.offsetX);
	}
	
	public double getCenterY(int coreY, ForgeDirection wardDir, ForgeDirection wardOri)
	{
		return getStartY(coreY, wardDir, wardOri) + (width/2 + 1)*(1 - wardOri.offsetY);
	}
	
	public double getCenterZ(int coreZ, ForgeDirection wardDir,  ForgeDirection wardOri)
	{
		return getStartZ(coreZ, wardDir, wardOri) + (width/2 + 1)*(1 - wardOri.offsetZ);
	}
	
	public int getStartX(int coreX, ForgeDirection wardDir, ForgeDirection wardOri)
	{
		ForgeDirection ex = ForgeDirection.getOrientation(ForgeDirection.ROTATION_MATRIX[wardDir.ordinal()][wardOri.ordinal()]);
		return coreX + ex.offsetX*cornerOffsetX + wardOri.offsetX*cornerOffsetY + wardDir.offsetX;
	}
	
	public int getStartY(int coreY, ForgeDirection wardDir, ForgeDirection wardOri)
	{
		ForgeDirection ex = ForgeDirection.getOrientation(ForgeDirection.ROTATION_MATRIX[wardDir.ordinal()][wardOri.ordinal()]);
		return coreY + ex.offsetY*cornerOffsetX + wardOri.offsetY*cornerOffsetY + wardDir.offsetY;
	}
	
	public int getStartZ(int coreZ, ForgeDirection wardDir, ForgeDirection wardOri)
	{
		ForgeDirection ex = ForgeDirection.getOrientation(ForgeDirection.ROTATION_MATRIX[wardDir.ordinal()][wardOri.ordinal()]);
		return coreZ + ex.offsetZ*cornerOffsetX + wardOri.offsetZ*cornerOffsetY + wardDir.offsetZ;
	}
}
