package com.ggollmer.inevera.item;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;

/**
 * IneveraCraft
 *
 * MagicItemInevera.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class DemonItemInevera extends ItemInevera
{
	protected Random rand;
	
	/**
	 * @param id
	 */
	public DemonItemInevera(int id)
	{
		super(id);
		rand = new Random();
	}
	
	@Override
	public boolean onEntityItemUpdate(EntityItem entity)
	{
		if(entity.worldObj.isDaytime() && !entity.worldObj.isRemote)
		{
			float f = entity.getBrightness(1.0F);
			
			if(f > 0.5F && rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F&& entity.worldObj.canBlockSeeTheSky(MathHelper.floor_double(entity.posX), MathHelper.floor_double(entity.posY), MathHelper.floor_double(entity.posZ)))
			{
				entity.playSound("random.fizz", 0.4F, 2.0F + this.rand.nextFloat() * 0.4F);
				entity.attackEntityFrom(DamageSource.inFire, 3);
				entity.setFire(5 + (rand.nextInt()%10));
			}
		}
		
		return false;
	}
}
