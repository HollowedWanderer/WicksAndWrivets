package net.hollowed.wikwriv.client;

import de.xyndra.wikwriv.Side;
import de.xyndra.wikwriv.blockentities.BlockEntityTypes;
import de.xyndra.wikwriv.renderers.CogBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.hollowed.wikwriv.client.model.player.DoubleTopHatModel;
import net.hollowed.wikwriv.client.model.player.TopHatModel;
import net.hollowed.wikwriv.client.model.player.TripleTopHatModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import static de.xyndra.wikwriv.events.EventRegisterKt.registerEvents;

@Environment(EnvType.CLIENT)
public class WicksWrivetsClient implements ClientModInitializer {
    public static final EntityModelLayer TOP_HAT_LAYER = new EntityModelLayer(Identifier.of("wikwriv", "top_hat_layer"), "main");
    public static final EntityModelLayer DOUBLE_TOP_HAT_LAYER = new EntityModelLayer(Identifier.of("wikwriv", "double_top_hat_layer"), "main");
    public static final EntityModelLayer TRIPLE_TOP_HAT_LAYER = new EntityModelLayer(Identifier.of("wikwriv", "triple_top_hat_layer"), "main");
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(TOP_HAT_LAYER, TopHatModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(DOUBLE_TOP_HAT_LAYER, DoubleTopHatModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(TRIPLE_TOP_HAT_LAYER, TripleTopHatModel::getTexturedModelData);
        BlockEntityRendererRegistry.register(BlockEntityTypes.INSTANCE.getCOG_BLOCKENTITY_TYPE(), CogBlockEntityRenderer::new);
        registerEvents(Side.CLIENT);
    }
}