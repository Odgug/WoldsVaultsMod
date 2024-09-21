//package xyz.iwolfking.woldsvaults.mixins.qolhunters;
//
//
//import io.iridium.qolhunters.QOLHunters;
//import iskallia.vault.core.data.key.ThemeKey;
//import iskallia.vault.core.vault.Vault;
//import iskallia.vault.core.vault.VaultRegistry;
//import iskallia.vault.core.vault.WorldManager;
//import iskallia.vault.core.vault.objective.Objectives;
//import iskallia.vault.event.event.VaultJoinEvent;
//import net.minecraft.ChatFormatting;
//import net.minecraft.client.Minecraft;
//import net.minecraft.network.chat.Component;
//import net.minecraft.network.chat.Style;
//import net.minecraft.network.chat.TextColor;
//import net.minecraft.network.chat.TextComponent;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.event.TickEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//import org.spongepowered.asm.mixin.Shadow;
//
//import java.util.function.Consumer;
//
//@Mixin(targets = "io/iridium/qolhunters/QOLHunters$ClientForgeEvents", remap = false)
//public abstract class MixinQOLHunters {
//    @Shadow private static Component vaultSubtitle;
//
//    @Shadow
//    public static String getVaultObjective(String key) {
//        return null;
//    }
//
//    @Shadow private static Component vaultTitle;
//
//    /**
//     * @author
//     * @reason
//     */
//    @Overwrite
//    @SubscribeEvent
//    public static void onVaultJoin(VaultJoinEvent event) {
//        ResourceLocation theme = (ResourceLocation) ((WorldManager) event.getVault().get(Vault.WORLD)).get(WorldManager.THEME);
//        ThemeKey themeKey = (ThemeKey) VaultRegistry.THEME.getKey(theme);
//        vaultSubtitle = (new TextComponent(themeKey.getName())).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(themeKey.getColor()))).withStyle(ChatFormatting.ITALIC);
//        String obj = getVaultObjective((String) ((Objectives) event.getVault().get(Vault.OBJECTIVES)).get(Objectives.KEY));
//        vaultTitle = (new TextComponent(obj + " Vault")).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(14536734)));
//        MinecraftForge.EVENT_BUS.addListener(new Consumer<TickEvent.ClientTickEvent>() {
//            public void accept(TickEvent.ClientTickEvent tickEvent) {
//                if (QOLHunters.ClientForgeEvents.vaultTitle != null && Minecraft.getInstance().player != null && Minecraft.getInstance().screen == null) {
//                    QOLHunters.displayTitleOnScreen(QOLHunters.ClientForgeEvents.vaultTitle);
//                    QOLHunters.displaySubtitleOnScreen(QOLHunters.ClientForgeEvents.vaultSubtitle);
//                    QOLHunters.ClientForgeEvents.vaultTitle = null;
//                    QOLHunters.ClientForgeEvents.vaultSubtitle = null;
//                    MinecraftForge.EVENT_BUS.unregister(this);
//                }
//
//            }
//        });
//    }
//}
