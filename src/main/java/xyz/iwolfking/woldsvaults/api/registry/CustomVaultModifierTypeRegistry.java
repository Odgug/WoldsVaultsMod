package xyz.iwolfking.woldsvaults.api.registry;

import xyz.iwolfking.woldsvaults.api.registry.record.CustomVaultModifierTypeEntry;
import xyz.iwolfking.woldsvaults.config.forge.WoldsVaultsConfig;
import xyz.iwolfking.woldsvaults.init.ModCustomVaultObjectiveEntries;

import java.util.ArrayList;
import java.util.List;

public class CustomVaultModifierTypeRegistry {

    private static final List<CustomVaultModifierTypeEntry> CUSTOM_VAULT_MODIFIER_ENTRIES = new ArrayList<>();

    public static void addEntry(CustomVaultModifierTypeEntry entry) {
        CUSTOM_VAULT_MODIFIER_ENTRIES.add(entry);
    }

    public static int getSize() {
        return CUSTOM_VAULT_MODIFIER_ENTRIES.size();
    }

    public static void registerAllCustomVaultModifierTypes() {
        ModCustomVaultObjectiveEntries.registerCustomObjectives();
        if(WoldsVaultsConfig.COMMON.enableDebugMode.get()) {
            System.out.println("Wold's Vaults - Registered " + getSize() + " Custom Vault Objective Entries.");
        }
    }


    public static List<CustomVaultModifierTypeEntry> getCustomVaultObjectiveEntries() {
        return CUSTOM_VAULT_MODIFIER_ENTRIES;
    }

    public static boolean contains(String id) {
        for(CustomVaultModifierTypeEntry entry : getCustomVaultObjectiveEntries()) {
            if(id.equals(entry.id())) {
                return true;
            }
        }
        return false;
    }
}
