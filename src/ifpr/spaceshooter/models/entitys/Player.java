package ifpr.spaceshooter.models.entitys;



import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player {

    private final int x;
    private int y;
    private int velY;
    private final int defaultVelY;
    private int width, height;

    private Image image;
    private ArrayList<Shot> shots;

    public Player() {
        this.x = 100;
        this.y = 100;
        this.defaultVelY = 3;

        this.shots = new ArrayList<>();
    }

    public void load() {
        ImageIcon reference = new ImageIcon("res\\player.png");
        image = reference.getImage();

        this.width = image.getWidth(null);
        this.height = image.getWidth(null);
    }

    public void shot() {
        this.shots.add(new Shot((x+(width)) - 15, (y + (height/4)) + 3));
    }

    public void update() {
        if(y <= 10) {
            this.y = this.y + 10;
        }else if(y>650) {
            this.y = this.y - 10;
        }
        this.y += velY;
    }

    public void keyPressed(KeyEvent key) {
        int keyCode = key.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                velY = defaultVelY;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                velY = -defaultVelY;
                break;
        }
    }

    public void keyReleased(KeyEvent key) {
        int keyCode = key.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                velY = 0;
                break;
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_X:
                shot();
                break;
        }
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

    public Image getImage() {
        return image;
    }

    public ArrayList<Shot> getShots() {
        return shots;
    }
}
