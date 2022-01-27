package me.afek.slendermanmod;

import me.afek.slendermanmod.entity.ModEntityTypes;
import me.afek.slendermanmod.entity.custom.SlendermanEntity;
import me.afek.slendermanmod.entity.render.SlendermanRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

@Mod(MainMod.MOD_ID)
public class MainMod {

    public static final String MOD_ID = "slendermanmod";

    public MainMod() {
        GeckoLib.initialize();

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::registerEntityAttributes);
//        eventBus.addListener(this::registerRenderers);
        MinecraftForge.EVENT_BUS.register(this);

        ModEntityTypes.register(eventBus);
    }

    public void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.SLENDER_MAN.get(), SlendermanEntity.createAttributes().build());
    }

    @OnlyIn(Dist.CLIENT)
    private void registerRenderers(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SLENDER_MAN.get(),
                SlendermanRenderer::new);
    }
}
