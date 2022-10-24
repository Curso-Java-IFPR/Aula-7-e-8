package ifpr.spaceshooter.models.entitys;

import javax.swing.*;
import java.awt.*;

public class Enemy {

    private Image image;

    private int x, y;
    private int width, height;

    private boolean isVisible;

    private int speed = 5;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;

        this.isVisible = true;
    }

    public void load() {
        ImageIcon reference = new ImageIcon("res//enemy.png");
        image = reference.getImage();

        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    public void update() {
        x -= speed;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Image getImage() {
        return image;
    }
}
