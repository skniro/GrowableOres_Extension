package com.skniro.growable_ores_extension.block.renderer;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.skniro.growable_ores_extension.block.entity.Alchemyblockentity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.model.BuiltInModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class AlchemyblockentityRenderer extends TileEntityRenderer<Alchemyblockentity> {
    public AlchemyblockentityRenderer(TileEntityRendererDispatcher context) {
        super(context);
    }

    @Override
    public void render(Alchemyblockentity entity, float tickDelta, MatrixStack matrices,
                       IRenderTypeBuffer vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack = entity.getRenderStack();
        Direction direction = entity.getBlockState().get(BlockStateProperties.HORIZONTAL_FACING);
        matrices.push();
        switch (direction) {
            case NORTH:
                matrices.translate(0.5f, 0.5f, -0.01f);
                break;
            case SOUTH:
                matrices.translate(0.5f, 0.5f, 1.01f);
                break;
            case WEST:
                matrices.translate(-0.01f, 0.5f, 0.5f);
                break;
            case EAST:
                matrices.translate(1.01f, 0.5f, 0.5f);
                break;
        }
        matrices.scale(0.35f, 0.35f, 0.35f);
        switch (direction) {
            case NORTH:
                matrices.rotate(Vector3f.YP.rotationDegrees(0));
                break;
            case SOUTH:
                matrices.rotate(Vector3f.YP.rotationDegrees(180));
                break;
            case WEST:
                matrices.rotate(Vector3f.YP.rotationDegrees(90));
                break;
            case EAST:
                matrices.rotate(Vector3f.YP.rotationDegrees(270));
                break;
        }
        itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.GUI, getLightLevel(entity.getWorld(),
                entity.getPos()), OverlayTexture.NO_OVERLAY, matrices, vertexConsumers);
        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightFor(LightType.BLOCK, pos);
        int sLight = world.getLightFor(LightType.SKY, pos);
        return LightTexture.packLight(bLight, Math.max(sLight, 15));
    }
}