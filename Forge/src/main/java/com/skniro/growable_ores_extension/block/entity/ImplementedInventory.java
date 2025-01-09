package com.skniro.growable_ores_extension.block.entity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.inventory.container.Container;

import javax.annotation.Nullable;
import java.util.List;

/**
 * A simple {@code SidedInventory} implementation with only default methods + an item list getter.
 *
 * <h2>Reading and writing to tags</h2>
 * Use {@link ItemStackHelper#saveAllItems(CompoundNBT, NonNullList)} and {@link ItemStackHelper#loadAllItems(CompoundNBT, NonNullList)}
 * on {@linkplain #getItems() the item list}.
 *
 * License: <a href="https://creativecommons.org/publicdomain/zero/1.0/">CC0</a>
 * @author Juuz
 */
public interface ImplementedInventory extends ISidedInventory {
    /**
     * Gets the item list of this inventory.
     * Must return the same instance every time it's called.
     *
     * @return the item list
     */
    NonNullList<ItemStack> getItems();

    /**
     * Creates an inventory from the item list.
     *
     * @param items the item list
     * @return a new inventory
     */

    // SidedInventory

    /**
     * Gets the available slots to automation on the side.
     *
     * <p>The default implementation returns an array of all slots.
     *
     * @param side the side
     * @return the available slots
     */
    @Override
    default int[] getSlotsForFace(Direction side) {
        int[] result = new int[getItems().size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }

        return result;
    }

    /**
     * Returns true if the stack can be inserted in the slot at the side.
     *
     * <p>The default implementation returns true.
     *
     * @param slot the slot
     * @param stack the stack
     * @param side the side
     * @return true if the stack can be inserted
     */
    @Override
    default boolean canInsertItem(int slot, ItemStack stack, @Nullable Direction side) {
        return true;
    }

    /**
     * Returns true if the stack can be extracted from the slot at the side.
     *
     * <p>The default implementation returns true.
     *
     * @param slot the slot
     * @param stack the stack
     * @param side the side
     * @return true if the stack can be extracted
     */
    @Override
    default boolean canExtractItem(int slot, ItemStack stack, Direction side) {
        return true;
    }

    // Inventory

    /**
     * Returns the inventory size.
     *
     * <p>The default implementation returns the size of {@link #getItems()}.
     *
     * @return the inventory size
     */
    @Override
    default int getInventoryStackLimit() {
        return getItems().size();
    }

    /**
     * @return true if this inventory has only empty stacks, false otherwise
     */
    @Override
    default boolean isEmpty() {
        for (int i = 0; i < getInventoryStackLimit(); i++) {
            ItemStack stack = getStackInSlot(i);
            if (!stack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets the item in the slot.
     *
     * @param slot the slot
     * @return the item in the slot
     */
    @Override
    default ItemStack getStackInSlot(int slot) {
        return getItems().get(slot);
    }

    /**
     * Takes a stack of the size from the slot.
     *
     * <p>(default implementation) If there are less items in the slot than what are requested,
     * takes all items in that slot.
     *
     * @param slot the slot
     * @param count the item count
     * @return a stack
     */
    @Override
    default ItemStack decrStackSize(int slot, int count) {
        ItemStack result = ItemStackHelper.getAndSplit(getItems(), slot, count);
        if (!result.isEmpty()) {
            markDirty();
        }

        return result;
    }

    /**
     * Removes the current stack in the {@code slot} and returns it.
     *
     * <p>The default implementation uses {@link ItemStackHelper#takeItem(List, int)}
     *
     * @param slot the slot
     * @return the removed stack
     */
    @Override
    default ItemStack removeStackFromSlot(int slot) {
        return ItemStackHelper.getAndRemove(getItems(), slot);
    }

    /**
     * Replaces the current stack in the {@code slot} with the provided stack.
     *
     * <p>If the stack is too big for this inventory ({@link Container#getInventoryStackLimit()}),
     * it gets resized to this inventory's maximum amount.
     *
     * @param slot the slot
     * @param stack the stack
     */
    @Override
    default void setInventorySlotContents(int slot, ItemStack stack) {
        getItems().set(slot, stack);
        if (stack.getCount() > getInventoryStackLimit()) {
            stack.setCount(getInventoryStackLimit());
        }
    }

    /**
     * Clears {@linkplain #getItems() the item list}}.
     */
    @Override
    default void clear() {
        getItems().clear();
    }

    @Override
    default void markDirty() {
        // Override if you want behavior.
    }

    @Override
    default boolean isUsableByPlayer(PlayerEntity player) {
        return true;
    }
}