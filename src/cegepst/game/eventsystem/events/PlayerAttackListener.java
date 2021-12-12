package cegepst.game.eventsystem.events;

import cegepst.game.entities.enemies.Enemy;

import java.util.ArrayList;

public interface PlayerAttackListener {

    void onTargetAttack(int id, int damage);
    void onRowAttack(ArrayList<Enemy> enemies, int damage);
    void onAreaAttack(int[] ids, int damage);
}
