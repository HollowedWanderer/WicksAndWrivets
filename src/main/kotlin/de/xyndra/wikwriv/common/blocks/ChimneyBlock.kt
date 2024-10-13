package de.xyndra.wikwriv.common.blocks

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import de.xyndra.wikwriv.common.blockentities.BlockEntityTypes
import de.xyndra.wikwriv.common.blockentities.ChimneyBlockEntity
import de.xyndra.wikwriv.common.randomPosNeg
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.particle.ParticleTypes
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ChimneyBlock(settings: Settings) : BlockWithEntity(settings.ticksRandomly()) {
    override fun getCodec(): MapCodec<out BlockWithEntity?>? {
        return CODEC
    }

    override fun createBlockEntity(
        pos: BlockPos?,
        state: BlockState?
    ): BlockEntity? {
        return ChimneyBlockEntity(pos!!, state!!)
    }

    override fun getRenderType(state: BlockState?): BlockRenderType? {
        return BlockRenderType.MODEL
    }

    override fun <T : BlockEntity> getTicker(
        world: World?,
        state: BlockState?,
        type: BlockEntityType<T>?
    ): BlockEntityTicker<T>? {
        return validateTicker(type, BlockEntityTypes.CHIMNEY_BLOCKENTITY_TYPE, ChimneyBlockEntity.Companion::smokeTick)
    }

    companion object {
        val CODEC: MapCodec<ChimneyBlock> = RecordCodecBuilder.mapCodec { instance: RecordCodecBuilder.Instance<ChimneyBlock> ->
            instance.group(
                createSettingsCodec()
            ).apply(instance, ::ChimneyBlock)
        }

        fun spawnSmokeParticle(world: World, pos: BlockPos) {
            val random = world.getRandom()
            val simpleParticleType = ParticleTypes.CAMPFIRE_SIGNAL_SMOKE
            world.addImportantParticle(
                simpleParticleType,
                true,
                pos.x + 0.5 + (random.nextDouble() / 3.0).randomPosNeg(random),
                pos.y + random.nextDouble() + random.nextDouble(),
                pos.z + 0.5 + (random.nextDouble() / 3.0).randomPosNeg(random),
                0.0,
                0.07,
                0.0
            )
        }
    }
}