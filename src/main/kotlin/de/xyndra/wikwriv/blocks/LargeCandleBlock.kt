package de.xyndra.wikwriv.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.util.function.BooleanBiFunction
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView

class LargeCandleBlock(settings: Settings) : Block(settings.nonOpaque()) {
    fun makeShape(): VoxelShape {
        var shape: VoxelShape = VoxelShapes.empty()
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.3125, 0.0, 0.3125, 0.6875, 0.875, 0.6875), BooleanBiFunction.OR)
        return shape
    }

    override fun getOutlineShape(state: BlockState?, view: BlockView?, pos: BlockPos?, context: ShapeContext?): VoxelShape? {
        return makeShape()
    }
}