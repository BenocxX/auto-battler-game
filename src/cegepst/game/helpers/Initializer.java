package cegepst.game.helpers;

import cegepst.engine.helpers.LoopingIndex;
import cegepst.game.entities.enemies.Enemy;
import cegepst.game.entities.enemies.RunnerZombie;
import cegepst.game.entities.enemies.Zombie;
import cegepst.game.entities.shopPhase.ShopStation;
import cegepst.game.entities.miscellaneous.TriggerArea;
import cegepst.game.map.AttackingRow;
import cegepst.game.map.DefendingRow;
import cegepst.game.map.Row;

import java.util.ArrayList;

public class Initializer {

    public ArrayList<ShopStation> getShopStations() {
        ArrayList<ShopStation> shopStations = new ArrayList<>();
        shopStations.add(new ShopStation(300, 300, 1));
        shopStations.add(new ShopStation(380, 300, 2));
        shopStations.add(new ShopStation(460, 300, 3));
        shopStations.add(new ShopStation(540, 300, 4));
        return shopStations;
    }

    public ArrayList<TriggerArea> getTriggerAreasForShopStations(ArrayList<ShopStation> shopStations) {
        ArrayList<TriggerArea> triggerAreas = new ArrayList<>();
        for (ShopStation shopStation : shopStations) {
            TriggerArea triggerArea = new TriggerArea(shopStation.getId(),
                    shopStation.getWidth() / 2, 50);
            triggerArea.teleport(CenteringMachine.centerHorizontally(shopStation.getHitBox(), triggerArea.getWidth()),
                    shopStation.getY() + shopStation.getHeight() + 10);
            triggerAreas.add(triggerArea);
        }
        return triggerAreas;
    }

    public ArrayList<Enemy> getEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new Zombie());
        enemies.add(new RunnerZombie());
        return enemies;
    }

    public ArrayList<AttackingRow> getAttackingRows() {
        ArrayList<AttackingRow> rows = new ArrayList<>();
        rows.add(new AttackingRow(400));
        rows.add(new AttackingRow(450));
        rows.add(new AttackingRow(500));
        return rows;
    }

    public ArrayList<DefendingRow> getDefendingRows(ArrayList<AttackingRow> attackingRows) {
        ArrayList<DefendingRow> rows = new ArrayList<>();
        for (AttackingRow attackingRow : attackingRows) {
            rows.add(new DefendingRow(attackingRow));
        }
        return rows;
    }

    public ArrayList<Row> initializeRows(ArrayList<AttackingRow> attackingRows, ArrayList<DefendingRow> defendingRows) {
        ArrayList<Row> rows = new ArrayList<>();
        rows.addAll(attackingRows);
        rows.addAll(defendingRows);
        return rows;
    }
}
