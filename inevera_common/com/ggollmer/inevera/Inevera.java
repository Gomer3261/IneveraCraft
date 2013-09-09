package com.ggollmer.inevera;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.creativetab.CreativeTabs;

import com.ggollmer.inevera.block.IneveraBlocks;
import com.ggollmer.inevera.client.effect.IneveraEffectHelper;
import com.ggollmer.inevera.configuration.ConfigurationHandler;
import com.ggollmer.inevera.core.handlers.LocalizationHandler;
import com.ggollmer.inevera.core.helper.LogHelper;
import com.ggollmer.inevera.core.proxy.CommonProxy;
import com.ggollmer.inevera.creativetab.CreativeTabInevera;
import com.ggollmer.inevera.greatward.GreatwardManager;
import com.ggollmer.inevera.greatward.GreatwardHelper;
import com.ggollmer.inevera.item.IneveraItems;
import com.ggollmer.inevera.item.crafting.IneveraRecipes;
import com.ggollmer.inevera.lib.Reference;
import com.ggollmer.inevera.lib.Strings;
import com.ggollmer.inevera.network.PacketHandler;
import com.ggollmer.inevera.world.gen.IneveraWorldGen;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLFingerprintViolationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.relauncher.Side;

/**
 * IneveraCraft
 *
 * Inevera.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@Mod(
		modid=Reference.MOD_ID,
		name=Reference.MOD_NAME,
		version=Reference.MOD_VERSION,
		dependencies = Reference.DEPENDENCIES,
		certificateFingerprint = Reference.FINGERPRINT
		)
@NetworkMod(
		channels = { Reference.CHANNEL_NAME },
		clientSideRequired = true,
		serverSideRequired = false,
		packetHandler = PacketHandler.class)
public class Inevera
{
	@Instance(Reference.MOD_ID)
	public static Inevera instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;
	
	public static CreativeTabs tabsInevera = new CreativeTabInevera(CreativeTabs.getNextID(), Reference.MOD_ID);
	
	@EventHandler
    public void invalidFingerprint(FMLFingerprintViolationEvent event)
	{
        LogHelper.log(Level.SEVERE, Strings.LOG_FINGERPRINT_ERROR);
    }
	
	/**
	 * Occurs before the mod is hooked into minecraft
	 * Initialize blocks and items
	 * 
	 * should only be called by forge
	 * @param event the forge event calling this function
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		/* Initialize our logger */
		LogHelper.init();
		
		/* Load localizations into minecraft */
		LocalizationHandler.loadLanguages();
		
		/* Load configuration settings */
		ConfigurationHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.CHANNEL_NAME + File.separator + Reference.MOD_ID + ".cfg"));
		
		/* Instantiate IneveraCraft's blocks */
		IneveraBlocks.init();
		
		/* Instantiate IneveraCraft's Items */
		IneveraItems.init();
		
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
			/* Instantiate IneveraCraft's Effect Helper */
			IneveraEffectHelper.init();
		}
		
		/* Instantiate IneveraCraft's Greatward Helper */
		GreatwardHelper.init();
		
		/* Instantiate IneveraCraft's Greatward Manager */
		GreatwardManager.init();
	}
	
	/**
	 * Occurs as the mod is hooked into minecraft
	 * (Mod hookup order may be undefined)
	 * 
	 * Should only be called by forge
	 * @param event the forge event calling this function
	 */
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		/* Instantiate IneveraCraft's Tile Entities */
        proxy.registerTileEntities();
        
        /* Instantiate IneveraCraft's Custom Renderers */
        proxy.initRenderingAndTextures();
        
        /* Instantiate IneveraCraft's World Generation */
		IneveraWorldGen.init();
        
        /* Instantiate IneveraCraft's Recipes */
        IneveraRecipes.init();
	}
	
	/**
	 * Occurs when all mods have been initialized
	 * (Code that works cross mod)
	 * 
	 * Should only be called by forge
	 * @param event the forge event calling this function
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
