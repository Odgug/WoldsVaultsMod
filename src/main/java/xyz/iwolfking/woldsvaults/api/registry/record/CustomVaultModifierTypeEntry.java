package xyz.iwolfking.woldsvaults.api.registry.record;

import iskallia.vault.core.vault.modifier.registry.VaultModifierType;
import net.minecraft.resources.ResourceLocation;

public record CustomVaultModifierTypeEntry(ResourceLocation id, VaultModifierType<?, ?> type) {

}