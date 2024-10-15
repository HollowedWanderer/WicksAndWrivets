package de.xyndra.wikwriv.renderers

import de.xyndra.wikwriv.blockentities.CogBlockEntity
import net.hollowed.wikwriv.WicksWrivets
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import net.minecraft.client.util.GlfwUtil
import net.minecraft.util.Identifier
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.model.DefaultedBlockGeoModel
import software.bernie.geckolib.renderer.GeoBlockRenderer

class CogBlockEntityModel : DefaultedBlockGeoModel<CogBlockEntity>(Identifier.of(WicksWrivets.MOD_ID, "cog")) {
    override fun setCustomAnimations(
        animatable: CogBlockEntity,
        instanceId: Long,
        animationState: AnimationState<CogBlockEntity>
    ) {
        val (speed, clockwise, offset) = animatable.getNecessaryData() ?: return
        val root = animationProcessor.getBone("root")!!
        if (speed == 0.0f) {
            root.rotZ = 0f
            return
        }
        // more division -> slower rate of change
        var rot = ((GlfwUtil.getTime() / (16.0 - speed.toDouble())) % 1.0) * 360.0
        if (clockwise) {
            rot *= -1.0
        }
        if (offset) {
            // 8 Teeth and half a tooth offset. but also not half a tooth cause gecko is weird
            rot += (360.0 / 8.0) * 4
        }
        // Division cause gecko is weird
        root.rotZ = (rot / 8).toFloat() % 360f
    }
}

class CogBlockEntityRenderer(context: BlockEntityRendererFactory.Context) : GeoBlockRenderer<CogBlockEntity>(CogBlockEntityModel())