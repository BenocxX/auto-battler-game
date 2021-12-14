package cegepst.game.eventsystem.events;

import cegepst.game.entities.projectiles.Projectile;

public interface SunListener {

    void onSunCreation(Projectile projectile);
    void onSunUtilisation();
}
