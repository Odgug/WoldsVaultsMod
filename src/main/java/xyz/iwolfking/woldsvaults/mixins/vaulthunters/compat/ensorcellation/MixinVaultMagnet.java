package xyz.iwolfking.woldsvaults.mixins.vaulthunters.compat.ensorcellation;


import cofh.ensorcellation.init.EnsorcEnchantments;
import iskallia.vault.gear.item.CuriosGearItem;
import iskallia.vault.gear.item.VaultGearItem;
import iskallia.vault.item.MagnetItem;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "cofhcore"),
                @Condition(type = Condition.Type.MOD, value = "ensorcellation"),
        }
)
@Mixin(value = MagnetItem.class, remap = false)
public abstract class MixinVaultMagnet extends Item implements VaultGearItem, CuriosGearItem, ICurioItem {
    public MixinVaultMagnet(Properties p_41383_) {
        super(p_41383_);
    }


    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if(enchantment.equals(EnsorcEnchantments.SOULBOUND.get())) {
            return true;
        }
        else {
            return super.canApplyAtEnchantingTable(stack, enchantment);
        }

    }
}
