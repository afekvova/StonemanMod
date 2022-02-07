package me.afek.stonemanmod.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class BaseEntity extends MonsterEntity {

    protected int attackTimer = 0, attackAnimationLength = 1;
    protected boolean isAttackStaredAlready = false;

    protected BaseEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.attackTimer > 0)
            this.attackTimer--;
        if (this.swinging && this.attackTimer == 0) {
            this.attackTimer = this.attackAnimationLength;
            this.isAttackStaredAlready = false;
        }

    }

    public void setAttackAnimationLength(float attackAnimationLength) {
        this.attackAnimationLength = (int) (attackAnimationLength * 20.0F);
    }
}
