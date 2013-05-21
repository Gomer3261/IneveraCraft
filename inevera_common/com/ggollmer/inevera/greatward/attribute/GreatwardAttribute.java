package com.ggollmer.inevera.greatward.attribute;

import com.ggollmer.inevera.greatward.GreatwardComponent;


/**
 * IneveraCraft
 *
 * IGreatwardAttribute.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public abstract class GreatwardAttribute extends GreatwardComponent
{

	/**
	 * @param dimx
	 * @param dimy
	 * @param patternPath
	 */
	public GreatwardAttribute(int dimx, int dimy, String patternPath, String name)
	{
		super(dimx, dimy, patternPath, name);
	}	
}
