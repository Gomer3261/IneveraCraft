package com.ggollmer.inevera.configuration;

import java.io.File;
import java.util.logging.Level;

import com.ggollmer.inevera.lib.BlockIds;
import com.ggollmer.inevera.lib.BlockNames;
import com.ggollmer.inevera.lib.ItemIds;
import com.ggollmer.inevera.lib.ItemNames;
import com.ggollmer.inevera.lib.Reference;

import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.Configuration;

/**
 * IneveraCraft
 *
 * ConfigurationHandler.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ConfigurationHandler
{
	public static Configuration configuration;
	
	public static void init(File configFile)
	{
		configuration = new Configuration(configFile);
		
		try
		{
			configuration.load();
			
			/* BlockId Configuration */
			BlockIds.LIQUEFYING_CAULDRON = configuration.getBlock(BlockNames.LIQUEFYING_CAULDRON_NAME, BlockIds.LIQUEFYING_CAULDRON_DEFAULT).getInt(BlockIds.LIQUEFYING_CAULDRON_DEFAULT);
			BlockIds.SULFURIC_LIQUEFIER = configuration.getBlock(BlockNames.SULFURIC_LIQUEFIER_NAME, BlockIds.SULFURIC_LIQUEFIER_DEFAULT).getInt(BlockIds.SULFURIC_LIQUEFIER_DEFAULT);
			
			/* ItemId Configuration */
			ItemIds.DEMON_FLESH = configuration.getItem(ItemNames.DEMON_FLESH_NAME, ItemIds.DEMON_FLESH_DEFAULT).getInt(ItemIds.DEMON_FLESH_DEFAULT);
			ItemIds.DEMON_BONE = configuration.getItem(ItemNames.DEMON_BONE_NAME, ItemIds.DEMON_BONE_DEFAULT).getInt(ItemIds.DEMON_BONE_DEFAULT);
			ItemIds.BASIC_WARDING_TOOLS = configuration.getItem(ItemNames.BASIC_WARDING_TOOLS_NAME, ItemIds.BASIC_WARDING_TOOLS_DEFAULT).getInt(ItemIds.BASIC_WARDING_TOOLS_DEFAULT);
			
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its configuration");
		}
		finally
		{
			configuration.save();
		}
	}
}
