package com.skniro.growable_ores_extension.block.entity;

import com.skniro.growable_ores_extension.recipe.AlchemyCraftingRecipe;
import com.skniro.growable_ores_extension.recipe.AlchemyRecipeType;
import com.skniro.growable_ores_extension.screen.AlchemyBlockScreenHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class Alchemyblockentity extends BlockEntity implements MenuProvider, ImplementedInventory {
    private final ItemStackHandler itemHandler = new ItemStackHandler(2);
    private final NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);
    private LazyOptional<? extends IItemHandler>[] lazyItemHandler = SidedInvWrapper.create(this, new Direction[]{Direction.UP, Direction.DOWN});
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;


    protected final ContainerData propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;
    private final int DEFAULT_MAX_PROGRESS = 72;

    public Alchemyblockentity(BlockPos pos, BlockState state) {
        super(AlchemyBlockEntityType.ALCHEMY_BLOCK_ENTITY.get(), pos, state);
        this.propertyDelegate = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> Alchemyblockentity.this.progress;
                    case 1 -> Alchemyblockentity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: Alchemyblockentity.this.progress = value;
                    case 1: Alchemyblockentity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public ItemStack getRenderStack() {
            return this.getItem(INPUT_SLOT);
    }
    @Override
    public void setChanged() {
        level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        super.setChanged();
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.growableores.cane_converter");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory playerInventory, Player player) {
        return new AlchemyBlockScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        ContainerHelper.saveAllItems(nbt, inventory);
        nbt.putInt("cane_converter.progress", progress);
        nbt.putInt("cane_converter.max_progress", maxProgress);
    }

    @Override
    public void load(CompoundTag nbt) {
        ContainerHelper.loadAllItems(nbt, inventory);
        progress = nbt.getInt("cane_converter.progress");
        maxProgress = nbt.getInt("cane_converter.max_progress");
        super.load(nbt);
    }

    public void tick(Level world, BlockPos pos, BlockState state) {
        if(world.isClientSide()) {
            return;
        }
        if(hasRecipe() && canInsertIntoOutputSlot()) {
            increaseCraftingProgress();
            setChanged(world, pos, state);

            if(hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = DEFAULT_MAX_PROGRESS;
    }

    private void craftItem() {
        Optional<AlchemyCraftingRecipe> recipe = getCurrentRecipe();
        this.removeItem(INPUT_SLOT, 1);
        this.setItem(OUTPUT_SLOT, new ItemStack(recipe.get().getResultItem().getItem(),
                this.getItem(OUTPUT_SLOT).getCount() + recipe.get().getResultItem().getCount()));
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        if (direction != Direction.DOWN) {
            return new int[]{INPUT_SLOT};
        } else {
            return new int[]{OUTPUT_SLOT};
        }
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return slot != OUTPUT_SLOT;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean canInsertIntoOutputSlot() {
        return this.getItem(OUTPUT_SLOT).isEmpty() ||
                this.getItem(OUTPUT_SLOT).getCount() < this.getItem(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasRecipe() {
        Optional<AlchemyCraftingRecipe> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().getResultItem();
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }
    private Optional<AlchemyCraftingRecipe> getCurrentRecipe() {
        SimpleContainer inv = new SimpleContainer(this.getContainerSize());
        for(int i = 0; i < this.getContainerSize(); i++) {
            inv.setItem(i, this.getItem(i));
        }
        return this.getLevel().getRecipeManager()
                .getRecipeFor(AlchemyRecipeType.Cane_Converter_TYPE.get(), inv, this.getLevel());
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getItem(OUTPUT_SLOT).isEmpty() || this.getItem(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
    int maxCount = this.getItem(OUTPUT_SLOT).isEmpty() ? 64 : this.getItem(OUTPUT_SLOT).getMaxStackSize();
    int currentCount = this.getItem(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
}

   @Nullable
   @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
    return ClientboundBlockEntityDataPacket.create(this);
}

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }


    public <T> LazyOptional<T> getCapability(Capability<T> capability, @javax.annotation.Nullable Direction facing) {
        if (capability == ForgeCapabilities.ITEM_HANDLER && facing != null && !this.remove) {
            LazyOptional var10000;
            switch (facing) {
                case DOWN -> var10000 = this.lazyItemHandler[1].cast();
                default-> var10000 = this.lazyItemHandler[0].cast();
            }

            return var10000;
        } else {
            return super.getCapability(capability, facing);
        }
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        for(int x = 0; x < this.lazyItemHandler.length; ++x) {
            this.lazyItemHandler[x].invalidate();
        }
    }

    public void reviveCaps() {
        super.reviveCaps();
        this.lazyItemHandler = SidedInvWrapper.create(this, new Direction[]{Direction.UP, Direction.DOWN});
    }

}
