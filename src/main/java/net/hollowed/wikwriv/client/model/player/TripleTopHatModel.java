// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package net.hollowed.wikwriv.client.model.player;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class TripleTopHatModel extends EntityModel<Entity> {
    public final ModelPart bb_main;
    public TripleTopHatModel(ModelPart root) {
        this.bb_main = root.getChild("bb_main");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(36, 15).cuboid(-4.0F, -23.0F, -4.0F, 8.0F, 13.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-6.0F, -10.0F, -6.0F, 12.0F, 3.0F, 12.0F, new Dilation(0.0F))
                .uv(0, 15).cuboid(-4.5F, -23.5F, -4.5F, 9.0F, 14.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 15).cuboid(-8.0F, -15.0F, -4.0F, 9.0F, 14.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(3.5F, -23.5F, -0.5F, -0.1298F, 0.017F, 0.1298F));

        ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-9.0F, -3.0F, -6.0F, 12.0F, 3.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -22.0F, 0.0F, -0.1298F, 0.017F, 0.1298F));

        ModelPartData cube_r3 = bb_main.addChild("cube_r3", ModelPartBuilder.create().uv(36, 15).cuboid(-7.0F, -14.0F, -4.0F, 8.0F, 13.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -24.0F, 0.0F, -0.1298F, 0.017F, 0.1298F));


        ModelPartData cube_r4 = bb_main.addChild("cube_r4", ModelPartBuilder.create().uv(0, 15).cuboid(-8F, -31.0F, -3.5F, 9.0F, 14.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(3.5F, -23.5F, -0.5F, -0.1298F, 0.017F, 0.1298F));

        ModelPartData cube_r5 = bb_main.addChild("cube_r5", ModelPartBuilder.create().uv(0, 0).cuboid(-9F, -19.0F, -6.0F, 12.0F, 3.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -22.0F, 0.0F, -0.1298F, 0.017F, 0.1298F));

        ModelPartData cube_r6 = bb_main.addChild("cube_r6", ModelPartBuilder.create().uv(36, 15).cuboid(-7F, -30.0F, -4.0F, 8.0F, 13.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -24.0F, 0.0F, -0.1298F, 0.017F, 0.1298F));
        return TexturedModelData.of(modelData, 128, 128);
    }
    @Override
    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        bb_main.render(matrices, vertices, light, overlay);
    }
}