package com.ggollmer.inevera.greatward.attribute;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
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
 * GreatwardAttributeVelocity.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GreatwardAttributeVelocity extends GreatwardAttribute
{
	private static final int OPERATION_COST = 30;
	private static final int OPERATION_COOLDOWN = 20;
	private static final int MAX_TARGETS_PER_OPERATION = 8;
	
	/**
	 * @param name The unique name of the greatward component.
	 */
	public GreatwardAttributeVelocity(String name)
	{
		super(name);
		addGreatwardMap(GreatwardConstants.GW_MINOR_TYPE, GreatwardConstants.GW_ATTRIBUTE_VELOCITY_MINOR_LOCATION, GreatwardConstants.GW_MINOR_DIMENSION, GreatwardConstants.GW_MINOR_DIMENSION);
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
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
			int particleSetting = Minecraft.getMinecraft().gameSettings.particleSetting;
			if(Math.abs(rand.nextInt())%(19*(particleSetting+1)) == 0)
			{
				int maxParticles = rand.nextInt(5-particleSetting)+8-(3*particleSetting);
				double primary_angle = rand.nextDouble()*Math.PI*2;
				double initial_angle = primary_angle - (Math.PI/1.1)/2;
				int life = rand.nextInt(1)+8;
				for(int i=0; i<maxParticles; i++)
				{
					float effectMultiplier = greatward.getEffectMultiplier(world);
					if(effectMultiplier==0f) continue;
					
					double angle = initial_angle + ((float)i/(float)maxParticles)*(Math.PI/1.1);
					
					double length = Math.cos(angle - primary_angle)*greatward.radius*2;
					
					double vel;
					double accel;
					
					if(effectMultiplier > 0)
					{
						vel = (length/life)/6;
						accel = ( 2*(length*5/6)/(life*life) ) * (effectMultiplier);
					}
					else
					{
						vel = (length/life);
						accel = ( length/(life*life) ) * (effectMultiplier);
					}
					
					double mx = 0 + Math.cos(primary_angle)*-1*vel*greatward.getWardOrientation().offsetX +
							Math.sin(primary_angle)*-1*vel*greatward.getWardOriright().offsetX;
					double my = 0 + Math.cos(primary_angle)*-1*vel*greatward.getWardOrientation().offsetY +
							Math.sin(primary_angle)*-1*vel*greatward.getWardOriright().offsetY;
					double mz = 0 + Math.cos(primary_angle)*-1*vel*greatward.getWardOrientation().offsetZ +
							Math.sin(primary_angle)*-1*vel*greatward.getWardOriright().offsetZ;
					
					double ax = 0 + Math.cos(primary_angle)*-1*accel*greatward.getWardOrientation().offsetX +
							Math.sin(primary_angle)*-1*accel*greatward.getWardOriright().offsetX;
					double ay = 0 + Math.cos(primary_angle)*-1*accel*greatward.getWardOrientation().offsetY +
							Math.sin(primary_angle)*-1*accel*greatward.getWardOriright().offsetY;
					double az = 0 + Math.cos(primary_angle)*-1*accel*greatward.getWardOrientation().offsetZ +
							Math.sin(primary_angle)*-1*accel*greatward.getWardOriright().offsetZ;
					
					double px = greatward.centerX + 
							Math.cos(angle)*greatward.radius*greatward.getWardOrientation().offsetX +
							Math.sin(angle)*greatward.radius*greatward.getWardOriright().offsetX +
							(0.2f + rand.nextDouble()/10)*greatward.getWardDirection().offsetX;
					double py = greatward.centerY + 
							Math.cos(angle)*greatward.radius*greatward.getWardOrientation().offsetY +
							Math.sin(angle)*greatward.radius*greatward.getWardOriright().offsetY +
							(0.2f + rand.nextDouble()/10)*greatward.getWardDirection().offsetY;
					double pz = greatward.centerZ + 
							Math.cos(angle)*greatward.radius*greatward.getWardOrientation().offsetZ +
							Math.sin(angle)*greatward.radius*greatward.getWardOriright().offsetZ +
							(0.2f + rand.nextDouble()/10)*greatward.getWardDirection().offsetZ;
					
					Minecraft.getMinecraft().effectRenderer.addEffect(new GreatwardFX(world, life-1, px, py, pz, mx, my, mz, ax, ay, az, true));
				}
			}
		}
	}
	
	@Override
	public boolean isValidEntityTarget(Entity target)
	{
		return (target instanceof EntityLivingBase);
	}
	
	@Override
	public void registerValidId(int id)
	{
		return;
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
					if(effectMultiplier > 0)
					{
						((EntityLivingBase) target).addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), OPERATION_COOLDOWN+10, (greatward.wardPieceMultiplier)));
					}
					else if(effectMultiplier < 0)
					{
						((EntityLivingBase) target).addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), OPERATION_COOLDOWN+10, (greatward.wardPieceMultiplier)));
					}
					
					target_ids.add(target.entityId);
					target_positions.add(Vec3.fakePool.getVecFromPool(target.posX, target.posY, target.posZ));
					target_arguments.add(String.format("%f", effectMultiplier));
				}
			}
			
			PacketDispatcher.sendPacketToAllInDimension(PacketTypeHandler.populatePacket(new PacketGreatwardAction(this.getName(), world.provider.dimensionId, true, target_ids, target_positions, target_arguments)), world.provider.dimensionId);
			
			greatward.currentCoreEnergy -= OPERATION_COST*targetCount;
		}
		
		/* Blocks */
		
		greatward.addOperationDelay(OPERATION_COOLDOWN);
	}

	@Override
	public void performGreatwardAction(World world, boolean target_entity, int id, double posX, double posY, double posZ, String args)
	{
		IneveraEffectHelper.spawnEffect(EffectConstants.EFFECT_GROUND_EMANATE_NAME, world, Minecraft.getMinecraft().effectRenderer, posX, posY, posZ, args);
	}
}
