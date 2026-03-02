package net.chemthunder.armada.api.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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

    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().withColor(endColor);
    }

    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }
}
