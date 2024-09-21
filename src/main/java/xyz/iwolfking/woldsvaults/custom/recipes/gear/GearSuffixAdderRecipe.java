package xyz.iwolfking.woldsvaults.custom.recipes.gear;

import iskallia.vault.gear.attribute.type.VaultGearAttributeTypeMerger;
import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.gear.item.VaultGearItem;
import iskallia.vault.init.ModGearAttributes;
import iskallia.vault.item.crystal.recipe.AnvilContext;
import iskallia.vault.item.crystal.recipe.VanillaAnvilRecipe;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.world.item.ItemStack;
import xyz.iwolfking.woldsvaults.init.ModItems;

public class GearSuffixAdderRecipe extends VanillaAnvilRecipe {
    @Override
    public boolean onSimpleCraft(AnvilContext context) {
        ItemStack primary = context.getInput()[0];
        ItemStack secondary = context.getInput()[1];
        if (primary.getItem() instanceof VaultGearItem && secondary.getItem() == ModItems.WANING_AUGMENTER) {
            ItemStack output = primary.copy();
            VaultGearData gear = VaultGearData.read(output);
            int currentPrefixCount = gear.get(ModGearAttributes.SUFFIXES, VaultGearAttributeTypeMerger.intSum());

            //If at the max number of slots already, don't allow any more.
            if(currentPrefixCount == 3) {
                return false;
            }

            gear.updateAttribute(ModGearAttributes.SUFFIXES, currentPrefixCount + 1);
            gear.write(output);
            context.setOutput(output);

            context.onTake(context.getTake().append(() -> {
                context.getInput()[0].shrink(1);
                context.getInput()[1].shrink(1);
            }));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRegisterJEI(IRecipeRegistration iRecipeRegistration) {

    }
}
