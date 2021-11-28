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
            TriggerArea triggerArea = new TriggerArea(shopStation.getId(),
                    shopStation.getWidth() / 2, 50);
            triggerArea.teleport(CenteringMachine.centerHorizontally(shopStation.getHitBox(), triggerArea.getWidth()),
                    shopStation.getY() + shopStation.getHeight() + 10);
            triggerAreas.add(triggerArea);
        }
        return triggerAreas;
    }
}
