package cegepst.game.entities.plants;

public enum Plants {

    PEASHOOTER(new Peashooter(0, 0)),
    SUNFLOWER(new Sunflower(0, 0));

    private Plant plant;

    Plants(Plant plant) {
        this.plant = plant;
    }

    public Plant getPlant() {
        return plant.getPlantOfSameType();
    }
}
