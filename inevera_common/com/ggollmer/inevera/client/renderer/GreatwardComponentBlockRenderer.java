package com.ggollmer.inevera.client.renderer;

import org.lwjgl.opengl.GL11;

import com.ggollmer.inevera.block.BlockGreatwardComponent;
import com.ggollmer.inevera.lib.RenderIds;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

/**
 * IneveraCraft
 *
 * GreatwardCoreRenderer.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GreatwardComponentBlockRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		if(!(block instanceof BlockGreatwardComponent))
		{
			return;
		}
		
		renderer.setOverrideBlockTexture(((BlockGreatwardComponent)block).getCoreIcon(~BlockGreatwardComponent.ACTIVE_BIT));
		GL11.glScalef(0.99f, 0.99f, 0.99f);
		renderer.renderBlockAsItem(Block.stone, metadata, modelID);
		renderer.clearOverrideBlockTexture();
		GL11.glScalef(1.0101010101f, 1.0101010101f, 1.0101010101f);
		renderer.setOverrideBlockTexture(((BlockGreatwardComponent)block).getIcon(0, metadata));
		renderer.renderBlockAsItem(Block.glass, metadata, modelID);
		renderer.clearOverrideBlockTexture();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		if(!(block instanceof BlockGreatwardComponent))
		{
			return false;
		}
		
		GL11.glEnable(GL11.GL_BLEND);
		renderInnerCube(world, x, y, z, block, ((BlockGreatwardComponent)block).getCoreIcon(world.getBlockMetadata(x, y, z)));
		renderer.renderStandardBlock(block, x, y, z);
		GL11.glDisable(GL11.GL_BLEND);
		
		return true;
	}
	
	public void renderInnerCube(IBlockAccess world, int x, int y, int z, Block block, Icon icon)
	{
		Tessellator.instance.setBrightness(1024);
		Tessellator.instance.setColorRGBA(255, 255, 255, 255);
		
		//UP
		RenderUtils.drawTexturedQuad(x + 0.02f, y + 0.98f, z + 0.02f, icon, 0.96d, 0d, 0.96d, ForgeDirection.UP);
		
		//DOWN
		RenderUtils.drawTexturedQuad(x + 0.02f, y + 0.02f, z + 0.02f, icon, 0.96d, 0d, 0.96d, ForgeDirection.DOWN);
		
		//NORTH
		RenderUtils.drawTexturedQuad(x + 0.02f, y + 0.02f, z + 0.98f, icon, 0.96d, 0.96d, 0d, ForgeDirection.NORTH);
		
		//SOUTH
		RenderUtils.drawTexturedQuad(x + 0.02f, y + 0.02f, z + 0.02f, icon, 0.96d, 0.96d, 0d, ForgeDirection.SOUTH);
		
		//EAST
		RenderUtils.drawTexturedQuad(x + 0.98f, y + 0.02f, z + 0.02f, icon, 0d, 0.96d, 0.96d, ForgeDirection.EAST);
		
		//WEST
		RenderUtils.drawTexturedQuad(x + 0.02f, y + 0.02f, z + 0.02f, icon, 0d, 0.96d, 0.96d, ForgeDirection.WEST);
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return RenderIds.greatwardCoreRenderer;
	}
}
