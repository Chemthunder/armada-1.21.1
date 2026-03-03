package net.chemthunder.armada.data;

import net.chemthunder.armada.data.provider.ArmadaItemTagGen;
import net.chemthunder.armada.data.provider.resources.ArmadaLangGen;
import net.chemthunder.armada.data.provider.resources.ArmadaModelGen;
import net.chemthunder.armada.impl.index.data.ArmadaEnchantments;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class ArmadaDataGenerator implements DataGeneratorEntrypoint {
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ArmadaModelGen::new);
        pack.addProvider(ArmadaLangGen::new);

        pack.addProvider(ArmadaItemTagGen::new);
        pack.addProvider(ArmadaDynamicRegistryGen::new);
	}

    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, ArmadaEnchantments::bootstrap);
    }
}
