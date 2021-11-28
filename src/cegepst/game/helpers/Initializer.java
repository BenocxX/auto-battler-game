package cegepst.game.helpers;

import cegepst.game.entities.shopPhase.BuyStation;
import cegepst.game.entities.miscellaneous.TriggerArea;

import java.util.ArrayList;

public class Initializer {

    public ArrayList<BuyStation> getBuyStations() {
        ArrayList<BuyStation> buyStations = new ArrayList<>();
        buyStations.add(new BuyStation(300, 300, 1));
        buyStations.add(new BuyStation(380, 300, 2));
        buyStations.add(new BuyStation(460, 300, 3));
        buyStations.add(new BuyStation(540, 300, 4));
        return buyStations;
    }

    public ArrayList<TriggerArea> getTriggerAreasForBuyStations(ArrayList<BuyStation> buyStations) {
        ArrayList<TriggerArea> triggerAreas = new ArrayList<>();
        for (BuyStation buyStation : buyStations) {
            triggerAreas.add(new TriggerArea(buyStation.getId(), buyStation.getX(),
                    buyStation.getY() + buyStation.getHeight() + 10, buyStation.getWidth(), 50));
        }
        return triggerAreas;
    }
}
