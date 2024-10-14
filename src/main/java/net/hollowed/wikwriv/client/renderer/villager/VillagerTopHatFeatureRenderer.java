package net.hollowed.wikwriv.client.renderer.villager;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.hollowed.wikwriv.client.model.player.TopHatModel;
import net.hollowed.wikwriv.common.items.ModItems;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class VillagerTopHatFeatureRenderer extends FeatureRenderer<VillagerEntity, VillagerResemblingModel<VillagerEntity>> {
    private final TopHatModel hatModel;
    private static final Identifier NORMAL_TEXTURE = Identifier.of("wikwriv", "textures/entity/top_hat.png");

    public VillagerTopHatFeatureRenderer(FeatureRendererContext<VillagerEntity, VillagerResemblingModel<VillagerEntity>> context, ModelPart modelPart) {
        super(context);
        this.hatModel = new TopHatModel(modelPart);
    }

    @Override
    public void render(
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider,
            int light,
            VillagerEntity livingEntity,
            float limbAngle,
            float limbDistance,
            float tickDelta,
            float age,
            float headYaw,
            float headPitch
    ) {
        ItemStack stack = livingEntity.getEquippedStack(EquipmentSlot.HEAD);

        // Check if the villager is wearing the Top Hat item
        if (!livingEntity.isInvisible() && stack.getItem() == ModItems.TOP_HAT) {
            matrixStack.push();

            // Overlay for lighting
            int overlay = LivingEntityRenderer.getOverlay(livingEntity, 0.0F);

            // Sync the hat with the villager's head
            syncModelPoseWithVillager(this.getContextModel());

            // Render the Top Hat
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucent(getTexture(livingEntity)));
            hatModel.render(matrixStack, vertexConsumer, light, overlay, 0);  // Ensure render parameters are correct

            matrixStack.pop();
        }
    }

    private void syncModelPoseWithVillager(VillagerResemblingModel<VillagerEntity> villagerModel) {
        // Sync the hat with the villager's head
        hatModel.bb_main.copyTransform(villagerModel.getHead());
    }

    @Override
    public Identifier getTexture(VillagerEntity livingEntity) {
        return NORMAL_TEXTURE;
    }
}
