package net.chemthunder.armada.impl.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.acoyt.acornlib.api.util.ParticleUtils;
import net.acoyt.acornlib.impl.index.AcornParticles;
import net.chemthunder.armada.api.item.ArmadaItem;
import net.chemthunder.armada.impl.Armada;
import net.chemthunder.armada.impl.index.ArmadaDataComponents;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class TuningForkItem extends ArmadaItem implements ModelVaryingItem, CustomHitParticleItem, ColorableItem {
    public static final int maxCharges = 30;
    public static final SimpleParticleType[] EFFECTS = new SimpleParticleType[]{
            AcornParticles.LIGHT_GRAY_SWEEP,
            AcornParticles.GRAY_SWEEP
    };

    public static final SimpleParticleType[] ALT_EFFECTS = new SimpleParticleType[]{
            AcornParticles.MAGENTA_SWEEP,
            AcornParticles.PURPLE_SWEEP
    };

    public int startColor(ItemStack itemStack) {
        if (getSkin(itemStack) == 1) {
            return 0xFF2d2534;
        }
        return 0xFF2a2e28;
    }

    public int endColor(ItemStack itemStack) {
        if (getSkin(itemStack) == 1) {
            return 0xFF7a6873;
        }
        return 0xFFa99797;
    }

    public int backgroundColor(ItemStack itemStack) {
        if (getSkin(itemStack) == 1) {
            return 0xFF0c070e;
        }
        return 0xF0171315;
    }

    public TuningForkItem(Settings settings) {
        super(settings.component(ArmadaDataComponents.CHARGES, 0).component(ArmadaDataComponents.SKIN, 0), 0xFF2a2e28, 0xFFa99797, 0xF0171315);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return super.use(world, user, hand);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        if (player != null) {
            ItemStack stack = player.getStackInHand(player.getActiveHand());

            if (context.getWorld().getBlockState(context.getBlockPos()).isOf(Blocks.SMITHING_TABLE)) {
                var skin = ArmadaDataComponents.SKIN;
                int result = 0;

                switch (stack.getOrDefault(skin, 0)) {
                    case 0 -> result = 1;
                    case 1 -> result = 0;
                }

                stack.set(skin, result);

                player.swingHand(player.getActiveHand());
                player.playSoundToPlayer(SoundEvents.BLOCK_SMITHING_TABLE_USE, SoundCategory.BLOCKS, 1, 1);
            }
        }

        return super.useOnBlock(context);
    }

    public Identifier getModel(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity) {
        String skinId = "";

        switch (getSkin(stack)) {
            case 0 -> skinId = "tuning_fork";
            case 1 -> skinId = "skin/valediction";
        }

        if (entity != null) {
            if (entity.isUsingItem()) {
                return MiscUtils.isGui(renderMode) ? Armada.id(skinId) : Armada.id(skinId + "_handheld_blocking");
            }
        }

      //  Armada.LOGGER.info(skinId);
        return MiscUtils.isGui(renderMode) ? Armada.id(skinId) : Armada.id(skinId + "_handheld");
    }

    public List<Identifier> getModelsToLoad() {
        return Arrays.asList(
                Armada.id("tuning_fork"),
                Armada.id("tuning_fork_handheld"),
                Armada.id("tuning_fork_handheld_blocking"),
                Armada.id("skin/valediction"),
                Armada.id("skin/valediction_handheld"),
                Armada.id("skin/valediction_handheld_blocking")
        );
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 7.0f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.6f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(Identifier.ofVanilla("base_entity_interaction_range"), 1.0f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    public void spawnHitParticles(PlayerEntity playerEntity, Entity target) {
        ItemStack stack = playerEntity.getStackInHand(Hand.MAIN_HAND);

        if (getSkin(stack) == 1) {
            ParticleUtils.spawnSweepParticles(ALT_EFFECTS[playerEntity.getRandom().nextInt(ALT_EFFECTS.length)], playerEntity);
        } else {
            ParticleUtils.spawnSweepParticles(EFFECTS[playerEntity.getRandom().nextInt(EFFECTS.length)], playerEntity);
        }

    }

    public Text getName(ItemStack stack) {
        if (getSkin(stack) == 1) {
            return Text.literal("Valediction").withColor(0x7d153b);
        }

        return super.getName(stack).copy().withColor(0xFFa99797);
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        var charges = ArmadaDataComponents.CHARGES;

        if (stack.getOrDefault(charges, 0) != TuningForkItem.maxCharges) {
            stack.set(charges, stack.getOrDefault(charges, 0) + 1);
        }
        return super.postHit(stack, target, attacker);
    }

    public static int getSkin(ItemStack stack) {
        return stack.getOrDefault(ArmadaDataComponents.SKIN, 0);
    }
}
