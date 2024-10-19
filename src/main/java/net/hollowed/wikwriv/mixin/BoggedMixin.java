package net.hollowed.wikwriv.mixin;

import net.hollowed.wikwriv.client.WicksWrivetsClient;
import net.hollowed.wikwriv.client.renderer.player.DoubleTopHatFeatureRenderer;
import net.hollowed.wikwriv.client.renderer.player.TopHatFeatureRenderer;
import net.minecraft.client.render.entity.BoggedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.entity.mob.BoggedEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BoggedEntityRenderer.class)
public abstract class BoggedMixin extends SkeletonEntityRenderer<BoggedEntity> {

    public BoggedMixin(EntityRendererFactory.Context context) {
        super(context);
    }

    @Inject(method = "<init>", at = @At("CTOR_HEAD"))
    public void addCustomFeature(EntityRendererFactory.Context ctx, CallbackInfo ci) {
        this.addFeature(new TopHatFeatureRenderer(this, ctx.getModelLoader().getModelPart(WicksWrivetsClient.TOP_HAT_LAYER)));
        this.addFeature(new DoubleTopHatFeatureRenderer(this, ctx.getModelLoader().getModelPart(WicksWrivetsClient.DOUBLE_TOP_HAT_LAYER)));
    }
}
