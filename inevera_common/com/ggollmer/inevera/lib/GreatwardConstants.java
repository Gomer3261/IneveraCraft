package com.ggollmer.inevera.lib;

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
	/* Greatward type strings */
    public static final String GW_MINOR_TYPE = "minor";
    public static final String GW_NORMAL_TYPE = "normal";
    public static final String GW_MAJOR_TYPE = "major";
	
    /* Greatward resource locations */
	public static final String GW_MAP_LOCATION = "/mods/inevera/greatwards";
	public static final String GW_MINOR_LOCATION = GW_MAP_LOCATION + GW_MINOR_TYPE + "/";
    public static final String GW_NORMAL_LOCATION = GW_MAP_LOCATION + GW_NORMAL_TYPE + "/";
    public static final String GW_MAJOR_LOCATION = GW_MAP_LOCATION + GW_MAJOR_TYPE + "/";
    
    /* Greatward target constants */
    public static final int GW_TARGET_WIDTH = 3;
    public static final int GW_TARGET_HEIGHT = 3;
    public static final String GW_TARGET_ALL_NAME = "t_all";
    
    /* Greatward target maps */
	public static final String GW_TARGET_ALL_LOCATION = GW_MAP_LOCATION + "/" + GW_TARGET_ALL_NAME + ".txt";
}
