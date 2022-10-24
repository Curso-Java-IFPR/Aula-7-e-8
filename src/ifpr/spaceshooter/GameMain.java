package ifpr.spaceshooter;

import ifpr.spaceshooter.models.Fase;

import javax.swing.*;

public class GameMain extends JFrame {

    public GameMain() {
        add(new Fase());
        setTitle("Space Shooter");
        setSize(1024, 728);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GameMain();
    }
}
