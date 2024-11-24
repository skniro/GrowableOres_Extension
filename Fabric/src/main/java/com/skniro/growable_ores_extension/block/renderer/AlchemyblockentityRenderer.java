package com.skniro.growable_ores_extension.block.renderer;


import com.skniro.growable_ores_extension.block.entity.Alchemyblockentity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class AlchemyblockentityRenderer implements BlockEntityRenderer<Alchemyblockentity> {
    public AlchemyblockentityRenderer(BlockEntityRendererFactory.Context context) {
    }
    @Override
    public void render(Alchemyblockentity entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = entity.getRenderStack();
        Direction direction = entity.getCachedState().get(Properties.HORIZONTAL_FACING);
        matrices.push();
        switch (direction) {
            case NORTH -> matrices.translate(0.5f, 0.5f, -0.01f);
            case SOUTH -> matrices.translate(0.5f, 0.5f, 1.01f);
            case WEST -> matrices.translate(-0.01f, 0.5f, 0.5f);
            case EAST -> matrices.translate(1.01f, 0.5f, 0.5f);
        }
        matrices.scale(0.35f, 0.35f, 0.35f);
        switch (direction) {
            case NORTH -> matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(0));
            case SOUTH -> matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180));
            case WEST -> matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90));
            case EAST -> matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(270));
        }
        itemRenderer.renderItem(stack, ModelTransformation.Mode.GUI, getLightLevel(entity.getWorld(),
                entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, 1);
        matrices.pop();
    }
    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, Math.max(sLight, 15));
    }
}