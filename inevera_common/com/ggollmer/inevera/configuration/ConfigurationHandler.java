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
			BlockIds.GREATWARD_DUMMY_GROUND = configuration.getBlock(BlockNames.GREATWARD_DUMMY_GROUND_NAME, BlockIds.GREATWARD_DUMMY_GROUND_DEFAULT).getInt(BlockIds.GREATWARD_DUMMY_GROUND_DEFAULT);
			BlockIds.GREATWARD_DUMMY_WOOD = configuration.getBlock(BlockNames.GREATWARD_DUMMY_WOOD_NAME, BlockIds.GREATWARD_DUMMY_WOOD_DEFAULT).getInt(BlockIds.GREATWARD_DUMMY_WOOD_DEFAULT);
			BlockIds.GREATWARD_DUMMY_ROCK = configuration.getBlock(BlockNames.GREATWARD_DUMMY_ROCK_NAME, BlockIds.GREATWARD_DUMMY_ROCK_DEFAULT).getInt(BlockIds.GREATWARD_DUMMY_ROCK_DEFAULT);
			BlockIds.GREATWARD_DUMMY_IRON = configuration.getBlock(BlockNames.GREATWARD_DUMMY_IRON_NAME, BlockIds.GREATWARD_DUMMY_IRON_DEFAULT).getInt(BlockIds.GREATWARD_DUMMY_IRON_DEFAULT);
			BlockIds.GREATWARD_DUMMY_CLOTH = configuration.getBlock(BlockNames.GREATWARD_DUMMY_CLOTH_NAME, BlockIds.GREATWARD_DUMMY_CLOTH_DEFAULT).getInt(BlockIds.GREATWARD_DUMMY_CLOTH_DEFAULT);
			BlockIds.GREATWARD_DUMMY_SAND = configuration.getBlock(BlockNames.GREATWARD_DUMMY_SAND_NAME, BlockIds.GREATWARD_DUMMY_SAND_DEFAULT).getInt(BlockIds.GREATWARD_DUMMY_SAND_DEFAULT);
			BlockIds.GREATWARD_DUMMY_GLASS = configuration.getBlock(BlockNames.GREATWARD_DUMMY_GLASS_NAME, BlockIds.GREATWARD_DUMMY_GLASS_DEFAULT).getInt(BlockIds.GREATWARD_DUMMY_GLASS_DEFAULT);
			
			BlockIds.MINOR_WARD_CORE = configuration.getBlock(BlockNames.MINOR_WARD_CORE_NAME, BlockIds.MINOR_WARD_CORE_DEFAULT).getInt(BlockIds.MINOR_WARD_CORE_DEFAULT);
			BlockIds.MINOR_HORA_CELL = configuration.getBlock(BlockNames.MINOR_HORA_CELL_NAME, BlockIds.MINOR_HORA_CELL_DEFAULT).getInt(BlockIds.MINOR_HORA_CELL_DEFAULT);
			
			BlockIds.ACID_VAT = configuration.getBlock(BlockNames.ACID_VAT_NAME, BlockIds.ACID_VAT_DEFAULT).getInt(BlockIds.ACID_VAT_DEFAULT);
			
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
