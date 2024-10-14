package de.xyndra.wikwriv.renderers

import de.xyndra.wikwriv.blockentities.CogBlockEntity
import net.hollowed.wikwriv.WicksWrivets
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.DefaultedBlockGeoModel
import software.bernie.geckolib.renderer.GeoBlockRenderer

class CogBlockEntityRenderer(context: BlockEntityRendererFactory.Context) : GeoBlockRenderer<CogBlockEntity>(DefaultedBlockGeoModel<CogBlockEntity>(Identifier.of(WicksWrivets.MOD_ID, "cog")))