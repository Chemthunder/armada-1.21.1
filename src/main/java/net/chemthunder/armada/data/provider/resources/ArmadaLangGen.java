package net.chemthunder.armada.data.provider.resources;

import net.chemthunder.armada.impl.index.ArmadaItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static net.chemthunder.armada.impl.index.ArmadaEnchantmentEffects.ENCHANT_EFFECTS;
import static net.chemthunder.armada.impl.index.ArmadaItems.ITEMS;
import static net.chemthunder.armada.impl.index.ArmadaStatusEffects.STATUS_EFFECTS;

public class ArmadaLangGen extends FabricLanguageProvider {
    public ArmadaLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        ITEMS.registerLang(wrapperLookup, translationBuilder);
        ENCHANT_EFFECTS.registerLang(wrapperLookup, translationBuilder);
        STATUS_EFFECTS.registerLang(wrapperLookup, translationBuilder);

        translationBuilder.add("itemGroup.armada", "Armada");
    }
}
