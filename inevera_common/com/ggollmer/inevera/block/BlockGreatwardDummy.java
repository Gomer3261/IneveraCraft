package com.ggollmer.inevera.block;

import java.util.ArrayList;
import java.util.Random;

import com.ggollmer.inevera.core.helper.LogHelper;
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
import net.minecraft.util.Vec3;
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
	/* Variables that are changed before a new instance is created */
	public int imitationId=2;
	public int imitationMetadata=0;
	public TileGreatwardCore core;
	

	/**
	 * @param id The id of the new block
	 */
	public BlockGreatwardDummy(int id, Material material, String name)
	{
		// TODO: Make sound file replicate correctly.
		// TODO: Helper method for checking which material the block is.
		// TODO: Add special effects while breaking.
		// TODO: Make greatward core tile created dummies base on material.
		// TODO: Allow glass to be a ward component.
		super(id, material);
		this.setUnlocalizedName(name);
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
		return (TileEntity)new TileGreatwardDummy(imitationId, imitationMetadata, core);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		
		if(dummy != null && dummy.getCoreTile() != null)
		{
			dummy.getCoreTile().invalidateGreatward();
			this.imitationId = dummy.getImitationId();
			this.imitationMetadata = dummy.getImitationMetadata();
		}
		
		world.removeBlockTileEntity(x, y, z);
		
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
	
	/**
	 * @param oldId
	 * @param oldMetadata
	 * @param tileGreatwardCore
	 */
	public void setDummyValues(int newId, int newMetadata,
			TileGreatwardCore newCore)
	{
		this.imitationId = newId;
		this.imitationMetadata = newMetadata;
		this.core = newCore;
	}
	
	private void updateImitationData(IBlockAccess world, int x, int y, int z)
	{
		TileGreatwardDummy dummy = (TileGreatwardDummy)world.getBlockTileEntity(x, y, z);
		if(dummy != null)
		{
			imitationId = dummy.getImitationId();
			imitationMetadata = dummy.getImitationMetadata();
		}
	}
	
	/* Mimicking the properties of the copied block! */
	
	@Override
	public float getBlockHardness(World world, int x, int y, int z)
	{
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].getBlockHardness(world, x, y, z);
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int par5)
    {
		updateImitationData(blockAccess, x, y, z);
        return Block.blocksList[imitationId].getIcon(par5, blockAccess.getBlockMetadata(x, y, z));
    }
	
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int par5)
	{
		updateImitationData(world, x, y, z);
		Block.blocksList[imitationId].onBlockDestroyedByPlayer(world, x, y, z, par5);
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return Block.blocksList[imitationId].quantityDropped(random);
	}
	
	@Override
	public int idDropped(int par1, Random random, int par3)
    {
        return Block.blocksList[imitationId].idDropped(par1, random, par3);
    }
	
	@Override
	public float getPlayerRelativeBlockHardness(EntityPlayer player, World world, int x, int y, int z)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].getPlayerRelativeBlockHardness(player, world, x, y, z);
    }
	
	@Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int par5, float par6, int par7)
    {
		updateImitationData(world, x, y, z);
		Block.blocksList[imitationId].dropBlockAsItemWithChance(world, x, y, z, par5, par6, par7);
    }
	
	@Override
	public int damageDropped(int par1)
	{
		return Block.blocksList[imitationId].damageDropped(par1);
	}
	
	@Override
	public float getExplosionResistance(Entity par1Entity)
	{
		return Block.blocksList[imitationId].getExplosionResistance(par1Entity);
	}
	
	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion par5Explosion)
	{
		updateImitationData(world, x, y, z);
		Block.blocksList[imitationId].onBlockDestroyedByExplosion(world, x, y, z, par5Explosion);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].onBlockActivated(world, x, y, z, par5EntityPlayer, par6, par7, par8, par9);
    }
	
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity par5Entity)
	{
		updateImitationData(world, x, y, z);
		Block.blocksList[imitationId].onEntityWalking(world, x, y, z, par5Entity);
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int par5, float par6, float par7, float par8, int par9)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].onBlockPlaced(world, x, y, z, par5, par6, par7, par8, par9);
    }
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer par5EntityPlayer)
	{
		updateImitationData(world, x, y, z);
		Block.blocksList[imitationId].onBlockClicked(world, x, y, z, par5EntityPlayer);
	}
	
	@Override
	public void velocityToAddToEntity(World world, int x, int y, int z, Entity par5Entity, Vec3 par6Vec3)
	{
		updateImitationData(world, x, y, z);
		Block.blocksList[imitationId].velocityToAddToEntity(world, x, y, z, par5Entity, par6Vec3);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity par5Entity)
	{
		updateImitationData(world, x, y, z);
		Block.blocksList[imitationId].onEntityCollidedWithBlock(world, x, y, z, par5Entity);
	}
	
	@Override
	public void harvestBlock(World world, EntityPlayer par2EntityPlayer, int x, int y, int z, int par6)
	{
		updateImitationData(world, x, y, z);
		Block.blocksList[imitationId].harvestBlock(world, par2EntityPlayer, x, y, z, par6);
	}
	
	@Override
	public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        return Block.blocksList[imitationId].quantityDroppedWithBonus(par1, par2Random);
    }
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
	{
		updateImitationData(world, x, y, z);
		Block.blocksList[imitationId].onBlockPlacedBy(world, x, y, z, par5EntityLiving, par6ItemStack);
	}
	
	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int par5)
	{
		updateImitationData(world, x, y, z);
		Block.blocksList[imitationId].onPostBlockPlaced(world, x, y, z, par5);
	}
	
	@Override
	public boolean onBlockEventReceived(World world, int x, int y, int z, int par5, int par6)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].onBlockEventReceived(world, x, y, z, par5, par6);
    }
	
	@Override
	public int getMobilityFlag()
	{
		return Block.blocksList[imitationId].getMobilityFlag();
	}
	
	@Override
	public float getAmbientOcclusionLightValue(IBlockAccess blockAccess, int x, int y, int z)
	{
		updateImitationData(blockAccess, x, y, z);
		return Block.blocksList[imitationId].getAmbientOcclusionLightValue(blockAccess, x, y, z);
	}
	
	@Override
	public void onFallenUpon(World world, int x, int y, int z, Entity par5Entity, float par6)
	{
		updateImitationData(world, x, y, z);
		Block.blocksList[imitationId].onFallenUpon(world, x, y, z, par5Entity, par6);
	}
	
	@Override
	public int idPicked(World world, int x, int y, int z)
    {
        return ((TileGreatwardDummy)world.getBlockTileEntity(x, y, z)).getImitationId();
    }
	
	@Override
	public int getDamageValue(World world, int x, int y, int z)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].damageDropped(world.getBlockMetadata(x, y, z));
    }
	
	@Override
	public void onSetBlockIDWithMetaData(World world, int x, int y, int z, int par5)
	{
		updateImitationData(world, x, y, z);
		Block.blocksList[imitationId].onSetBlockIDWithMetaData(world, x, y, z, par5);
	}
	
	@Override
	public void fillWithRain(World world, int x, int y, int z)
	{
		updateImitationData(world, x, y, z);
		Block.blocksList[imitationId].fillWithRain(world, x, y, z);
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		updateImitationData(world, x, y, z);
		return Block.lightValue[imitationId];
	}
	
	@Override
	public boolean isLadder(World world, int x, int y, int z)
	{
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].isLadder(world, x, y, z);
	}
	
	@Override
	public boolean isBlockNormalCube(World world, int x, int y, int z)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].isBlockNormalCube(world, x, y, z);
    }
	
	@Override
	public boolean isBlockReplaceable(World world, int x, int y, int z)
	{
		return false;
	}
	
	@Override
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].removeBlockByPlayer(world, player, x, y, z);
    }
	
	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
    {	
		updateImitationData(world, x, y, z);
        return blockFlammability[imitationId];
    }
	
	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].getFlammability(world, x, y, z, metadata, face) > 0;
    }
	
	@Override
	public int getFireSpreadSpeed(World world, int x, int y, int z, int metadata, ForgeDirection face)
    {
		updateImitationData(world, x, y, z);
        return blockFireSpreadSpeed[imitationId];
    }
	
	@Override
	public boolean isFireSource(World world, int x, int y, int z, int metadata, ForgeDirection side)
	{
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].isFireSource(world, x, y, z, metadata, side);
	}
	
	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].getBlockDropped(world, x, y, z, metadata, fortune);
    }
	
	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].canSilkHarvest(world, player, x, y, z, metadata);
    }
	
	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].canCreatureSpawn(type, world, x, y, z);
    }
	
	@Override
	public boolean isBed(World world, int x, int y, int z, EntityLiving player)
	{
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].isBed(world, x, y, z, player);
	}
	
	@Override
	public void beginLeavesDecay(World world, int x, int y, int z)
	{
		updateImitationData(world, x, y, z);
		Block.blocksList[imitationId].beginLeavesDecay(world, x, y, z);
	}
	
	@Override
	public boolean canSustainLeaves(World world, int x, int y, int z)
	{
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].canSustainLeaves(world, x, y, z);
	}
	
	@Override
	public boolean isLeaves(World world, int x, int y, int z)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].isLeaves(world, x, y ,z);
    }
	
	@Override
	public boolean isWood(World world, int x, int y, int z)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].isWood(world, x, y, z);
    }
	
	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
	}
	
	@Override
	public void onBlockExploded(World world, int x, int y, int z, Explosion explosion)
    {
		updateImitationData(world, x, y, z);
		Block.blocksList[imitationId].onBlockExploded(world, x, y, z, explosion);
    }
	
	@Override
	public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
	{
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].canPlaceTorchOnTop(world, x, y, z);
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].getPickBlock(target, world, x, y, z);
    }
	
	@Override
	public boolean isBlockFoliage(World world, int x, int y, int z)
	{
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].isBlockFoliage(world, x, y, z);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean addBlockDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].addBlockDestroyEffects(world, x, y, z, meta, effectRenderer);
    }
	
	@Override
	public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
	{
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].canSustainPlant(world, x, y, z, direction, plant);
	}
	
	@Override
	public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
    {
		updateImitationData(world, x, y, z);
		Block.blocksList[imitationId].onPlantGrow(world, x, y, z, sourceX, sourceY, sourceZ);
    }
	
	@Override
	public boolean isFertile(World world, int x, int y, int z)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].isFertile(world, x, y, z);
    }
	
	@Override
	public int getLightOpacity(World world, int x, int y, int z)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].getLightOpacity(world, x, y, z);
    }
	
	@Override
	public boolean canDragonDestroy(World world, int x, int y, int z)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].canDragonDestroy(world, x, y, z);
    }
	
	@Override
	public boolean isBeaconBase(World worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
		updateImitationData(worldObj, x, y, z);
		return Block.blocksList[imitationId].isBeaconBase(worldObj, x, y, z, beaconX, beaconY, beaconZ);
    }
	
	@Override
	public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis)
    {
		updateImitationData(worldObj, x, y, z);
		return Block.blocksList[imitationId].rotateBlock(worldObj, x, y, z, axis);
    }
	
	@Override
	public ForgeDirection[] getValidRotations(World worldObj, int x, int y, int z)
    {
		updateImitationData(worldObj, x, y, z);
		return Block.blocksList[imitationId].getValidRotations(worldObj, x, y, z);
    }
	
	@Override
	public float getEnchantPowerBonus(World world, int x, int y, int z)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].getEnchantPowerBonus(world, x, y, z);
    }
	
	@Override
	public boolean recolourBlock(World world, int x, int y, int z, ForgeDirection side, int colour)
    {
		updateImitationData(world, x, y, z);
		return Block.blocksList[imitationId].recolourBlock(world, x, y, z, side, colour);
    }
}
