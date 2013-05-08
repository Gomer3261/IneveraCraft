package com.ggollmer.inevera.greatward.effect;

import com.ggollmer.inevera.greatward.GreatwardComponent;

/**
 * IneveraCraft
 *
 * IGreatwardEffect.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public abstract class GreatwardEffect extends GreatwardComponent
{

	/**
	 * @param dimx
	 * @param dimy
	 * @param patternPath
	 */
	public GreatwardEffect(int dimx, int dimy, String patternPath)
	{
		super(dimx, dimy, patternPath);
	}

}
