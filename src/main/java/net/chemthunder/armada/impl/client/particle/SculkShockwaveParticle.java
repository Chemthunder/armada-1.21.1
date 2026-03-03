package net.chemthunder.armada.impl.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class SculkShockwaveParticle extends ExplosionLargeParticle {
    public SculkShockwaveParticle(ClientWorld world, double x, double y, double z, double d, SpriteProvider spriteProvider) {
        super(world, x, y, z, d, spriteProvider);
        this.maxAge = 24;
        this.scale = 10.0F;
        this.gravityStrength = 0.0F;
        this.velocityX = 0.0F;
        this.velocityY = 0.0F;
        this.velocityZ = 0.0F;
        this.red = 1.0F;
        this.green = 1.0F;
        this.blue = 1.0F;
        this.setSpriteForAge(spriteProvider);
    }

    // Utils
    public static void hamiltonProduct(Quaternionf input, Quaternionf other) {
        float f = input.x;
        float g = input.y();
        float h = input.z();
        float i = input.w();
        float j = other.x();
        float k = other.y();
        float l = other.z();
        float m = other.w();
        input.x = i * j + f * m + g * l - h * k;
        input.y = i * k - f * l + g * m + h * j;
        input.z = i * l + f * k - g * j + h * m;
        input.w = i * m - f * j - g * k - h * l;
    }

    @Override
    public float getSize(float tickDelta) {
        float d = ((float) this.age + tickDelta) / (float) this.maxAge;
        return this.scale * MathHelper.clamp(d, 0.0F, 1.0F);
    }

    public void render(VertexConsumer vertexConsumer, Camera camera, Quaternionf quaternionf, float tickDelta) {
        this.alpha = MathHelper.clamp(-2.0F * (((float) this.age + tickDelta) / (float) this.maxAge) + 2.0F, 0.0F, 1.0F);
        float pi = (float) Math.PI;
        this.render(vertexConsumer, camera, tickDelta, (quaternion) -> {
            hamiltonProduct(quaternion, RotationAxis.POSITIVE_Y.rotationDegrees(0.0F));
            hamiltonProduct(quaternion, RotationAxis.POSITIVE_X.rotationDegrees(pi * -0.5F));
        });
        this.render(vertexConsumer, camera, tickDelta, (quaternion) -> {
            hamiltonProduct(quaternion, RotationAxis.POSITIVE_Y.rotationDegrees(-pi));
            hamiltonProduct(quaternion, RotationAxis.POSITIVE_X.rotationDegrees(pi * 0.5F));
        });
    }

    public void tick() {
        super.tick();
    }

    private void render(VertexConsumer vertexConsumer, Camera camera, float tickDelta, Consumer<Quaternionf> rotator) {
        Vec3d vec3d = camera.getPos();
        float f = (float) (MathHelper.lerp(tickDelta, this.prevPosX, this.x) - vec3d.getX());
        float g = (float) (MathHelper.lerp(tickDelta, this.prevPosY, this.y) - vec3d.getY());
        float h = (float) (MathHelper.lerp(tickDelta, this.prevPosZ, this.z) - vec3d.getZ());
        Quaternionf quaternionf = new Quaternionf().rotateXYZ(89.5F, 0.0F, 0.0F);
        rotator.accept(quaternionf);
        Vector3f[] vec3fs = new Vector3f[]{
                new Vector3f(-1.0F, -1.0F, 0.0F),
                new Vector3f(-1.0F, 1.0F, 0.0F),
                new Vector3f(1.0F, 1.0F, 0.0F),
                new Vector3f(1.0F, -1.0F, 0.0F)
        };

        float size = this.getSize(tickDelta);

        for (int i = 0; i < 4; ++i) {
            Vector3f vec3f = vec3fs[i];
            vec3f.rotate(quaternionf);
            vec3f.mul(size);
            vec3f.add(f, g, h);
        }

        int brightness = this.getBrightness(tickDelta);
        this.vertex(vertexConsumer, vec3fs[0], this.getMaxU(), this.getMaxV(), brightness);
        this.vertex(vertexConsumer, vec3fs[1], this.getMaxU(), this.getMinV(), brightness);
        this.vertex(vertexConsumer, vec3fs[2], this.getMinU(), this.getMinV(), brightness);
        this.vertex(vertexConsumer, vec3fs[3], this.getMinU(), this.getMaxV(), brightness);
    }

    private void vertex(VertexConsumer vertexConsumer, Vector3f pos, float u, float v, int light) {
        vertexConsumer.vertex(pos.x, pos.y, pos.z).texture(u, v).color(this.red, this.green, this.blue, this.alpha).light(light);
    }

    public int getBrightness(float tint) {
        return 240;
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType parameters, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new SculkShockwaveParticle(clientWorld, d, e, f, g, this.spriteProvider);
        }
    }
}
