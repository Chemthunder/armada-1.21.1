package net.chemthunder.armada.impl.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class HeadhunterEntity extends Entity {
    public int ticksActive = 0;
    public int maxLifespan = 90;

    public HeadhunterEntity(EntityType<?> type, World world) {
        super(type, world);
    }


    protected void initDataTracker(DataTracker.Builder builder) {
    }

    public void tick() {
        ticksActive++;

        if (this.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.END_ROD,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    2,
                    0,
                    0,
                    0,
                    0.2f);

            Box area = new Box(this.getBlockPos()).expand(7);
            List<LivingEntity> entities = serverWorld.getEntitiesByClass(LivingEntity.class, area, entity -> true);

            for (LivingEntity entity : entities) {
                entity.damage(entity.getDamageSources().magic(), 4.0f);
                serverWorld.spawnParticles(
                        ParticleTypes.END_ROD,
                        entity.getX() + 0.5f,
                        entity.getY() + 0.5f,
                        entity.getZ() + 0.5f,
                        4,
                        0,
                        0,
                        0,
                        1
                );
            }
        }

        if (ticksActive == maxLifespan) {
            this.returnToOwner();
        }
        super.tick();
    }

    private void returnToOwner() {
        this.discard();
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.ticksActive = nbt.getInt("ticksActive");
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putInt("ticksActive", ticksActive);
    }

    public boolean shouldRender(double distance) {
        return true;
    }
}
