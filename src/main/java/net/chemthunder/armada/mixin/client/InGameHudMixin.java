//package net.chemthunder.armada.mixin.client;
//
//import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
//import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
//import net.chemthunder.armada.impl.Armada;
//import net.chemthunder.armada.impl.index.ArmadaDataComponents;
//import net.chemthunder.armada.impl.index.ArmadaItems;
//import net.minecraft.client.MinecraftClient;
//import net.minecraft.client.gui.DrawContext;
//import net.minecraft.client.gui.hud.InGameHud;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.Identifier;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Unique;
//import org.spongepowered.asm.mixin.injection.At;
//
//@Mixin(InGameHud.class)
//public abstract class InGameHudMixin {
//
//    @Unique
//    private static final Identifier TUNE_CROSSHAIR_0 = Armada.id("hud/tuning_crosshair_0");
//
//    @WrapOperation(method = "renderCrosshair", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V", ordinal = 0))
//    private void tuningForkCrosshair(DrawContext instance, Identifier texture, int x, int y, int width, int height, Operation<Void> original) {
//        PlayerEntity player = MinecraftClient.getInstance().player;
//
//        if (player != null) {
//            if (player.isUsingItem()) {
//                ItemStack stack = player.getStackInHand(player.getActiveHand());
//
//                if (stack.isOf(ArmadaItems.TUNING_FORK)) {
//                    var component = ArmadaDataComponents.TUNING_FORK_USES;
//
//                    switch (stack.getOrDefault(component, 0)) {
//                        case 0 -> original.call(instance, Armada.id("hud/tuning_crosshair_0"), x, y, width, height);
//                        case 1 -> original.call(instance, Armada.id("hud/tuning_crosshair_1"), x, y, width, height);
//                        case 2 -> original.call(instance, Armada.id("hud/tuning_crosshair_2"), x, y, width, height);
//                    }
//                }
//            } else {
//                original.call(instance, texture, x, y, width, height);
//            }
//        }
//    }
//}
