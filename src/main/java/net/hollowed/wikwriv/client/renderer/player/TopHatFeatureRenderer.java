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
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class TopHatFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
	private final TopHatModel hatModel;

	private static final Identifier NORMAL_TEXTURE = Identifier.of("wikwriv", "textures/entity/top_hat.png");

	public TopHatFeatureRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context, ModelPart modelPart) {
		super(context);
		this.hatModel = new TopHatModel(modelPart);
	}

	@Override
	public void render(
			MatrixStack matrixStack,
			VertexConsumerProvider vertexConsumerProvider,
			int light,
			AbstractClientPlayerEntity playerEntity,
			float limbAngle,
			float limbDistance,
			float tickDelta,
			float age,
			float headYaw,
			float headPitch
	) {
		ItemStack stack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
		if (!playerEntity.isInvisible() && stack.getItem() == ModItems.TOP_HAT) {
			matrixStack.push();

			int m = LivingEntityRenderer.getOverlay(playerEntity, 0.0F);

			// Sync hat model with player
			PlayerEntityModel<AbstractClientPlayerEntity> playerModel = this.getContextModel();
			syncModelPose(playerModel);

			// Render top hat
			VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucent(getTexture(playerEntity)));
			hatModel.render(matrixStack, vertexConsumer, light, m, 0);
			matrixStack.pop();
		}
	}

	private void syncModelPose(PlayerEntityModel<AbstractClientPlayerEntity> playerModel) {
		// Sync model to the player's head
		hatModel.bb_main.copyTransform(playerModel.head);
	}

	@Override
	public Identifier getTexture(AbstractClientPlayerEntity playerEntity) {
		return NORMAL_TEXTURE;
	}
}