package net.chemthunder.armada.impl.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ArmadaItem extends Item implements ColorableItem {
    int startColor;
    int endColor;
    int backgroundcolor;

    public ArmadaItem(Settings settings, int startColor, int endColor, int backgroundcolor) {
        super(settings);
        this.startColor = startColor;
        this.endColor = endColor;
        this.backgroundcolor = backgroundcolor;
    }

    public int startColor(ItemStack itemStack) {
        return startColor;
    }

    public int endColor(ItemStack itemStack) {
        return endColor;
    }

    public int backgroundColor(ItemStack itemStack) {
        return backgroundcolor;
    }
}
