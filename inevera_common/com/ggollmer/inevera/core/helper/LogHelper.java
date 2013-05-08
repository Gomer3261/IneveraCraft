package com.ggollmer.inevera.core.helper;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ggollmer.inevera.lib.Reference;

import cpw.mods.fml.common.FMLLog;

/**\
 * IneveraCraft
 *
 * LogHelper
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class LogHelper
{
	private static Logger ineveraLogger = Logger.getLogger(Reference.MOD_ID);
	
	public static void init()
	{
		ineveraLogger.setParent(FMLLog.getLogger());
	}
	
	public static void log(Level logLevel, String message)
	{
        ineveraLogger.log(logLevel, message);
    }
	
	public static void debugLog(String message)
	{
		if(Reference.DEBUG_MODE)
		{
			ineveraLogger.log(Level.INFO, message);
		}
	}
}
