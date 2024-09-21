package xyz.iwolfking.woldsvaults.mixins.vaulthunters.recipes;

import iskallia.vault.item.crystal.recipe.AnvilRecipe;
import iskallia.vault.item.crystal.recipe.AnvilRecipes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.iwolfking.woldsvaults.custom.recipes.capstone.*;
import xyz.iwolfking.woldsvaults.custom.recipes.gear.GearPrefixAdderRecipe;
import xyz.iwolfking.woldsvaults.custom.recipes.gear.GearRepairAdderRecipe;
import xyz.iwolfking.woldsvaults.custom.recipes.gear.GearSuffixAdderRecipe;
import xyz.iwolfking.woldsvaults.custom.recipes.gear.MercyEnchantmentAdderRecipe;
import xyz.iwolfking.woldsvaults.custom.recipes.tool.OmegaToolCapacityAdderRecipe;
import xyz.iwolfking.woldsvaults.custom.recipes.tool.ToolCapacityAdderRecipe;
import xyz.iwolfking.woldsvaults.custom.recipes.tool.ToolModifierNullifierRecipe;
import xyz.iwolfking.woldsvaults.custom.recipes.tool.ToolStylishAdderRecipe;

import java.util.List;

@Mixin(value = AnvilRecipes.class, remap = false)
public abstract class MixinAnvilRecipes {


    @Shadow private static List<AnvilRecipe> REGISTRY;

    @Inject(method = "register()V", at = @At("HEAD"))
    private static void register(CallbackInfo ci) {
        woldsVaults$register(new FrenzyCapstoneRecipe());
        woldsVaults$register(new ProsperousCapstoneRecipe());
        woldsVaults$register(new AllSeeingEyeCapstoneRecipe());
        woldsVaults$register(new MoteClarityCapstoneRecipe());
        woldsVaults$register(new MoteSanctityCapstoneRecipe());
        woldsVaults$register(new MotePurityCapstoneRecipe());
        woldsVaults$register(new EnchantedCapstoneRecipe());
        woldsVaults$register(new VendoorCapstoneRecipe());
        woldsVaults$register(new GearPrefixAdderRecipe());
        woldsVaults$register(new GearSuffixAdderRecipe());
        woldsVaults$register(new GearRepairAdderRecipe());
        woldsVaults$register(new ToolCapacityAdderRecipe());
        woldsVaults$register(new OmegaToolCapacityAdderRecipe());
        woldsVaults$register(new ToolModifierNullifierRecipe());
        woldsVaults$register(new ToolStylishAdderRecipe());
        woldsVaults$register(new MercyEnchantmentAdderRecipe());
    }

    @Unique
    private static <T extends AnvilRecipe> void woldsVaults$register(T recipe) {
        REGISTRY.add(recipe);
    }
}
