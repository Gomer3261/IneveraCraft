package com.ggollmer.inevera.greatward.target;

import com.ggollmer.inevera.greatward.Greatward;
import com.ggollmer.inevera.lib.GreatwardConstants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * IneveraCraft
 *
 * GreatwardTargetPlayer.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GreatwardTargetPlayer extends GreatwardTarget
{

	/**
	 * @param name The name of the unique greatward component
	 */ 
	public GreatwardTargetPlayer(String name)
	{
		super(GreatwardConstants.GW_TARGET_PLAYER_LOCATION, name);
	}
	
	@Override
	public void onGreatwardInit(World world, Greatward greatward)
	{
	}
	
	@Override
	public void renderAmbientParticles(World world, Greatward greatward)
	{
	}

	@Override
	public boolean isValidEntityTarget(Entity entity)
	{
		return (entity instanceof EntityPlayer);
	}

	@Override
	public boolean isValidBlockTarget(int id, int metadata)
	{
		return false;
	}
}
