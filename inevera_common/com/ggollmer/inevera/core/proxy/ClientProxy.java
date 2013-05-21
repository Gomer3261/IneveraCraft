package com.ggollmer.inevera.core.proxy;

import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.common.ForgeDirection;

import com.ggollmer.inevera.client.renderer.TileGreatwardCoreRenderer;
import com.ggollmer.inevera.lib.RenderIds;
import com.ggollmer.inevera.tileentity.TileGreatwardCore;
import com.ggollmer.inevera.tileentity.TileGreatwardPiece;
import com.ggollmer.inevera.tileentity.TileInevera;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * IneveraCraft
 *
 * ClientProxy
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ClientProxy extends CommonProxy
{
	public static int renderPass;

	@Override
    public void registerSoundHandler()
	{
		// TODO Create sound handler class
        /*MinecraftForge.EVENT_BUS.register(new SoundHandler());*/
    }
	
	@Override
	public void initRenderingAndTextures()
	{
		RenderIds.greatwardCoreRenderer = RenderingRegistry.getNextAvailableRenderId();
		
		RenderingRegistry.registerBlockHandler(new TileGreatwardCoreRenderer());
		
		//MinecraftForgeClient.registerItemRenderer(BlockIds.MINOR_WARD_CORE, renderer)
	}
	
	@Override
	public void registerTileEntities()
	{
		super.registerTileEntities();
		
		//ClientRegistry.bindTileEntitySpecialRenderer(TileGreatwardCoreMinor.class, new TileGreatwardCoreRenderer());
	}
	
	@Override
    public void handleTileEntityPacket(int x, int y, int z, ForgeDirection orientation, String customName) {

        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getBlockTileEntity(x, y, z);

        if (tileEntity != null) {
            if (tileEntity instanceof TileInevera) {
                ((TileInevera) tileEntity).setOrientation(orientation);
                ((TileInevera) tileEntity).setCustomName(customName);
            }
        }
    }
	
	@Override
	public void handleGreatwardPiecePacket(int x, int y, int z, int coreX, int coreY, int coreZ)
	{
		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getBlockTileEntity(x, y, z);
		
		if (tileEntity != null) {
            if (tileEntity instanceof TileGreatwardPiece) {
            	((TileGreatwardPiece) tileEntity).setCorePosition(coreX, coreY, coreZ);
            }
		}
	}
	
	@Override
	public void handleGreatwardCorePacket(int x, int y, int z, boolean valid, byte direction, List<ChunkCoordinates> pieces)
	{
		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getBlockTileEntity(x, y, z);
		
		if (tileEntity != null) {
            if (tileEntity instanceof TileGreatwardCore) {
            	((TileGreatwardCore) tileEntity).setValidGreatward(valid);
            	((TileGreatwardCore) tileEntity).setWardDirection(ForgeDirection.getOrientation(direction));
            	((TileGreatwardCore) tileEntity).setPiecesCoordList(pieces);
            }
		}
	}
}
