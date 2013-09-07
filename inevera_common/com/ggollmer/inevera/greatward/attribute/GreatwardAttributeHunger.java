package com.ggollmer.inevera.greatward.attribute;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.ggollmer.inevera.client.effect.IneveraEffectHelper;
import com.ggollmer.inevera.client.particle.GreatwardFX;
import com.ggollmer.inevera.greatward.Greatward;
import com.ggollmer.inevera.lib.EffectConstants;
import com.ggollmer.inevera.lib.GreatwardConstants;
import com.ggollmer.inevera.network.PacketTypeHandler;
import com.ggollmer.inevera.network.packet.PacketGreatwardAction;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * IneveraCraft
 *
 * GreatwardAttributeHunger.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GreatwardAttributeHunger extends GreatwardAttribute
{
	private static final int OPERATION_COST = 30;
	private static final int OPERATION_COOLDOWN = 60;
	private static final int MAX_TARGETS_PER_OPERATION = 8;
	private static final int AMOUNT_PER_OPERATION = 1;
	/**
	 * @param name The unique name of the greatward component.
	 */
	public GreatwardAttributeHunger(String name)
	{
		super(name);
		addGreatwardMap(GreatwardConstants.GW_MINOR_TYPE, GreatwardConstants.GW_ATTRIBUTE_HUNGER_MINOR_LOCATION, GreatwardConstants.GW_MINOR_DIMENSION, GreatwardConstants.GW_MINOR_DIMENSION);
		//TODO: add constructors for normal and major maps.
	}

	@Override
	public void onGreatwardInit(World world, Greatward greatward)
	{
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void renderAmbientParticles(World world, Greatward greatward)
	{
		//TODO: Custom hunger ambient effects.
		
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
			if(rand.nextInt()%24 > 11)
			{
				int maxParticles = rand.nextInt(4)+1;
				for(int i=0; i<maxParticles; i++)
				{
					float effectMultiplier = greatward.getEffectMultiplier(world);
					if(effectMultiplier==0f) continue;
					
					double angle = rand.nextDouble()*Math.PI*2;
					int life = (rand.nextInt()+12)%44;
					
					double mx = ((rand.nextDouble()+.13)/8f)*greatward.getWardDirection().offsetX*effectMultiplier;
					double my = ((rand.nextDouble()+.13)/8f)*greatward.getWardDirection().offsetY*effectMultiplier;
					double mz = ((rand.nextDouble()+.13)/8f)*greatward.getWardDirection().offsetZ*effectMultiplier;
					
					double offx = (effectMultiplier < 0) ? Math.abs(mx*life) : 0;
					double offy = (effectMultiplier < 0) ? Math.abs(my*life) : 0;
					double offz = (effectMultiplier < 0) ? Math.abs(mz*life) : 0;
					
					double px = greatward.centerX + 
							Math.cos(angle)*rand.nextDouble()*greatward.radius*greatward.getWardOrientation().offsetX +
							Math.sin(angle)*rand.nextDouble()*greatward.radius*greatward.getWardOriright().offsetX +
							offx*greatward.getWardDirection().offsetX;
					double py = greatward.centerY + 
							Math.cos(angle)*rand.nextDouble()*greatward.radius*greatward.getWardOrientation().offsetY +
							Math.sin(angle)*rand.nextDouble()*greatward.radius*greatward.getWardOriright().offsetY +
							offy*greatward.getWardDirection().offsetY;
					double pz = greatward.centerZ + 
							Math.cos(angle)*rand.nextDouble()*greatward.radius*greatward.getWardOrientation().offsetZ +
							Math.sin(angle)*rand.nextDouble()*greatward.radius*greatward.getWardOriright().offsetZ +
							offz*greatward.getWardDirection().offsetZ;
					
					Minecraft.getMinecraft().effectRenderer.addEffect(new GreatwardFX(world, life, px, py, pz, mx, my, mz));
				}
			}
		}
	}
	
	@Override
	public boolean isValidEntityTarget(Entity target)
	{
		return ((target instanceof EntityPlayer) || (target instanceof EntityAnimal) || (target instanceof IGWFeedableEntity));
	}
	
	@Override
	public void registerValidId(int id)
	{
		if(Block.blocksList[id] instanceof IGWHealableBlock)
		{
			super.registerValidId(id);
		}
	}

	@Override
	public boolean canPerformOperation(World world, Greatward greatward)
	{
		if(greatward.currentCoreEnergy >= OPERATION_COST)
		{
			return true;
		}
		return false;
	}

	@Override
	public void performGreatwardEffects(World world, Greatward greatward)
	{
		float effectMultiplier = greatward.getEffectMultiplier(world);
		
		/* Entities */
		if(!greatward.entityTargets.isEmpty())
		{
			/* Limit targets to a certain number. */
			int maximum_targets = (int)(greatward.currentCoreEnergy / OPERATION_COST);
			maximum_targets = (maximum_targets < MAX_TARGETS_PER_OPERATION) ? maximum_targets : MAX_TARGETS_PER_OPERATION;
			int targetCount = (greatward.entityTargets.size() < maximum_targets) ? greatward.entityTargets.size() : maximum_targets;
			int startIndex = (greatward.entityTargets.size()>1) ? rand.nextInt(greatward.entityTargets.size()-1) : 0;
			
			List<Integer> target_ids = new ArrayList<Integer>();
			List<Vec3> target_positions = new ArrayList<Vec3>();
			List<String> target_arguments = new ArrayList<String>();
			
			for(int i=0; i<targetCount; i++)
			{
				Entity target = greatward.entityTargets.get((startIndex + i)%greatward.entityTargets.size());
				
				if(!target.isDead)
				{
					if(target instanceof IGWFeedableEntity) /* Custom feedable entities. */
					{
						((IGWFeedableEntity)target).onGreatwardFeed((int)(AMOUNT_PER_OPERATION*effectMultiplier*greatward.wardPieceMultiplier));
					}
					else if(target instanceof EntityPlayer) /* Players */
					{
						((EntityPlayer)target).getFoodStats().addStats((int)(AMOUNT_PER_OPERATION*effectMultiplier*greatward.wardPieceMultiplier), 1f);
					}
					else /* Basic animals */
					{
						if (effectMultiplier > 0)
						{
							EntityAnimal target_animal = (EntityAnimal)target;
							if(target_animal.getGrowingAge() == 0 && target_animal.inLove <= 0)
							{
								target_animal.func_110196_bT();
							}
						}
					}
					
					target_ids.add(target.entityId);
					target_positions.add(Vec3.fakePool.getVecFromPool(target.posX, target.posY+(target.height/2), target.posZ));
					target_arguments.add(Float.toString(effectMultiplier));
				}
			}
			
			PacketDispatcher.sendPacketToAllInDimension(PacketTypeHandler.populatePacket(new PacketGreatwardAction(this.getName(), world.provider.dimensionId, true, target_ids, target_positions, target_arguments)), world.provider.dimensionId);
			
			greatward.currentCoreEnergy -= OPERATION_COST*targetCount;
		}
		
		/* Blocks */
		if(!greatward.blockTargets.isEmpty())
		{
			List<Integer> target_ids = new ArrayList<Integer>();
			List<Vec3> target_positions = new ArrayList<Vec3>();
			List<String> target_arguments = new ArrayList<String>();
			
			for(ChunkCoordinates coord : greatward.blockTargets)
			{
				int id = world.getBlockId(coord.posX, coord.posY, coord.posZ);
				if(Block.blocksList[id] instanceof IGWHealableBlock)
				{
					((IGWFeedableBlock)Block.blocksList[id]).onGreatwardFeed(world, coord.posX, coord.posY, coord.posZ, (int)(AMOUNT_PER_OPERATION*effectMultiplier*greatward.wardPieceMultiplier));
					target_ids.add(id);
					target_positions.add(Vec3.fakePool.getVecFromPool(coord.posX, coord.posY, coord.posZ));
					target_arguments.add(Float.toString(effectMultiplier));
				}
			}
			
			PacketDispatcher.sendPacketToAllInDimension(PacketTypeHandler.populatePacket(new PacketGreatwardAction(this.getName(), world.provider.dimensionId, false, target_ids, target_positions, target_arguments)), world.provider.dimensionId);
		}
		
		greatward.addOperationDelay(OPERATION_COOLDOWN);
	}

	@Override
	public void performGreatwardAction(World world, boolean target_entity, int id, double posX, double posY, double posZ, String args)
	{
		IneveraEffectHelper.spawnEffect(EffectConstants.EFFECT_ABSORB_NAME, world, Minecraft.getMinecraft().effectRenderer, posX, posY, posZ, args);
	}
}
