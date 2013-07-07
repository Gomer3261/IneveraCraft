package com.ggollmer.inevera.greatward.attribute;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.ggollmer.inevera.client.effect.IneveraEffectHelper;
import com.ggollmer.inevera.client.particle.IneveraSparkFX;
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
public class GreatwardAttributePassability extends GreatwardAttribute
{
	private static final int OPERATION_COST = 4;
	private static final int OPERATION_COOLDOWN = 6;
	private static final int MAX_TARGETS_PER_OPERATION = 4;
	
	/**
	 * @param name The unique name of the greatward component.
	 */
	public GreatwardAttributePassability(String name)
	{
		super(name);
		addGreatwardMap(GreatwardConstants.GW_MINOR_TYPE, GreatwardConstants.GW_ATTRIBUTE_PASSABILITY_MINOR_LOCATION, GreatwardConstants.GW_MINOR_DIMENSION, GreatwardConstants.GW_MINOR_DIMENSION);
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
			if(rand.nextInt()%3 == 0)
			{
				int maxParticles = rand.nextInt(12)+13;
				for(int i=0; i<maxParticles; i++)
				{
					float effectMultiplier = greatward.getEffectMultiplier(world);
					if(effectMultiplier==0f) continue;
					
					double angle = rand.nextDouble()*Math.PI*2;
					int life = rand.nextInt(12)+16;
					
					double px = greatward.centerX + 
							Math.cos(angle)*greatward.radius*greatward.getWardOrientation().offsetX +
							Math.sin(angle)*greatward.radius*greatward.getWardOriright().offsetX +
							greatward.height*rand.nextDouble()*greatward.getWardDirection().offsetX;
					double py = greatward.centerY + 
							Math.cos(angle)*greatward.radius*greatward.getWardOrientation().offsetY +
							Math.sin(angle)*greatward.radius*greatward.getWardOriright().offsetY +
							greatward.height*rand.nextDouble()*greatward.getWardDirection().offsetY;
					double pz = greatward.centerZ + 
							Math.cos(angle)*greatward.radius*greatward.getWardOrientation().offsetZ +
							Math.sin(angle)*greatward.radius*greatward.getWardOriright().offsetZ +
							greatward.height*rand.nextDouble()*greatward.getWardDirection().offsetZ;
					
					EntityFX particle = new IneveraSparkFX(world, life-1, px, py, pz);
					particle.multipleParticleScaleBy(1.3F);
					particle.setRBGColorF(255, 0, 255);
					
					Minecraft.getMinecraft().effectRenderer.addEffect(particle);
				}
			}
		}
	}
	
	@Override
	public boolean isValidEntityTarget(Entity target)
	{
		return true;
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
		
		List<Integer> currTargetIds = new ArrayList<Integer>();
		for(Entity target : greatward.entityTargets)
		{
			if(!target.isDead) currTargetIds.add(target.entityId);
		}
		
		@SuppressWarnings("unchecked")
		List<Integer> activeTargetIds = (List<Integer>)greatward.stateStorage.get(GreatwardConstants.GW_ATTRIBUTE_PASSABILITY_STATE);
		if(activeTargetIds == null) activeTargetIds = currTargetIds;
		
		/* Entities */
		if(!currTargetIds.isEmpty() || !activeTargetIds.isEmpty())
		{
			List<Entity> possibleTargets = new ArrayList<Entity>();
			
			
			for(Integer tId : currTargetIds)
			{
				if(!activeTargetIds.contains(tId))
				{
					if(effectMultiplier == 1 || greatward.getEffectName() == GreatwardConstants.GW_EFFECT_CHAOTIC_NAME)
					{
						Entity target = world.getEntityByID(tId);
						if(target != null)
						{
							if(!target.isDead)
							{
								possibleTargets.add(target);
							}
						}
					}
					else
					{
						/* Trapping entities inside the greatward. */
						activeTargetIds.add(tId);
					}
				}
			}
			for(Integer tId : activeTargetIds)
			{
				if(!currTargetIds.contains(tId))
				{
					if(effectMultiplier == -1 || greatward.getEffectName() == GreatwardConstants.GW_EFFECT_CHAOTIC_NAME)
					{
						Entity target = world.getEntityByID(tId);
						if(target != null)
						{
							if(!target.isDead)
							{
								possibleTargets.add(target);
							}
						}
					}
					else
					{
						/* Someone has left the greatward. */
						activeTargetIds.remove(tId);
					}
				}
			}
			
			if(possibleTargets.size() > 0)
			{
				int maximumTargets = (int)(greatward.currentCoreEnergy / OPERATION_COST);
				maximumTargets = (maximumTargets < MAX_TARGETS_PER_OPERATION*greatward.wardPieceMultiplier) ? maximumTargets : MAX_TARGETS_PER_OPERATION*greatward.wardPieceMultiplier;
				int targetCount = (possibleTargets.size() < maximumTargets) ? possibleTargets.size() : maximumTargets;
				
				int startIndex = (possibleTargets.size()>1) ? rand.nextInt(possibleTargets.size()-1) : 0;
				
				List<Integer> target_ids = new ArrayList<Integer>();
				List<Vec3> target_positions = new ArrayList<Vec3>();
				List<String> target_arguments = new ArrayList<String>();
				
				for(int i=0; i<targetCount; i++)
				{
					Entity target = possibleTargets.get((startIndex + i)%possibleTargets.size());
					
					double dx = target.posX - greatward.centerX;
					double dy = target.posY - greatward.centerY;
					double dz = target.posZ - greatward.centerZ;
					double dtot = Math.sqrt( (dx*dx) + (dy*dy) + (dz*dz) );
					double mtot = greatward.radius/8;
					double pieceMultiplier = 1D + (greatward.wardPieceMultiplier-1D)/6D;
					
					mtot = (mtot > dtot) ? dtot : mtot;
					
					double mx = (mtot)*(dx/dtot)*pieceMultiplier * ( (!activeTargetIds.contains(target.entityId)) ? 1 : -1 );
					double my = (mtot)*(dy/dtot)*pieceMultiplier * ( (!activeTargetIds.contains(target.entityId)) ? 1 : -1 );
					double mz = (mtot)*(dz/dtot)*pieceMultiplier * ( (!activeTargetIds.contains(target.entityId)) ? 1 : -1 );
					
					if(!(target instanceof EntityPlayerMP))
					{
						target.motionX += mx;
						target.motionY += my;
						target.motionZ += mz;
					}
					
					target_ids.add(target.entityId);
					target_positions.add(Vec3.fakePool.getVecFromPool(target.posX, target.posY+target.height/2, target.posZ));
					target_arguments.add(String.format("%f:%f:%f:%f", mx, my, mz, Math.PI/4));
					
					target.moveEntity(mx, my, mz);
				}
				
				PacketDispatcher.sendPacketToAllInDimension(PacketTypeHandler.populatePacket(new PacketGreatwardAction(this.getName(), world.getWorldInfo().getDimension(), true, target_ids, target_positions, target_arguments)), world.getWorldInfo().getDimension());
				
				greatward.currentCoreEnergy -= OPERATION_COST*targetCount/greatward.wardPieceMultiplier;
			}
		}
		
		/* Blocks */
		
		greatward.stateStorage.put(GreatwardConstants.GW_ATTRIBUTE_PASSABILITY_STATE, activeTargetIds);
		greatward.addOperationDelay(OPERATION_COOLDOWN);
	}

	@Override
	public void performGreatwardAction(World world, boolean target_entity, int id, double posX, double posY, double posZ, String args)
	{
		String[] indvargs = args.split(EffectConstants.EFFECT_ARG_SEPARATOR);
		
		double mx=Double.valueOf(indvargs[0]);
		double my=Double.valueOf(indvargs[1]);
		double mz=Double.valueOf(indvargs[2]);
		double oAngle=Double.valueOf(indvargs[3]);
		
		String effectArgs = String.format("%f:%f:%f:%f", mx*-1, my*-1, mz*-1, oAngle);
		
		IneveraEffectHelper.spawnEffect(EffectConstants.EFFECT_DIRECTIONAL_BURST_NAME, world, Minecraft.getMinecraft().effectRenderer, posX, posY, posZ, effectArgs);
		
		if(Minecraft.getMinecraft().thePlayer.entityId == id)
		{
			EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
			player.motionX += mx;
			player.motionY += my;
			player.motionZ += mz;
		}
	}
}
