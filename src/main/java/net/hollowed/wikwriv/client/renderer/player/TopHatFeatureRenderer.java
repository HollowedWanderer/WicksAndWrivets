package net.hollowed.wikwriv.client.renderer.player;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.hollowed.wikwriv.client.model.player.TopHatModel;
import net.hollowed.wikwriv.common.items.ModItems;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class TopHatFeatureRenderer<T extends LivingEntity> extends FeatureRenderer<T, BipedEntityModel<T>> {
	private final TopHatModel hatModel;
	private static final Identifier NORMAL_TEXTURE = Identifier.of("wikwriv", "textures/entity/top_hat.png");

	public TopHatFeatureRenderer(FeatureRendererContext<T, BipedEntityModel<T>> context, ModelPart modelPart) {
		super(context);
		this.hatModel = new TopHatModel(modelPart);
	}

	@Override
	public void render(
			MatrixStack matrixStack,
			VertexConsumerProvider vertexConsumerProvider,
			int light,
			T livingEntity,
			float limbAngle,
			float limbDistance,
			float tickDelta,
			float age,
			float headYaw,
			float headPitch
	) {
		ItemStack stack = livingEntity.getEquippedStack(EquipmentSlot.HEAD);

		// Check if the entity is wearing the Top Hat item
		if (!livingEntity.isInvisible() && stack.getItem() == ModItems.TOP_HAT) {
			matrixStack.push();

			// Overlay for lighting
			int overlay = LivingEntityRenderer.getOverlay(livingEntity, 0.0F);

			// Sync model pose based on the type of entity
			if (livingEntity instanceof AbstractClientPlayerEntity) {
				syncModelPoseWithPlayer(this.getContextModel());
			} else if (livingEntity instanceof ArmorStandEntity) {
				syncModelPoseWithArmorStand((BipedEntityModel<ArmorStandEntity>) this.getContextModel());
			} else if (livingEntity instanceof ZombieEntity) {
				syncModelPoseWithZober(this.getContextModel());
			}

			// Render the Top Hat
			VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucent(getTexture(livingEntity)));
			hatModel.render(matrixStack, vertexConsumer, light, overlay, 0);  // Ensure render parameters are correct

			matrixStack.pop();
		}
	}

	private void syncModelPoseWithPlayer(BipedEntityModel<T> playerModel) {
		// Sync the hat with the player's head
		hatModel.bb_main.copyTransform(playerModel.head);
	}

	private void syncModelPoseWithArmorStand(BipedEntityModel<ArmorStandEntity> armorStandModel) {
		// Sync the hat with the armor stand's head
		hatModel.bb_main.copyTransform(armorStandModel.head);
	}

	private void syncModelPoseWithZober(BipedEntityModel<T> zoberModel) {
		// Manually apply the zombie head's rotation and position to the hat model
		hatModel.bb_main.pitch = zoberModel.head.pitch;
		hatModel.bb_main.yaw = zoberModel.head.yaw;
		hatModel.bb_main.roll = zoberModel.head.roll;

		// Adjust position of the hat to match the head's position
		hatModel.bb_main.pivotX = zoberModel.head.pivotX;
		hatModel.bb_main.pivotY = zoberModel.head.pivotY;
		hatModel.bb_main.pivotZ = zoberModel.head.pivotZ;
	}

	@Override
	public Identifier getTexture(T livingEntity) {
		return NORMAL_TEXTURE;
	}
}
