package net.chemthunder.armada.impl.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.chemthunder.armada.impl.Armada;
import net.chemthunder.armada.impl.item.HeadhunterItem;
import net.chemthunder.armada.impl.item.SobriquetItem;
import net.minecraft.item.Item;

public interface ArmadaItems {
    ItemRegistrant ITEMS = new ItemRegistrant(Armada.MOD_ID);

    Item SOBRIQUET = ITEMS.register("sobriquet", SobriquetItem::new, new Item.Settings().attributeModifiers(SobriquetItem.createAttributeModifiers()).maxCount(1));
    Item HEADHUNTER = ITEMS.register("headhunter", HeadhunterItem::new, new Item.Settings().attributeModifiers(HeadhunterItem.createAttributeModifiers()).maxCount(1));

    static void init() {
        //
    }
}
