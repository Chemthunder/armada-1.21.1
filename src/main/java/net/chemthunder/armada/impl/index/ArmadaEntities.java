package net.chemthunder.armada.impl.index;

import net.acoyt.acornlib.api.registrants.EntityTypeRegistrant;
import net.chemthunder.armada.impl.Armada;
import net.chemthunder.armada.impl.client.render.entity.HeadhunterEntityRenderer;
import net.chemthunder.armada.impl.entity.HeadhunterEntity;
import net.chemthunder.armada.impl.entity.SoundwaveEntity;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EmptyEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

@SuppressWarnings("all")
public interface ArmadaEntities {
    EntityTypeRegistrant ENTITIES = new EntityTypeRegistrant<>(Armada.MOD_ID);

    EntityType<HeadhunterEntity> HEADHUNTER = ENTITIES.register("headhunter", EntityType.Builder.create(HeadhunterEntity::new, SpawnGroup.MISC).dimensions(0.1F, 0.1F));
    EntityType<SoundwaveEntity> SOUNDWAVE = ENTITIES.register("soundwave", EntityType.Builder.create(SoundwaveEntity::new, SpawnGroup.MISC).dimensions(1.5F, 1.5F));

    static void init() {
        //
    }

    static void clientInit() {
        EntityRendererRegistry.register(HEADHUNTER, HeadhunterEntityRenderer::new);
        EntityRendererRegistry.register(SOUNDWAVE, EmptyEntityRenderer::new);
    }
}
