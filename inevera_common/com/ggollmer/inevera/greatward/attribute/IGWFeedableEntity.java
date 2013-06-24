package com.ggollmer.inevera.greatward.attribute;

/**
 * IneveraCraft
 *
 * IGWFeedableEntity.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public interface IGWFeedableEntity
{

	/**
	 * Called when a greatward feeds the entity.
	 * @param i The amount the greatward has fed the entity.
	 */
	void onGreatwardFeed(int i);
}
