package net.hollowed.wikwriv.mixin;

import net.hollowed.wikwriv.client.WicksWrivetsClient;
import net.hollowed.wikwriv.client.renderer.player.DoubleTopHatFeatureRenderer;
import net.hollowed.wikwriv.client.renderer.player.TopHatFeatureRenderer;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SkeletonEntityRenderer.class)
public abstract class SkeletonMixin<T extends AbstractSkeletonEntity> extends BipedEntityRenderer<T, SkeletonEntityModel<T>> {

    public SkeletonMixin(EntityRendererFactory.Context ctx, SkeletonEntityModel<T> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    // Inject at the constructor head to add custom features like TopHatFeatureRenderer
    @Inject(method = "<init>(Lnet/minecraft/client/render/entity/EntityRendererFactory$Context;Lnet/minecraft/client/render/entity/model/EntityModelLayer;Lnet/minecraft/client/render/entity/model/EntityModelLayer;Lnet/minecraft/client/render/entity/model/EntityModelLayer;)V",
            at = @At("CTOR_HEAD"))
    public void addCustomFeature(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer innerArmorLayer, EntityModelLayer outerArmorLayer, CallbackInfo ci) {
        this.addFeature(new TopHatFeatureRenderer(this, ctx.getModelLoader().getModelPart(WicksWrivetsClient.TOP_HAT_LAYER)));
        this.addFeature(new DoubleTopHatFeatureRenderer(this, ctx.getModelLoader().getModelPart(WicksWrivetsClient.DOUBLE_TOP_HAT_LAYER)));
    }
}
