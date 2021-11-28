package cegepst.game.helpers;

import cegepst.game.entities.shopPhase.ShopStation;
import cegepst.game.entities.miscellaneous.TriggerArea;

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
            triggerAreas.add(new TriggerArea(shopStation.getId(), shopStation.getX(),
                    shopStation.getY() + shopStation.getHeight() + 10, shopStation.getWidth(), 50));
        }
        return triggerAreas;
    }
}
