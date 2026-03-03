package net.chemthunder.armada.impl.entity;

import net.chemthunder.armada.impl.index.ArmadaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class SoundwaveEntity extends Entity {
    public SoundwaveEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    protected void initDataTracker(DataTracker.Builder builder) {

    }


    public void tick() {
        World world = this.getWorld();

        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.END_ROD,
                    this.getX() + world.getRandom().nextBetween(-6,5),
                    this.getY() + world.getRandom().nextBetween(-6,5),
                    this.getZ() + world.getRandom().nextBetween(-6,5),
                    2,
                    0,
                    0,
                    0,
                    0
            );
        }


        super.tick();
    }

    public boolean hasNoGravity() {
        return true;
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
