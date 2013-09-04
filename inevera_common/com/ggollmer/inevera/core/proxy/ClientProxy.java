package com.ggollmer.inevera.core.proxy;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.ForgeDirection;

import com.ggollmer.inevera.client.effect.IneveraEffectHelper;
import com.ggollmer.inevera.client.renderer.GreatwardComponentBlockRenderer;
import com.ggollmer.inevera.greatward.GreatwardManager;
import com.ggollmer.inevera.lib.RenderIds;
import com.ggollmer.inevera.tileentity.TileGreatwardCore;
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
		
		RenderingRegistry.registerBlockHandler(new GreatwardComponentBlockRenderer());
		
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
	public void handleGreatwardCorePacket(int x, int y, int z, boolean valid, byte direction, byte orientation, List<ChunkCoordinates> pieces, int blockId, String type, String target, String attribute, String effect, List<String> augments)
	{
		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getBlockTileEntity(x, y, z);
		
		if (tileEntity != null) {
            if (tileEntity instanceof TileGreatwardCore) {
            	((TileGreatwardCore) tileEntity).updateWardFromPacket(valid, direction, orientation, pieces, blockId, type, target, attribute, effect, augments);
            }
		}
	}
	
	@Override
	public void handleGreatwardActionPacket(String type, int dimension_id, boolean target_is_entity, List<Integer> target_ids, List<Vec3> target_positions, List<String> args)
	{
		if(FMLClientHandler.instance().getClient().theWorld.provider.dimensionId == dimension_id)
		{
			for(int i=0; i<target_ids.size(); i++)
			{
				GreatwardManager.getAttributeByName(type).performGreatwardAction(Minecraft.getMinecraft().theWorld, target_is_entity, target_ids.get(i), target_positions.get(i).xCoord, target_positions.get(i).yCoord, target_positions.get(i).zCoord, args.get(i));
			}
		}
	}
	
	@Override
	public void handleIneveraEffectPacket(String type, double posX, double posY, double posZ, String args)
	{
		IneveraEffectHelper.spawnEffect(type, Minecraft.getMinecraft().theWorld, Minecraft.getMinecraft().effectRenderer, posX, posY, posZ, args);
	}
}
