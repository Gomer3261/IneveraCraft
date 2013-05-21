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
	/* Greatward type constants */
    public static final String GW_MINOR_TYPE = "minor";
    public static final String GW_NORMAL_TYPE = "normal";
    public static final String GW_MAJOR_TYPE = "major";
    
    public static final int GW_MINOR_WIDTH = 9;
    public static final int GW_MINOR_HEIGHT = 9;
    public static final int GW_NORMAL_WIDTH = 12;
    public static final int GW_NORMAL_HEIGHT = 12;
    public static final int GW_MAJOR_WIDTH = 15;
    public static final int GW_MAJOR_HEIGHT = 15;
	
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
}
