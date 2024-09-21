package xyz.iwolfking.woldsvaults.custom.curios;

import iskallia.vault.init.ModItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

@Deprecated
public class ShardPouchCurio implements ICurio {

    protected ItemStack pouch;

    public ShardPouchCurio(ItemStack pouch) {
        this.pouch = pouch;
    }

    @Override
    public boolean canEquip(SlotContext slotContext) {
        return slotContext.entity() instanceof Player player && CuriosApi.getCuriosHelper().findCurios(player, ModItems.SHARD_POUCH).isEmpty();
    }

    @Override
    public ItemStack getStack() {
        return pouch;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack) {
        ICurio.super.onEquip(slotContext, prevStack);
    }
}
