// File: core/Main.java
package core;

import java.awt.BorderLayout;

import javax.swing.*;
import stage.stage_1;
import status_basis.UI;

public class Main extends JFrame {
    public Main() {
        // JFrame 창 생성
        setTitle("게임 구현");
        setSize(1100, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 레이아웃을 BorderLayout으로 설정
        setLayout(new BorderLayout());

        stage_1 stage1 = new stage_1();
        add(stage1, BorderLayout.CENTER);

        UI ui = new UI();
        add(ui, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}
