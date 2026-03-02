package net.chemthunder.armada.data.provider;

import net.chemthunder.armada.impl.index.ArmadaItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ArmadaModelGen extends FabricModelProvider {
    public ArmadaModelGen(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //
    }

    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(ArmadaItems.SOBRIQUET, Models.GENERATED);
        generator.register(ArmadaItems.HEADHUNTER, Models.GENERATED);
    }
}
