package com.ggollmer.inevera;

import net.minecraft.creativetab.CreativeTabs;

import com.ggollmer.inevera.creativetab.CreativeTabInevera;
import com.ggollmer.inevera.lib.Reference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

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
		version=Reference.MOD_VERSION
		)
public class Inevera
{
	@Instance(Reference.MOD_ID)
	public static Inevera instance;
	
	public static CreativeTabs tabsInevera = new CreativeTabInevera(CreativeTabs.getNextID(), Reference.MOD_ID);
	
	/**
	 * Occurs before the mod is hooked into minecraft
	 * Initialize blocks and items
	 * 
	 * should only be called by forge
	 * @param event the forge event calling this function
	 */
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		
	}
	
	/**
	 * Occurs as the mod is hooked into minecraft
	 * (Mod hookup order may be undefined)
	 * 
	 * Should only be called by forge
	 * @param event the forge event calling this function
	 */
	@Init
	public void init(FMLInitializationEvent event)
	{
		
	}
	
	/**
	 * Occurs when all mods have been initialized
	 * (Code that works cross mod)
	 * 
	 * Should only be called by forge
	 * @param event the forge event calling this function
	 */
	@PostInit
	public void init(FMLPostInitializationEvent event)
	{
		
	}
}
