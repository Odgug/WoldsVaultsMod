package xyz.iwolfking.woldsvaults.mixins;

import cofh.ensorcellation.enchantment.CurseMercyEnchantment;
import cofh.lib.enchantment.EnchantmentCoFH;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = CurseMercyEnchantment.class, remap = false)
public abstract class MixinCurseMercyEnchantment extends EnchantmentCoFH{
    protected MixinCurseMercyEnchantment(Rarity rarityIn, EnchantmentCategory typeIn, EquipmentSlot[] slots) {
        super(rarityIn, typeIn, slots);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        this.treasureEnchantment = false;
    }
}
