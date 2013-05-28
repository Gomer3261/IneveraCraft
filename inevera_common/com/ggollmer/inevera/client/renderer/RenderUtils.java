package com.ggollmer.inevera.client.renderer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraftforge.common.ForgeDirection;

/**
 * IneveraCraft
 *
 * RenderUtils.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class RenderUtils
{
	public static void drawTexturedQuad(double x, double y, double z, Icon icon, double width, double height, double depth, ForgeDirection side) {

        Tessellator tessellator = Tessellator.instance;
        
        switch(side)
        {
        	case UP:
        		tessellator.addVertexWithUV(x + 0, y + height, z + depth, icon.getMinU(), icon.getMaxV());
            	tessellator.addVertexWithUV(x + width, y + height, z + depth, icon.getMaxU(), icon.getMaxV());
            	tessellator.addVertexWithUV(x + width, y + 0, z + 0, icon.getMaxU(), icon.getMinV());
            	tessellator.addVertexWithUV(x + 0, y + 0, z + 0, icon.getMinU(), icon.getMinV());
        		break;
        	case DOWN:
        		tessellator.addVertexWithUV(x + 0, y + 0, z + 0, icon.getMinU(), icon.getMinV());
        		tessellator.addVertexWithUV(x + width, y + 0, z + 0, icon.getMaxU(), icon.getMinV());
        		tessellator.addVertexWithUV(x + width, y + height, z + depth, icon.getMaxU(), icon.getMaxV());
        		tessellator.addVertexWithUV(x + 0, y + height, z + depth, icon.getMinU(), icon.getMaxV());
        		break;
        	case NORTH:
        		tessellator.addVertexWithUV(x + 0, y + 0, z + 0, icon.getMinU(), icon.getMinV());
            	tessellator.addVertexWithUV(x + width, y + 0, z + depth, icon.getMaxU(), icon.getMinV());
            	tessellator.addVertexWithUV(x + width, y + height, z + depth, icon.getMaxU(), icon.getMaxV());
            	tessellator.addVertexWithUV(x + 0, y + height, z + 0, icon.getMinU(), icon.getMaxV());
        		break;
        	case SOUTH:
        		tessellator.addVertexWithUV(x + 0, y + height, z + 0, icon.getMinU(), icon.getMaxV());
            	tessellator.addVertexWithUV(x + width, y + height, z + depth, icon.getMaxU(), icon.getMaxV());
            	tessellator.addVertexWithUV(x + width, y + 0, z + depth, icon.getMaxU(), icon.getMinV());
            	tessellator.addVertexWithUV(x + 0, y + 0, z + 0, icon.getMinU(), icon.getMinV());
        		break;
        	case EAST:
        		tessellator.addVertexWithUV(x + 0, y + height, z + 0, icon.getMinU(), icon.getMaxV());
        		tessellator.addVertexWithUV(x + width, y + height, z + depth, icon.getMaxU(), icon.getMaxV());
        		tessellator.addVertexWithUV(x + width, y + 0, z + depth, icon.getMaxU(), icon.getMinV());
        		tessellator.addVertexWithUV(x + 0, y + 0, z + 0, icon.getMinU(), icon.getMinV());
        		break;
        	case WEST:
        		tessellator.addVertexWithUV(x + 0, y + 0, z + 0, icon.getMinU(), icon.getMinV());
            	tessellator.addVertexWithUV(x + width, y + 0, z + depth, icon.getMaxU(), icon.getMinV());
            	tessellator.addVertexWithUV(x + width, y + height, z + depth, icon.getMaxU(), icon.getMaxV());
            	tessellator.addVertexWithUV(x + 0, y + height, z + 0, icon.getMinU(), icon.getMaxV());
        		break;
    		default:
    			break;
        }
    }
}
