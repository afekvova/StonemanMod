package me.afek.stonemanmod.entity.custom;

import me.afek.stonemanmod.entity.goals.AttackPlayerGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class StonemanEntity extends BaseEntity {


    public StonemanEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes().add(Attributes.MAX_HEALTH, 40.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.7F).add(Attributes.ATTACK_DAMAGE, 7.0D).add(Attributes.FOLLOW_RANGE, 64.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.85D, false));
        this.targetSelector.addGoal(1, new AttackPlayerGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void checkDespawn() {
        //NoSupportMethod
    }
}
