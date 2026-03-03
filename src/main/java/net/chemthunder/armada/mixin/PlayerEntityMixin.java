package net.chemthunder.armada.mixin;

import net.chemthunder.armada.impl.index.ArmadaDataComponents;
import net.chemthunder.armada.impl.index.ArmadaEnchantmentEffects;
import net.chemthunder.armada.impl.index.ArmadaItems;
import net.chemthunder.armada.mixin.client.PlayerEntityRendererMixin;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
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
        var component = ArmadaDataComponents.TUNING_FORK_USES;

        if (stack.isOf(ArmadaItems.TUNING_FORK)) {
            if (!EnchantmentHelper.hasAnyEnchantmentsWith(stack, ArmadaEnchantmentEffects.ATTUNE)) {
                if (stack.getOrDefault(component, 0) > 0) {
                    if (!player.isCreative()) {
                        if (stack.getOrDefault(component, 0) != 0) {
                            stack.set(component, stack.getOrDefault(component, 0) - 1);
                        }
                    }

                    Box area = new Box(player.getBlockPos()).expand(7);
                    List<LivingEntity> entities = player.getWorld().getEntitiesByClass(LivingEntity.class, area, entity -> true);

                    for (LivingEntity entity : entities) {
                        if (entity != player) {
                            entity.setVelocity(area.getCenter().subtract(entity.getPos()).multiply(-0.5));
                            entity.addVelocity(0, 0.3f, 0);
                            entity.velocityModified = true;

                        }
                    }

                    cir.setReturnValue(false);
                }
            }
        }
    }
}
