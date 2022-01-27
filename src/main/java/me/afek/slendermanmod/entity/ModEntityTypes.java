package me.afek.slendermanmod.entity;

import me.afek.slendermanmod.MainMod;
import me.afek.slendermanmod.entity.custom.SlendermanEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MainMod.MOD_ID);

    public static final RegistryObject<EntityType<SlendermanEntity>> SLENDER_MAN = ENTITY_TYPES
            .register("slenderman", () -> EntityType.Builder.of(SlendermanEntity::new, EntityClassification.CREATURE).sized(0.6F, 2.3F).build((new ResourceLocation(MainMod.MOD_ID, "slenderman")).toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
