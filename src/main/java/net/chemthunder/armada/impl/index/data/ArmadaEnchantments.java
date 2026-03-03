package net.chemthunder.armada.impl.index.data;

import net.chemthunder.armada.impl.Armada;
import net.chemthunder.armada.impl.index.ArmadaEnchantmentEffects;
import net.chemthunder.armada.impl.index.tag.ArmadaTags;
import net.minecraft.block.Block;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface ArmadaEnchantments {
    RegistryKey<Enchantment> ATTUNE = create("attune");

    private static RegistryKey<Enchantment> create(String id) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Armada.id(id));
    }

    static void bootstrap(Registerable<Enchantment> registerable) {
        RegistryEntryLookup<Enchantment> enchantmentLookup = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        RegistryEntryLookup<EntityType<?>> entityTypeLookup = registerable.getRegistryLookup(RegistryKeys.ENTITY_TYPE);
        RegistryEntryLookup<Block> blockLookup = registerable.getRegistryLookup(RegistryKeys.BLOCK);
        RegistryEntryLookup<Item> itemLookup = registerable.getRegistryLookup(RegistryKeys.ITEM);

        registerable.register(ATTUNE, Enchantment.builder(Enchantment.definition(
                                itemLookup.getOrThrow(ArmadaTags.TUNING_FORKS),
                                2,
                                1,
                                Enchantment.leveledCost(5, 0),
                                Enchantment.leveledCost(17, 0),
                                7,
                                AttributeModifierSlot.MAINHAND
                        ))
                        .addEffect(ArmadaEnchantmentEffects.ATTUNE)
                        .build(ATTUNE.getValue())
        );
    }
}
