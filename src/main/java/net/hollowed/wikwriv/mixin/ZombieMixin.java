package net.hollowed.wikwriv.mixin;

import net.hollowed.wikwriv.client.EntitiesClient;
import net.hollowed.wikwriv.client.renderer.player.DoubleTopHatFeatureRenderer;
import net.hollowed.wikwriv.client.renderer.player.TopHatFeatureRenderer;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieBaseEntityRenderer.class)
public abstract class ZombieMixin<T extends ZombieEntity, M extends ZombieEntityModel<T>> extends BipedEntityRenderer<T, M> {

    public ZombieMixin(EntityRendererFactory.Context ctx, M model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(method = "<init>", at = @At("CTOR_HEAD"))
    public void addCustomFeature(EntityRendererFactory.Context ctx, ZombieEntityModel bodyModel, ZombieEntityModel legsArmorModel, ZombieEntityModel bodyArmorModel, CallbackInfo ci) {
        this.addFeature(new TopHatFeatureRenderer(this, ctx.getModelLoader().getModelPart(EntitiesClient.TOP_HAT_LAYER)));
        this.addFeature(new DoubleTopHatFeatureRenderer(this, ctx.getModelLoader().getModelPart(EntitiesClient.DOUBLE_TOP_HAT_LAYER)));
    }
}
