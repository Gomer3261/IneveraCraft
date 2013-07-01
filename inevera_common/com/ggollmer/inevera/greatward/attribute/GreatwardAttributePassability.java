package com.ggollmer.inevera.greatward.attribute;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
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
	private static final int OPERATION_COST = 30;
	private static final int OPERATION_COOLDOWN = 20;
	private static final int MAX_TARGETS_PER_OPERATION = 8;
	
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
		
		// TODO: Implement
		
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
					double dx = target.posX - greatward.centerX;
					double dy = target.posY - greatward.centerY;
					double dz = target.posZ - greatward.centerZ;
					double dtot = Math.sqrt( (dx*dx) + (dy*dy) + (dz*dz) );
					double mtot = greatward.radius/8;
					
					mtot = (mtot > dtot) ? dtot : mtot;
					
					double mx = (mtot)*(dx/dtot)*effectMultiplier;
					double my = (mtot)*(dy/dtot)*effectMultiplier;
					double mz = (mtot)*(dz/dtot)*effectMultiplier;
					
					target_ids.add(target.entityId);
					target_positions.add(Vec3.fakePool.getVecFromPool(target.posX, target.posY, target.posZ));
					target_arguments.add(String.format("%f:%f:%f:%f", mx, my, mz, 0.21D));
					
					target.moveEntity(mx, my, mz);
				}
			}
			
			PacketDispatcher.sendPacketToAllInDimension(PacketTypeHandler.populatePacket(new PacketGreatwardAction(this.getName(), world.getWorldInfo().getDimension(), true, target_ids, target_positions, target_arguments)), world.getWorldInfo().getDimension());
			
			greatward.currentCoreEnergy -= OPERATION_COST*targetCount/greatward.wardPieceMultiplier;
		}
		
		/* Blocks */
		
		greatward.addOperationDelay(OPERATION_COOLDOWN);
	}

	@Override
	public void performGreatwardAction(World world, boolean target_entity, int id, double posX, double posY, double posZ, String args)
	{
		IneveraEffectHelper.spawnEffect(EffectConstants.EFFECT_DIRECTIONAL_BURST_NAME, world, Minecraft.getMinecraft().effectRenderer, posX, posY, posZ, args);
	}
}
