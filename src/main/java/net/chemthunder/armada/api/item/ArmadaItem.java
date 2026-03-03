package net.chemthunder.armada.api.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.chemthunder.armada.impl.Armada;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

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

    public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return oldStack.getItem() != newStack.getItem();
    }
}
