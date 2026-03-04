package net.chemthunder.armada.impl.index;

import net.acoyt.acornlib.api.registrants.StatusEffectRegistrant;
import net.chemthunder.armada.impl.Armada;
import net.chemthunder.armada.impl.effect.DeafnessEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.entry.RegistryEntry;

public interface ArmadaStatusEffects {
    StatusEffectRegistrant STATUS_EFFECTS = new StatusEffectRegistrant(Armada.MOD_ID);

    RegistryEntry<StatusEffect> DEAFNESS = STATUS_EFFECTS.registerRef("deafness", new DeafnessEffect(StatusEffectCategory.HARMFUL, 0x000000));

    static void init() {
        //
    }
}
