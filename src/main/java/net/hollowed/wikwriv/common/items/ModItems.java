package net.hollowed.wikwriv.common.items;

import net.hollowed.wikwriv.WicksWrivets;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item TOP_HAT = registerItem("top_hat", new ArmorItem(ModArmorMaterials.TOP_HAT, ArmorItem.Type.HELMET, (new Item.Settings())));

    public static final Item DOUBLE_TOP_HAT = registerItem("double_top_hat", new ArmorItem(ModArmorMaterials.TOP_HAT, ArmorItem.Type.HELMET, (new Item.Settings())));

    public static final Item BEESWAX = registerItem("beeswax", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(WicksWrivets.MOD_ID, name), item);
    }

    public static void registerModItems() {
        WicksWrivets.LOGGER.info("Registering Mod Items for " + WicksWrivets.MOD_ID);
    }
}
