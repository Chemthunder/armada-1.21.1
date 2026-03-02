package net.chemthunder.armada.data.provider;

import net.chemthunder.armada.impl.index.ArmadaItems;
import net.chemthunder.armada.impl.index.tag.ArmadaTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ArmadaItemTagGen extends FabricTagProvider.ItemTagProvider {
    public ArmadaItemTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.getOrCreateTagBuilder(ArmadaTags.TWO_HANDED)
                .add(ArmadaItems.HEADHUNTER)
                .setReplace(false);
    }
}
