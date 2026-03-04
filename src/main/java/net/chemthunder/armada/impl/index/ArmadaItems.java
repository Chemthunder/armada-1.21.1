package net.chemthunder.armada.impl.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.chemthunder.armada.impl.Armada;
import net.chemthunder.armada.impl.item.*;
import net.minecraft.item.Item;

public interface ArmadaItems {
    ItemRegistrant ITEMS = new ItemRegistrant(Armada.MOD_ID);

    Item SOBRIQUET = ITEMS.register("sobriquet", SobriquetItem::new, new Item.Settings()
            .attributeModifiers(SobriquetItem.createAttributeModifiers())
            .maxCount(1)
    );

    Item HEADHUNTER = ITEMS.register("headhunter", HeadhunterItem::new, new Item.Settings()
            .attributeModifiers(HeadhunterItem.createAttributeModifiers())
            .maxCount(1)
    );

    Item BLOOMING_PETAL = ITEMS.register("blooming_petal", BloomingPetalItem::new, new Item.Settings()
            .attributeModifiers(BloomingPetalItem.createAttributeModifiers())
            .maxCount(1)
    );

    Item TUNING_FORK = ITEMS.register("tuning_fork", TuningForkItem::new, new Item.Settings()
            .attributeModifiers(TuningForkItem.createAttributeModifiers())
            .maxCount(1)
    );

    Item BLAZE_OF_GLORY = ITEMS.register("blaze_of_glory", BlazeOfGloryItem::new, new Item.Settings()
            .attributeModifiers(BlazeOfGloryItem.createAttributeModifiers())
            .maxCount(1)
    );

    Item CARRION_BROADSWORD = ITEMS.register("carrion_broadsword", CarrionBroadswordItem::new, new Item.Settings()
            .attributeModifiers(CarrionBroadswordItem.createAttributeModifiers())
            .maxCount(1)
    );

    Item INTRASOLAR = ITEMS.register("intrasolar", IntrasolarItem::new, new Item.Settings()
            .attributeModifiers(IntrasolarItem.createAttributeModifiers())
            .maxCount(1)
    );

    static void init() {
        //
    }
}
