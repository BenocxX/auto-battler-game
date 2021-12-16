package cegepst.game.helpers;

import cegepst.engine.RenderingEngine;
import cegepst.engine.menu.MenuSystem;
import cegepst.game.controls.MousePad;
import cegepst.game.displays.Display;
import cegepst.game.entities.shops.ShopStation;
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

    public MenuSystem getShopMenuSystem(MousePad mousePad, Display parent) {
        MenuSystem menuSystem = new MenuSystem();
        menuSystem.addMousePadDevice(mousePad);
        menuSystem.addButton(ButtonFactory.inventoryButton(10, RenderingEngine.HEIGHT - 60, parent));
        menuSystem.addButton(ButtonFactory.battleButton(10, RenderingEngine.HEIGHT - 120));
        return menuSystem;
    }

    public MenuSystem getBattleMenuSystem(MousePad mousePad, Display parent) {
        MenuSystem menuSystem = new MenuSystem();
        menuSystem.addMousePadDevice(mousePad);
        menuSystem.addButton(ButtonFactory.inventoryButton(10, RenderingEngine.HEIGHT - 60, parent));
        menuSystem.addButton(ButtonFactory.leaveBattleButton(10, RenderingEngine.HEIGHT - 120));
        return menuSystem;
    }
}
