package com.skniro.growable_ores_extension.block.entity;

import com.skniro.growable_ores_extension.recipe.AlchemyCraftingRecipe;
import com.skniro.growable_ores_extension.screen.AlchemyBlockScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;
import java.util.Optional;

public class Alchemyblockentity extends TileEntity implements INamedContainerProvider, ImplementedInventory, ITickableTileEntity {
    private final ItemStackHandler itemHandler = new ItemStackHandler(2);
    private final NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);
    private LazyOptional<? extends IItemHandler>[] lazyItemHandler = SidedInvWrapper.create(this, new Direction[]{Direction.UP, Direction.DOWN});
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;


    protected final IIntArray propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;
    private final int DEFAULT_MAX_PROGRESS = 72;

    public Alchemyblockentity() {
        super(AlchemyBlockEntityType.ALCHEMY_BLOCK_ENTITY.get());
        this.propertyDelegate = new IIntArray() {
            @Override
            public int get(int index) {
                switch (index) {
                    case 0:
                        return Alchemyblockentity.this.progress;
                    case 1:
                        return Alchemyblockentity.this.maxProgress;
                    default:
                        return 0;
                }
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: Alchemyblockentity.this.progress = value;
                    case 1: Alchemyblockentity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    public ItemStack getRenderStack() {
            return this.getStackInSlot(INPUT_SLOT);
    }


    @Override
    public int getSizeInventory() {
        return this.inventory.size();
    }

    @Override
    public void markDirty() {
        world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 3);
        super.markDirty();
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public TranslationTextComponent getDisplayName() {
        return new TranslationTextComponent("gui.growableores.cane_converter");
    }

    @Nullable
    @Override
    public Container createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new AlchemyBlockScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);
        ItemStackHelper.saveAllItems(nbt, inventory);
        nbt.putInt("cane_converter.progress", progress);
        nbt.putInt("cane_converter.max_progress", maxProgress);
        return nbt;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        ItemStackHelper.loadAllItems(nbt, inventory);
        progress = nbt.getInt("cane_converter.progress");
        maxProgress = nbt.getInt("cane_converter.max_progress");
        super.read(state ,nbt);
    }

    public void tick() {
        if(world.isRemote()) {
            return;
        }
        if(hasRecipe() && canInsertIntoOutputSlot()) {
            increaseCraftingProgress();
            markDirty();

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
        this.decrStackSize(INPUT_SLOT, 1);
        this.setInventorySlotContents(OUTPUT_SLOT, new ItemStack(recipe.get().getRecipeOutput().getItem(),
                this.getStackInSlot(OUTPUT_SLOT).getCount() + recipe.get().getRecipeOutput().getCount()));
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
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return slot != OUTPUT_SLOT;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean canInsertIntoOutputSlot() {
        return this.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                this.getStackInSlot(OUTPUT_SLOT).getCount() < this.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasRecipe() {
        Optional<AlchemyCraftingRecipe> recipe = getCurrentRecipe();
        if (recipe.isPresent()) {
            ItemStack output = recipe.get().getRecipeOutput();
            return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
        } else {
            return false;
        }

    }
    private Optional<AlchemyCraftingRecipe> getCurrentRecipe() {
        Inventory inv = new Inventory(this.getSizeInventory());
        for(int i = 0; i < this.getSizeInventory(); i++) {
            inv.setInventorySlotContents(i, this.getStackInSlot(i));
        }
        return this.getWorld().getRecipeManager()
                .getRecipe(AlchemyCraftingRecipe.Type.INSTANCE, inv, this.getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.getStackInSlot(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
    int maxCount = this.getStackInSlot(OUTPUT_SLOT).isEmpty() ? 64 : this.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    int currentCount = this.getStackInSlot(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
}

   @Nullable
   @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
       return new SUpdateTileEntityPacket(this.pos, 1, this.write(new CompoundNBT()));
   }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        this.read(state, tag);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }


    public <T> LazyOptional<T> getCapability(Capability<T> capability, @javax.annotation.Nullable Direction facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && facing != null && !this.removed) {
            LazyOptional var10000;
            switch (facing) {
                case DOWN:
                    var10000 = this.lazyItemHandler[1].cast();
                    break;
                default:
                    var10000 = this.lazyItemHandler[0].cast();
                    break;
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
