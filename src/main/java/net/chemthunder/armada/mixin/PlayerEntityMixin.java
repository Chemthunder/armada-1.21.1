package net.chemthunder.armada.mixin;

import net.chemthunder.armada.impl.index.ArmadaDataComponents;
import net.chemthunder.armada.impl.index.ArmadaEnchantmentEffects;
import net.chemthunder.armada.impl.index.ArmadaItems;
import net.chemthunder.armada.impl.item.TuningForkItem;
import net.chemthunder.armada.mixin.client.PlayerEntityRendererMixin;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void tuningForkShieldEffect(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack stack = player.getStackInHand(player.getActiveHand());
        var charges = ArmadaDataComponents.CHARGES;

        if (source != null) {
            if (stack.isOf(ArmadaItems.TUNING_FORK)) {
                if (player.isUsingItem() && !player.isCreative()) {
                    if (!source.isOf(DamageTypes.FALL)) {
                        player.setVelocity(player.getRotationVec(0).multiply(-0.4f));
                        player.velocityModified = true;
                        player.damage(source, amount / 2);
                        cir.setReturnValue(false);
                    }

                    if (stack.getOrDefault(charges, 0) != TuningForkItem.maxCharges) {
                        stack.set(charges, stack.getOrDefault(charges, 0) + 1);
                    }
                }
            }
        }
    }
}
