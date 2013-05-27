package com.ggollmer.inevera.greatward;

import java.util.List;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;


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
	public boolean findPattern(World world, String wardType, int coreX, int coreY, int coreZ, ForgeDirection ey, ForgeDirection ez, int id, int meta, List<ChunkCoordinates> greatwardBlocks)
	{
		if(this.hasGreatwardMap(wardType))
		{			
			ForgeDirection ex = ForgeDirection.getOrientation(ForgeDirection.ROTATION_MATRIX[ez.ordinal()][ey.ordinal()]);
			
			int sx = GreatwardManager.getDimensionsForType(wardType).getStartX(coreX, ez, ey);
			int sy = GreatwardManager.getDimensionsForType(wardType).getStartY(coreY, ez, ey);
			int sz = GreatwardManager.getDimensionsForType(wardType).getStartZ(coreZ, ez, ey);
			
			return areaMatchesPattern(world, wardType, id, meta, sx, sy, sz, ex, ey, ez, greatwardBlocks, true);
		}
		return false;
	}
}
