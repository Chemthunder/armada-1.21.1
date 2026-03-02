package net.chemthunder.armada.impl.item;

import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.CustomHitSoundItem;
import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.acoyt.acornlib.api.util.ParticleUtils;
import net.acoyt.acornlib.impl.index.AcornParticles;
import net.chemthunder.armada.api.item.ArmadaItem;
import net.chemthunder.armada.impl.Armada;
import net.chemthunder.armada.impl.entity.HeadhunterEntity;
import net.chemthunder.armada.impl.index.ArmadaEntities;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class HeadhunterItem extends ArmadaItem implements ModelVaryingItem, CustomHitParticleItem, CustomHitSoundItem {
    public static final SimpleParticleType[] EFFECTS = new SimpleParticleType[]{
            AcornParticles.GOLD_SWEEP,
            AcornParticles.ALT_GOLD_SWEEP
    };

    public HeadhunterItem(Settings settings) {
        super(settings, 0xFFde8e13, 0xFFf1c04a, 0xF0381b00);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!user.getItemCooldownManager().isCoolingDown(this)) {
            HeadhunterEntity hunter = new HeadhunterEntity(ArmadaEntities.HEADHUNTER, world);

            hunter.setPos(user.getX(), user.getY() + 1.0f, user.getZ());
            world.spawnEntity(hunter);
            user.getItemCooldownManager().set(this, 90);
        }

        return super.use(world, user, hand);
    }

    public Identifier getModel(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity) {
        return MiscUtils.isGui(renderMode) ? Armada.id("headhunter") : Armada.id("headhunter_handheld");
    }

    public List<Identifier> getModelsToLoad() {
        return Arrays.asList(
                Armada.id("headhunter"),
                Armada.id("headhunter_handheld")
        );
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 6.5f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.7f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(Identifier.ofVanilla("base_entity_interaction_range"), 1.0f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    public void spawnHitParticles(PlayerEntity playerEntity, Entity entity) {
        ParticleUtils.spawnSweepParticles(EFFECTS[playerEntity.getRandom().nextInt(EFFECTS.length)], playerEntity);
    }

    public void playHitSound(PlayerEntity player, Entity entity) {
        player.playSound(SoundEvents.BLOCK_NETHER_GOLD_ORE_BREAK, 1.0F, (float) (1.0F + player.getRandom().nextGaussian() / 10.0F));
    }
}
