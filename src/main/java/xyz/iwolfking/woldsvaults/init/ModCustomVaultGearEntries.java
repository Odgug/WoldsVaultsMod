package xyz.iwolfking.woldsvaults.init;

import xyz.iwolfking.woldsvaults.api.registry.CustomVaultGearRegistry;
import xyz.iwolfking.woldsvaults.api.registry.record.CustomVaultGearEntry;
import xyz.iwolfking.woldsvaults.config.fake.CustomVaultGearModelRollRaritiesConfig;
import xyz.iwolfking.woldsvaults.custom.models.gear.Battlestaffs;
import xyz.iwolfking.woldsvaults.custom.models.gear.LootSacks;
import xyz.iwolfking.woldsvaults.custom.models.gear.Plushies;
import xyz.iwolfking.woldsvaults.custom.models.gear.Tridents;

public class ModCustomVaultGearEntries {
    public static void registerGearEntries() {
        CustomVaultGearRegistry.addEntry(new CustomVaultGearEntry(ModItems.BATTLESTAFF, "Battlestaff", Battlestaffs.REGISTRY, CustomVaultGearModelRollRaritiesConfig.BATTLESTAFF_MODEL_ROLLS));
        CustomVaultGearRegistry.addEntry(new CustomVaultGearEntry(ModItems.TRIDENT, "Trident", Tridents.REGISTRY, CustomVaultGearModelRollRaritiesConfig.TRIDENT_MODEL_ROLLS));
        CustomVaultGearRegistry.addEntry(new CustomVaultGearEntry(ModItems.PLUSHIE, "Plushie", Plushies.REGISTRY, CustomVaultGearModelRollRaritiesConfig.PLUSHIE_MODEL_ROLLS));
        CustomVaultGearRegistry.addEntry(new CustomVaultGearEntry(ModItems.LOOT_SACK, "Loot Sack", LootSacks.REGISTRY, CustomVaultGearModelRollRaritiesConfig.LOOT_SACKS_MODEL_ROLLS));
    }
}
