package com.ggollmer.inevera.world.gen;

import java.util.Random;

import com.ggollmer.inevera.block.IneveraBlocks;
import com.ggollmer.inevera.core.helper.LogHelper;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

/**
 * IneveraCraft
 *
 * WorldGenDemonFossil.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class WorldGenDemonFossil implements IWorldGenerator
{
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		WorldGenMinable oreGen = new WorldGenMinable(IneveraBlocks.demonFossil.blockID, 6);
		
		if(world.getWorldInfo().getDimension() == 0)
		{
			for(int i=0; i<8; i++)
			{
				int px = chunkX*16 + rand.nextInt(16);
				int py = rand.nextInt(64);
				int pz = chunkZ*16 + rand.nextInt(16);
				
				LogHelper.debugLog(String.format("Fossil At: %d %d %d", px, py, pz));
				
				oreGen.generate(world, rand, px, py, pz);
			}
		}
	}

}
