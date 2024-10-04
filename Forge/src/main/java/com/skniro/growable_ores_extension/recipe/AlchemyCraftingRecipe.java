package com.skniro.growable_ores_extension.recipe;


import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;



    public record AlchemyCraftingRecipe(Ingredient inputItem, ItemStack output) implements Recipe<AlchemyCraftingRecipeInput> {
        @Override
        public DefaultedList<Ingredient> getIngredients() {
            DefaultedList<Ingredient> list = DefaultedList.of();
            list.add(this.inputItem);
            return list;
        }
        @Override
        public boolean matches(AlchemyCraftingRecipeInput input, World world) {
            if(world.isClient()) {
                return false;
            }
            return inputItem.test(input.getStackInSlot(1));
        }
        @Override
        public ItemStack craft(AlchemyCraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
            return output.copy();
        }
        @Override
        public boolean fits(int width, int height) {
            return true;
        }
        @Override
        public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
            return output;
        }
        @Override
        public RecipeSerializer<?> getSerializer() {
            return AlchemyRecipeType.Cane_Converter_SERIALIZER;
        }
        @Override
        public RecipeType<?> getType() {
            return AlchemyRecipeType.Cane_Converter_TYPE;
        }
        public static class Serializer implements RecipeSerializer<AlchemyCraftingRecipe> {
            public static final MapCodec<AlchemyCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                    Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(AlchemyCraftingRecipe::inputItem),
                    ItemStack.CODEC.fieldOf("result").forGetter(AlchemyCraftingRecipe::output)
            ).apply(inst, AlchemyCraftingRecipe::new));
            public static final PacketCodec<RegistryByteBuf, AlchemyCraftingRecipe> STREAM_CODEC =
                    PacketCodec.tuple(
                            Ingredient.PACKET_CODEC, AlchemyCraftingRecipe::inputItem,
                            ItemStack.PACKET_CODEC, AlchemyCraftingRecipe::output,
                            AlchemyCraftingRecipe::new);
            @Override
            public MapCodec<AlchemyCraftingRecipe> codec() {
                return CODEC;
            }
            @Override
            public PacketCodec<RegistryByteBuf, AlchemyCraftingRecipe> packetCodec() {
                return STREAM_CODEC;
            }
        }
    }




