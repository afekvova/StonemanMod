package me.afek.stonemanmod.entity.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.math.vector.Vector3d;

public class AttackPlayerGoal<T extends LivingEntity> extends NearestAttackableTargetGoal {

    public AttackPlayerGoal(MobEntity p_i50313_1_, Class<T> p_i50313_2_, boolean p_i50313_3_) {
        super(p_i50313_1_, p_i50313_2_, p_i50313_3_);
    }

    public static float getYaw(Vector3d vector) {
        double dx = vector.x();
        double dz = vector.z();
        double yaw = 0.0D;
        if (dx != 0.0D) {
            if (dx < 0.0D) {
                yaw = 4.71238898038469D;
            } else {
                yaw = 1.5707963267948966D;
            }

            yaw -= Math.atan(dz / dx);
        } else if (dz < 0.0D) {
            yaw = 3.141592653589793D;
        }

        return (float) (-yaw * 57.29577951308232D);
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
        PlayerEntity player = this.mob.level.getNearestPlayer(this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
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

    private boolean canSeePlayer(PlayerEntity player) {
        Vector3d vector3d = player.getViewVector(1.0F).normalize();
        Vector3d vector3d1 = new Vector3d(this.mob.getX() - player.getX(), this.mob.getEyeY() - player.getEyeY(), this.mob.getZ() - player.getZ());
        double d1 = vector3d.normalize().dot(vector3d1.normalize());
        return d1 >= 0.5;
    }
}
