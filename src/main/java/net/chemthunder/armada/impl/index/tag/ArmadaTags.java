package net.chemthunder.armada.impl.index.tag;

import net.chemthunder.armada.impl.Armada;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public interface ArmadaTags {
    TagKey<Item> TWO_HANDED = create("two_handed");
    TagKey<Item> TUNING_FORKS = create("tuning_forks");

    private static TagKey<Item> create(String id) {
        return TagKey.of(RegistryKeys.ITEM, Armada.id(id));
    }
}
