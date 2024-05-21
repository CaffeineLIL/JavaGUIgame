package core;

import javax.swing.SwingUtilities;

import player.Player;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Player frame = new Player();
            frame.setVisible(true);
        });
    }
}
