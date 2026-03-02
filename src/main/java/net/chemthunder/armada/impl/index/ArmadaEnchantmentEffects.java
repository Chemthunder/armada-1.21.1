package net.chemthunder.armada.impl.index;

import net.acoyt.acornlib.api.registrants.EnchantmentEffectRegistrant;
import net.chemthunder.armada.impl.Armada;
import net.minecraft.component.ComponentType;
import net.minecraft.util.Unit;

public interface ArmadaEnchantmentEffects {
    EnchantmentEffectRegistrant ENCHANT_EFFECTS = new EnchantmentEffectRegistrant(Armada.MOD_ID);

 //   ComponentType<Unit> REELING = ENCHANT_EFFECTS.register( "reeling", builder -> builder.codec(Unit.CODEC));

    static void init() {
        //
    }
}
