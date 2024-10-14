package de.xyndra.wikwriv.blocks

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import de.xyndra.wikwriv.blockentities.CogBlockEntity
import net.minecraft.block.Block
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.state.StateManager
import net.minecraft.state.property.IntProperty
import net.minecraft.util.math.BlockPos

class CogBlock(settings: Settings) : BlockWithEntity(settings.nonOpaque().noBlockBreakParticles()) {
    init {
        defaultState = this.stateManager.defaultState.with(SPINNY, 1)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        builder!!.add(SPINNY)
    }

    override fun getCodec(): MapCodec<out BlockWithEntity?>? {
        return CODEC
    }

    override fun createBlockEntity(
        pos: BlockPos?,
        state: BlockState?
    ): BlockEntity? {
        return CogBlockEntity(pos!!, state!!)
    }

    override fun getRenderType(state: BlockState?): BlockRenderType? {
        return BlockRenderType.ENTITYBLOCK_ANIMATED
    }

    companion object {
        val CODEC: MapCodec<CogBlock> = RecordCodecBuilder.mapCodec { instance: RecordCodecBuilder.Instance<CogBlock> ->
            instance.group(
                createSettingsCodec()
            ).apply(instance, ::CogBlock)
        }
        val SPINNY: IntProperty = IntProperty.of("spinny", 0, 15)
    }
}