package com.ggollmer.inevera.world.gen;

import java.util.Random;

import com.ggollmer.inevera.block.IneveraBlocks;

import net.minecraft.block.Block;
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
		switch(world.provider.dimensionId)
		{
			case 0:
				generateOverworld(world, rand, chunkX*16, chunkZ*16);
				break;
			case 1:
				generateEnd(world, rand, chunkX*16, chunkZ*16);
				break;
			case -1:
				generateNether(world, rand, chunkX*16, chunkZ*16);
				break;
		}
	}
	
	private void generateOverworld(World world, Random rand, int chunkX, int chunkZ)
	{
		this.spawnOres(IneveraBlocks.demonFossil, world, rand, chunkX, chunkZ, 16, 16, 5, 3, 0, 70);
	}
	
	private void generateEnd(World world, Random rand, int chunkX, int chunkZ)
	{
	}
	
	private void generateNether(World world, Random rand, int chunkX, int chunkZ)
	{
	}

	 /**
     * 
     * This Method adds ore generation. See below what all params mean
     * 
     * @param Block which you want to spawn
     * @param World where you want it to spawn
     * @param Randomizer used for spawning
     * @param Start of the Chunk in X-Direction
     * @param Start of the Chunk in Z-Direction
     * @param Max X-Size where the block can spawn, normal = 16
     * @param Max Z-Size where the block can spawn, normal = 16
     * @param The vain size
     * @param The chance to spawn a block
     * @param Minimum Y-level to spawn block
     * @param Maximum Y-level to spawn block
     * 
     */
    private void spawnOres(Block block, World world, Random random, int chunkX, int chunkZ, int XMax, int ZMax, int vainSize, int spawnChance, int YMin, int YMax)
    {
            for(int i = 0; i < spawnChance; i ++)
            {
                    int posX = chunkX + random.nextInt(XMax);
                    int posZ = chunkZ + random.nextInt(ZMax);
                    int posY = YMin + random.nextInt(YMax-YMin);
                    (new WorldGenMinable(block.blockID, vainSize)).generate(world, random, posX, posY, posZ);
            }
    }
}
