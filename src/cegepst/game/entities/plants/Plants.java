package cegepst.game.entities.plants;

public enum Plants {

    PEASHOOTER(new Plant(PlantType.PEASHOOTER)),
    SUNFLOWER(new Plant(PlantType.SUNFLOWER)),
    GATLING_PEA(new Plant(PlantType.GATLINGPEA)),
    WALL_NUT(new Plant(PlantType.WALL_NUT)),
    DOUBLE_PEASHOOTER(new DoublePeashooter(PlantType.DOUBLE_PEASHOOTER));

    private Plant plant;

    Plants(Plant plant) {
        this.plant = plant;
    }

    public Plant getPlant() {
        return plant.getPlantOfSameType();
    }
}
