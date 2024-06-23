# 자바 스윙을 활용한 던전 게임 개발 해보기

# 실행 화면
![image](https://github.com/CaffeineLIL/JavaGUIgame/assets/66466351/6c7a99d1-3201-4a49-9a16-7d0a4ecd1f72)


결과 :![](https://velog.velcdn.com/images/caffeinelil/post/52dc920b-e490-4f9a-a7c7-0f4be300891f/image.gif)



## 기능 설명
### 0. 기본적인 게임의 틀 잡기
 우리가 만들어야 하는 것은 JFrame을 사용한 던전 게임이다. 하지만 게임에는 여러가지 요소가 있기에 게임의 요소들을 JPanel로 만들고, JPanel의 메서드 ```
  setOpaque(false); ```를 활용하여 Panel들의 배경을 투명색으로 설정하여 자연스럽게 보이도록 하였습니다.
  
  게임을 표시할 JFrame창의 기본 세팅을 하고, 게임의 요소를 제작한 JPanel들을 add()하는 식으로 게임 요소들을 추가하여 게임을 구현하였습니다.

또한 각종 요소에 대한 추상 클래스를 만들어 플레이어가 적에게 가할 데미지를 계산하는 메서드를 만들고, 다른 클래스에서 상속받을 수 있도록 하여 객체를 활용할 수 있도록 하였습니다.

 
 ```java
 // File: core/Main.java. 게임 실행 main메서드가 유일하게 존재
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

 ```
 



## 1.플레이어 

 

*Player.java 파일에 구현함.


  ### 플레이어  이동 구현
  
- 체력 10. 체력이 모두 소진되면 게임오버.
- 적에게 투사체를 원하는 방향으로 발사하여 적에게 적중 시 적의 체력을 감소시킴.
```java
package abstracts;


public abstract class abstractPlayer extends abstractBasis {
	final private int HP = 4;
	final private  double baseATK = 3.5;
	private  double effectiveDmg;
	//캐릭터 공격 데미지 계산 공식
	//예상 피해값 = 캐릭터 기본 공격력 * √(획득한 아이템들의 대미지 합계 * 1.2 + 1) + 아이템의 특수 공격력 수치
	public  double PlayerAtk(double totalDmgup, double FlatDmgup ) {
		effectiveDmg = baseATK * Math.sqrt(totalDmgup * 1.2 + 1) + FlatDmgup;
		return effectiveDmg;
	}
	
	public double getHP() {
		return HP;
	}
}
```

- 이동 : 키보드 W, A, S, D를 눌러 각각 상, 좌, 하, 우 방향으로 이동.
코드가 많으니 키 1개의 경우만 설명하겠습니다.
```java
   private void setupKeyBindings() {
        InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();

        // 기존 WASD 키 바인딩
        inputMap.put(KeyStroke.getKeyStroke("pressed W"), "upPressed");
        inputMap.put(KeyStroke.getKeyStroke("released W"), "upReleased");
  
	// 이동 액션
        actionMap.put("upPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upPressed = true;
            }
        });
   actionMap.put("upReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upPressed = false;
            }
        });
  
  ```
   우선 키 바인딩을 위해 inPutMap.put()을 사용하여 키보드 키가 눌려질 때, 떼질 때 문자열을 출력하도록 코드를 작성합니다. 문자열 "upPressed"가 인식되면 ```actionPerformed()```메서드가 작동하여 ```uppressed```의 변수값을 변화시킵니다.
이후 변수값을 인식하여 
  ```java 
  if (upPressed) {
                newY = (int) Math.max(y - MOVE_AMOUNT, 0);
                playerImg.moveDown();  // 위로 이동 시 이미지 변경
                moved = true;
            }
  ```
  로 플레이어의 좌표값을 변화시켜 움직임을 구현합니다.
  
 또한 플레이어가 자연스럽게 움직일수 있도록 해야하는데, 여러개의 JPanel이 겹치는 제 프로그램의 특성 상 반복 명령을 구현하는데 일반적인 반복문은 적합하지 않다 생각해, timer 객체를 사용하여 반복문을 처리하였습니다.
 ```Timer timer = new Timer(10, e -> { ~~~``` 를 통해 타이머 객체를 만들어 10ms만큼 명령이 반복되게 하였고, 람다식을 통해 위에서 설명한 이동 기능을 구현하였습니다.

```java
 // 벽과의 충돌 감지
 if (!collisionDetector.checkCollision(new Rectangle(newX, newY, SIZE, SIZE))) {
  x = newX;
   y = newY;
 }
```           
문장을 삽입하여 다른 패널에서 만들어 놓은 벽에 부딛힐 경우 플레이어의 좌표값을 더이상 변화시키지 않도록 하는 조건문을 생성하였습니다.
```boolean moved = false;``` 변수르 만들어 플레이어가 움직이고 있는지, 아닌지 판단하여 뒤에 설명할 플레이어의 애니메이션 구현 기능과 연결되도록 하였습니다.
 
 ### 플레이어 이미지 구현

플레이어 머리 부분 이미지:
> ![lilpaface_sp](https://github.com/CaffeineLIL/JavaGUIgame/assets/66466351/08e186f8-58ac-4e26-b05b-fbeb58b77149)

플레이어 몸통 부분 이미지:
>![lilpadown_sp_strip10](https://github.com/CaffeineLIL/JavaGUIgame/assets/66466351/2d2ab1d6-60f2-417b-a967-ec2fd6943b8b)

해당 플레이어 이미지를 사용하였습니다. 해당 이미지를 서로 합쳐 이동할떄마다 플레이어가 걸어다니는 애니메이션이 출력되도록 구현하였습니다.

BufferedImage 형의 참조변수를 제작하여 ```ImageIO.read(new File())```을 통해 원본 파일을 불러와 변수에 할당합니다. 이후 움직일따마다 플레이어가 걸어가는 애니메이션을 구현하기 위해 Arraylist를 만들고 해당 ArrayList에 일정 좌표만큼 사진을 잘라 저장하고, 움직일떄마다 ArrayList안의 내용물을 순차적으로 보여주여 애니메이션을 구현하였습니다.
![](https://velog.velcdn.com/images/caffeinelil/post/e2b8739f-100f-48bf-b850-f8e0d013d529/image.png)
위 사진은 영사기의 시초를 나타낸 사진인데, 해당 사진에서 설명하는 영사기의 원리와 동일한 원리입니다.

**구현은 어떻게 하면 좋을까?**
```java
body_cropped = body_image.getSubimage(imgcutX, imageY, width, height);
changebody.add(body_cropped); //애니메이션을 나타낼 리스트에 저장
```
이처럼 getSubimage()를 이용하여 원하는 좌표만큼 이미지를 잘라내 변수에 저장하고 리스트에 해당 변수를 저장해줍니다.

```java
 // 아래로 움직일 경우 이미지를 변경합니다.
    public void moveDown() {
        //changebody 리스트에서 이미지를 순환합니다.
        imgindex = (imgindex + 1) % changebody.size();
        combineImages(); // 이동 후 이미지를 다시 합성합니다.
    }
```
이후 플레이어가 키보드를 통해 이동할 경우 리스트의 다음 인덱스로 넘어가며 이미지를 순환하고, 해당 리스트가 계속 반복되도록 리스트의 최대 크기만큼 모듈러 연산을 진행하여 구현하여 줍니다.

```java
public void moveInit() {
        imageX = 0;
        imageY = 0;
        imgindex = 0;
        if(changebody.isEmpty()) cropBodyImage();
        combineImages();
    }
 ```
그리고 움직임을 멈출 경우 moveInit()메서드를 통해 이미지의 인덱스를 초기화하여 줍니다.

```java
 // 두 이미지를 합성하여 새로운 이미지를 생성합니다.
    private void combineImages() {
        BufferedImage body_cropped = changebody.get(imgindex);
        int combinedWidth = head_image.getWidth();
        int combinedHeight = head_image.getHeight() + body_cropped.getHeight();
        image = new BufferedImage(combinedWidth, combinedHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(head_image, 0, 0, null); // head_image를 (0, 0)에 그립니다.
        g2d.drawImage(body_cropped, 10, 60, null); // body_cropped를 (10, 60 의 높이)에 그립니다.
        g2d.dispose(); // 그래픽 객체를 제거합니다.
        	
        // 이미지 크기를 조정합니다.
        image = scaleImage(image, 130, 190); // 원하는 크기로 변경
    }
```

플레이어에게 완전한 이미지를 표시해 주어야 하니, 불러온 머리부분 이미지와 몸 부분 이미지를 불러와 완전한 이미지로 만들 준비를 합니다.
```new BufferedImage(combinedWidth, combinedHeight, BufferedImage.TYPE_INT_ARGB)```를 통해 빈 이미지를 하나 만들어주고 ```Graphics2D g2d = image.createGraphics()```로 그래픽 객체를 만들어 ```g2d.drawImage()``` 를 통해 잘라낸 머리부분 이미지, 몸 부분 이미지를 적절한 위치에 빈 이미지에 그려줍니다.

<details>
<summary>이미지 불러오기 전체 코드 - 클릭할 경우 펼쳐집니다.</summary>


```java
  package player;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerImage {
    private BufferedImage head_image;
    private BufferedImage body_image;
    private BufferedImage image;
    BufferedImage body_cropped;
    
    private int imageX = 0;
    private int imageY;
    private int imgindex = 0;
    
    private ArrayList<BufferedImage> changebody = new ArrayList<>();
    
    private BufferedImage secondBufferimg;
    private Graphics2D secondBufferG2d;

    public PlayerImage() {
        try {
            head_image = ImageIO.read(new File("assets/maidlilpa/lilpaface_sp.png"));
            body_image = ImageIO.read(new File("assets/maidlilpa/lilpadown_sp_strip10.png"));
            
            if(changebody.isEmpty()) cropBodyImage();
            
            combineImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 자른 body_image 부분을 image에 저장하고 반환합니다.
    public BufferedImage getImage() {
        return image;
    }

    // 더블 버퍼링을 사용하여 이미지를 그리고 화면에 출력합니다.
    public void paint(Graphics g) {
        if (secondBufferimg == null) {
            secondBufferimg = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
            secondBufferG2d = secondBufferimg.createGraphics();
        }
        update(secondBufferG2d);
        g.drawImage(secondBufferimg, 0, 0, null);
    }

    public void update(Graphics g) {
        secondBufferimg = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        secondBufferG2d = secondBufferimg.createGraphics();
        secondBufferG2d.drawImage(image, 0, 0, 50, 50, 0, 30, 30, 30, (ImageObserver) this);
    }

    // body_image의 일부를 잘라냅니다.
    private void cropBodyImage() {
        int imgcutX = 0;
        for (int i = 0; i < 9; i++) {
            int width = Math.min(30, body_image.getWidth() - imgcutX);
          
            int height = Math.min(30, body_image.getHeight() - imageY);

            body_cropped = body_image.getSubimage(imgcutX, imageY, width, height);
            changebody.add(body_cropped);
            imgcutX = imgcutX + 32;
        }
    }
    
    public void moveInit() {
        imageX = 0;
        imageY = 0;
        imgindex = 0;
        if(changebody.isEmpty()) cropBodyImage();
        combineImages();
    } 	

    // 아래로 움직일 경우 이미지를 변경합니다.
    public void moveDown() {
        //changebody 리스트에서 이미지를 순환합니다.
        imgindex = (imgindex + 1) % changebody.size();
        combineImages(); // 이동 후 이미지를 다시 합성합니다.
    }

    // 두 이미지를 합성하여 새로운 이미지를 생성합니다.
    private void combineImages() {
        BufferedImage body_cropped = changebody.get(imgindex);
        int combinedWidth = head_image.getWidth();
        int combinedHeight = head_image.getHeight() + body_cropped.getHeight();
        image = new BufferedImage(combinedWidth, combinedHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(head_image, 0, 0, null); // head_image를 (0, 0)에 그립니다.
        g2d.drawImage(body_cropped, 10, 60, null); // body_cropped를 (10, 60 의 높이)에 그립니다.
        g2d.dispose(); // 그래픽 객체를 제거합니다.
        	
        // 이미지 크기를 조정합니다.
        image = scaleImage(image, 130, 190); // 원하는 크기로 변경
    }

    // 이미지 크기를 조정하는 메서드
    private BufferedImage scaleImage(BufferedImage originalImage, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();
        return scaledImage;
    }

    
}

  ```
 </details>
  
  

### 플레이어 공격 구현
공격 : 키보드 방향키를 눌러 해당 누른 방향으로 탄막을 발사하여 적에게 공격을 가함.
  우선 플레이어는 해당 문장을 통해 공격에 사용할 투사체를 가져옵니다.
  ```java
  Graphics2D g2d = (Graphics2D) g;
        for (Projectile projectile : projectiles) {
            if (projectile.isActive()) {
                projectile.draw(g2d);
            }
        }
```
투사체를 설정한 코드에 따라서, 투사체를 저장해 놓은 ArrayList를 만들어 주고 플레이어가 원하는 방향으로 투사체의 위치를 변화시키는 메서드를 만들어 줍니다.
```java
   private void shootProjectile(double directionX, double directionY) {
        projectiles.add(new Projectile(x + SIZE / 2, y + SIZE / 2, directionX, directionY, speed, projectileSize));
    }
```
  이후 화살표를 할당해 놓은 코드에 해당 메서드를 호출하게끔 하여 공격 기능을 구현했습니다.
  ```java
   // 화살표 키 액션 (투사체-Projectile 발사)
        actionMap.put("upkeyPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upkeyPressed = true;
                shootProjectile(0, -1); // 위쪽으로 발사
                
            }
        });
  ```
  

  ## 2. 적
  *** WeakEnemy.java 파일에 구현 ***
  구현해야 할 기능
* 플레이어 추적 밑 이동
- 플레이어와 닿으면 플레이어의 체력 감소시킴
-  탄막에 닿으면 적의 체력 감소

 ### 플레이어 추적, 이동 구현
 ```java
    @Override
    public void move() {
    	
    	int newX;
    	int newY;
    
        //지속적으로 플레이어의 데미지 갱신
        PlayerDmg = player.getDamage();
        checkHit();
        checkCollisionWithPlayer();
        
        // x축과 y축에 대해 개별적으로 이동 벡터 계산
        double dx = playerX - x;
        double dy = playerY - y;

        // 이동 벡터의 크기 계산
        double distanceToTarget = Math.sqrt(dx * dx + dy * dy);
        
        // 플레이어와 적이 충돌할 거리 설정
        double collisionDistance = 1;
        
        // 적과 플레이어의 좌표가 일치하면 목표 위치에 도달한 것으로 간주
  if (distanceToTarget <=collisionDistance || ((int) x == (int)playerX && (int) y == (int) playerY)) {
	   newX = playerX;
	    newY = playerY;
	        } else {
	            // x축과 y축에 대해 이동 거리 계산
	            double xMoveAmount = MOVE_AMOUNT * (dx / distanceToTarget);
	            double yMoveAmount = MOVE_AMOUNT * (dy / distanceToTarget);
	            
	            newX = (int)(x + xMoveAmount);
	            newY = (int)(y + yMoveAmount);
	            
	            //벽에 닿지 않을 경우 위치 변환
	            if (!collisionDetector.checkCollision(new Rectangle(newX, newY, SIZE, SIZE))) {
	                x = newX;
	                y = newY;
	                
	    // x, y 좌표가 각각 플레이어 위치를 넘어가지 않도록 조정
	    if (Math.abs(playerX - x) <Math.abs(xMoveAmount)){
	                    x = playerX;
	                }
	    if (Math.abs(playerY - y) <Math.abs(yMoveAmount)) {
	                    y = playerY;
	                }
	            }
	        }  // 히트박스 업데이트
        Enemyhitbox.setLocation((int) x, (int) y);

        // 플레이어와 충돌 감지 및 체력 감소 처리
        checkCollisionWithPlayer();
        
        repaint();
    }

                                                          
```checkHit();``` ```checkCollisionWithPlayer();``` 를 호출하여 적의 피격, 공격을 체크합니다. 이후 플레이어의 좌표를 받아와 playerX, playerY에 저장하고 현재 적의 위치의 좌표와 비교하여 이동 거리 벡터를 계산합니다. 이후 벡터의 평행이동을 통해 플레이어에게 닿을 때까지 지속적으로 이동합니다.
                                                                      이후 플레이어의 이미지를 불러오는 방식과 똑같이 이미지를 불러와 적에게 할당합니다.

## 3. 게임의 창 구현 
   ![](https://velog.velcdn.com/images/caffeinelil/post/8aa8785e-0d11-45f0-bfcb-9b16a9a683ea/image.png)
해당 형식으로 게임의 요소들을 구성합니다.
즉, JFrame창은 가만히 놔두고 여러개의 JPanel을 배치하는 형식입니다.
                                                          이제 앞서 만든 각종 기능들의 패널을 add() 메서드를 통해 패널에 패널을 더함으로써 스테이지를 구현합니다.

또한 플레이어와 적의 움직임을 제한하기 위해 (즉, 화면 밖으로 나가지 않게 하기 위해) 투명색의 사각형을 윤곽선에 배치하여 벽을 만들어줍니다.
```java                                                 
 private void initializeWalls() {
        // 벽 좌표 및 크기 추가
collisionDetector.addWall(new Rectangle(10, 0, 1000, 50)); //위쪽 벽
collisionDetector.addWall(new Rectangle(80, 520, 1000, 50)); //아래쪽 벽
collisionDetector.addWall(new Rectangle(-20, 20, 50, 600)); // 왼쪽 벽 
collisionDetector.addWall(new Rectangle(1005, 20, 50, 600)); 
  }
```
 
벽 충돌 감지 클래스
```java
package utils;

import java.awt.Rectangle;
import java.util.ArrayList;

import abstracts.abstractHitbox;
import player.Projectile;


//벽 충돌 감지
public class CollisionDetector {

    private ArrayList<Rectangle> walls;

    public CollisionDetector() {
        walls = new ArrayList<>();
    }

    public void addWall(Rectangle wall) {
        walls.add(wall);
    }

    public boolean checkCollision(Rectangle Hitbox) {
        for (Rectangle wall : walls) {
            if (Hitbox.intersects(wall)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkProjectileCollision(Projectile projectile, abstractHitbox targetHitbox) {
        return projectile.getHitbox().intersects(targetHitbox.toRectangle());
    }
}
 ```
 
  스테이지 구현 전체 코드
   ```java
  package stage;

import javax.swing.*;
import player.Player;
import Enemy.WeakEnemy;
import utils.CollisionDetector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class stage_1 extends JPanel {
    private BufferedImage backgroundImage;
    private CollisionDetector collisionDetector;  // 충돌 감지기 추가
    private Player player;
    private int playerX, playerY;
    private double enemy1_hp;
    private boolean EnemyAlive = true;
    Timer timer;

    ArrayList<WeakEnemy> Enemy = new ArrayList<>();

    public stage_1() {
        // 이미지 로드
        Map_bg mapBg = new Map_bg();
        backgroundImage = mapBg.getImage();
        
        // 충돌 감지기 초기화 및 벽 추가
        collisionDetector = new CollisionDetector();
        initializeWalls();

        player = new Player();
        playerX = player.getPlayerX();
        playerY = player.getPlayerY();
        
        // 플레이어에 충돌 감지기 설정
        player.setCollisionDetector(collisionDetector);
        
        add(player);

        
        WeakEnemy enemy_1 = new WeakEnemy(playerX, playerY);
        enemy_1.setCollisionDetector(collisionDetector); // 적에게도 충돌 감지기 설정
        enemy1_hp = enemy_1.getenHp();
        add(enemy_1);

      
        // 타이머 설정 (적의 이동을 주기적으로 업데이트하기 위해)
        timer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.player_isalive() && EnemyAlive == true) {
                    enemy_1.setPlayerPosition(player.getPlayerX(), player.getPlayerY());
                    enemy_1.move();
                    enemy_1.repaint();
                } else {
                    // 플레이어가 죽으면 타이머 멈추기
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    private void initializeWalls() {
        // 벽 좌표 및 크기 추가
        collisionDetector.addWall(new Rectangle(10, 0, 1000, 50)); //위쪽 벽
        collisionDetector.addWall(new Rectangle(80, 520, 1000, 50)); //아래쪽 벽
        collisionDetector.addWall(new Rectangle(-20, 20, 50, 600)); // 왼쪽 벽 
        collisionDetector.addWall(new Rectangle(1005, 20, 50, 600)); //오른쪽 벽
    }

    // 투명한 벽을 그리는 메서드 추가
    private void drawWalls(Graphics2D g2d) {
        g2d.setColor(new Color(0, 0, 0, 0)); // 투명한 색상으로 나중에 바꾸기
        g2d.fillRect(10, 0, 1000, 50);
        g2d.fillRect(80, 520, 1000, 50);
        g2d.fillRect(-20, 20, 50, 600);
        g2d.fillRect(1005, 20, 50, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // 배경 이미지 그리기
        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        // 투명한 벽 그리기
        drawWalls(g2d);

        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        // JPanel의 크기를 JFrame과 동일하게 반환합니다.
        return new Dimension(1100, 700);
    }
}

  ```
  이렇게 다양한 Panel을 더한 Panel을 만들어 게임 진행 스테이지를 만들어줍니다. 
  이후 JFrame을 만드는 클래스에서 
  ```java 
  hp = (int)player.getHp(); // 플레이어의 HP만큼 생명력 표시
        UI ui = new UI(hp);
        
        if(!PlayerAlive) {
        	System.out.println("Player dead");
        	System.exit(1); //플레이어 사망 시 게임 종료
        }
        
        add(ui, BorderLayout.NORTH);

  ```
  를 통해 플레이어 사망 로직을 추가하여 현재 플레이어의 체력만큼 하트 개수를 만들어 JFrame에 추가합니다.
  
  개선하면 좋을 점:
  - 적의 추적 로직 개선, 아직 움직임이 부자연스러움
  - 플레이어가 사용하는 투사체에 이미지 삽입해야하는데, 이미지가 잘 안불러와짐. 
 - 공격할때, 이동할때 렉이 걸림. 어딘가 로직에 시간복잡도를 많이 잡아는 로직이 있는거 같음. 아직 못찾음. 다시 확인해봐야함 
  
 
  
# 전체 코드는 제 Github에 올려놓았습니다.
## https://github.com/CaffeineLIL/JavaGUIgame
  
