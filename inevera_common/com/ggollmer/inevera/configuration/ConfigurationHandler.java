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
			BlockIds.GREATWARD_WOOD_PIECE = configuration.getBlock(BlockNames.GREATWARD_WOOD_PIECE_NAME, BlockIds.GREATWARD_WOOD_PIECE_DEFAULT).getInt(BlockIds.GREATWARD_WOOD_PIECE_DEFAULT);
			
			BlockIds.MINOR_WARD_CORE = configuration.getBlock(BlockNames.MINOR_WARD_CORE_NAME, BlockIds.MINOR_WARD_CORE_DEFAULT).getInt(BlockIds.MINOR_WARD_CORE_DEFAULT);
			BlockIds.MINOR_HORA_CELL = configuration.getBlock(BlockNames.MINOR_HORA_CELL_NAME, BlockIds.MINOR_HORA_CELL_DEFAULT).getInt(BlockIds.MINOR_HORA_CELL_DEFAULT);
			
			BlockIds.ACID_VAT = configuration.getBlock(BlockNames.ACID_VAT_NAME, BlockIds.ACID_VAT_DEFAULT).getInt(BlockIds.ACID_VAT_DEFAULT);
			
			/* ItemId Configuration */
			ItemIds.GREATWARD_WOOD_PIECE = configuration.getItem(ItemNames.GREATWARD_WOOD_PIECE_NAME, ItemIds.GREATWARD_WOOD_PIECE_DEFAULT).getInt(ItemIds.GREATWARD_WOOD_PIECE_DEFAULT);
			
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
