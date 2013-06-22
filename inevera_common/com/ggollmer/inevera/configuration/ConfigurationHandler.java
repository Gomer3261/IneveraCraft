package com.ggollmer.inevera.configuration;

import java.io.File;
import java.util.logging.Level;

import com.ggollmer.inevera.lib.BlockIds;
import com.ggollmer.inevera.lib.BlockNames;
import com.ggollmer.inevera.lib.GreatwardConstants;
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
			BlockIds.GREATWARD_STONE_PIECE = configuration.getBlock(BlockNames.GREATWARD_STONE_PIECE_NAME, BlockIds.GREATWARD_STONE_PIECE_DEFAULT).getInt(BlockIds.GREATWARD_STONE_PIECE_DEFAULT);
			BlockIds.GREATWARD_SAND_PIECE = configuration.getBlock(BlockNames.GREATWARD_SAND_PIECE_NAME, BlockIds.GREATWARD_SAND_PIECE_DEFAULT).getInt(BlockIds.GREATWARD_SAND_PIECE_DEFAULT);
			BlockIds.GREATWARD_NETHER_PIECE = configuration.getBlock(BlockNames.GREATWARD_NETHER_PIECE_NAME, BlockIds.GREATWARD_NETHER_PIECE_DEFAULT).getInt(BlockIds.GREATWARD_NETHER_PIECE_DEFAULT);
			BlockIds.GREATWARD_METAL_PIECE = configuration.getBlock(BlockNames.GREATWARD_METAL_PIECE_NAME, BlockIds.GREATWARD_METAL_PIECE_DEFAULT).getInt(BlockIds.GREATWARD_METAL_PIECE_DEFAULT);
			BlockIds.GREATWARD_PRECIOUS_PIECE = configuration.getBlock(BlockNames.GREATWARD_PRECIOUS_PIECE_NAME, BlockIds.GREATWARD_PRECIOUS_PIECE_DEFAULT).getInt(BlockIds.GREATWARD_PRECIOUS_PIECE_DEFAULT);
			BlockIds.GREATWARD_GEM_PIECE = configuration.getBlock(BlockNames.GREATWARD_GEM_PIECE_NAME, BlockIds.GREATWARD_GEM_PIECE_DEFAULT).getInt(BlockIds.GREATWARD_GEM_PIECE_DEFAULT);
			
			BlockIds.MINOR_WARD_CORE = configuration.getBlock(BlockNames.MINOR_WARD_CORE_NAME, BlockIds.MINOR_WARD_CORE_DEFAULT).getInt(BlockIds.MINOR_WARD_CORE_DEFAULT);
			BlockIds.MINOR_HORA_CELL = configuration.getBlock(BlockNames.MINOR_HORA_CELL_NAME, BlockIds.MINOR_HORA_CELL_DEFAULT).getInt(BlockIds.MINOR_HORA_CELL_DEFAULT);
			
			BlockIds.ACID_VAT = configuration.getBlock(BlockNames.ACID_VAT_NAME, BlockIds.ACID_VAT_DEFAULT).getInt(BlockIds.ACID_VAT_DEFAULT);
			
			/* ItemId Configuration */
			ItemIds.DEMON_FLESH = configuration.getItem(ItemNames.DEMON_FLESH_NAME, ItemIds.DEMON_FLESH_DEFAULT).getInt(ItemIds.DEMON_FLESH_DEFAULT);
			ItemIds.DEMON_BONE = configuration.getItem(ItemNames.DEMON_BONE_NAME, ItemIds.DEMON_BONE_DEFAULT).getInt(ItemIds.DEMON_BONE_DEFAULT);
			ItemIds.BASIC_WARDING_TOOLS = configuration.getItem(ItemNames.BASIC_WARDING_TOOLS_NAME, ItemIds.BASIC_WARDING_TOOLS_DEFAULT).getInt(ItemIds.BASIC_WARDING_TOOLS_DEFAULT);
			
			/* Greatward Configuration */
			GreatwardConstants.GREATWARD_WOOD_PIECE_VALUE = configuration.get(GreatwardConstants.GREATWARD_PIECE_CATEGORY, BlockNames.GREATWARD_WOOD_PIECE_NAME, GreatwardConstants.GREATWARD_WOOD_PIECE_DEFAULT_VALUE).getInt(GreatwardConstants.GREATWARD_WOOD_PIECE_DEFAULT_VALUE);
			GreatwardConstants.GREATWARD_STONE_PIECE_VALUE = configuration.get(GreatwardConstants.GREATWARD_PIECE_CATEGORY, BlockNames.GREATWARD_STONE_PIECE_NAME, GreatwardConstants.GREATWARD_STONE_PIECE_DEFAULT_VALUE).getInt(GreatwardConstants.GREATWARD_STONE_PIECE_DEFAULT_VALUE);
			GreatwardConstants.GREATWARD_SAND_PIECE_VALUE = configuration.get(GreatwardConstants.GREATWARD_PIECE_CATEGORY, BlockNames.GREATWARD_SAND_PIECE_NAME, GreatwardConstants.GREATWARD_SAND_PIECE_DEFAULT_VALUE).getInt(GreatwardConstants.GREATWARD_SAND_PIECE_DEFAULT_VALUE);
			GreatwardConstants.GREATWARD_NETHER_PIECE_VALUE = configuration.get(GreatwardConstants.GREATWARD_PIECE_CATEGORY, BlockNames.GREATWARD_NETHER_PIECE_NAME, GreatwardConstants.GREATWARD_NETHER_PIECE_DEFAULT_VALUE).getInt(GreatwardConstants.GREATWARD_NETHER_PIECE_DEFAULT_VALUE);
			GreatwardConstants.GREATWARD_METAL_PIECE_VALUE = configuration.get(GreatwardConstants.GREATWARD_PIECE_CATEGORY, BlockNames.GREATWARD_METAL_PIECE_NAME, GreatwardConstants.GREATWARD_METAL_PIECE_DEFAULT_VALUE).getInt(GreatwardConstants.GREATWARD_METAL_PIECE_DEFAULT_VALUE);
			GreatwardConstants.GREATWARD_PRECIOUS_PIECE_VALUE = configuration.get(GreatwardConstants.GREATWARD_PIECE_CATEGORY, BlockNames.GREATWARD_PRECIOUS_PIECE_NAME, GreatwardConstants.GREATWARD_PRECIOUS_PIECE_DEFAULT_VALUE).getInt(GreatwardConstants.GREATWARD_PRECIOUS_PIECE_DEFAULT_VALUE);
			GreatwardConstants.GREATWARD_GEM_PIECE_VALUE = configuration.get(GreatwardConstants.GREATWARD_PIECE_CATEGORY, BlockNames.GREATWARD_GEM_PIECE_NAME, GreatwardConstants.GREATWARD_GEM_PIECE_DEFAULT_VALUE).getInt(GreatwardConstants.GREATWARD_GEM_PIECE_DEFAULT_VALUE);
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
