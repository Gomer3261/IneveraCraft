package com.ggollmer.inevera.core.proxy;

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
		// TODO Fill in with custom renderers
	}
	
	@Override
	public void registerTileEntities()
	{
		super.registerTileEntities();
		
		// TODO Associate custom renderers with tile entities
	}
}
