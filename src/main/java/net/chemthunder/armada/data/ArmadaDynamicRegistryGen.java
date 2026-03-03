package net.chemthunder.armada.data;

import net.chemthunder.armada.impl.Armada;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ArmadaDynamicRegistryGen extends FabricDynamicRegistryProvider {
    public ArmadaDynamicRegistryGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
        entries.addAll(wrapperLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT));
    }

    public String getName() {
        return Armada.MOD_ID + "_dynamic";
    }
}
