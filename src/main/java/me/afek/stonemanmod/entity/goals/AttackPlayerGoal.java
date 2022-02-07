package me.afek.stonemanmod.entity.goals;

import me.afek.stonemanmod.entity.FixEntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;
import java.util.List;

public class AttackPlayerGoal<T extends LivingEntity> extends NearestAttackableTargetGoal {
    FixEntityPredicate entityPredicate;

    public AttackPlayerGoal(MobEntity p_i50313_1_, Class<T> p_i50313_2_, boolean p_i50313_3_) {
        super(p_i50313_1_, p_i50313_2_, p_i50313_3_);
        this.entityPredicate = (new FixEntityPredicate()).range(this.getFollowDistance());
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity == null) {
            livingentity = this.targetMob;
        }

        if (livingentity == null) {
            return false;
        } else if (!livingentity.isAlive() || canSeePlayer((PlayerEntity) livingentity)) {
            return false;
        } else {
            Team team = this.mob.getTeam();
            Team team1 = livingentity.getTeam();
            if (team != null && team1 == team) {
                return false;
            } else {
                double d0 = this.getFollowDistance();
                if (this.mob.distanceToSqr(livingentity) > d0 * d0) {
                    return false;
                } else {
                    if (livingentity instanceof PlayerEntity && ((PlayerEntity) livingentity).abilities.invulnerable) {
                        return false;
                    } else {
                        this.mob.setTarget(livingentity);
                        return true;
                    }
                }
            }
        }
    }

    @Override
    protected void findTarget() {
        PlayerEntity player = getNearestEntity(this.mob.level.players(), this.entityPredicate, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        if (player != null) {
            boolean canSeenPlayer = canSeePlayer(player);
            if (canSeenPlayer) {
                this.target = null;
                this.mob.getNavigation().stop();
                this.stop();
                return;
            }

            this.target = player;
        }
    }

    public <T extends LivingEntity> T getNearestEntity(List<? extends T> p_217361_1_, FixEntityPredicate p_217361_2_, @Nullable LivingEntity p_217361_3_, double p_217361_4_, double p_217361_6_, double p_217361_8_) {
        double d0 = -1.0D;
        T t = null;

        for (T t1 : p_217361_1_) {
            if (p_217361_2_.test(p_217361_3_, t1)) {
                double d1 = t1.distanceToSqr(p_217361_4_, p_217361_6_, p_217361_8_);
                if (d0 == -1.0D || d1 < d0) {
                    d0 = d1;
                    t = t1;
                }
            }
        }

        return t;
    }

    private boolean canSeePlayer(PlayerEntity player) {
        Vector3d vector3d = player.getViewVector(1.0F).normalize();
        Vector3d vector3d1 = new Vector3d(this.mob.getX() - player.getX(), this.mob.getEyeY() - player.getEyeY(), this.mob.getZ() - player.getZ());
        double d1 = vector3d.normalize().dot(vector3d1.normalize());
        return d1 >= 0.5;
    }
}
