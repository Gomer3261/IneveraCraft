package com.ggollmer.inevera.greatward;

import java.util.List;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import com.ggollmer.inevera.greatward.attribute.GreatwardAttribute;
import com.ggollmer.inevera.greatward.augment.GreatwardAugment;
import com.ggollmer.inevera.greatward.effect.GreatwardEffect;
import com.ggollmer.inevera.greatward.target.GreatwardTarget;

/**
 * IneveraCraft
 *
 * Greatward.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class Greatward
{
	protected GreatwardTarget target;
	protected GreatwardAttribute attribute;
	protected GreatwardEffect effect;
	protected List<GreatwardAugment> augments;
	protected List<ChunkCoordinates> greatwardBlocks;
	
	/**
	 * @param target
	 * @param attribute
	 * @param effect
	 * @param augments
	 * @param greatwardBlocks 
	 * @param wardDirection 
	 */
	public Greatward(GreatwardTarget target, GreatwardAttribute attribute,
			GreatwardEffect effect, List<GreatwardAugment> augments, List<ChunkCoordinates> greatwardBlocks)
	{
		this.target = target;
		this.attribute = attribute;
		this.effect = effect;
		this.augments = augments;
		this.greatwardBlocks = greatwardBlocks;
	}
	
	public List<ChunkCoordinates> getGreatwardBlocks()
	{
		return greatwardBlocks;
	}
	
	public void update(World world)
	{
		
	}
}
