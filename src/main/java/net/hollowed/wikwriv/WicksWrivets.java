package net.hollowed.wikwriv;

import net.fabricmc.api.ModInitializer;

import net.hollowed.wikwriv.common.ModItemGroups;
import net.hollowed.wikwriv.common.blocks.ModBlocks;
import net.hollowed.wikwriv.common.items.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WicksWrivets implements ModInitializer {
	public static final String MOD_ID = "wikwriv";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();

		LOGGER.info("Hello Fabric world!");
	}
}