package net.chemthunder.armada.impl.index;

import net.acoyt.acornlib.api.registrants.ItemGroupRegistrant;
import net.chemthunder.armada.impl.Armada;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public interface ArmadaItemGroups {
    ItemGroupRegistrant ITEM_GROUPS = new ItemGroupRegistrant(Armada.MOD_ID);

    ItemGroup MAIN = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ArmadaItems.HEADHUNTER))
            .displayName(Text.translatable("itemGroup.armada").styled(style -> style.withColor(0xFFf1c04a)))
            .build();

    static void init() {
        ITEM_GROUPS.register("armada", MAIN, ArmadaItemGroups::addEntries);
    }

    private static void addEntries(FabricItemGroupEntries itemGroup) {
        for (Item ITEMS : ArmadaItems.ITEMS.toRegister) {
            itemGroup.add(ITEMS);
        }

        ItemStack Valediction = new ItemStack(ArmadaItems.TUNING_FORK);
        Valediction.set(ArmadaDataComponents.SKIN, 1);
        itemGroup.addAfter(ArmadaItems.TUNING_FORK, Valediction);
    }
}
