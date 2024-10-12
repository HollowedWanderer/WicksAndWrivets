package net.hollowed.wikwriv.common;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.hollowed.wikwriv.WicksWrivets;
import net.hollowed.wikwriv.common.blocks.ModBlocks;
import net.hollowed.wikwriv.common.items.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.TOP_HAT))
            .displayName(Text.translatable("itemGroup.wikwriv.wickswrivets"))
            .entries((context, entries) -> {
                entries.add(ModBlocks.WROUGHT_IRON_BLOCK);
                entries.add(ModBlocks.FILIGREED_WROUGHT_IRON_BLOCK);
                entries.add(ModBlocks.SLATE);
            })
            .build();


    public static void registerItemGroups() {
        Registry.register(Registries.ITEM_GROUP, Identifier.of("wikwriv", "wikwriv"), ITEM_GROUP);
        WicksWrivets.LOGGER.info("Registering Item Group for " + WicksWrivets.MOD_ID);
    }
}
