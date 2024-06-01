// File: core/Main.java
package core;

import java.awt.BorderLayout;

import javax.swing.*;

import player.Player;
import stage.stage_1;
import status_basis.UI;

public class Main extends JFrame {
	Player player;
	int hp;
	boolean PlayerAlive;
	
    public Main() {
        // JFrame 창 생성
        setTitle("게임 구현");
        setSize(1100, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        player = new Player();
        PlayerAlive = player.player_isalive();

        // 레이아웃을 BorderLayout으로 설정
        setLayout(new BorderLayout());

        stage_1 stage1 = new stage_1();
        add(stage1, BorderLayout.CENTER);
        //add(stage1);

       
        hp = (int)player.getHp(); // 플레이어의 HP만큼 생명력 표시
        UI ui = new UI(hp);
        
        if(!PlayerAlive) {
        	System.out.println("Player dead");
        	System.exit(1); //플레이어 사망 시 게임 종료
        }
        
        add(ui, BorderLayout.NORTH);
        //add(ui);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}
