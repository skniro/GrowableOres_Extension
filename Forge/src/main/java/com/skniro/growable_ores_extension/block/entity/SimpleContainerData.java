package com.skniro.growable_ores_extension.block.entity;

import net.minecraft.util.IIntArray;

public class SimpleContainerData implements IIntArray {
    private final int[] data;

    public SimpleContainerData(int size) {
        this.data = new int[size];
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for length " + data.length);
        }
        return data[index];
    }

    @Override
    public void set(int index, int value) {
        if (index < 0 || index >= data.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for length " + data.length);
        }
        data[index] = value;
    }

    @Override
    public int size() {
        return data.length;
    }
}
