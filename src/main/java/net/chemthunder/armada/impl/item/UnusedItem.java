//package net.chemthunder.armada.impl.item;
//
//import net.acoyt.acornlib.api.item.CritEffectItem;
//import net.acoyt.acornlib.api.item.CustomHitParticleItem;
//import net.acoyt.acornlib.api.item.ModelVaryingItem;
//import net.acoyt.acornlib.api.item.SprintUsableItem;
//import net.acoyt.acornlib.api.util.MiscUtils;
//import net.acoyt.acornlib.api.util.ParticleUtils;
//import net.acoyt.acornlib.impl.index.AcornParticles;
//import net.chemthunder.armada.api.item.ArmadaItem;
//import net.chemthunder.armada.impl.Armada;
//import net.chemthunder.armada.impl.index.ArmadaDataComponents;
//import net.chemthunder.armada.impl.index.ArmadaEnchantmentEffects;
//import net.minecraft.client.render.model.json.ModelTransformationMode;
//import net.minecraft.component.type.AttributeModifierSlot;
//import net.minecraft.component.type.AttributeModifiersComponent;
//import net.minecraft.enchantment.EnchantmentHelper;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.attribute.EntityAttributeModifier;
//import net.minecraft.entity.attribute.EntityAttributes;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.particle.SimpleParticleType;
//import net.minecraft.server.world.ServerWorld;
//import net.minecraft.util.Hand;
//import net.minecraft.util.Identifier;
//import net.minecraft.util.TypedActionResult;
//import net.minecraft.world.World;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class UnusedItem extends ArmadaItem implements ModelVaryingItem, SprintUsableItem, CustomHitParticleItem, CritEffectItem {
//    public static final SimpleParticleType[] EFFECTS = new SimpleParticleType[]{
//            AcornParticles.LIGHT_GRAY_SWEEP,
//            AcornParticles.GRAY_SWEEP
//    };
//
//    public UnusedItem(Settings settings) {
//        super(settings.component(ArmadaDataComponents.TUNING_FORK_USES, 2).component(ArmadaDataComponents.HELD_TICKS, 0), 0xFF2a2e28, 0xFFa99797, 0xF0171315);
//    }
//
//    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
//        ItemStack stack = user.getStackInHand(hand);
//
//        user.setCurrentHand(hand);
//        return super.use(world, user, hand);
//    }
//
//    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
//        var HELD_TICKS = ArmadaDataComponents.HELD_TICKS;
//
//        if (EnchantmentHelper.hasAnyEnchantmentsWith(stack, ArmadaEnchantmentEffects.ATTUNE)) {
//            if (world instanceof ServerWorld serverWorld) {
//                if (user instanceof PlayerEntity player) {
//                    if (!player.getItemCooldownManager().isCoolingDown(this)) {
//                        stack.set(HELD_TICKS, stack.getOrDefault(HELD_TICKS, 0) + 1);
////                        SoundwaveEntity soundwaveEntity = new SoundwaveEntity(ArmadaEntities.SOUNDWAVE, serverWorld);
////
////                        soundwaveEntity.setPosition(user.getX(), user.getEyeY() - 0.10000000149011612, user.getZ());
////                  //      soundwaveEntity.setVelocity(user, user.getPitch(), user.getHeadYaw(), 0.0F, 1.5F, 0.0F);
////                        soundwaveEntity.setVelocity(user.getRotationVec(0).multiply(1));
////                        soundwaveEntity.setPitch(user.getPitch());
////                        soundwaveEntity.setYaw(user.getHeadYaw());
////                        soundwaveEntity.velocityModified = true;
//
//               //         serverWorld.spawnEntity(soundwaveEntity);
//
//                        if (stack.getOrDefault(HELD_TICKS, 0) >= 90) {
//                            player.getItemCooldownManager().set(this, 90);
//                            stack.set(HELD_TICKS, 0);
//                        }
//                    }
//                }
//            }
//        }
//        super.usageTick(world, user, stack, remainingUseTicks);
//    }
//
//    public Identifier getModel(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity) {
//        if (entity != null) {
//            if (entity.isUsingItem()) {
//                if (EnchantmentHelper.hasAnyEnchantmentsWith(stack, ArmadaEnchantmentEffects.ATTUNE)) {
//                    return MiscUtils.isGui(renderMode) ? Armada.id("tuning_fork") : Armada.id("tuning_fork_handheld_attuning");
//                } else {
//                    return MiscUtils.isGui(renderMode) ? Armada.id("tuning_fork") : Armada.id("tuning_fork_handheld_blocking");
//                }
//            }
//        }
//
//        return MiscUtils.isGui(renderMode) ? Armada.id("tuning_fork") : Armada.id("tuning_fork_handheld");
//    }
//
//    public List<Identifier> getModelsToLoad() {
//        return Arrays.asList(
//                Armada.id("tuning_fork"),
//                Armada.id("tuning_fork_handheld"),
//                Armada.id("tuning_fork_handheld_blocking"),
//                Armada.id("tuning_fork_handheld_attuning")
//        );
//    }
//
//    public static AttributeModifiersComponent createAttributeModifiers() {
//        return AttributeModifiersComponent.builder()
//                .add(
//                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
//                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 6.5f, EntityAttributeModifier.Operation.ADD_VALUE),
//                        AttributeModifierSlot.MAINHAND
//                )
//                .add(
//                        EntityAttributes.GENERIC_ATTACK_SPEED,
//                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.6f, EntityAttributeModifier.Operation.ADD_VALUE),
//                        AttributeModifierSlot.MAINHAND
//                )
//                .build();
//    }
//
//    public void spawnHitParticles(PlayerEntity playerEntity, Entity target) {
//        ParticleUtils.spawnSweepParticles(EFFECTS[playerEntity.getRandom().nextInt(EFFECTS.length)], playerEntity);
//    }
//
//    public void critEffect(PlayerEntity player, ItemStack stack, Entity target) {
//        var component = ArmadaDataComponents.TUNING_FORK_USES;
//
//        if (stack.getOrDefault(component, 0) != 2) {
//            stack.set(component, stack.getOrDefault(component, 0) + 1);
//        }
//    }
//}
