package com.ggollmer.inevera.greatward.attribute;

import com.ggollmer.inevera.lib.GreatwardConstants;

/**
 * IneveraCraft
 *
 * GreatwardAttributeHealth.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GreatwardAttributeHealth extends GreatwardAttribute
{

	/**
	 * @param name
	 */
	public GreatwardAttributeHealth(String name)
	{
		super(name);
		addGreatwardMap(GreatwardConstants.GW_MINOR_TYPE, GreatwardConstants.GW_ATTRIBUTE_HEALTH_MINOR_LOCATION, GreatwardConstants.GW_MINOR_WIDTH, GreatwardConstants.GW_MINOR_HEIGHT);
		//TODO: add constructors for normal and major maps.
	}

}
