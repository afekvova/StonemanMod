package me.afek.slendermanmod.entity.model;

import me.afek.slendermanmod.MainMod;
import me.afek.slendermanmod.entity.custom.SlendermanEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SlendermanModel extends AnimatedGeoModel<SlendermanEntity> {

    @Override
    public ResourceLocation getModelLocation(SlendermanEntity object) {
        return new ResourceLocation(MainMod.MOD_ID, "geo/model_geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SlendermanEntity object) {
        return new ResourceLocation(MainMod.MOD_ID, "textures/entity/texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SlendermanEntity animatable) {
        return new ResourceLocation(MainMod.MOD_ID, "animations/model_animation.json");
    }
}
