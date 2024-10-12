package net.hollowed.wikwriv.common.blocks;

import net.hollowed.wikwriv.WicksWrivets;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block WROUGHT_IRON_BLOCK = registerBlock("wrought_iron_block",
            new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.NETHERITE).strength(5F, 6F)));

    public static final Block FILIGREED_WROUGHT_IRON_BLOCK = registerBlock("filigreed_wrought_iron_block",
            new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.NETHERITE).strength(5F, 6F)));

    public static final Block SLATE = registerBlock("slate",
            new PillarBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE).strength(5F, 6F)));

    public static final Block SLATE_BRICKS = registerBlock("slate_bricks",
            new PillarBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.DEEPSLATE).strength(5F, 6F)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(WicksWrivets.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(WicksWrivets.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        WicksWrivets.LOGGER.info("Registering ModBlocks for " + WicksWrivets.MOD_ID);
    }
}
