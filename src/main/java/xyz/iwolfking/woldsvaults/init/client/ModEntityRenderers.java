package xyz.iwolfking.woldsvaults.init.client;


import iskallia.vault.entity.model.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.iwolfking.woldsvaults.init.ModEntities;
import xyz.iwolfking.woldsvaults.custom.objectives.data.bosses.client.WoldBossRenderer;

@OnlyIn(Dist.CLIENT)
public class ModEntityRenderers {
    public ModEntityRenderers() {
    }

    public static void register(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.WOLD, ctx -> new WoldBossRenderer(ctx, ModModelLayers.FIGHTER));
    }
}
