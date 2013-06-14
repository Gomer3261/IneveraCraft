package com.ggollmer.inevera.client.effect;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EffectRenderer;

/**
 * IneveraCraft
 *
 * IneveraEffect.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
@SideOnly(Side.CLIENT)
public class IneveraEffect
{
	EffectRenderer renderer;
	String arguments;
	double posX;
	double posY;
	double posZ;
	
	public IneveraEffect(EffectRenderer effectRenderer, double px, double py, double pz, String args)
	{
		this.renderer = effectRenderer;
		this.posX = px;
		this.posY = py;
		this.posZ = pz;
		this.arguments = args;
	}
}
