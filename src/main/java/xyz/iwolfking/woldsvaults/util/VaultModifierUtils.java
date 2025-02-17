package xyz.iwolfking.woldsvaults.util;

import iskallia.vault.VaultMod;
import iskallia.vault.core.random.ChunkRandom;
import iskallia.vault.core.random.JavaRandom;
import iskallia.vault.core.random.RandomSource;
import iskallia.vault.core.vault.Modifiers;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.modifier.registry.VaultModifierRegistry;
import iskallia.vault.core.vault.modifier.spi.VaultModifier;
import iskallia.vault.core.vault.player.Listener;
import iskallia.vault.core.vault.player.Listeners;
import iskallia.vault.init.ModConfigs;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.Iterator;
import java.util.List;

public class VaultModifierUtils {
    private static final TextComponent MODIFIER_ADDED_SUFFIX = new TextComponent(" was added to the vault.");
    public static void sendModifierAddedMessage(ServerPlayer player, VaultModifier<?> modifier, Integer stackSize) {
        player.displayClientMessage(modifier.getChatDisplayNameComponent(stackSize).copy().append(MODIFIER_ADDED_SUFFIX), false);
    }

    public static void addModifierFromPool(Vault vault, ResourceLocation modifierPool) {
        List<VaultModifier<?>> modifiers = ModConfigs.VAULT_MODIFIER_POOLS.getRandom(modifierPool, 0, (RandomSource) JavaRandom.ofNanoTime());

        if(!modifiers.isEmpty()) {
            Iterator<VaultModifier<?>> modIter = modifiers.iterator();

            VaultModifier<?> modifier = VaultModifierRegistry.get(VaultMod.id("empty"));

            while(modIter.hasNext()) {
                VaultModifier<?> mod = (VaultModifier<?>) modIter.next();
                modifier = mod;
                ((Modifiers)vault.get(Vault.MODIFIERS)).addModifier(mod, 1, true, ChunkRandom.any());
            }

            if(modifier.getId().equals(VaultMod.id("empty"))) {
                return;
            }

            for (Listener listener : ((Listeners) vault.get(Vault.LISTENERS)).getAll()) {
                listener.getPlayer().ifPresent((other) -> {
                    sendModifierAddedMessage(other, modifiers.get(0), 1);
                });
            }
        }
    }
}
