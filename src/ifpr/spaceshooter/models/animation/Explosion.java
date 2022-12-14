package ifpr.spaceshooter.models.animation;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Explosion {

    private int x,y, xmap, ymap;

    private int width, height;

    private Animation animation;
    private BufferedImage[] sprites;

    private boolean remove;
    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;

        width = 30;
        height = 30;

        try {
            BufferedImage spritesheet = ImageIO.read(new File("res/explosion.gif"));

            sprites = new BufferedImage[6];
            for (int i = 0; i < sprites.length; i++) {
                sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(40);
    }

    public void update() {
        animation.update();
        if(animation.hasPlayedOnce()) {
            remove = true;
        }
    }

    public boolean shouldRemove() {
        return remove;
    }

    public void setMapPosition(int x, int y) {
        xmap = x;
        ymap = y;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(animation.getImage(), x + xmap / width / 2, y + ymap - height / 2, null);
    }
}
