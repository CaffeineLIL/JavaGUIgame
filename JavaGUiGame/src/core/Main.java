// File: core/Main.java
package core;

import javax.swing.*;
import stage.stage_1;

public class Main extends JFrame {
    public Main() {
        // JFrame 창 생성
        setTitle("캐릭터 움직임 구현");
        setSize(1100, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
       
        stage_1 stage1 = new stage_1();
        add(stage1);
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}
