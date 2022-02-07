package me.afek.stonemanmod.entity.render;

import me.afek.stonemanmod.MainMod;
import me.afek.stonemanmod.entity.custom.StonemanEntity;
import me.afek.stonemanmod.entity.model.StonemanModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.ResourceLocation;

public class StonemanRenderer extends LivingRenderer<StonemanEntity, PlayerModel<StonemanEntity>> {

    public StonemanRenderer(EntityRendererManager renderManager) {
        super(renderManager, new StonemanModel<>(0.0F, false), 0.5F);
        this.shadowRadius = 0.7F;
    }

    @Override
    public ResourceLocation getTextureLocation(StonemanEntity p_110775_1_) {
        return new ResourceLocation(MainMod.MOD_ID, "textures/entity/texture.png");
    }
}
