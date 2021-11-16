package cegepst.engine.resources.images;

import cegepst.engine.controls.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Animator {

    private HashMap<Direction, Image[]> frames;
    private BufferedImage spriteSheet;
    private int currentAnimationFrame;
    private int nextFrame;
    private int animationSpeed;

    public Animator(BufferedImage spriteSheet, int animationSpeed) {
        this.spriteSheet = spriteSheet;
        this.animationSpeed = animationSpeed;
        nextFrame = this.animationSpeed;
        currentAnimationFrame = 1;
        frames = new HashMap<>();
    }

    public void updateAnimationFrame(boolean isMoving) {
        if (isMoving) {
            move();
        } else {
            idle();
        }
    }

    public void loadAnimations(int x,
                               int y,
                               int width,
                               int height,
                               int nbImages) {
        Direction[] directions = {
                Direction.DOWN,
                Direction.LEFT,
                Direction.RIGHT,
                Direction.UP
        };
        for (int i = 0; i < directions.length; i++, y += height) {
            Image[] images = SpritesheetHandler.getFrames(spriteSheet,
                    x, y, width, height, nbImages);
            frames.put(directions[i], images);
        }
    }

    public Image getImage(Direction direction) {
        return frames.get(direction)[currentAnimationFrame];
    }

    private void move() {
        --nextFrame;
        if (nextFrame == 0) {
            goToNextFrame();
            nextFrame = animationSpeed;
        }
    }

    private void goToNextFrame() {
        ++currentAnimationFrame;
        if (currentAnimationFrame >= frames.get(Direction.DOWN).length) {
            currentAnimationFrame = 0;
        }
    }

    private void idle() {
        currentAnimationFrame = 1;
    }
}
