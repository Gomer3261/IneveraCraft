package com.ggollmer.inevera.client.renderer;

import org.lwjgl.opengl.GL11;

import com.ggollmer.inevera.block.BlockGreatwardComponent;
import com.ggollmer.inevera.lib.RenderIds;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
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
public class TileGreatwardCoreRenderer implements ISimpleBlockRenderingHandler
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
		
		//renderer.setOverrideBlockTexture(((BlockGreatwardComponent)block).getCoreIcon(world.getBlockMetadata(x, y, z)));
		//GL11.glScalef(0.99f, 0.99f, 0.99f);
		//renderer.renderStandardBlockWithColorMultiplier(Block.glowStone, x, y, z, 255, 255, 255);
		renderer.renderBlockUsingTexture(Block.glowStone, x, y, z, ((BlockGreatwardComponent)block).getCoreIcon(world.getBlockMetadata(x, y, z)));
		//renderer.clearOverrideBlockTexture();
		//GL11.glScalef(1.0101010100f, 1.0101010100f, 1.0101010100f);
		renderer.renderStandardBlock(block, x, y, z);
		
		return true;
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
