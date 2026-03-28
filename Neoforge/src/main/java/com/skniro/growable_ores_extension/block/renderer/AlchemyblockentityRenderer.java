package com.skniro.growable_ores_extension.block.renderer;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.skniro.growable_ores_extension.block.entity.Alchemyblockentity;
import com.skniro.growable_ores_extension.block.renderer.state.AlchemyBlockEntityRenderState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class AlchemyblockentityRenderer implements BlockEntityRenderer<Alchemyblockentity, AlchemyBlockEntityRenderState> {
    public AlchemyblockentityRenderer(BlockEntityRendererProvider.Context context) {
    }
    @Override
    public void submit(AlchemyBlockEntityRenderState state, PoseStack matrices, SubmitNodeCollector queue, CameraRenderState cameraState) {
        Direction direction = state.blockState.getValue(BlockStateProperties.HORIZONTAL_FACING);
        matrices.pushPose();
        switch (direction) {
            case NORTH -> matrices.translate(0.5f, 0.5f, -0.01f);
            case SOUTH -> matrices.translate(0.5f, 0.5f, 1.01f);
            case WEST -> matrices.translate(-0.01f, 0.5f, 0.5f);
            case EAST -> matrices.translate(1.01f, 0.5f, 0.5f);
        }
        matrices.scale(0.35f, 0.35f, 0.35f);
        switch (direction) {
            case NORTH -> matrices.mulPose(Axis.YP.rotationDegrees(0));
            case SOUTH -> matrices.mulPose(Axis.YP.rotationDegrees(180));
            case WEST -> matrices.mulPose(Axis.YP.rotationDegrees(90));
            case EAST -> matrices.mulPose(Axis.YP.rotationDegrees(270));
        }
        state.item.submit(matrices, queue, state.lightCoords, OverlayTexture.NO_OVERLAY,0);
/*        itemRenderer.renderItem(stack, ItemDisplayContext.GUI, getLightLevel(entity.getWorld(),
                entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);*/
        matrices.popPose();
    }
    private int getLightLevel(Level world, BlockPos pos) {
        int bLight = world.getBrightness(LightLayer.BLOCK, pos);
        int sLight = world.getBrightness(LightLayer.SKY, pos);
        return LightCoordsUtil.pack(bLight, Math.max(sLight, 15));
    }

    @Override
    public AlchemyBlockEntityRenderState createRenderState() {
        return new AlchemyBlockEntityRenderState();
    }

    public void extractRenderState(Alchemyblockentity entity, AlchemyBlockEntityRenderState state, float tickProgress, Vec3 cameraPos, @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderer.super.extractRenderState(entity, state, tickProgress, cameraPos, crumblingOverlay);
        ItemModelResolver itemModelResolver = Minecraft.getInstance().getItemModelResolver();
        itemModelResolver.updateForTopItem(state.item, entity.getRenderStack(), ItemDisplayContext.GUI, entity.getLevel(), entity, 1);
        state.blockPos = entity.getBlockPos();
        state.blockState = entity.getBlockState();
        state.lightCoords = getLightLevel(entity.level(), entity.getBlockPos());
    }


}