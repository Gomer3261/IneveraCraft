package com.ggollmer.inevera.greatward;

import java.util.List;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.ggollmer.inevera.core.helper.LogHelper;
import com.ggollmer.inevera.lib.GreatwardConstants;


/**
 * IneveraCraft
 *
 * IGreatwardAttribute.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public abstract class GreatwardFullComponent extends GreatwardComponent
{
	public static final int MINOR_TYPE_OFFSET_X = -2;
	public static final int MINOR_TYPE_OFFSET_Y = -2;
	
	public static final int NORMAL_TYPE_OFFSET_X = -2;
	public static final int NORMAL_TYPE_OFFSET_Y = -2;
	
	public static final int MAJOR_TYPE_OFFSET_X = -2;
	public static final int MAJOR_TYPE_OFFSET_Y = -2;
	

	/**
	 * @param name The name of the greatward component.
	 */
	public GreatwardFullComponent(String name)
	{
		super(name);
	}

	/**
	 * Used to detect if the attribute's ward pattern exists provided the given constraints.
	 * @param world The world to look for the pattern within.
	 * @param wardType The type of ward to look for.
	 * @param coreX The x coordinate of the ward core.
	 * @param coreY The y coordinate of the ward core.
	 * @param coreZ The z coordinate of the ward core.
	 * @param ex The direction that x movements in the ward map should map to.
	 * @param ez The direction that the ward should face.
	 * @param id The id of the ward piece to look for.
	 * @param meta The metadata of the ward piece to look for.
	 * @param greatwardBlocks The list of blocks containing all pieces of the greatward.
	 * @return True if the pattern is found.
	 */
	public boolean findPattern(World world, String wardType, int coreX, int coreY, int coreZ, ForgeDirection ex, ForgeDirection ez, int id, int meta, List<ChunkCoordinates> greatwardBlocks)
	{
		if(this.hasGreatwardMap(wardType))
		{
			int px = coreX + ez.offsetX;
			int py = coreY + ez.offsetY;
			int pz = coreZ + ez.offsetZ;
			
			ForgeDirection ey = ForgeDirection.getOrientation(ForgeDirection.ROTATION_MATRIX[ez.ordinal()][ex.ordinal()]);
			
			int sx = px + ex.offsetX*getXOffsetMultiplier(wardType) + ey.offsetX*getYOffsetMultiplier(wardType);
			int sy = py + ex.offsetY*getXOffsetMultiplier(wardType) + ey.offsetY*getYOffsetMultiplier(wardType);
			int sz = pz + ex.offsetZ*getXOffsetMultiplier(wardType) + ey.offsetZ*getYOffsetMultiplier(wardType);
			
			LogHelper.debugLog(String.format("Looking for greatward attribute: x: %d, y: %d, z: %d, dir %s", sx, sy, sz, ex.toString()));
			return areaMatchesPattern(world, wardType, id, meta, sx, sy, sz, ex, ey, ez, greatwardBlocks, true);
		}
		return false;
	}
	
	public int getXOffsetMultiplier(String wardType)
	{
		if(wardType == GreatwardConstants.GW_MINOR_TYPE)
		{
			return GreatwardFullComponent.MINOR_TYPE_OFFSET_X;
		}
		else if(wardType == GreatwardConstants.GW_NORMAL_TYPE)
		{
			return GreatwardFullComponent.NORMAL_TYPE_OFFSET_X;
		}
		else if(wardType == GreatwardConstants.GW_MAJOR_TYPE)
		{
			return GreatwardFullComponent.MAJOR_TYPE_OFFSET_X;
		}
		
		return 0;
	}
	
	public int getYOffsetMultiplier(String wardType)
	{
		if(wardType == GreatwardConstants.GW_MINOR_TYPE)
		{
			return GreatwardFullComponent.MINOR_TYPE_OFFSET_Y;
		}
		else if(wardType == GreatwardConstants.GW_NORMAL_TYPE)
		{
			return GreatwardFullComponent.NORMAL_TYPE_OFFSET_Y;
		}
		else if(wardType == GreatwardConstants.GW_MAJOR_TYPE)
		{
			return GreatwardFullComponent.MAJOR_TYPE_OFFSET_Y;
		}
		
		return 0;
	}
}
