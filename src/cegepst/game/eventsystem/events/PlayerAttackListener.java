package cegepst.game.eventsystem.events;

import cegepst.game.entities.enemies.Enemy;

import java.util.ArrayList;

public interface PlayerAttackListener {

    void onTargetAttack(Enemy enemy, int damage);
    void onRowAttack(ArrayList<Enemy> enemies, int damage);
    void onAreaAttack(ArrayList<Enemy> enemies, int damage);
}
