package com.ggollmer.inevera.block;

import com.ggollmer.inevera.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.ResourceLocation;

/**
 * IneveraCraft
 *
 * BlockInevera.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public abstract class BlockInevera extends BlockContainer
{

	/**
	 * @param par1
	 * @param par2Material
	 */
	protected BlockInevera(int id, Material material)
	{
		super(id, material);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		blockIcon = iconRegister.registerIcon(new ResourceLocation(Reference.MOD_ID.toLowerCase(), getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1)).toString());
	}
}
