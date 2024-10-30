package xyz.iwolfking.woldsvaults.mixins.vaulthunters.recipes;

import iskallia.vault.config.recipe.ForgeRecipeType;
import iskallia.vault.config.recipe.ForgeRecipesConfig;
import iskallia.vault.gear.crafting.recipe.VaultForgeRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

@Mixin(ForgeRecipeType.class)
public class MixinForgeRecipeType {

    @Shadow
    @Final
    @Mutable
    private static ForgeRecipeType[] $VALUES;

    @Invoker("<init>")
    private static ForgeRecipeType invokeInit(String name, int id, BiFunction<ResourceLocation, ItemStack, ? extends VaultForgeRecipe> recipeClassCtor, Supplier<ForgeRecipesConfig<?, ?>>  configSupplier) {
        throw new AssertionError();
    }

    @Inject(method = "<clinit>", at = @At(value = "FIELD", target = "Liskallia/vault/config/recipe/ForgeRecipeType;$VALUES:[Liskallia/vault/config/recipe/ForgeRecipeType;", shift = At.Shift.AFTER))
    private static void wold$addRecipeType(CallbackInfo ci) {
        List<ForgeRecipeType> recipeTypes = new ArrayList<>(Arrays.asList($VALUES));
        ForgeRecipeType last = recipeTypes.get(recipeTypes.size() - 1);
        int i = 1;
        $VALUES = recipeTypes.toArray(new ForgeRecipeType[0]);
    }
}
