package de.xyndra.wikwriv.worldgen

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.math.BlockPos
import net.minecraft.world.ChunkRegion
import net.minecraft.world.HeightLimitView
import net.minecraft.world.Heightmap
import net.minecraft.world.biome.source.BiomeAccess
import net.minecraft.world.biome.source.BiomeSource
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.StructureAccessor
import net.minecraft.world.gen.chunk.Blender
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.VerticalBlockSample
import net.minecraft.world.gen.noise.NoiseConfig
import java.util.concurrent.CompletableFuture
import java.util.function.Function

class WikWrivLevelSource(customBiomeSource: BiomeSource) : ChunkGenerator(customBiomeSource) {
    override fun getCodec(): MapCodec<out ChunkGenerator?>? {
        return CODEC
    }

    override fun carve(
        chunkRegion: ChunkRegion?,
        seed: Long,
        noiseConfig: NoiseConfig?,
        biomeAccess: BiomeAccess?,
        structureAccessor: StructureAccessor?,
        chunk: Chunk?,
        carverStep: GenerationStep.Carver?
    ) {
    }

    override fun buildSurface(
        region: ChunkRegion?,
        structures: StructureAccessor?,
        noiseConfig: NoiseConfig?,
        chunk: Chunk?
    ) {
    }

    override fun populateEntities(region: ChunkRegion?) {
    }

    override fun getWorldHeight(): Int {
        return 384
    }

    override fun populateNoise(
        blender: Blender?,
        noiseConfig: NoiseConfig?,
        structureAccessor: StructureAccessor?,
        chunk: Chunk?
    ): CompletableFuture<Chunk?>? {
        return CompletableFuture.completedFuture(chunk)
    }

    override fun getSeaLevel(): Int {
        return 1
    }

    override fun getMinimumY(): Int {
        return -64
    }

    override fun getHeight(
        x: Int,
        z: Int,
        heightmap: Heightmap.Type?,
        world: HeightLimitView?,
        noiseConfig: NoiseConfig?
    ): Int {
        return 0
    }

    override fun getColumnSample(
        x: Int,
        z: Int,
        world: HeightLimitView?,
        noiseConfig: NoiseConfig?
    ): VerticalBlockSample? {
        return VerticalBlockSample(0, arrayOfNulls(0))
    }

    override fun getDebugHudText(
        text: List<String?>?,
        noiseConfig: NoiseConfig?,
        pos: BlockPos?
    ) {
    }

    companion object {
        @JvmField
        val CODEC: MapCodec<WikWrivLevelSource?> =
            RecordCodecBuilder.mapCodec { levelSourceInstance: RecordCodecBuilder.Instance<WikWrivLevelSource> ->
                return@mapCodec levelSourceInstance.group(
                    BiomeSource.CODEC.fieldOf("biome_source").forGetter(WikWrivLevelSource::biomeSource)
                ).apply(
                    levelSourceInstance,
                    levelSourceInstance.stable(Function { biomeSource: BiomeSource -> WikWrivLevelSource(biomeSource) })
                )
            }
    }
}
