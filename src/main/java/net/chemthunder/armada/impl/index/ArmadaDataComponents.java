package net.chemthunder.armada.impl.index;

import com.mojang.serialization.Codec;
import net.acoyt.acornlib.api.registrants.ComponentTypeRegistrant;
import net.chemthunder.armada.impl.Armada;
import net.minecraft.component.ComponentType;

public interface ArmadaDataComponents {
    ComponentTypeRegistrant DATA_COMPONENTS = new ComponentTypeRegistrant(Armada.MOD_ID);

    ComponentType<Boolean> HEADHUNTER_RETURNED = DATA_COMPONENTS.register("headhunter_returned", booleanBuilder -> booleanBuilder.codec(Codec.BOOL));

    static void init() {
        //
    }
}
