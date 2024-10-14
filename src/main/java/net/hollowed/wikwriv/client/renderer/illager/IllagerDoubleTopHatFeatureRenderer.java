package net.hollowed.wikwriv.client.renderer.illager;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.hollowed.wikwriv.client.model.player.DoubleTopHatModel;
import net.hollowed.wikwriv.client.model.player.TopHatModel;
import net.hollowed.wikwriv.common.items.ModItems;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.IllagerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class IllagerDoubleTopHatFeatureRenderer extends FeatureRenderer<IllagerEntity, IllagerEntityModel<IllagerEntity>> {
    private final DoubleTopHatModel hatModel;
    private static final Identifier NORMAL_TEXTURE = Identifier.of("wikwriv", "textures/entity/top_hat.png");

    public IllagerDoubleTopHatFeatureRenderer(FeatureRendererContext<IllagerEntity, IllagerEntityModel<IllagerEntity>> context, ModelPart modelPart) {
        super(context);
        this.hatModel = new DoubleTopHatModel(modelPart);
    }

    private void syncModelPoseWithIllager(IllagerEntityModel<IllagerEntity> illagerModel) {
        // Sync the hat with the illager's head
        hatModel.bb_main.copyTransform(illagerModel.getHead());
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, IllagerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        ItemStack stack = entity.getEquippedStack(EquipmentSlot.HEAD);

        // Check if the illager is wearing the Top Hat item
        if (!entity.isInvisible() && stack.getItem() == ModItems.DOUBLE_TOP_HAT) {
            matrices.push();

            // Overlay for lighting
            int overlay = LivingEntityRenderer.getOverlay(entity, 0.0F);

            // Sync the hat with the illager's head
            syncModelPoseWithIllager(this.getContextModel());

            // Render the Top Hat
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(getTexture()));
            hatModel.render(matrices, vertexConsumer, light, overlay, 0);  // Ensure render parameters are correct

            matrices.pop();
        }
    }

    private Identifier getTexture() {
        return NORMAL_TEXTURE;
    }
}

