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
import kotlin.random.Random

class CogBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(BlockEntityTypes.COG_BLOCKENTITY_TYPE, pos, state), GeoBlockEntity {
    val direction = Random.nextBoolean()
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(AnimationController(this, ::deployAnimController))
    }

    fun deployAnimController(state: AnimationState<CogBlockEntity>): PlayState {
        var speed = 1.0f
        if (world != null) {
            val state = world!!.getBlockState(pos)
            if (state.block !is CogBlock) {
                return PlayState.STOP
            }
            speed = 0.0f + state[CogBlock.SPINNY] * 1f/16f
        }
        state.setControllerSpeed(speed)
        return if (direction) {
            state.setAndContinue(RawAnimation.begin().thenLoop("gear.spin"))
        } else {
            state.setAndContinue(RawAnimation.begin().thenLoop("gear.spinOther"))
        }
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return cache
    }

    val cache = GeckoLibUtil.createInstanceCache(this)
}