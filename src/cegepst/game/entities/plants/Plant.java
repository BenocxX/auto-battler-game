package cegepst.game.entities.plants;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public abstract class Plant extends StaticEntity {

    protected Image image;
    protected String name;

    @Override
    public abstract void draw(Buffer buffer);
    public abstract void update();
    public abstract boolean isCooldownOver();
    public abstract Projectile ability();

    public Image getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
