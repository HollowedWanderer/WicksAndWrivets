package de.xyndra.wikwriv.blockentities

import de.xyndra.wikwriv.blocks.CogBlock
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.math.BlockPos
import software.bernie.geckolib.animatable.GeoBlockEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.animation.PlayState
import software.bernie.geckolib.animation.RawAnimation
import software.bernie.geckolib.util.GeckoLibUtil

class CogBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(BlockEntityTypes.COG_BLOCKENTITY_TYPE, pos, state), GeoBlockEntity {
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(AnimationController(this, ::deployAnimController))
    }

    override fun shouldPlayAnimsWhileGamePaused(): Boolean {
        return true
    }

    fun getNecessaryData(): Triple<Float, Boolean, Boolean>? {
        var speed = 1.0f
        var clockwise = false
        var offset = false
        if (world != null) {
            val state = world!!.getBlockState(pos)
            if (state.block !is CogBlock) {
                return null
            }
            speed = 0.0f + state[CogBlock.SPINNY].toFloat()
            clockwise = state[CogBlock.CW]
            offset = state[CogBlock.OFFSET]
        }
        return Triple(speed, clockwise, offset)
    }

    fun deployAnimController(state: AnimationState<CogBlockEntity>): PlayState {
        return state.setAndContinue(RawAnimation.begin().thenLoop("gear.static"))
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return cache
    }

    val cache = GeckoLibUtil.createInstanceCache(this)
}