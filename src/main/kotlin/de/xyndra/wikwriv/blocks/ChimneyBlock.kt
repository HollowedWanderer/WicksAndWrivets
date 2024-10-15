package de.xyndra.wikwriv.blocks

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import de.xyndra.wikwriv.blockentities.BlockEntityTypes
import de.xyndra.wikwriv.blockentities.ChimneyBlockEntity
import de.xyndra.wikwriv.randomPosNeg
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.ShapeContext
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.particle.ParticleTypes
import net.minecraft.util.function.BooleanBiFunction
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World

// TODO: Add proper item model and remove particles
class ChimneyBlock(settings: Settings) : BlockWithEntity(settings) {
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

    // TODO: Also do this for the chimney without smoke
    fun makeShape(): VoxelShape {
        var shape: VoxelShape = VoxelShapes.empty()
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0.0, 0.25, 0.75, 0.8125, 0.75), BooleanBiFunction.OR)
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.125, 0.8125, 0.125, 0.875, 1.0, 0.875), BooleanBiFunction.OR)
        return shape
    }

    override fun getOutlineShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape? {
        return makeShape()
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
                pos.y + 0.8 + random.nextDouble() + random.nextDouble(),
                pos.z + 0.5 + (random.nextDouble() / 3.0).randomPosNeg(random),
                (random.nextDouble() * 0.0075).randomPosNeg(random) + 0.03,
                0.07,
                (random.nextDouble() * 0.005).randomPosNeg(random)
            )
        }
    }
}