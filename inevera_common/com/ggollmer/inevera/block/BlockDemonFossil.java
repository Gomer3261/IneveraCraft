package com.ggollmer.inevera.block;

import java.util.Random;

import com.ggollmer.inevera.Inevera;
import com.ggollmer.inevera.item.IneveraItems;
import com.ggollmer.inevera.lib.BlockNames;
import com.ggollmer.inevera.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * IneveraCraft
 *
 * BlockDemonFossil.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class BlockDemonFossil extends Block
{
	public BlockDemonFossil(int id)
	{
		super(id, Material.rock);
		setHardness(2.0f);
		setStepSound(Block.soundStoneFootstep);
		setUnlocalizedName(BlockNames.DEMON_FOSSIL_NAME);
		setCreativeTab(Inevera.tabsInevera);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		
		blockIcon = iconRegister.registerIcon(new ResourceLocation(Reference.MOD_ID.toLowerCase(), getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1)).toString());
	}
	
	
	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int par5, float chance, int par7)
	{	
		if (!world.isRemote)
        {
            if (world.rand.nextFloat() <= chance)
            {
            	if(world.rand.nextInt(4) == 0) this.dropBlockAsItem_do(world, x, y, z, new ItemStack(IneveraItems.demonBone, 1));
            	for(int i=0; i<world.rand.nextInt(4)+1; i++)
            	{
            		this.dropBlockAsItem_do(world, x, y, z, new ItemStack(IneveraItems.demonFlesh, 1));
            	}
            }
        }
	}
	
	@SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
		if(world.isDaytime() && world.canBlockSeeTheSky(x, y+1, z))
		{
			for(int i=0; i<12; i++)
			{
				ForgeDirection side = ForgeDirection.VALID_DIRECTIONS[world.rand.nextInt(ForgeDirection.VALID_DIRECTIONS.length)];
				double px = x + 0.5D + ( (side.offsetX != 0) ? (0.5D * side.offsetX * (1 + world.rand.nextDouble()/20)) : (world.rand.nextDouble()%0.53 * ( (world.rand.nextBoolean()) ? -1 : 1 )) );
				double py = y + 0.5D + ( (side.offsetY != 0) ? (0.5D * side.offsetY * (1 + world.rand.nextDouble()/20)) : (world.rand.nextDouble()%0.53 * ( (world.rand.nextBoolean()) ? -1 : 1 )) );
				double pz = z + 0.5D + ( (side.offsetZ != 0) ? (0.5D * side.offsetZ * (1 + world.rand.nextDouble()/20)) : (world.rand.nextDouble()%0.53 * ( (world.rand.nextBoolean()) ? -1 : 1 )) );
				
				world.spawnParticle("flame", px, py, pz, 0D, 0D, 0D);
				world.spawnParticle("smoke", px, py, pz, 0D, 0D, 0D);
			}
			
			if(0.3 > world.rand.nextFloat())
			{
				world.playSound(x, y, z, "random.fizz", 0.4F, 2.0F + world.rand.nextFloat() * 0.4F, true);
			}
		}
    }
	
	@Override
    public void updateTick(World world, int x, int y, int z, Random random)
    {
		if(world.isDaytime() && world.canBlockSeeTheSky(x, y+1, z))
		{
			if(!world.isRemote && (world.rand.nextFloat()) < world.getLightBrightness(x, y+1, z)/8)
			{	
				dropBlockAsItem_do(world, x, y, z, new ItemStack(Block.cobblestone, 1));
				world.setBlockToAir(x, y, z);
			}
		}
		
		world.scheduleBlockUpdate(x, y, z, blockID, tickRate(world));
    }
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		world.scheduleBlockUpdate(x, y, z, blockID, tickRate(world));
	}
	
	@Override
	public int tickRate(World world)
	{
		return 5;
	}
}
