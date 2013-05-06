package com.ggollmer.inevera.core.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

/**
 * IneveraCraft
 *
 * CommonProxy
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class CommonProxy implements IGuiHandler
{
	public void registerSoundHandler()
	{
    }

    public void initRenderingAndTextures()
    {
    }

    public void registerTileEntities()
    {
        //GameRegistry.registerTileEntity(Tile.class, TileNames.TE_TILE_NAME);
    }
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z)
	{
		// TODO Fill out as GUIs are added to the mod.
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z)
	{
		// TODO Fill out as GUIs are added to the mod.
		return null;
	}

}
