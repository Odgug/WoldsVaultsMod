package xyz.iwolfking.woldsvaults.events.entity;

import cofh.core.init.CoreMobEffects;
import iskallia.vault.block.CoinPileBlock;
import iskallia.vault.block.VaultChestBlock;
import iskallia.vault.block.VaultOreBlock;
import iskallia.vault.core.event.CommonEvents;
import iskallia.vault.gear.attribute.type.VaultGearAttributeTypeMerger;
import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.gear.item.VaultGearItem;
import iskallia.vault.util.calc.PlayerStat;
import iskallia.vault.world.data.ServerVaults;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.config.forge.WoldsVaultsConfig;
import xyz.iwolfking.woldsvaults.init.ModEffects;
import xyz.iwolfking.woldsvaults.init.ModGearAttributes;
import xyz.iwolfking.woldsvaults.custom.items.gear.VaultLootSackItem;
import xyz.iwolfking.woldsvaults.custom.items.gear.VaultPlushieItem;
import xyz.iwolfking.woldsvaults.api.helpers.event.WoldEventHelper;

@Mod.EventBusSubscriber(
        modid = WoldsVaults.MOD_ID
)
public class LivingEntityEvents {

    private static SoundEvent ANCHOR_SLAM_SOUND = null;

    public static void init() {
         ANCHOR_SLAM_SOUND  = Registry.SOUND_EVENT.get(new ResourceLocation("bettercombat:anchor_slam"));
    }

    @SubscribeEvent
    public static void capLightningDamageInVaults(LivingHurtEvent event) {
        if(event.getEntityLiving() instanceof Player player && event.getSource().equals(DamageSource.LIGHTNING_BOLT)) {
            ServerVaults.get(player.level).ifPresent(vault -> {
                event.setAmount( Math.min(event.getAmount(), player.getMaxHealth() * 0.2F));
                player.addEffect(new MobEffectInstance(CoreMobEffects.SHOCKED.get(), 80, 0));
            });
        }
    }

    @SubscribeEvent
    public static void reavingDamage(LivingHurtEvent event) {
        //Prevent an entity from being reaved more than once or applying to non-melee strikes.
        if(event.getEntityLiving().hasEffect(ModEffects.REAVING) || !WoldEventHelper.isNormalAttack()) {
            return;
        }

        if(event.getSource().getEntity() instanceof Player player && player.getMainHandItem().getItem() instanceof VaultGearItem) {
            VaultGearData data = VaultGearData.read(player.getMainHandItem().copy());
            if(data != null && data.has(ModGearAttributes.REAVING_DAMAGE)) {
                if(WoldsVaultsConfig.COMMON.enableDebugMode.get()) {
                    WoldsVaults.LOGGER.debug("[WOLD'S VAULTS] Added " + (event.getEntityLiving().getMaxHealth() * data.get(ModGearAttributes.REAVING_DAMAGE, VaultGearAttributeTypeMerger.floatSum())) + " bonus reaving damage to attack.");
                }
                event.getEntityLiving().addEffect(new MobEffectInstance(ModEffects.REAVING, Integer.MAX_VALUE, 0));
                event.getEntityLiving().addEffect(new MobEffectInstance(iskallia.vault.init.ModEffects.NO_AI, 20, 0));
                event.setAmount(event.getAmount() + (event.getEntityLiving().getMaxHealth() * data.get(ModGearAttributes.REAVING_DAMAGE, VaultGearAttributeTypeMerger.floatSum())));
                if(ANCHOR_SLAM_SOUND != null) {
                    WoldsVaults.LOGGER.debug("Anchor Slam Sound was null, Better Combat mod is missing.");
                }
                player.getLevel().playSound(null, event.getEntity(), ANCHOR_SLAM_SOUND, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        }
    }

    @SubscribeEvent(
            priority = EventPriority.LOW
    )
    public static void onDamageTotem(LivingHurtEvent event) {
        Level world = event.getEntity().getCommandSenderWorld();
        if (!world.isClientSide() && world instanceof ServerLevel) {
            LivingEntity var3 = event.getEntityLiving();
            if (var3 instanceof Player) {
                Player player = (Player)var3;
                if (!event.getSource().isBypassArmor()) {
                    ItemStack offHand = event.getEntityLiving().getOffhandItem();
                    if (!ServerVaults.get(world).isEmpty() || !(offHand.getItem() instanceof VaultGearItem)) {
                        if (offHand.getItem() instanceof VaultPlushieItem) {
                            int damage = (int) CommonEvents.PLAYER_STAT.invoke(PlayerStat.DURABILITY_DAMAGE, player, Math.max(1.0F, event.getAmount() / 6.0F)).getValue();
                            if (damage <= 1) {
                                damage = 1;
                            }

                            offHand.hurtAndBreak(damage, event.getEntityLiving(), (entity) -> {
                                entity.broadcastBreakEvent(EquipmentSlot.OFFHAND);
                            });
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent(
            priority = EventPriority.LOW
    )
    public static void onBreakVaultBlock(BlockEvent.BreakEvent event) {

        Level world = event.getPlayer().getCommandSenderWorld();
        if (!world.isClientSide() && world instanceof ServerLevel) {
            LivingEntity var3 = event.getPlayer();
            if (var3 instanceof Player player) {
                if (event.getState().getBlock() instanceof VaultChestBlock || event.getState().getBlock() instanceof CoinPileBlock || event.getState().getBlock() instanceof VaultOreBlock) {
                    ItemStack offHand = event.getPlayer().getOffhandItem();
                    if (!ServerVaults.get(world).isEmpty() || !(offHand.getItem() instanceof VaultGearItem)) {
                        if (offHand.getItem() instanceof VaultLootSackItem) {
                            int damage = (int)CommonEvents.PLAYER_STAT.invoke(PlayerStat.DURABILITY_DAMAGE, player, 1.0F).getValue();
                            if (damage <= 1) {
                                damage = 1;
                            }

                            offHand.hurtAndBreak(damage, event.getPlayer(), (entity) -> {
                                entity.broadcastBreakEvent(EquipmentSlot.OFFHAND);
                            });
                        }
                    }
                }
            }
        }
    }

}
