package cardTest;

import javax.swing.*;


import java.awt.*;


import java.awt.event.KeyEvent;


import java.awt.event.KeyListener;


class Player {


 private int x, y; // 플레이어의 좌표


 private Image image; // 플레이어 이미지


 public Player(int x, int y, Image image) {


 this.x = x;


 this.y = y;


 this.image = image;


 }

 public void draw(Graphics g) {
 g.drawImage(image, x, y, null);
 }


 


 public void move(int dx, int dy) {


 x += dx;


 y += dy;


 }


 // 이동 가능한지 여부 체크


 public boolean canMove(int dx, int dy) {


 // 이동 가능한지 여부를 판단하는 로직을 추가할 수 있습니다.


 return true;


 }


}

class Monster {
 private int x, y; // 몬스터의 좌표

 private Image image; // 몬스터 이미지
 

 public Monster(int x, int y, Image image) {
 this.x = x;
 this.y = y;
 this.image = image;

 }

 public void draw(Graphics g) {
 g.drawImage(image, x, y, null);
 }


 // 이동 가능한지 여부 체크
 public boolean canMove(int dx, int dy) {
 // 이동 가능한지 여부를 판단하는 로직을 추가할 수 있습니다.
 return true;
 }

}

public class DungeonGame extends JPanel implements KeyListener {
 private Player player;
 private Monster monster;
 public DungeonGame() {
 // 플레이어 초기화
 ImageIcon playerIcon = new ImageIcon("player.jpg");
 Image playerImage = playerIcon.getImage();
 player = new Player(50, 50, playerImage);

 // 몬스터 초기화
 ImageIcon monsterIcon = new ImageIcon("monster.jpg");
 Image monsterImage = monsterIcon.getImage();
 monster = new Monster(200, 200, monsterImage);


 // 키 리스너 등록
 setFocusable(true);
 addKeyListener(this);
 }


@Override
public void paintComponent(Graphics g) {
super.paintComponent(g);
// 배경 이미지 그리기
ImageIcon backgroundIcon = new ImageIcon("background.jpg");
Image backgroundImage = backgroundIcon.getImage();
g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

 // 플레이어 그리기
 player.draw(g);
 // 몬스터 그리기
 monster.draw(g);
 }
 @Override
 public void keyPressed(KeyEvent e) {
 int keyCode = e.getKeyCode();
 int dx = 0, dy = 0;
 // 플레이어 이동 처리
 switch (keyCode) {


 case KeyEvent.VK_UP:


 dy = -5;


 break;


 case KeyEvent.VK_DOWN:


 dy = 5;


 break;


 case KeyEvent.VK_LEFT:


 dx = -5;


 break;


 case KeyEvent.VK_RIGHT:


 dx = 5;


 break;


 }


 if (player.canMove(dx, dy)) {


 player.move(dx, dy);


 repaint();


 }


 }


 


 @Override
 public void keyTyped(KeyEvent e) {}


 @Override
 public void keyReleased(KeyEvent e) {}


 
 public static void main(String[] args) {


 JFrame frame = new JFrame("Dungeon Game");


 DungeonGame game = new DungeonGame();


 frame.add(game);


 frame.setSize(800, 600);


 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


 frame.setVisible(true);


 }


}