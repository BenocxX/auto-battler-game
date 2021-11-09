package cegepst.game;

import cegepst.game.entities.BuyStation;
import cegepst.game.entities.Trigger;

import java.util.ArrayList;
import java.util.HashMap;

public class Initializer {

    public ArrayList<BuyStation> getBuyStations() {
        ArrayList<BuyStation> buyStations = new ArrayList<>();
        buyStations.add(new BuyStation(100, 100));
        buyStations.add(new BuyStation(200, 100));
        buyStations.add(new BuyStation(300, 100));
        buyStations.add(new BuyStation(400, 100));
        return buyStations;
    }

    public HashMap<Trigger, Triggerable> getTriggersForBuyStations(ArrayList<BuyStation> buyStations) {
        HashMap<Trigger, Triggerable> hashMap = new HashMap<>();
        for (BuyStation buyStation : buyStations) {
            Trigger trigger = new Trigger();
            trigger.teleport(buyStation.getX() - 10, buyStation.getY() + buyStation.getHeight() + 10);
            trigger.setDimension(50, 50);
            hashMap.put(trigger, buyStation);
        }
        return hashMap;
    }
}
