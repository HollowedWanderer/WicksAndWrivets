package net.hollowed.wikwriv.mixin;

import net.hollowed.wikwriv.client.EntitiesClient;
import net.hollowed.wikwriv.client.renderer.illager.IllagerDoubleTopHatFeatureRenderer;
import net.hollowed.wikwriv.client.renderer.illager.IllagerTopHatFeatureRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.IllagerEntityRenderer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.IllagerEntityModel;
import net.minecraft.entity.mob.IllagerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IllagerEntityRenderer.class)
public abstract class IllagerMixin<T extends IllagerEntity> extends MobEntityRenderer<IllagerEntity, IllagerEntityModel<IllagerEntity>> {

    public IllagerMixin(EntityRendererFactory.Context context, IllagerEntityModel<IllagerEntity> entityModel, float f) {
        super(context, entityModel, f);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void addCustomFeature(EntityRendererFactory.Context ctx, IllagerEntityModel<IllagerEntity> model, float shadowRadius, CallbackInfo ci) {
        this.addFeature(new IllagerTopHatFeatureRenderer(this, ctx.getModelLoader().getModelPart(EntitiesClient.TOP_HAT_LAYER)));
        this.addFeature(new IllagerDoubleTopHatFeatureRenderer(this, ctx.getModelLoader().getModelPart(EntitiesClient.DOUBLE_TOP_HAT_LAYER)));
    }
}

