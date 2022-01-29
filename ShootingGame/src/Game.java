import java.awt.*;
import java.util.*;

import javax.swing.*;

public class Game extends Thread {
	private int delay = 20;
	private long pretime;
	private int cnt; // 이벤트 발생 주기를 컨트롤
	private int score;
	
	private Image player = new ImageIcon("player.png").getImage();
	
	private int playerX, playerY;
	private int playerWidth = player.getWidth(null);
	private int playerHeight = player.getHeight(null);
	private int playerSpeed = 10;
	private int playerHP = 30;
	
	private boolean up, down, left, right, shooting;
	private boolean isOver; // 게임오버 여부
	
	private ArrayList<PlayerAttack> playerAttackList = new ArrayList<PlayerAttack>(); //공격을 담은 리스트
	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>(); // Enemy, EnemyAttack을 담을 ArrayList
	private ArrayList<EnemyAttack> enemyAttackList = new ArrayList<EnemyAttack>();
	
	private PlayerAttack playerAttack; // ArrayList안의 내용에 쉽게 접근할수 있게하는 변수
	private Enemy enemy;
	private EnemyAttack enemyAttack;
	
	//private Audio backgroundMusic;
	private Audio hitSound;
	
	@Override
	public void run() {		
		//backgroundMusic = new Audio("zerg_2.mp3", true); //mp3 파일이라 UnsupportedAudioFileException걸림;
		hitSound = new Audio("HKMISSLE.WAV", false);
		
		reset();
		
		while (true) {
			while(!isOver) {
				//cnt를 delay 밀리초가 지날떄마다 증가
				pretime = System.currentTimeMillis();
				if (System.currentTimeMillis() - pretime < delay) { // 좀더 정확한 주기를 위해 현재시간 - cnt증가하기 전 시간 < delay일 경우 그 차이만큼 Thread에 sleep
					try {
						Thread.sleep(delay - System.currentTimeMillis() + pretime);
						keyProcess();
						playerAttackProcess();
						enemyAppearProcess();
						enemyMoveProcess();
						enemyAttackProcess();
						cnt++;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			try {
				Thread.sleep(100);				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void reset() { // 다시하기 기능, 게임 상태 초기화 해줄 메소드
		isOver = false;
		// 위치 초기화
		cnt = 0;
		score = 0;
		playerX = 10;
		playerY = (Main.SCREEN_HEIGHT - playerHeight) / 2;
		playerHP = 30;
		//backgroundMusic.start();
		
		playerAttackList.clear();
		enemyList.clear();
		enemyAttackList.clear();
	}
	private void keyProcess() {
		if (up && playerY - playerSpeed > 0) playerY -= playerSpeed; //화면에서 안나가는 선에서 playerX,Y값 조정
		if (down && playerY + playerHeight + playerSpeed < Main.SCREEN_HEIGHT) playerY += playerSpeed;
		if (left && playerX - playerSpeed > 0) playerX -= playerSpeed;
		if (right && playerX + playerWidth + playerSpeed < Main.SCREEN_WIDTH) playerX += playerSpeed; 
		if (shooting && cnt % 15 == 0) {
			playerAttack = new PlayerAttack(playerX + 222, playerY + 25); // 플레이어와 적당히 떨어진 위치에 공격을 만들고 리스트에 넣어줌
			playerAttackList.add(playerAttack);
		}
	}
	
	private void playerAttackProcess() { // 공격 처리 메소드
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			playerAttack.fire();
			
			// 충돌판정
			for (int j = 0; j < enemyList.size(); j++) { // player의 공격 이미지가 enemy 이미지와 겹쳐있는 부분이 있는지 검사해주면 됨
				enemy = enemyList.get(j);
				if (playerAttack.x > enemy.x && playerAttack.x < enemy.x + enemy.width && playerAttack.y > enemy.y && playerAttack.y < enemy.y + enemy.height) {
					hitSound.start();
					enemy.hp -= playerAttack.attack; // 겹친 부분이 있으면 적의 hp를 줄이고 해당 공격 삭제
					playerAttackList.remove(playerAttack);
				}
				if (enemy.hp <= 0) { // enemy hp가 0이하라면 제거
					enemyList.remove(enemy);
					score += 1000;
				}
			}
		}
	}
	
	// 주기적으로 적을 출현시키는 메소드
	private void enemyAppearProcess() {
		if (cnt % 80 == 0) {
			enemy = new Enemy(1120, (int)(Math.random()*621)); //화면 끝에서 랜덤한 위치에 출현시키기 위해 y값을 1~620 랜덤으로 나오게 함
			enemyList.add(enemy);
		}
	}
	
	private void enemyMoveProcess() {
		for (int i = 0; i < enemyList.size(); i++) {
			enemy = enemyList.get(i);
			enemy.move();
		}
	}
	
	private void enemyAttackProcess() {
		if (cnt % 50 == 0) {
			enemyAttack = new EnemyAttack(enemy.x - 79, enemy.y + 35);
			enemyAttackList.add(enemyAttack);
		}
		
		for (int i = 0; i < enemyAttackList.size(); i++) {
			enemyAttack = enemyAttackList.get(i); // ArrayList에 담긴 공격 하나하나에 접근해 fire 메소드 호출
			enemyAttack.fire();
			
			if (enemyAttack.x > playerX && enemyAttack.x < playerX + playerWidth && enemyAttack.y > playerY && enemyAttack.y < playerY + playerHeight) {	
				playerHP -= enemyAttack.attack;
				enemyAttackList.remove(enemyAttack);
				hitSound.start();
				if (playerHP <= 0) isOver = true;
			}
		}
		
	}
	
	public void gameDraw(Graphics g) { // 게임안의 요소들을 그려줄 메소드
		playerDraw(g);
		enemyDraw(g);
		infoDraw(g);
	}
	
	public void infoDraw(Graphics g) { // 게임 관련 정보 그려주는 메소드
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString("SCORE : " + score, 40, 80);
		if (isOver) { // 게임 재시작 할 수있다는 안내문
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.BOLD, 80));
			g.drawString("Press R to restart", 295, 380);
		}
	}
	
	public void playerDraw(Graphics g) { // player에 관한 요소를 그릴 메소드
		g.drawImage(player, playerX, playerY, null);
		g.setColor(Color.green); // 체력바 그리기
		g.fillRect(playerX - 1, playerY - 40, playerHP * 6, 20); // 체력바의 배수만큼의 초록색 사각형을 플레이어와 적의 위에 그려줌
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			g.drawImage(playerAttack.image, playerAttack.x, playerAttack.y, null);
		}
	}
	
	public void enemyDraw(Graphics g) {
		for (int i = 0; i < enemyList.size(); i++) {
			enemy = enemyList.get(i);
			g.drawImage(enemy.image, enemy.x, enemy.y, null);
			g.setColor(Color.green); // 체력바 그리기
			g.fillRect(enemy.x + 1, enemy.y - 40, enemy.hp * 15, 20);
		}
		for (int i = 0; i < enemyAttackList.size(); i++) { //적의 공격 그리는 부분도 추가
			enemyAttack = enemyAttackList.get(i);
			g.drawImage(enemyAttack.image, enemyAttack.x, enemyAttack.y, null);
		}
	}
	
	public boolean isOver() {
		return isOver;
	}
	
	public void setUp(boolean up) {
		this.up = up;
	}
	
	public void setDown(boolean down) {
		this.down = down;
	}
	
	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public void setRight(boolean right) {
		this.right = right;
	}
	
	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}
	
}
