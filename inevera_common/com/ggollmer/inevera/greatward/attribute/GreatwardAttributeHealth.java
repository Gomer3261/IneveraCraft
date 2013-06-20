package com.ggollmer.inevera.greatward.attribute;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.ggollmer.inevera.client.effect.IneveraEffectHelper;
import com.ggollmer.inevera.client.particle.GreatwardDummyDamageFX;
import com.ggollmer.inevera.client.particle.GreatwardFX;
import com.ggollmer.inevera.greatward.Greatward;
import com.ggollmer.inevera.lib.BlockIds;
import com.ggollmer.inevera.lib.GreatwardConstants;
import com.ggollmer.inevera.network.PacketTypeHandler;
import com.ggollmer.inevera.network.packet.PacketGreatwardAction;
import com.ggollmer.inevera.block.BlockGreatwardComponent;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * IneveraCraft
 *
 * GreatwardAttributeHealth.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GreatwardAttributeHealth extends GreatwardAttribute
{
	private static final int OPERATION_COST = 30;
	private static final int TARGETS_PER_OPERATION = 5;
	private static final int AMOUNT_PER_OPERATION = 1;
	/**
	 * @param name The unique name of the greatward component.
	 */
	public GreatwardAttributeHealth(String name)
	{
		super(name);
		addGreatwardMap(GreatwardConstants.GW_MINOR_TYPE, GreatwardConstants.GW_ATTRIBUTE_HEALTH_MINOR_LOCATION, GreatwardConstants.GW_MINOR_DIMENSION, GreatwardConstants.GW_MINOR_DIMENSION);
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
		if(world.isRemote)
		{
			for(int i=0; i<16; i++)
			{
				double angle = rand.nextDouble()*Math.PI*2;
				double px = greatward.centerX + 
						Math.cos(angle)/**rand.nextDouble()*/*greatward.radius*greatward.getWardOrientation().offsetX +
						Math.sin(angle)/**rand.nextDouble()*/*greatward.radius*greatward.getWardOriright().offsetX +
						rand.nextDouble()*greatward.height*greatward.getWardDirection().offsetX;
				double py = greatward.centerY + 
						Math.cos(angle)/**rand.nextDouble()*/*greatward.radius*greatward.getWardOrientation().offsetY +
						Math.sin(angle)/**rand.nextDouble()*/*greatward.radius*greatward.getWardOriright().offsetY +
						rand.nextDouble()*greatward.height*greatward.getWardDirection().offsetY;
				double pz = greatward.centerZ + 
						Math.cos(angle)/**rand.nextDouble()*/*greatward.radius*greatward.getWardOrientation().offsetZ +
						Math.sin(angle)/**rand.nextDouble()*/*greatward.radius*greatward.getWardOriright().offsetZ +
						rand.nextDouble()*greatward.height*greatward.getWardDirection().offsetZ;
				double mx = 0;//rand.nextDouble()*(rand.nextInt()%1)/120f;
				double my = 0;//rand.nextDouble()*(rand.nextInt()%1)/120f;
				double mz = 0;//rand.nextDouble()*(rand.nextInt()%1)/120f;
				
				Minecraft.getMinecraft().effectRenderer.addEffect(new GreatwardFX(world, px, py, pz, mx, my, mz));
				//Minecraft.getMinecraft().effectRenderer.addEffect(new GreatwardDummyDamageFX(world, px, py, pz, 0.0D, 0.0D, 0.0D, (BlockGreatwardComponent)Block.blocksList[BlockIds.GREATWARD_WOOD_PIECE], 1, 0, null));
			}
		}
	}
	
	@Override
	public boolean isValidEntityTarget(Entity target)
	{
		return ((target instanceof EntityLiving) || (target instanceof IGWHealableEntity));
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
	public void performGreatwardEffects(World world, Greatward greatward, float effectMultiplier)
	{
		/* Entities */
		if(!greatward.entityTargets.isEmpty())
		{
			int targetCount = (greatward.entityTargets.size() < TARGETS_PER_OPERATION) ? greatward.entityTargets.size() : TARGETS_PER_OPERATION;
			int startIndex = (greatward.entityTargets.size()>1) ? rand.nextInt(greatward.entityTargets.size()-1) : 0;
			
			List<Integer> target_ids = new ArrayList<Integer>();
			List<Vec3> target_positions = new ArrayList<Vec3>();
			
			for(int i=0; i<targetCount; i++)
			{
				Entity target = greatward.entityTargets.get((startIndex + i)%greatward.entityTargets.size());
				
				if(!target.isDead)
				{
					if(target instanceof IGWHealableEntity)
					{
						((IGWHealableEntity)target).onGreatwardHeal((int)(AMOUNT_PER_OPERATION*effectMultiplier));
					}
					else
					{
						if(effectMultiplier < 0)
						{
							((EntityLiving)target).attackEntityFrom(DamageSource.magic, (int)(-1*effectMultiplier));
						}
						else
						{
							((EntityLiving)target).heal((int)(1*effectMultiplier));
						}
					}
					
					target_ids.add(target.entityId);
					target_positions.add(Vec3.fakePool.getVecFromPool(target.posX, target.posY, target.posZ));
				}
			}
			PacketDispatcher.sendPacketToAllInDimension(PacketTypeHandler.populatePacket(new PacketGreatwardAction(this.getName(), world.getWorldInfo().getDimension(), true, target_ids, target_positions, Float.toString(effectMultiplier))), world.getWorldInfo().getDimension());
			greatward.currentCoreEnergy -= OPERATION_COST;
		}
		
		/* Blocks */
		if(!greatward.blockTargets.isEmpty())
		{
			List<Integer> target_ids = new ArrayList<Integer>();
			List<Vec3> target_positions = new ArrayList<Vec3>();
			
			for(ChunkCoordinates coord : greatward.blockTargets)
			{
				int id = world.getBlockId(coord.posX, coord.posY, coord.posZ);
				if(Block.blocksList[id] instanceof IGWHealableBlock)
				{
					((IGWHealableBlock)Block.blocksList[id]).onGreatwardHeal(world, coord.posX, coord.posY, coord.posZ);
					target_ids.add(id);
					target_positions.add(Vec3.fakePool.getVecFromPool(coord.posX, coord.posY, coord.posZ));
				}
			}
			
			PacketDispatcher.sendPacketToAllInDimension(PacketTypeHandler.populatePacket(new PacketGreatwardAction(this.getName(), world.getWorldInfo().getDimension(), true, target_ids, target_positions, Float.toString(effectMultiplier))), world.getWorldInfo().getDimension());
		}
	}

	@Override
	public void performGreatwardAction(World world, boolean target_entity, int id, double posX, double posY, double posZ, String args)
	{
		IneveraEffectHelper.spawnEffect("health", world, Minecraft.getMinecraft().effectRenderer, posX, posY, posZ, args);
	}
}
