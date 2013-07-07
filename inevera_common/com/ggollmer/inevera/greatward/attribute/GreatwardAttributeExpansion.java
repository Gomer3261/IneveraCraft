package com.ggollmer.inevera.greatward.attribute;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

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
public class GreatwardAttributeExpansion extends GreatwardAttribute
{
	private static final int OPERATION_COST = 30;
	private static final int OPERATION_COOLDOWN = 20;
	private static final int MAX_TARGETS_PER_OPERATION = 8;
	
	/**
	 * @param name The unique name of the greatward component.
	 */
	public GreatwardAttributeExpansion(String name)
	{
		super(name);
		addGreatwardMap(GreatwardConstants.GW_MINOR_TYPE, GreatwardConstants.GW_ATTRIBUTE_EXPANSION_MINOR_LOCATION, GreatwardConstants.GW_MINOR_DIMENSION, GreatwardConstants.GW_MINOR_DIMENSION);
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
			if(rand.nextInt()%29 == 0)
			{
				int maxParticles = rand.nextInt(5)+5;
				int life = rand.nextInt(6)+16;
				for(int i=0; i<maxParticles; i++)
				{
					float effectMultiplier = greatward.getEffectMultiplier(world);
					if(effectMultiplier==0f) continue;
					
					double angle = rand.nextDouble()*Math.PI*2;
					
					double vel = greatward.radius/life*effectMultiplier;
					
					double mx = 0 + Math.cos(angle)*vel*greatward.getWardOrientation().offsetX +
							Math.sin(angle)*vel*greatward.getWardOriright().offsetX;
					double my = 0 + Math.cos(angle)*vel*greatward.getWardOrientation().offsetY +
							Math.sin(angle)*vel*greatward.getWardOriright().offsetY;
					double mz = 0 + Math.cos(angle)*vel*greatward.getWardOrientation().offsetZ +
							Math.sin(angle)*vel*greatward.getWardOriright().offsetZ;
					
					double px = greatward.centerX + ( (effectMultiplier > 0) ? 0.0D :
							Math.cos(angle)*greatward.radius*greatward.getWardOrientation().offsetX +
							Math.sin(angle)*greatward.radius*greatward.getWardOriright().offsetX ) +
							(0.2f + rand.nextDouble()/10)*greatward.getWardDirection().offsetX;
					double py = greatward.centerY + ( (effectMultiplier > 0) ? 0.0D :
							Math.cos(angle)*greatward.radius*greatward.getWardOrientation().offsetY +
							Math.sin(angle)*greatward.radius*greatward.getWardOriright().offsetY ) +
							(0.2f + rand.nextDouble()/10)*greatward.getWardDirection().offsetY;
					double pz = greatward.centerZ + ( (effectMultiplier > 0) ? 0.0D :
							Math.cos(angle)*greatward.radius*greatward.getWardOrientation().offsetZ +
							Math.sin(angle)*greatward.radius*greatward.getWardOriright().offsetZ ) +
							(0.2f + rand.nextDouble()/10)*greatward.getWardDirection().offsetZ;
					
					Minecraft.getMinecraft().effectRenderer.addEffect(new GreatwardFX(world, life-1, px, py, pz, mx, my, mz, true));
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
					double mtot = (greatward.radius/8)*effectMultiplier;
					
					mtot = (mtot > dtot) ? dtot : mtot;
					
					double mx = (mtot)*(dx/dtot);
					double my = (mtot)*(dy/dtot);
					double mz = (mtot)*(dz/dtot);
					
					target_ids.add(target.entityId);
					target_positions.add(Vec3.fakePool.getVecFromPool(target.posX, target.posY, target.posZ));
					target_arguments.add(String.format("%f:%f:%f", mx, my, mz));
					
					target.motionX += mx;
					target.motionY += my;
					target.motionZ += mz;
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
		Minecraft.getMinecraft().effectRenderer.addEffect(new GreatwardFX(world, 10, posX, posY+0.2D, posZ).multipleParticleScaleBy(2.0f));
		
		if(id == Minecraft.getMinecraft().thePlayer.entityId)
		{
			EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
			if(!player.isDead)
			{
				String[] indvargs = args.split(EffectConstants.EFFECT_ARG_SEPARATOR);
				
				player.motionX += Double.valueOf(indvargs[0]);
				player.motionY += Double.valueOf(indvargs[1]);
				player.motionZ += Double.valueOf(indvargs[2]);
			}
		}
	}
}
