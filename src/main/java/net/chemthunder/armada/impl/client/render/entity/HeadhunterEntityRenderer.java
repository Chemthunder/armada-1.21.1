package net.chemthunder.armada.impl.client.render.entity;

import net.chemthunder.armada.impl.entity.HeadhunterEntity;
import net.chemthunder.armada.impl.index.ArmadaItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class HeadhunterEntityRenderer extends EntityRenderer<HeadhunterEntity> {
    public HeadhunterEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    public Identifier getTexture(HeadhunterEntity entity) {
        return null;
    }

    public void render(HeadhunterEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = new ItemStack(ArmadaItems.HEADHUNTER);
        int rotationSpeed = 50;

        matrices.push();

        if (entity.ticksActive > 0) {
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(10));
   //         matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((entity.age + tickDelta)));


            matrices.translate(0, 0, -0.1f);


            itemRenderer.renderItem(
                    stack,
                    ModelTransformationMode.THIRD_PERSON_RIGHT_HAND,
                    light,
                    OverlayTexture.DEFAULT_UV,
                    matrices,
                    vertexConsumers,
                    entity.getWorld(),
                    1
            );
        }

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
