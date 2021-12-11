package cegepst.game.eventsystem.events;

public interface PlayerAttackListener {

    void onTargetAttack(int id, int damage);
    void onAreaAttack(int[] ids, int damage);
}
