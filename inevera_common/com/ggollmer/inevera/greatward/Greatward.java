package com.ggollmer.inevera.greatward;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.ggollmer.inevera.core.helper.LogHelper;
import com.ggollmer.inevera.core.helper.NBTHelper;
import com.ggollmer.inevera.greatward.attribute.GreatwardAttribute;
import com.ggollmer.inevera.greatward.augment.GreatwardAugment;
import com.ggollmer.inevera.greatward.effect.GreatwardEffect;
import com.ggollmer.inevera.greatward.target.GreatwardTarget;
import com.ggollmer.inevera.lib.GreatwardConstants;

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
	protected ForgeDirection wardDirection;
	
	/**
	 * @param target
	 * @param attribute
	 * @param effect
	 * @param augments
	 * @param greatwardBlocks 
	 * @param wardDirection 
	 */
	public Greatward(GreatwardTarget target, GreatwardAttribute attribute,
			GreatwardEffect effect, List<GreatwardAugment> augments, ForgeDirection wardDirection, List<ChunkCoordinates> greatwardBlocks)
	{
		this.target = target;
		this.attribute = attribute;
		this.effect = effect;
		this.augments = augments;
		this.wardDirection = wardDirection;
		this.greatwardBlocks = greatwardBlocks;
	}
	
	/**
	 * An empty greatward constructor, should be followed by an NBTTag read.
	 */
	public Greatward()
	{
		this(null, null, null, null, ForgeDirection.UNKNOWN, null);
	}
	
	/**
	 * Used to get the direction of the greatward.
	 * @return the direction the ward affects.
	 */
	public ForgeDirection getWardDirection()
	{
		return wardDirection;
	}
	
	/**
	 * Used to get the list of blocks contained in the greatward.
	 * @return The list of blocks making up the greatward.
	 */
	public List<ChunkCoordinates> getGreatwardBlocks()
	{
		return greatwardBlocks;
	}
	
	public String getTargetName()
	{
		return target.getName();
	}
	
	public String getAttributeName()
	{
		return attribute.getName();
	}
	
	public String getEffectName()
	{
		return effect.getName();
	}
	
	public List<String> getAugmentNames()
	{
		List<String> names = new ArrayList<String>();
		
		for(GreatwardAugment a : augments)
		{
			names.add(a.getName());
		}
		
		return names;
	}
	
	/**
	 * TODO: DO THIS THING
	 * @param world
	 */
	public void update(World world)
	{
		
	}
	
	/**
	 * Used to check if a greatward has been assembled correctly from NBT or the greatward manager.
	 * @return True if the greatward has been assembled correctly.
	 */
	public boolean isValidGreatward()
	{
		if(target == null) return false;
		if(attribute == null) return false;
		if(effect == null) return false;
		if(augments == null) return false;
		if(wardDirection.equals(ForgeDirection.UNKNOWN)) return false;
		if(greatwardBlocks == null) return false;
		if(greatwardBlocks.isEmpty()) return false;
		return true;
	}
	
	/**
	 * Reads greatward data from NBT restoring the greatward from a saved file.
	 * @param nbtTagCompound The compound to read from.
	 */
	public void readFromNBT(NBTTagCompound nbtTagCompound)
	{
		if (nbtTagCompound.hasKey(GreatwardConstants.GW_NBT_TARGET_KEY)) {
			target = GreatwardManager.getTargetByName(nbtTagCompound.getString(GreatwardConstants.GW_NBT_TARGET_KEY));
		}
		
		if (nbtTagCompound.hasKey(GreatwardConstants.GW_NBT_ATTRIBUTE_KEY)) {
			attribute = GreatwardManager.getAttributeByName(nbtTagCompound.getString(GreatwardConstants.GW_NBT_ATTRIBUTE_KEY));
		}
		
		if (nbtTagCompound.hasKey(GreatwardConstants.GW_NBT_EFFECT_KEY)) {
			effect = GreatwardManager.getEffectByName(nbtTagCompound.getString(GreatwardConstants.GW_NBT_EFFECT_KEY));
		}
		
		augments = new ArrayList<GreatwardAugment>();
		if (nbtTagCompound.hasKey(GreatwardConstants.GW_NBT_AUGMENTS_KEY)) {
			for(String str: NBTHelper.getStringListFromNBTTagList(nbtTagCompound.getTagList(GreatwardConstants.GW_NBT_AUGMENTS_KEY)))
			{
				augments.add(GreatwardManager.getAugmentByName(str));
			}
		}
		
		if (nbtTagCompound.hasKey(GreatwardConstants.GW_NBT_DIRECTION_KEY)) {
			wardDirection = ForgeDirection.getOrientation(nbtTagCompound.getByte(GreatwardConstants.GW_NBT_DIRECTION_KEY));
		}
		
		if (nbtTagCompound.hasKey(GreatwardConstants.GW_NBT_COORD_LIST_KEY)) {
            greatwardBlocks = NBTHelper.getChunkCoordinatesListFromNBTTagList(nbtTagCompound.getTagList(GreatwardConstants.GW_NBT_COORD_LIST_KEY));
        }
		
		LogHelper.debugLog(String.format("Loading Greatward From NBT dir: %S, blocks %d, target: %s, attribute: %s, effect %s, augments %d", wardDirection.toString(), greatwardBlocks.size(), target.getName(), attribute.getName(), effect.getName(), augments.size()));
	}
	
	/**
	 * Saves the greatward to an NBT compound so it can be restored at a later date.
	 * @param nbtTagCompound The compound to save to.
	 */
	public void writeToNBT(NBTTagCompound nbtTagCompound)
	{
		nbtTagCompound.setString(GreatwardConstants.GW_NBT_TARGET_KEY, target.getName());
		nbtTagCompound.setString(GreatwardConstants.GW_NBT_ATTRIBUTE_KEY, attribute.getName());
		nbtTagCompound.setString(GreatwardConstants.GW_NBT_EFFECT_KEY, effect.getName());
		
		List<String> augmentNames = new ArrayList<String>();
		if(augments != null)
		{
			for(GreatwardAugment augment : augments)
			{
				augmentNames.add(augment.getName());
			}
		}
		nbtTagCompound.setTag(GreatwardConstants.GW_NBT_AUGMENTS_KEY, NBTHelper.createObjectNBTTagListFromToString(GreatwardConstants.GW_NBT_AUGMENTS_KEY, augmentNames));
		
		nbtTagCompound.setByte(GreatwardConstants.GW_NBT_DIRECTION_KEY, (byte)wardDirection.ordinal());
		
		nbtTagCompound.setTag(GreatwardConstants.GW_NBT_COORD_LIST_KEY, NBTHelper.createChunkCoordinatesNBTTagList(greatwardBlocks));
	}

	/**
	 * Used to populate a greatward object from a network packet.
	 * @param direction The new greatward direction.
	 * @param blocks The blocks that make up the greatward.
	 * @param target The target type of the greatward.
	 * @param attribute The attribute type of the greatward.
	 * @param effect The effect type of the greatward.
	 * @param augments The augments applied to the greatward.
	 */
	public void updateWardFromPacket(byte direction, List<ChunkCoordinates> blocks, String target, String attribute, String effect, List<String> augments)
	{
		LogHelper.debugLog(String.format("Recieved Greatward update packet! dir: %S, Blocks: %d,  target: %s, attribute: %s, effect %s, augments %d", ForgeDirection.getOrientation(direction).toString(), blocks.size(), target, attribute, effect, augments.size()));
		
		wardDirection = ForgeDirection.getOrientation(direction);
		greatwardBlocks = blocks;
		this.target = GreatwardManager.getTargetByName(target);
		this.attribute = GreatwardManager.getAttributeByName(attribute);
		this.effect = GreatwardManager.getEffectByName(effect);
		
		this.augments = new ArrayList<GreatwardAugment>();
		for(String augment : augments)
		{
			this.augments.add(GreatwardManager.getAugmentByName(augment));
		}
	}
}
