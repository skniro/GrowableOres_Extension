package com.skniro.growable_ores_extension.screen;

import com.skniro.growable_ores_extension.block.entity.Alchemyblockentity;
import com.skniro.growable_ores_extension.block.entity.SimpleContainerData;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceResultSlot;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.world.World;


public class AlchemyBlockScreenHandler extends Container {
    private final IInventory inventory;
    private final World level;
    private final IIntArray propertyDelegate;
    public final Alchemyblockentity blockEntity;

    public AlchemyBlockScreenHandler(int syncId, PlayerInventory playerInventory, PacketBuffer extraData){
        this(syncId, playerInventory, playerInventory.player.world.getTileEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public AlchemyBlockScreenHandler(int syncId, PlayerInventory playerInventory, TileEntity blockEntity, IIntArray delegate) {
        super(AlchemyScreenHandlerType.ALCHEMY.get(), syncId);
        assertInventorySize(playerInventory,4);
        this.inventory = (Alchemyblockentity) blockEntity;
        inventory.openInventory(playerInventory.player);
        this.propertyDelegate = delegate;
        this.blockEntity = (Alchemyblockentity) blockEntity;
        this.level = playerInventory.player.world;;
        this.addSlot(new Slot(inventory, 0, 52, 34));
        this.addSlot(new FurnaceResultSlot(playerInventory.player, inventory, 1, 100, 34));



        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        trackIntArray(delegate);
    }


    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);  // Max Progress
        int progressArrowSize = 27; // This is the width in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }


    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(invSlot);
        if (slot != null && slot.getHasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.getSizeInventory()) {
                if (!this.mergeItemStack(originalStack, this.inventory.getSizeInventory(), this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(originalStack, 0, this.inventory.getSizeInventory(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return newStack;
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return this.inventory.isUsableByPlayer(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
