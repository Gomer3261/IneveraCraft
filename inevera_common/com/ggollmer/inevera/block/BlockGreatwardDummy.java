package com.ggollmer.inevera.block;

import java.util.ArrayList;

import com.ggollmer.inevera.core.helper.LogHelper;
import com.ggollmer.inevera.lib.BlockNames;
import com.ggollmer.inevera.tileentity.TileGreatwardCore;
import com.ggollmer.inevera.tileentity.TileGreatwardDummy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

/**
 * IneveraCraft
 *
 * BlockGreatwardDummy.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class BlockGreatwardDummy extends BlockInevera
{

	/**
	 * @param id The id of the new block
	 */
	public BlockGreatwardDummy(int id)
	{
		super(id, Material.rock);
		this.setUnlocalizedName(BlockNames.GREATWARD_DUMMY_NAME);
		this.setCreativeTab(null);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = null;
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return (TileEntity)new TileGreatwardDummy();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		
		if(dummy != null && dummy.getCoreTile() != null)
		{
			dummy.getCoreTile().invalidateGreatward();
		}
		
		Block.blocksList[dummy.getImitationId()].breakBlock(world, x, y, z, par5, par6);
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int id)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		TileGreatwardCore core = dummy.getCoreTile();
		if(core == null)
		{
			LogHelper.debugLog("dummy has no core!");
			return;
		}
		ForgeDirection gwDir = core.getWardDirection();
		
		if(world.getBlockId(x+gwDir.offsetX, y+gwDir.offsetY, z+gwDir.offsetZ) != 0)
		{
			dummy.invalidate();
		}
		else if(id == dummy.getImitationId())
		{
			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
			{
				if(!dir.equals(gwDir.getOpposite()) && !dir.equals(gwDir))
				{
					if(world.getBlockId(x+gwDir.offsetX, y+gwDir.offsetY, z+gwDir.offsetZ) == dummy.getImitationId())
					{
						dummy.invalidate();
					}
				}
			}
		}
		
		Block.blocksList[dummy.getImitationId()].onNeighborBlockChange(world, x, y, z, id);
	}
	
	/* Mimicking the properties of the copied block! */
	
	@Override
	public float getBlockHardness(World world, int x, int y, int z)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].getBlockHardness(world, x, y, z);
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int par5)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)blockAccess.getBlockTileEntity(x, y, z);
		LogHelper.debugLog("Getting texture for dummy of id: " + dummy.getImitationId());
		if(dummy.getCoreTile() == null)LogHelper.debugLog("For some reason the core is null...");
        return Block.blocksList[dummy.getImitationId()].getIcon(par5, blockAccess.getBlockMetadata(x, y, z));
    }
	
	/*@Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int par5, float par6, int par7)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		Block.blocksList[dummy.getImitationId()].dropBlockAsItemWithChance(world, x, y, z, par5, par6, par7);
    }*/
	
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity par5Entity)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		Block.blocksList[dummy.getImitationId()].onEntityWalking(world, x, y, z, par5Entity);
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer par5EntityPlayer)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		Block.blocksList[dummy.getImitationId()].onBlockClicked(world, x, y, z, par5EntityPlayer);
	}
	
	@Override
	public void harvestBlock(World world, EntityPlayer par2EntityPlayer, int x, int y, int z, int par6)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		Block.blocksList[dummy.getImitationId()].harvestBlock(world, par2EntityPlayer, x, y, z, par6);
	}
	
	@Override
	public float getAmbientOcclusionLightValue(IBlockAccess blockAccess, int x, int y, int z)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)blockAccess.getBlockTileEntity(x, y, z);
		// Hack to get around lighting change before our tile is created.
		if(dummy == null)
		{
			return 0.2F;
		}
		return Block.blocksList[dummy.getImitationId()].getAmbientOcclusionLightValue(blockAccess, x, y, z);
	}
	
	@Override
	public void onFallenUpon(World world, int x, int y, int z, Entity par5Entity, float par6)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		Block.blocksList[dummy.getImitationId()].onFallenUpon(world, x, y, z, par5Entity, par6);
	}
	
	@Override
	public int idPicked(World world, int x, int y, int z)
    {
        return ((TileGreatwardDummy)world.getBlockTileEntity(x, y, z)).getImitationId();
    }
	
	@Override
	public int getDamageValue(World world, int x, int y, int z)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].damageDropped(world.getBlockMetadata(x, y, z));
    }
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		// Bit of a hack to get around light changes before our tile is created.
		if(dummy == null)
		{
			return Block.lightValue[2];
		}
		return Block.lightValue[dummy.getImitationId()];
	}
	
	@Override
	public boolean isLadder(World world, int x, int y, int z)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].isLadder(world, x, y, z);
	}
	
	@Override
	public boolean isBlockNormalCube(World world, int x, int y, int z)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].isBlockNormalCube(world, x, y, z);
    }
	
	@Override
	public boolean isBlockReplaceable(World world, int x, int y, int z)
	{
		return false;
	}
	
	@Override
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].removeBlockByPlayer(world, player, x, y, z);
    }
	
	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
    {	
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
        return blockFlammability[dummy.getImitationId()];
    }
	
	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].getFlammability(world, x, y, z, metadata, face) > 0;
    }
	
	@Override
	public int getFireSpreadSpeed(World world, int x, int y, int z, int metadata, ForgeDirection face)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
        return blockFireSpreadSpeed[dummy.getImitationId()];
    }
	
	@Override
	public boolean isFireSource(World world, int x, int y, int z, int metadata, ForgeDirection side)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].isFireSource(world, x, y, z, metadata, side);
	}
	
	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].getBlockDropped(world, x, y, z, metadata, fortune);
    }
	
	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].canSilkHarvest(world, player, x, y, z, metadata);
    }
	
	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].canCreatureSpawn(type, world, x, y, z);
    }
	
	@Override
	public boolean isBed(World world, int x, int y, int z, EntityLiving player)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].isBed(world, x, y, z, player);
	}
	
	@Override
	public void beginLeavesDecay(World world, int x, int y, int z)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		Block.blocksList[dummy.getImitationId()].beginLeavesDecay(world, x, y, z);
	}
	
	@Override
	public boolean canSustainLeaves(World world, int x, int y, int z)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].canSustainLeaves(world, x, y, z);
	}
	
	@Override
	public boolean isLeaves(World world, int x, int y, int z)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].isLeaves(world, x, y ,z);
    }
	
	@Override
	public boolean isWood(World world, int x, int y, int z)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].isWood(world, x, y, z);
    }
	
	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
	}
	
	@Override
	public void onBlockExploded(World world, int x, int y, int z, Explosion explosion)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		Block.blocksList[dummy.getImitationId()].onBlockExploded(world, x, y, z, explosion);
    }
	
	@Override
	public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].canPlaceTorchOnTop(world, x, y, z);
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].getPickBlock(target, world, x, y, z);
    }
	
	@Override
	public boolean isBlockFoliage(World world, int x, int y, int z)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].isBlockFoliage(world, x, y, z);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean addBlockDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].addBlockDestroyEffects(world, x, y, z, meta, effectRenderer);
    }
	
	@Override
	public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].canSustainPlant(world, x, y, z, direction, plant);
	}
	
	@Override
	public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		Block.blocksList[dummy.getImitationId()].onPlantGrow(world, x, y, z, sourceX, sourceY, sourceZ);
    }
	
	@Override
	public boolean isFertile(World world, int x, int y, int z)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].isFertile(world, x, y, z);
    }
	
	@Override
	public int getLightOpacity(World world, int x, int y, int z)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].getLightOpacity(world, x, y, z);
    }
	
	@Override
	public boolean canDragonDestroy(World world, int x, int y, int z)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].canDragonDestroy(world, x, y, z);
    }
	
	@Override
	public boolean isBeaconBase(World worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)worldObj.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].isBeaconBase(worldObj, x, y, z, beaconX, beaconY, beaconZ);
    }
	
	@Override
	public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)worldObj.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].rotateBlock(worldObj, x, y, z, axis);
    }
	
	@Override
	public ForgeDirection[] getValidRotations(World worldObj, int x, int y, int z)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)worldObj.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].getValidRotations(worldObj, x, y, z);
    }
	
	@Override
	public float getEnchantPowerBonus(World world, int x, int y, int z)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].getEnchantPowerBonus(world, x, y, z);
    }
	
	@Override
	public boolean recolourBlock(World world, int x, int y, int z, ForgeDirection side, int colour)
    {
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		return Block.blocksList[dummy.getImitationId()].recolourBlock(world, x, y, z, side, colour);
    }
}
