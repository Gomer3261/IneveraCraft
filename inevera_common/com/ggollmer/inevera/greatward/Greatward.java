package com.ggollmer.inevera.greatward;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
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
	/* State variables */
	protected GreatwardTarget target;
	protected GreatwardAttribute attribute;
	protected GreatwardEffect effect;
	protected List<GreatwardAugment> augments;
	protected List<ChunkCoordinates> greatwardBlocks;
	protected String wardType;
	protected ForgeDirection wardDirection;
	protected ForgeDirection wardOrientation;
	
	/* Operation variables */
	private boolean initialized;
	
	public double centerX;
	public double centerY;
	public double centerZ;
	public double cornerX;
	public double cornerY;
	public double cornerZ;
	
	public double width;
	public double radius;
	public double height;
	
	public float costPerOperationModifier;
	public float coreEnergyDrawRate;
	
	public float currentCoreEnergy;
	public float maxCoreEnergy;
	
	public AxisAlignedBB bounds;
	public List<Entity> entityTargets;
	public List<ChunkCoordinates> blockTargets;
	
	/**
	 * @param target
	 * @param attribute
	 * @param effect
	 * @param augments
	 * @param greatwardBlocks 
	 * @param wardDirection 
	 */
	public Greatward(GreatwardTarget target, GreatwardAttribute attribute,
			GreatwardEffect effect, List<GreatwardAugment> augments, String wardType, ForgeDirection wardDirection, ForgeDirection wardOrientation, List<ChunkCoordinates> greatwardBlocks)
	{
		this.target = target;
		this.attribute = attribute;
		this.effect = effect;
		this.augments = augments;
		this.wardType = wardType;
		this.wardDirection = wardDirection;
		this.wardOrientation = wardOrientation;
		this.greatwardBlocks = greatwardBlocks;
		initialized = false;
	}
	
	/**
	 * An empty greatward constructor, should be followed by an NBTTag read.
	 */
	public Greatward()
	{
		this(null, null, null, null, null, ForgeDirection.UNKNOWN, ForgeDirection.UNKNOWN, null);
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
	 * Used to get the orientation for the greatward.
	 * @return the direction the ward is oriented to.
	 */
	public ForgeDirection getWardOrientation()
	{
		return wardOrientation;
	}
	
	/**
	 * Used to get the list of blocks contained in the greatward.
	 * @return The list of blocks making up the greatward.
	 */
	public List<ChunkCoordinates> getGreatwardBlocks()
	{
		return greatwardBlocks;
	}
	
	public String getGreatwardType()
	{
		return wardType;
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
	 * Used to update the greatward object, causing it to effect the real world.
	 * @param world
	 * @param coreX
	 * @param coreY
	 * @param coreZ
	 */
	public void update(World world, int coreX, int coreY, int coreZ)
	{
		if(!initialized)
		{	
			init(world, coreX, coreY, coreZ);
			target.onGreatwardInit(world, this);
			attribute.onGreatwardInit(world, this);
			effect.onGreatwardInit(world, this);
			
			for(GreatwardAugment augment : augments)
			{
				augment.onGreatwardInit(world, this);
			}
			
			recalculateBounds();
		}
		
		if(attribute.canPerformOperation(world, this))
		{
			entityTargets = target.findValidEntityTargets(world, this);
			blockTargets = target.findValidBlockTargets(world, this);
			
			attribute.performGreatwardEffects(world, this, effect.getEffectMultiplier(world, this));
		}
		
		if(currentCoreEnergy < maxCoreEnergy)
		{
			currentCoreEnergy += coreEnergyDrawRate;
			
			if(currentCoreEnergy > maxCoreEnergy)
			{
				currentCoreEnergy = maxCoreEnergy;
			}
		}
	}
	
	private void init(World world, int coreX, int coreY, int coreZ)
	{
		GreatwardDimensions dim = GreatwardManager.getDimensionsForType(wardType);
		cornerX = dim.getStartX(coreX, wardDirection, wardOrientation);
		cornerY = dim.getStartY(coreY, wardDirection, wardOrientation);
		cornerZ = dim.getStartZ(coreZ, wardDirection, wardOrientation);
		width = dim.getWidth();
		radius = dim.getRadius();
		height = dim.getHeight();
		
		costPerOperationModifier = 1;
		coreEnergyDrawRate = 1;
		
		currentCoreEnergy = 0;
		maxCoreEnergy = 100;
		
		initialized = true;
	}
	
	private void recalculateBounds()
	{
		ForgeDirection wardOri2 = ForgeDirection.getOrientation(ForgeDirection.ROTATION_MATRIX[wardDirection.ordinal()][wardOrientation.ordinal()]);
		double endX = cornerX + height*wardDirection.offsetX + width*wardOrientation.offsetX*-1 + width*wardOri2.offsetX;
		double endY = cornerY + height*wardDirection.offsetY + width*wardOrientation.offsetY*-1 + width*wardOri2.offsetY;
		double endZ = cornerZ + height*wardDirection.offsetZ + width*wardOrientation.offsetZ*-1 + width*wardOri2.offsetZ;
		
		double minX, maxX, minY, maxY, minZ, maxZ;
		
		minX = (cornerX < endX) ? cornerX : endX+1;
		maxX = (cornerX < endX) ? endX : cornerX+1;
		minY = (cornerY < endY) ? cornerY+1 : endY;
		maxY = (cornerY < endY) ? endY+1 : cornerY;
		minZ = (cornerZ < endZ) ? cornerZ : endZ+1;
		maxZ = (cornerZ < endZ) ? endZ : cornerZ+1;
		
		LogHelper.debugLog(String.format("Bounds Calculated: minX: %f minY: %f minZ: %f MaxX: %f MaxY: %f MaxZ: %f", minX, minY, minZ, maxX, maxY, maxZ));
		bounds = AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
		
		centerX = minX + (maxX - minX)/2;
		centerY = minY;
		centerZ = minZ + (maxZ - minZ)/2;
	}
	
	public boolean canTargetBlocks()
	{
		return attribute.canTargetBlocks();
	}
	
	public boolean isValidBlockTarget(int id)
	{
		return attribute.isValidBlockTarget(id);
	}
	
	public boolean isValidEntityTarget(Entity entity)
	{
		return attribute.isValidEntityTarget(entity);
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
		if(!GreatwardManager.isValidType(wardType)) return false;
		if(wardDirection.equals(ForgeDirection.UNKNOWN)) return false;
		if(wardOrientation.equals(ForgeDirection.UNKNOWN)) return false;
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
		
		if(nbtTagCompound.hasKey(GreatwardConstants.GW_NBT_WARD_TYPE_KEY)) {
			wardType = nbtTagCompound.getString(GreatwardConstants.GW_NBT_WARD_TYPE_KEY);
		}
		
		if (nbtTagCompound.hasKey(GreatwardConstants.GW_NBT_DIRECTION_KEY)) {
			wardDirection = ForgeDirection.getOrientation(nbtTagCompound.getByte(GreatwardConstants.GW_NBT_DIRECTION_KEY));
		}
		
		if (nbtTagCompound.hasKey(GreatwardConstants.GW_NBT_ORIENTATION_KEY)) {
			wardOrientation = ForgeDirection.getOrientation(nbtTagCompound.getByte(GreatwardConstants.GW_NBT_ORIENTATION_KEY));
		}
		
		if (nbtTagCompound.hasKey(GreatwardConstants.GW_NBT_COORD_LIST_KEY)) {
            greatwardBlocks = NBTHelper.getChunkCoordinatesListFromNBTTagList(nbtTagCompound.getTagList(GreatwardConstants.GW_NBT_COORD_LIST_KEY));
        }
		
		LogHelper.debugLog(String.format("Loading Greatward From NBT dir: %S, ori: %s, blocks %d, type: %s, target: %s, attribute: %s, effect %s, augments %d", wardDirection.toString(), wardOrientation.toString(), greatwardBlocks.size(), wardType, target.getName(), attribute.getName(), effect.getName(), augments.size()));
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
		
		nbtTagCompound.setString(GreatwardConstants.GW_NBT_WARD_TYPE_KEY, wardType);
		
		nbtTagCompound.setByte(GreatwardConstants.GW_NBT_DIRECTION_KEY, (byte)wardDirection.ordinal());
		
		nbtTagCompound.setByte(GreatwardConstants.GW_NBT_ORIENTATION_KEY, (byte)wardOrientation.ordinal());
		
		nbtTagCompound.setTag(GreatwardConstants.GW_NBT_COORD_LIST_KEY, NBTHelper.createChunkCoordinatesNBTTagList(greatwardBlocks));
	}

	/**
	 * Used to populate a greatward object from a network packet.
	 * @param direction The new greatward direction.
	 * @param blocks The blocks that make up the greatward.
	 * @param wardType The type of ward this greatward is.
	 * @param target The target type of the greatward.
	 * @param attribute The attribute type of the greatward.
	 * @param effect The effect type of the greatward.
	 * @param augments The augments applied to the greatward.
	 */
	public void updateWardFromPacket(byte direction, byte orientation, List<ChunkCoordinates> blocks, String wardType, String target, String attribute, String effect, List<String> augments)
	{
		LogHelper.debugLog(String.format("Recieved Greatward update packet! dir: %S, ori %s, Blocks: %d, type: %s, target: %s, attribute: %s, effect %s, augments %d", ForgeDirection.getOrientation(direction).toString(), ForgeDirection.getOrientation(orientation).toString(), blocks.size(), wardType, target, attribute, effect, augments.size()));
		
		wardDirection = ForgeDirection.getOrientation(direction);
		wardOrientation = ForgeDirection.getOrientation(orientation);
		greatwardBlocks = blocks;
		this.wardType = wardType;
		this.target = GreatwardManager.getTargetByName(target);
		this.attribute = GreatwardManager.getAttributeByName(attribute);
		this.effect = GreatwardManager.getEffectByName(effect);
		
		this.augments = new ArrayList<GreatwardAugment>();
		for(String augment : augments)
		{
			this.augments.add(GreatwardManager.getAugmentByName(augment));
		}
		
		initialized = false;
	}
}
