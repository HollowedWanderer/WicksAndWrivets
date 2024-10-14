package de.xyndra.wikwriv.blockentities

import net.hollowed.wikwriv.WicksWrivets
import net.hollowed.wikwriv.common.blocks.ModBlocks
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object BlockEntityTypes {
    val CHIMNEY_BLOCKENTITY_TYPE: BlockEntityType<ChimneyBlockEntity> = register("chimney", BlockEntityType.Builder.create({ pos, state -> ChimneyBlockEntity(pos, state) }, ModBlocks.CHIMNEY).build(null))
    val COG_BLOCKENTITY_TYPE: BlockEntityType<CogBlockEntity> = register("cog", BlockEntityType.Builder.create({ pos, state -> CogBlockEntity(pos, state) }, ModBlocks.COG).build(null))

    fun <T : BlockEntityType<*>> register(path: String, blockEntityType: T): T {
        return return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(WicksWrivets.MOD_ID, path), blockEntityType)
    }

    fun init() {}
}