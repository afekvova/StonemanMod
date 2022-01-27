package me.afek.slendermanmod.entity.render;

import me.afek.slendermanmod.entity.custom.SlendermanEntity;
import me.afek.slendermanmod.entity.model.SlendermanModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SlendermanRenderer extends GeoEntityRenderer<SlendermanEntity> {

    public SlendermanRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SlendermanModel());
        this.shadowRadius = 0.7F;
    }
}
