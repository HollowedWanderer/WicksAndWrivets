package net.hollowed.wikwriv.mixin;

import net.hollowed.wikwriv.client.WicksWrivetsClient;
import net.hollowed.wikwriv.client.renderer.player.DoubleTopHatFeatureRenderer;
import net.hollowed.wikwriv.client.renderer.player.TopHatFeatureRenderer;
import net.hollowed.wikwriv.client.renderer.player.TripleTopHatFeatureRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerFeaturesMixin extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    public PlayerFeaturesMixin(EntityRendererFactory.Context ctx, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(method = "<init>", at = @At("CTOR_HEAD"))
    private void addCustomFeature(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo ci) {
        this.addFeature(new TopHatFeatureRenderer(this, ctx.getModelLoader().getModelPart(WicksWrivetsClient.TOP_HAT_LAYER)));
        this.addFeature(new DoubleTopHatFeatureRenderer(this, ctx.getModelLoader().getModelPart(WicksWrivetsClient.DOUBLE_TOP_HAT_LAYER)));
        this.addFeature(new TripleTopHatFeatureRenderer(this, ctx.getModelLoader().getModelPart(WicksWrivetsClient.TRIPLE_TOP_HAT_LAYER)));
    }
}