package cegepst.game.entities.plants;

import cegepst.game.entities.projectiles.Projectile;

public class DoublePeashooter extends Plant {

    private final static int RESET_SECOND_SHOT_COOLDOWN = 10;

    private int secondShotCooldown;
    private boolean isFirstShotFired;

    public DoublePeashooter(PlantType plantType) {
        super(plantType);
        secondShotCooldown = RESET_SECOND_SHOT_COOLDOWN;
    }

    @Override
    public void update() {
        if (isFirstShotFired) {
            secondShotCooldown--;
            if (secondShotCooldown <= 0) {
                isFirstShotFired = false;
            }
        } else {
            cooldown--;
            if (cooldown <= 0) {
                cooldown = 0;
                isFirstShotFired = true;
            }
        }
    }

    @Override
    public Projectile ability() {
        if (isFirstShotFired) {
            cooldown = plantType.getAbilityCooldown();
        } else {
            secondShotCooldown = RESET_SECOND_SHOT_COOLDOWN;
        }
        return plantType.generateNewProjectile(this, x + width - 15, y + 5 - 2);
    }

    @Override
    public boolean isCooldownOver() {
        return cooldown == 0 || secondShotCooldown == 0;
    }

    public Plant getPlantOfSameType() {
        return new DoublePeashooter(plantType);
    }
}
