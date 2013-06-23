package com.ggollmer.inevera.lib;

import net.minecraftforge.common.Configuration;

/**
 * IneveraCraft
 *
 * GreatwardNames.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GreatwardConstants
{
	public static final String GREATWARD_CATEGORY = "greatward";
	
	/* Greatward piece constants */
	public static final String GREATWARD_PIECE_CATEGORY = GREATWARD_CATEGORY + Configuration.CATEGORY_SPLITTER + "pieces";
	
	public static final int GREATWARD_WOOD_PIECE_DEFAULT_VALUE = 1;
	public static final int GREATWARD_STONE_PIECE_DEFAULT_VALUE = 2;
	public static final int GREATWARD_SAND_PIECE_DEFAULT_VALUE = 2;
	public static final int GREATWARD_NETHER_PIECE_DEFAULT_VALUE = 3;
	public static final int GREATWARD_METAL_PIECE_DEFAULT_VALUE = 5;
	public static final int GREATWARD_PRECIOUS_PIECE_DEFAULT_VALUE = 6;
	public static final int GREATWARD_GEM_PIECE_DEFAULT_VALUE = 8;
	
	public static int GREATWARD_WOOD_PIECE_VALUE;
	public static int GREATWARD_STONE_PIECE_VALUE;
	public static int GREATWARD_SAND_PIECE_VALUE;
	public static int GREATWARD_NETHER_PIECE_VALUE;
	public static int GREATWARD_METAL_PIECE_VALUE;
	public static int GREATWARD_PRECIOUS_PIECE_VALUE;
	public static int GREATWARD_GEM_PIECE_VALUE;
	
	/* Greatward type constants */
    public static final String GW_MINOR_TYPE = "minor";
    public static final String GW_NORMAL_TYPE = "normal";
    public static final String GW_MAJOR_TYPE = "major";
    
    public static final int GW_MINOR_DIMENSION = 9;
    public static final int GW_MINOR_OFFSET_X = 2;
	public static final int GW_MINOR_OFFSET_Y = 2;
	public static final int GW_MINOR_PROJECTION = 4;
    
    public static final int GW_NORMAL_DIMENSION = 12;
    public static final int GW_NORMAL_OFFSET_X = 2;
	public static final int GW_NORMAL_OFFSET_Y = 2;
	public static final int GW_NORMAL_PROJECTION = 5;
    
    public static final int GW_MAJOR_DIMENSION = 15;
    public static final int GW_MAJOR_OFFSET_X = 2;
	public static final int GW_MAJOR_OFFSET_Y = 2;
	public static final int GW_MAJOR_PROJECTION = 6;
	
    /* Greatward NBT keys */
    public static final String GW_NBT_TARGET_KEY = "GwTarget";
    public static final String GW_NBT_ATTRIBUTE_KEY = "GwAttribute";
    public static final String GW_NBT_EFFECT_KEY = "GwEffect";
    public static final String GW_NBT_AUGMENTS_KEY = "GwAugments";
    public static final String GW_NBT_WARD_TYPE_KEY = "GwType";
    public static final String GW_NBT_DIRECTION_KEY = "GwDirection";
    public static final String GW_NBT_ORIENTATION_KEY = "GwOrientation";
    public static final String GW_NBT_COORD_LIST_KEY = "GwBlockList";
    public static final String GW_NBT_BLOCK_TYPE_KEY = "GwBlockType";
    
    /* Greatward resource locations */
	public static final String GW_MAP_LOCATION = "/mods/inevera/greatwards";
	public static final String GW_MINOR_LOCATION = GW_MAP_LOCATION + "/" + GW_MINOR_TYPE + "/";
    public static final String GW_NORMAL_LOCATION = GW_MAP_LOCATION + "/" + GW_NORMAL_TYPE + "/";
    public static final String GW_MAJOR_LOCATION = GW_MAP_LOCATION + "/" + GW_MAJOR_TYPE + "/";
    
    /* Greatward target constants */
    public static final int GW_TARGET_WIDTH = 3;
    public static final int GW_TARGET_HEIGHT = 3;
    
    public static final String GW_TARGET_ALL_NAME = "t_all";
    
    /* Greatward target maps */
	public static final String GW_TARGET_ALL_LOCATION = GW_MAP_LOCATION + "/" + GW_TARGET_ALL_NAME + ".txt";
	
	/* Greatward attribute constants */
	public static final String GW_ATTRIBUTE_HEALTH_NAME = "a_health";
	
	/* Greatward attribute maps */
	public static final String GW_ATTRIBUTE_HEALTH_MINOR_LOCATION = GW_MINOR_LOCATION + "/" + GW_ATTRIBUTE_HEALTH_NAME + ".txt";
	public static final String GW_ATTRIBUTE_HEALTH_NORMAL_LOCATION = GW_NORMAL_LOCATION + "/" + GW_ATTRIBUTE_HEALTH_NAME + ".txt";
	public static final String GW_ATTRIBUTE_HEALTH_MAJOR_LOCATION = GW_MAJOR_LOCATION + "/" + GW_ATTRIBUTE_HEALTH_NAME + ".txt";
	
	/* Greatward effect constants */
	public static final String GW_EFFECT_POSITIVE_NAME = "e_positive";
	public static final String GW_EFFECT_NEGATIVE_NAME = "e_negative";
	public static final String GW_EFFECT_CHAOTIC_NAME = "e_chaotic";
	
	/* Greatward effect maps */
	public static final String GW_EFFECT_POSITIVE_MINOR_LOCATION = GW_MINOR_LOCATION + "/" + GW_EFFECT_POSITIVE_NAME + ".txt";
	public static final String GW_EFFECT_POSITIVE_NORMAL_LOCATION = GW_NORMAL_LOCATION + "/" + GW_EFFECT_POSITIVE_NAME + ".txt";
	public static final String GW_EFFECT_POSITIVE_MAJOR_LOCATION = GW_MAJOR_LOCATION + "/" + GW_EFFECT_POSITIVE_NAME + ".txt";
	
	public static final String GW_EFFECT_NEGATIVE_MINOR_LOCATION = GW_MINOR_LOCATION + "/" + GW_EFFECT_NEGATIVE_NAME + ".txt";
	public static final String GW_EFFECT_NEGATIVE_NORMAL_LOCATION = GW_NORMAL_LOCATION + "/" + GW_EFFECT_NEGATIVE_NAME + ".txt";
	public static final String GW_EFFECT_NEGATIVE_MAJOR_LOCATION = GW_MAJOR_LOCATION + "/" + GW_EFFECT_NEGATIVE_NAME + ".txt";
	
	public static final String GW_EFFECT_CHAOTIC_MINOR_LOCATION = GW_MINOR_LOCATION + "/" + GW_EFFECT_CHAOTIC_NAME + ".txt";
	public static final String GW_EFFECT_CHAOTIC_NORMAL_LOCATION = GW_NORMAL_LOCATION + "/" + GW_EFFECT_CHAOTIC_NAME + ".txt";
	public static final String GW_EFFECT_CHAOTIC_MAJOR_LOCATION = GW_MAJOR_LOCATION + "/" + GW_EFFECT_CHAOTIC_NAME + ".txt";
}
