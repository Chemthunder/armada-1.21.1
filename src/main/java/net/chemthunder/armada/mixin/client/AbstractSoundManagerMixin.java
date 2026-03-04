package net.chemthunder.armada.mixin.client;

import net.chemthunder.armada.impl.index.ArmadaStatusEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.AbstractSoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractSoundInstance.class)
public abstract class AbstractSoundManagerMixin {

    @Inject(method = "getVolume", at = @At("HEAD"), cancellable = true)
    private void deafness(CallbackInfoReturnable<Float> cir) {
        PlayerEntity player = MinecraftClient.getInstance().player;

        if (player != null) {
            if (player.hasStatusEffect(ArmadaStatusEffects.DEAFNESS)) {
                cir.setReturnValue(0f);
            }
        }
    }
}
