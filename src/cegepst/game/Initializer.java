package cegepst.game;

import cegepst.game.entities.BuyStation;
import cegepst.game.entities.TriggerArea;

import java.util.ArrayList;

public class Initializer {

    public ArrayList<BuyStation> getBuyStations() {
        ArrayList<BuyStation> buyStations = new ArrayList<>();
        buyStations.add(new BuyStation(100, 100, 1));
        buyStations.add(new BuyStation(200, 100, 2));
        buyStations.add(new BuyStation(300, 100, 3));
        buyStations.add(new BuyStation(400, 100, 4));
        return buyStations;
    }

    public ArrayList<TriggerArea> getTriggerAreasForBuyStations(ArrayList<BuyStation> buyStations) {
        ArrayList<TriggerArea> triggerAreas = new ArrayList<>();
        for (BuyStation buyStation : buyStations) {
            triggerAreas.add(new TriggerArea(buyStation.getId(), buyStation.getX() - 10,
                    buyStation.getY() + buyStation.getHeight() + 10, 50, 50));
        }
        return triggerAreas;
    }
}
