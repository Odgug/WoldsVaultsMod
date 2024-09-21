package xyz.iwolfking.woldsvaults.init;

import iskallia.vault.VaultMod;
import iskallia.vault.gear.attribute.VaultGearAttribute;
import iskallia.vault.gear.attribute.config.ConfigurableAttributeGenerator;
import iskallia.vault.gear.attribute.type.VaultGearAttributeType;
import iskallia.vault.gear.comparator.VaultGearAttributeComparator;
import iskallia.vault.gear.reader.VaultGearModifierReader;
import iskallia.vault.init.ModGearAttributeGenerators;
import iskallia.vault.init.ModGearAttributeReaders;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.custom.items.gear.amulet.VaultAmuletEffect;
import xyz.iwolfking.woldsvaults.custom.items.gear.amulet.VaultAmuletEffectRegistry;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = WoldsVaults.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModGearAttributes {
    public static final VaultGearAttribute<Boolean> ROTATING_TOOL = attr("rotating", VaultGearAttributeType.booleanType(), ModGearAttributeGenerators.booleanFlag(), ModGearAttributeReaders.booleanReader("Stylish", 15378160), VaultGearAttributeComparator.booleanComparator());
    public static final VaultGearAttribute<Integer> TRIDENT_LOYALTY = attr("trident_loyalty",
            /* 171 */       VaultGearAttributeType.intType(), (ConfigurableAttributeGenerator<Integer, ?>)ModGearAttributeGenerators.intRange(), (VaultGearModifierReader<Integer>)ModGearAttributeReaders.addedIntReader("Loyalty", 3114911), (VaultGearAttributeComparator<Integer>)VaultGearAttributeComparator.intComparator());
    public static final VaultGearAttribute<Integer> TRIDENT_RIPTIDE = attr("trident_riptide",
            /* 171 */       VaultGearAttributeType.intType(), (ConfigurableAttributeGenerator<Integer, ?>)ModGearAttributeGenerators.intRange(), (VaultGearModifierReader<Integer>)ModGearAttributeReaders.addedIntReader("Riptide", 9514005), (VaultGearAttributeComparator<Integer>)VaultGearAttributeComparator.intComparator());
    public static final VaultGearAttribute<Float> TRIDENT_WINDUP = attr("trident_wind_up_percent",
            /* 177 */       VaultGearAttributeType.floatType(), (ConfigurableAttributeGenerator<Float, ?>)ModGearAttributeGenerators.floatRange(), (VaultGearModifierReader)ModGearAttributeReaders.percentageReader("Windup Time Reduction", 12925717), (VaultGearAttributeComparator<Float>)VaultGearAttributeComparator.floatComparator());
    public static final VaultGearAttribute<Boolean> TRIDENT_CHANNELING = attr("trident_channeling",
            /* 121 */       VaultGearAttributeType.booleanType(), (ConfigurableAttributeGenerator<Boolean, ?>)ModGearAttributeGenerators.booleanFlag(), (VaultGearModifierReader<Boolean>)ModGearAttributeReaders.booleanReader("Channeling", 12925823), VaultGearAttributeComparator.booleanComparator());

    public static final VaultGearAttribute<Float> CHANNELING_CHANCE = attr("trident_channeling_chance",
            /* 177 */       VaultGearAttributeType.floatType(), (ConfigurableAttributeGenerator<Float, ?>)ModGearAttributeGenerators.floatRange(), (VaultGearModifierReader)ModGearAttributeReaders.percentageReader("Channeling Chance", 12925893), (VaultGearAttributeComparator<Float>)VaultGearAttributeComparator.floatComparator());

    public static final VaultGearAttribute<Boolean> MAGNET_ENDERGIZED = attr("endergized",
            /* 121 */       VaultGearAttributeType.booleanType(), (ConfigurableAttributeGenerator<Boolean, ?>)ModGearAttributeGenerators.booleanFlag(), (VaultGearModifierReader<Boolean>)ModGearAttributeReaders.booleanReader("Endergized", 46276), VaultGearAttributeComparator.booleanComparator());
    /*     */
    /*     */

    public static final VaultGearAttribute<Float> REAVING_DAMAGE = attr("reaving_damage",
            VaultGearAttributeType.floatType(), (ConfigurableAttributeGenerator<Float, ?>)ModGearAttributeGenerators.floatRange(), ModGearAttributeReaders.percentageReader("Bonus Reaving Damage", 12417954), VaultGearAttributeComparator.floatComparator());
    public static final VaultGearAttribute<VaultAmuletEffect<?>> VAULT_AMULET_EFFECT = attr("amulet", VaultGearAttributeType.registryType(VaultAmuletEffectRegistry.getRegistry()), ModGearAttributeGenerators.noneGenerator(), ModGearAttributeReaders.none());
    @SubscribeEvent
    /*     */   public static void init(RegistryEvent.Register<VaultGearAttribute<?>> event) {
        /* 326 */     IForgeRegistry<VaultGearAttribute<?>> registry = event.getRegistry();
                      registry.register(TRIDENT_LOYALTY);
                      registry.register(TRIDENT_RIPTIDE);
                      registry.register(TRIDENT_WINDUP);
                      registry.register(TRIDENT_CHANNELING);
                      registry.register(CHANNELING_CHANCE);
                      registry.register(MAGNET_ENDERGIZED);
                      registry.register(REAVING_DAMAGE);
                      registry.register(VAULT_AMULET_EFFECT);
                      registry.register(ROTATING_TOOL);
        /*     */   }
    /*     */
    /*     */   public static void registerVanillaAssociations() {
        /*     */   }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   private static <T> VaultGearAttribute<T> attr(String name, VaultGearAttributeType<T> type, ConfigurableAttributeGenerator<T, ?> generator, VaultGearModifierReader<T> reader) {
        /* 469 */     return attr(name, type, generator, reader, null);
        /*     */   }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   private static <T> VaultGearAttribute<T> attr(String name, VaultGearAttributeType<T> type, ConfigurableAttributeGenerator<T, ?> generator, VaultGearModifierReader<T> reader, @Nullable VaultGearAttributeComparator<T> comparator) {
        /* 478 */     return new VaultGearAttribute(VaultMod.id(name), type, generator, reader, comparator);
        /*     */   }
}
