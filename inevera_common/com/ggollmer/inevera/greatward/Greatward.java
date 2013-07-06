package com.ggollmer.inevera.greatward;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.ggollmer.inevera.block.BlockGreatwardPiece;
import com.ggollmer.inevera.core.helper.LogHelper;
import com.ggollmer.inevera.core.helper.NBTHelper;
import com.ggollmer.inevera.greatward.attribute.GreatwardAttribute;
import com.ggollmer.inevera.greatward.augment.GreatwardAugment;
import com.ggollmer.inevera.greatward.effect.GreatwardEffect;
import com.ggollmer.inevera.greatward.target.GreatwardTarget;
import com.ggollmer.inevera.lib.GreatwardConstants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
	/**
	 * A map to store extra state information in (for complex ward components).
	 * Note: these states are not saved in NBT, if you require something extra saved in NBT contact gomer3261.
	 */
	public Map<String, Object> stateStorage;
	
	/* State variables */
	protected GreatwardTarget target;
	protected GreatwardAttribute attribute;
	protected GreatwardEffect effect;
	protected List<GreatwardAugment> augments;
	protected List<ChunkCoordinates> greatwardBlocks;
	protected int blockType;
	protected String wardType;
	protected ForgeDirection wardDirection;
	protected ForgeDirection wardOrientation;
	protected ForgeDirection wardOriright;
	
	/* Operation variables */
	private boolean initialized;
	
	/* Bounds center */
	public double centerX;
	public double centerY;
	public double centerZ;
	
	/* Block locations, not bounds corner*/
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
	
	public int wardPieceMultiplier;
	
	public int operationTimer;
	
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
			GreatwardEffect effect, List<GreatwardAugment> augments, String wardType, ForgeDirection wardDirection, ForgeDirection wardOrientation, List<ChunkCoordinates> greatwardBlocks, int blockId)
	{
		this.target = target;
		this.attribute = attribute;
		this.effect = effect;
		this.augments = augments;
		this.wardType = wardType;
		this.wardDirection = wardDirection;
		this.wardOrientation = wardOrientation;
		this.wardOriright = ForgeDirection.getOrientation(ForgeDirection.ROTATION_MATRIX[wardOrientation.ordinal()][wardDirection.ordinal()]);
		this.greatwardBlocks = (greatwardBlocks==null) ? new ArrayList<ChunkCoordinates>() : greatwardBlocks;
		this.blockType = blockId;
		stateStorage = new HashMap<String, Object>();
		initialized = false;
	}
	
	/**
	 * An empty greatward constructor. Used to load greatwards from NBT or Packets.
	 * Greatward location information will be fixed in the update loop.
	 */
	public Greatward()
	{
		this(null, null, null, null, null, ForgeDirection.UNKNOWN, ForgeDirection.UNKNOWN, null, -1);
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
	 * Used to get the final direction in this ward's orientation matrix.
	 * @return A final direction for the ward. (Right angle to the other 2 directions)
	 */
	public ForgeDirection getWardOriright()
	{
		return wardOriright;
	}
	
	/**
	 * Used to get the list of blocks contained in the greatward.
	 * @return The list of blocks making up the greatward.
	 */
	public List<ChunkCoordinates> getGreatwardBlocks()
	{
		return greatwardBlocks;
	}
	
	/**
	 * Used to get the type of block that the ward is constructed with.
	 * @return The block type that the ward consists of.
	 */
	public int getBlockType()
	{
		return blockType;
	}
	
	/**
	 * Used to get the type of the greatward (as a string)
	 * @return The type of the greatward.
	 */
	public String getGreatwardType()
	{
		return wardType;
	}
	
	/**
	 * Used to set a delay on the operation of the greatward (to prevent spamming)
	 * @param delay The delay in ticks to add to the greatward.
	 */
	public void addOperationDelay(int delay)
	{
		operationTimer += delay;
	}
	
	/**
	 * Used to get the target type as a string.
	 * @return The target type.
	 */
	public String getTargetName()
	{
		return target.getName();
	}
	
	/**
	 * Used to get the attribute type as a string.
	 * @return The attribute type.
	 */
	public String getAttributeName()
	{
		return attribute.getName();
	}
	
	/**
	 * Used to get the effect type as a string.
	 * @return The effect type.
	 */
	public String getEffectName()
	{
		return effect.getName();
	}
	
	/**
	 * Used to get the augment types as a string.
	 * @return The augment types.
	 */
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
	 * Used to get the effect multiplier in different locations.
	 * @param world The world that the greatward exists in.
	 * @return The multiplier for the greatward.
	 */
	public float getEffectMultiplier(World world)
	{
		return effect.getEffectMultiplier(world, this);
	}
	
	/**
	 * Used to render ambient ward particles within the ward's bounds.
	 */
	@SideOnly(Side.CLIENT)
	public void renderAmbientParticles(World world)
	{
		target.renderAmbientParticles(world, this);
		attribute.renderAmbientParticles(world, this);
		effect.renderAmbientParticles(world, this);
		
		for(GreatwardAugment augment : augments)
		{
			augment.renderAmbientParticles(world, this);
		}
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
		
		if(attribute.canPerformOperation(world, this) && operationTimer <= 0)
		{
			entityTargets = target.findValidEntityTargets(world, this);
			blockTargets = target.findValidBlockTargets(world, this);
			
			attribute.performGreatwardEffects(world, this);
		}
		
		if(currentCoreEnergy < maxCoreEnergy)
		{
			currentCoreEnergy += coreEnergyDrawRate;
			
			if(currentCoreEnergy > maxCoreEnergy)
			{
				currentCoreEnergy = maxCoreEnergy;
			}
		}
		
		if(operationTimer > 0)
		{
			operationTimer--;
		}
	}
	
	/**
	 * Used to initialized many important properties of the greatward.
	 * @param world The world the greatward exists in.
	 * @param coreX The x location of the greatward core.
	 * @param coreY The y location of the greatward core.
	 * @param coreZ The z location of the greatward core.
	 */
	private void init(World world, int coreX, int coreY, int coreZ)
	{
		GreatwardDimensions dim = GreatwardManager.getDimensionsForType(wardType);
		cornerX = dim.getStartX(coreX, wardDirection, wardOrientation, wardOriright);
		cornerY = dim.getStartY(coreY, wardDirection, wardOrientation, wardOriright);
		cornerZ = dim.getStartZ(coreZ, wardDirection, wardOrientation, wardOriright);
		width = dim.getWidth();
		radius = dim.getRadius();
		height = dim.getHeight();
		
		costPerOperationModifier = 1;
		coreEnergyDrawRate = 1;
		
		currentCoreEnergy = 0;
		maxCoreEnergy = 100;
		
		operationTimer = 0;
		
		wardPieceMultiplier = ( (BlockGreatwardPiece)Block.blocksList[blockType] ).getGreatwardPieceStrength();
		
		initialized = true;
	}
	
	/**
	 * Used to calculate the greatward's boundaries.
	 */
	private void recalculateBounds()
	{
		double cornerXd = (wardDirection.equals(ForgeDirection.EAST)) ? cornerX+1 : cornerX;
		double cornerYd = (wardDirection.equals(ForgeDirection.UP)) ? cornerY+1 : cornerY;
		double cornerZd = (wardDirection.equals(ForgeDirection.SOUTH)) ? cornerZ+1 : cornerZ;
		
		double endX = cornerXd + height*wardDirection.offsetX + width*wardOrientation.offsetX*-1 + width*wardOriright.offsetX*-1;
		double endY = cornerYd + height*wardDirection.offsetY + width*wardOrientation.offsetY*-1 + width*wardOriright.offsetY*-1;
		double endZ = cornerZd + height*wardDirection.offsetZ + width*wardOrientation.offsetZ*-1 + width*wardOriright.offsetZ*-1;
		
		double minX, maxX, minY, maxY, minZ, maxZ;
		
		minX = (cornerXd < endX) ? cornerXd : endX+1;
		maxX = (cornerXd < endX) ? endX : cornerXd+1;
		minY = (cornerYd < endY) ? cornerYd : endY+1;
		maxY = (cornerYd < endY) ? endY : cornerYd+1;
		minZ = (cornerZd < endZ) ? cornerZd : endZ+1;
		maxZ = (cornerZd < endZ) ? endZ : cornerZd+1;
		
		bounds = AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
		
		switch(wardDirection)
		{
			case UP:
			case DOWN:
				centerX = minX + (maxX - minX)/2f;
				centerY = cornerYd;
				centerZ = minZ + (maxZ - minZ)/2f;
				break;
			case NORTH:
			case SOUTH:
				centerX = minX + (maxX - minX)/2f;
				centerY = minY + (maxY - minY)/2f;
				centerZ = cornerZd;
				break;
			case EAST:
			case WEST:
				centerX = cornerXd;
				centerY = minY + (maxY - minY)/2f;
				centerZ = minZ + (maxZ - minZ)/2f;
				break;
			default:
				centerX = minX;
				centerY = minY;
				centerZ = minZ;
				LogHelper.log(Level.SEVERE, "Greatward.recalculateBounds(): Ward direction is UNKNOWN!");
				break;	
		}
	}
	
	/**
	 * Used to check if the greatward can target any blocks.
	 * @return True if the greatward can target blocks.
	 */
	public boolean canTargetBlocks()
	{
		return attribute.canTargetBlocks();
	}
	
	/**
	 * Used to check if a block id is a valid target.
	 * @param id The id to be checked.
	 * @return True if the block can be targeted.
	 */
	public boolean isValidBlockTarget(int id)
	{
		return attribute.isValidBlockTarget(id);
	}
	
	/**
	 * Used to check if an entity is a valid target for this greatward.
	 * @param entity The entity to be checked.
	 * @return True if the entity can be targeted.
	 */
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
		if(blockType == -1) return false;
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
		
		if(nbtTagCompound.hasKey(GreatwardConstants.GW_NBT_BLOCK_TYPE_KEY)) {
			blockType = nbtTagCompound.getInteger(GreatwardConstants.GW_NBT_BLOCK_TYPE_KEY);
		}
		
		wardOriright = ForgeDirection.getOrientation(ForgeDirection.ROTATION_MATRIX[wardOrientation.ordinal()][wardDirection.ordinal()]);
		
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
		
		nbtTagCompound.setInteger(GreatwardConstants.GW_NBT_BLOCK_TYPE_KEY, blockType);
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
	public void updateWardFromPacket(byte direction, byte orientation, List<ChunkCoordinates> blocks, int blockType, String wardType, String target, String attribute, String effect, List<String> augments)
	{	
		wardDirection = ForgeDirection.getOrientation(direction);
		wardOrientation = ForgeDirection.getOrientation(orientation);
		wardOriright = ForgeDirection.getOrientation(ForgeDirection.ROTATION_MATRIX[orientation][direction]);
		greatwardBlocks = blocks;
		this.blockType = blockType;
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
	
	/**
	 * Used to set greatward dimensions for a proxy greatward (In a remote world)
	 * Should be called after a packet is handled.
	 * @param world The world the greatward exists in.
	 * @param coreX The x position of the core.
	 * @param coreY The y position of the core.
	 * @param coreZ The z position of the core.
	 */
	public void initializeProxy(World world, int coreX, int coreY, int coreZ)
	{
		init(world, coreX, coreY, coreZ);
		recalculateBounds();
	}
}
