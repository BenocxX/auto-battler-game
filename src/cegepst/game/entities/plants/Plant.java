package cegepst.game.entities.plants;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;
import cegepst.game.entities.miscellaneous.Projectile;

import java.awt.*;

public abstract class Plant extends StaticEntity {

    protected Image image;
    protected String name;

    public abstract void update();
    @Override
    public abstract void draw(Buffer buffer);
    public abstract Projectile ability();
    public abstract boolean isCooldownOver();
    public abstract Plant getPlantOfSameType();

    public Image getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
