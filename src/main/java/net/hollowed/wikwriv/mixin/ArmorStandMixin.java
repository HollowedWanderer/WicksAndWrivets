package net.hollowed.wikwriv.mixin;

import net.hollowed.wikwriv.client.WicksWrivetsClient;
import net.hollowed.wikwriv.client.renderer.player.DoubleTopHatFeatureRenderer;
import net.hollowed.wikwriv.client.renderer.player.TopHatFeatureRenderer;
import net.minecraft.client.render.entity.ArmorStandEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.ArmorStandArmorEntityModel;
import net.minecraft.entity.decoration.ArmorStandEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorStandEntityRenderer.class)
public abstract class ArmorStandMixin extends LivingEntityRenderer<ArmorStandEntity, ArmorStandArmorEntityModel> {

    public ArmorStandMixin(EntityRendererFactory.Context ctx, ArmorStandArmorEntityModel model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(method = "<init>", at = @At("CTOR_HEAD"))
    public void addCustomFeature(EntityRendererFactory.Context context, CallbackInfo ci) {
        this.addFeature(new TopHatFeatureRenderer(this, context.getModelLoader().getModelPart(WicksWrivetsClient.TOP_HAT_LAYER)));
        this.addFeature(new DoubleTopHatFeatureRenderer(this, context.getModelLoader().getModelPart(WicksWrivetsClient.DOUBLE_TOP_HAT_LAYER)));
    }
}
