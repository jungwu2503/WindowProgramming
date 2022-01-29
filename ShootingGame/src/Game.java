import java.awt.*;
import java.util.*;

import javax.swing.*;

public class Game extends Thread {
	private int delay = 20;
	private long pretime;
	private int cnt; // �̺�Ʈ �߻� �ֱ⸦ ��Ʈ��
	private int score;
	
	private Image player = new ImageIcon("player.png").getImage();
	
	private int playerX, playerY;
	private int playerWidth = player.getWidth(null);
	private int playerHeight = player.getHeight(null);
	private int playerSpeed = 10;
	private int playerHP = 30;
	
	private boolean up, down, left, right, shooting;
	private boolean isOver; // ���ӿ��� ����
	
	private ArrayList<PlayerAttack> playerAttackList = new ArrayList<PlayerAttack>(); //������ ���� ����Ʈ
	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>(); // Enemy, EnemyAttack�� ���� ArrayList
	private ArrayList<EnemyAttack> enemyAttackList = new ArrayList<EnemyAttack>();
	
	private PlayerAttack playerAttack; // ArrayList���� ���뿡 ���� �����Ҽ� �ְ��ϴ� ����
	private Enemy enemy;
	private EnemyAttack enemyAttack;
	
	//private Audio backgroundMusic;
	private Audio hitSound;
	
	@Override
	public void run() {		
		//backgroundMusic = new Audio("zerg_2.mp3", true); //mp3 �����̶� UnsupportedAudioFileException�ɸ�;
		hitSound = new Audio("HKMISSLE.WAV", false);
		
		reset();
		
		while (true) {
			while(!isOver) {
				//cnt�� delay �и��ʰ� ���������� ����
				pretime = System.currentTimeMillis();
				if (System.currentTimeMillis() - pretime < delay) { // ���� ��Ȯ�� �ֱ⸦ ���� ����ð� - cnt�����ϱ� �� �ð� < delay�� ��� �� ���̸�ŭ Thread�� sleep
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

	public void reset() { // �ٽ��ϱ� ���, ���� ���� �ʱ�ȭ ���� �޼ҵ�
		isOver = false;
		// ��ġ �ʱ�ȭ
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
		if (up && playerY - playerSpeed > 0) playerY -= playerSpeed; //ȭ�鿡�� �ȳ����� ������ playerX,Y�� ����
		if (down && playerY + playerHeight + playerSpeed < Main.SCREEN_HEIGHT) playerY += playerSpeed;
		if (left && playerX - playerSpeed > 0) playerX -= playerSpeed;
		if (right && playerX + playerWidth + playerSpeed < Main.SCREEN_WIDTH) playerX += playerSpeed; 
		if (shooting && cnt % 15 == 0) {
			playerAttack = new PlayerAttack(playerX + 222, playerY + 25); // �÷��̾�� ������ ������ ��ġ�� ������ ����� ����Ʈ�� �־���
			playerAttackList.add(playerAttack);
		}
	}
	
	private void playerAttackProcess() { // ���� ó�� �޼ҵ�
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			playerAttack.fire();
			
			// �浹����
			for (int j = 0; j < enemyList.size(); j++) { // player�� ���� �̹����� enemy �̹����� �����ִ� �κ��� �ִ��� �˻����ָ� ��
				enemy = enemyList.get(j);
				if (playerAttack.x > enemy.x && playerAttack.x < enemy.x + enemy.width && playerAttack.y > enemy.y && playerAttack.y < enemy.y + enemy.height) {
					hitSound.start();
					enemy.hp -= playerAttack.attack; // ��ģ �κ��� ������ ���� hp�� ���̰� �ش� ���� ����
					playerAttackList.remove(playerAttack);
				}
				if (enemy.hp <= 0) { // enemy hp�� 0���϶�� ����
					enemyList.remove(enemy);
					score += 1000;
				}
			}
		}
	}
	
	// �ֱ������� ���� ������Ű�� �޼ҵ�
	private void enemyAppearProcess() {
		if (cnt % 80 == 0) {
			enemy = new Enemy(1120, (int)(Math.random()*621)); //ȭ�� ������ ������ ��ġ�� ������Ű�� ���� y���� 1~620 �������� ������ ��
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
			enemyAttack = enemyAttackList.get(i); // ArrayList�� ��� ���� �ϳ��ϳ��� ������ fire �޼ҵ� ȣ��
			enemyAttack.fire();
			
			if (enemyAttack.x > playerX && enemyAttack.x < playerX + playerWidth && enemyAttack.y > playerY && enemyAttack.y < playerY + playerHeight) {	
				playerHP -= enemyAttack.attack;
				enemyAttackList.remove(enemyAttack);
				hitSound.start();
				if (playerHP <= 0) isOver = true;
			}
		}
		
	}
	
	public void gameDraw(Graphics g) { // ���Ӿ��� ��ҵ��� �׷��� �޼ҵ�
		playerDraw(g);
		enemyDraw(g);
		infoDraw(g);
	}
	
	public void infoDraw(Graphics g) { // ���� ���� ���� �׷��ִ� �޼ҵ�
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString("SCORE : " + score, 40, 80);
		if (isOver) { // ���� ����� �� ���ִٴ� �ȳ���
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.BOLD, 80));
			g.drawString("Press R to restart", 295, 380);
		}
	}
	
	public void playerDraw(Graphics g) { // player�� ���� ��Ҹ� �׸� �޼ҵ�
		g.drawImage(player, playerX, playerY, null);
		g.setColor(Color.green); // ü�¹� �׸���
		g.fillRect(playerX - 1, playerY - 40, playerHP * 6, 20); // ü�¹��� �����ŭ�� �ʷϻ� �簢���� �÷��̾�� ���� ���� �׷���
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			g.drawImage(playerAttack.image, playerAttack.x, playerAttack.y, null);
		}
	}
	
	public void enemyDraw(Graphics g) {
		for (int i = 0; i < enemyList.size(); i++) {
			enemy = enemyList.get(i);
			g.drawImage(enemy.image, enemy.x, enemy.y, null);
			g.setColor(Color.green); // ü�¹� �׸���
			g.fillRect(enemy.x + 1, enemy.y - 40, enemy.hp * 15, 20);
		}
		for (int i = 0; i < enemyAttackList.size(); i++) { //���� ���� �׸��� �κе� �߰�
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
