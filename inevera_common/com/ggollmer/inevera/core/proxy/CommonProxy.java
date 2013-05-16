package com.ggollmer.inevera.core.proxy;

import com.ggollmer.inevera.lib.TileNames;
import com.ggollmer.inevera.tileentity.TileGreatwardCoreMinor;
import com.ggollmer.inevera.tileentity.TileGreatwardPiece;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

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
    	GameRegistry.registerTileEntity(TileGreatwardPiece.class, TileNames.TE_GREATWARD_PIECE_NAME);
        GameRegistry.registerTileEntity(TileGreatwardCoreMinor.class, TileNames.TE_GREATWARD_CORE_MINOR_NAME);
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

	public void handleTileEntityPacket(int x, int y, int z, ForgeDirection orientation, String customName)
	{
	}

	public void handleGreatwardPiecePacket(int x, int y, int z, int coreX, int coreY, int coreZ)
	{
	}
}
