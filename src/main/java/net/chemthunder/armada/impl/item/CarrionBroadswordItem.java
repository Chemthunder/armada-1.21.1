package net.chemthunder.armada.impl.item;

import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.chemthunder.armada.api.item.ArmadaItem;
import net.chemthunder.armada.impl.Armada;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class CarrionBroadswordItem extends ArmadaItem implements ModelVaryingItem {
    public CarrionBroadswordItem(Settings settings) {
        super(settings,0xFF2f0a16, 0xFF7a1d32, 0xF023111a);
    }

    public Identifier getModel(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity) {
        return MiscUtils.isGui(renderMode) ? Armada.id("carrion_broadsword") : Armada.id("carrion_broadsword_handheld");
    }

    public List<Identifier> getModelsToLoad() {
        return Arrays.asList(
                Armada.id("carrion_broadsword"),
                Armada.id("carrion_broadsword_handheld")
        );
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 8.0f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.9f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }
}
