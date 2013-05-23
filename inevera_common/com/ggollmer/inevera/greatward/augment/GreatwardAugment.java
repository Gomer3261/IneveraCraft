package com.ggollmer.inevera.greatward.augment;

import com.ggollmer.inevera.greatward.GreatwardComponent;

/**
 * IneveraCraft
 *
 * IGreatwardAugment.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public abstract class GreatwardAugment extends GreatwardComponent
{

	/**
	 * @param name The unique name of the greatwardComponent, used for NBT data.
	 */
	public GreatwardAugment(String name)
	{
		super(name);
	}
}
