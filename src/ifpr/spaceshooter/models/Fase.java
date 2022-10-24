package ifpr.spaceshooter.models;


import ifpr.spaceshooter.models.animation.Explosion;
import ifpr.spaceshooter.models.entitys.Enemy;
import ifpr.spaceshooter.models.entitys.Player;
import ifpr.spaceshooter.models.entitys.Shot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Fase extends JPanel implements ActionListener{

    private Image background;
    private Player player;
    private Timer timer;

    private ArrayList<Enemy> enemies;
    private ArrayList<Explosion> explosions;
    private boolean inGame = true;

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon reference = new ImageIcon("res\\background.png");
        background = reference.getImage();

        this.player = new Player();
        player.load();

        addKeyListener(new KeyboardAdapter());

       timer = new Timer(3, this);
       timer.start();

       enemies = new ArrayList<>();
       explosions = new ArrayList<>();

       loadEnemies();
    }

    private void createExplosion(int x, int y) {
        explosions.add(new Explosion(x, y));
    }

    public void checarColisoes() {
        Rectangle playerCollisionArea = player.getBounds();

        for (Enemy enemy : enemies) {
            Rectangle enemyCollisionArea = enemy.getBounds();
            if (playerCollisionArea.intersects(enemyCollisionArea)) {
                inGame = false;
            }
        }

        ArrayList<Shot> shots = player.getShots();
        for (Shot shot : shots) {
            Rectangle shotCollisionArea = shot.getBounds();
            for (Enemy enemy : enemies) {
                Rectangle enemyCollisionArea = enemy.getBounds();
                if (shotCollisionArea.intersects(enemyCollisionArea)) {
                    enemy.setVisible(false);
                    createExplosion(enemy.getX()-10, enemy.getY()+20);
                    shot.setVisible(false);
                }
            }
        }
    }

    public void loadEnemies() {
        for (int i = 0; i < 100; i++) {
            int x = (int)(Math.random()*8000+1024);
            int y = (int)(Math.random()*650);
            enemies.add(new Enemy(x,y));
        }
    }

    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        if(inGame) {
            graphics2D.drawImage(background, 0, 0, null);
            graphics2D.drawImage(player.getImage(), player.getX(), player.getY(), this);
            ArrayList<Shot> shots = player.getShots();
            for (Shot shot : shots) {
                shot.load();
                graphics2D.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
            }

            for (Enemy enemy : enemies) {
                enemy.load();
                graphics2D.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
            }
            for (Explosion explosion : explosions) {
                explosion.draw(graphics2D);
            }
        }else {

            graphics2D.setColor(new Color(0, 0, 0, 100));
            graphics2D.fillRect(0, 0, 1024, 728);

            int x = 1024/5, y = 728/2;
            String text = "Game Over";

            graphics2D.setFont(new Font("Public Sans", Font.BOLD, 108));
            graphics2D.setColor(Color.white);
            graphics2D.drawString(text, x, y);
        }
        graphics.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        ArrayList<Shot> shots = player.getShots();
        for (int i = 0; i < shots.size(); i++) {
            Shot shot = shots.get(i);
            if(shot.isVisible()) {
                shot.update();
            }else shots.remove(shot);
        }

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if(enemy.isVisible()) {
                enemy.update();
            }else enemies.remove(enemy);
        }

        for (int i = 0; i < explosions.size(); i++) {
            Explosion explosion = explosions.get(i);
            if(explosion.shouldRemove()) {
                explosions.remove(explosion);
            }else explosion.update();
        }

        checarColisoes();
        repaint();
    }

    private class KeyboardAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_ENTER:
                    if(!inGame) {
                        System.exit(0);
                    }
                    break;
                default:
                    player.keyReleased(e);
                    break;
            }
        }
    }
}
