package net.chemthunder.armada.impl.index;

import com.mojang.serialization.Codec;
import net.acoyt.acornlib.api.registrants.ComponentTypeRegistrant;
import net.chemthunder.armada.impl.Armada;
import net.minecraft.component.ComponentType;

public interface ArmadaDataComponents {
    ComponentTypeRegistrant DATA_COMPONENTS = new ComponentTypeRegistrant(Armada.MOD_ID);

    ComponentType<Integer> TUNING_FORK_USES = DATA_COMPONENTS.register("tuning_fork_uses", builder -> builder.codec(Codec.INT));
    ComponentType<Integer> HELD_TICKS = DATA_COMPONENTS.register("held_ticks", builder -> builder.codec(Codec.INT));


    static void init() {
        //
    }
}
