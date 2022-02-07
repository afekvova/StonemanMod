package me.afek.stonemanmod.entity;

import me.afek.stonemanmod.MainMod;
import me.afek.stonemanmod.entity.custom.StonemanEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MainMod.MOD_ID);

    public static final RegistryObject<EntityType<StonemanEntity>> SLENDER_MAN = ENTITY_TYPES
            .register("stoneman", () -> EntityType.Builder.of(StonemanEntity::new, EntityClassification.CREATURE).sized(0.6F, 2.3F).build((new ResourceLocation(MainMod.MOD_ID, "slenderman")).toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
