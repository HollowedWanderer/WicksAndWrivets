package de.xyndra.wikwriv.blockentities

import de.xyndra.wikwriv.blocks.ChimneyBlock
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.World

class ChimneyBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(BlockEntityTypes.CHIMNEY_BLOCKENTITY_TYPE, pos, state) {
    companion object {
        fun smokeTick(world: World, pos: BlockPos, state: BlockState, blockEntity: ChimneyBlockEntity) {
            val random: Random = world.random
            if (random.nextFloat() < 0.26F) {
                repeat(random.nextInt(2)+2) {
                    ChimneyBlock.Companion.spawnSmokeParticle(world, pos)
                }
            }
        }
    }
}