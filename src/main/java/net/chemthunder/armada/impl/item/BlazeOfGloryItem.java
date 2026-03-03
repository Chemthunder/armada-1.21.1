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

public class BlazeOfGloryItem extends ArmadaItem implements ModelVaryingItem {
    public BlazeOfGloryItem(Settings settings) {
        super(settings, 0xFF644f34, 0xFF321114, 0xF0180c0e);
    }

    public Identifier getModel(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity) {
        return MiscUtils.isGui(renderMode) ? Armada.id("blaze_of_glory") : Armada.id("blaze_of_glory_handheld");
    }

    public List<Identifier> getModelsToLoad() {
        return Arrays.asList(
                Armada.id("blaze_of_glory"),
                Armada.id("blaze_of_glory_handheld")
        );
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 9.0f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -3.0f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }
}
