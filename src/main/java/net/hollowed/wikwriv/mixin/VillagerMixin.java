package net.hollowed.wikwriv.mixin;

import net.hollowed.wikwriv.client.WicksWrivetsClient;
import net.hollowed.wikwriv.client.renderer.villager.VillagerDoubleTopHatFeatureRenderer;
import net.hollowed.wikwriv.client.renderer.villager.VillagerTopHatFeatureRenderer;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.entity.passive.VillagerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntityRenderer.class)
public abstract class VillagerMixin extends MobEntityRenderer<VillagerEntity, VillagerResemblingModel<VillagerEntity>> {

    public VillagerMixin(EntityRendererFactory.Context context, VillagerResemblingModel<VillagerEntity> entityModel, float f) {
        super(context, entityModel, f);
    }

    @Inject(method = "<init>", at = @At("CTOR_HEAD"))
    public void addCustomFeature(EntityRendererFactory.Context ctx, CallbackInfo ci) {
        this.addFeature(new VillagerTopHatFeatureRenderer(this, ctx.getModelLoader().getModelPart(WicksWrivetsClient.TOP_HAT_LAYER)));
        this.addFeature(new VillagerDoubleTopHatFeatureRenderer(this, ctx.getModelLoader().getModelPart(WicksWrivetsClient.DOUBLE_TOP_HAT_LAYER)));
    }
}
