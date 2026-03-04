package net.chemthunder.armada.impl;

import net.chemthunder.armada.impl.index.*;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Armada implements ModInitializer {
	public static final String MOD_ID = "armada";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	public void onInitialize() {
        ArmadaItems.init();
        ArmadaEnchantmentEffects.init();
        ArmadaDataComponents.init();
        ArmadaEntities.init();
        ArmadaItemGroups.init();
        ArmadaStatusEffects.init();

		LOGGER.info("Hello Fabric world!");
	}

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}