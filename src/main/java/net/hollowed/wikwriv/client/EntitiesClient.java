package net.hollowed.wikwriv.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.hollowed.wikwriv.client.model.player.TopHatModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class EntitiesClient implements ClientModInitializer {
    public static final EntityModelLayer TOP_HAT_LAYER = new EntityModelLayer(Identifier.of("wikwriv", "top_hat_layer"), "main");
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(TOP_HAT_LAYER, TopHatModel::getTexturedModelData);
    }
}