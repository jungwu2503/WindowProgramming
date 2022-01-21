import java.awt.*;
import java.util.*;

import javax.swing.*;

public class Game extends Thread {
	private int delay = 20;
	private long pretime;
	private int cnt; // �̺�Ʈ �߻� �ֱ⸦ ��Ʈ��
	
	private Image player = new ImageIcon("player.png").getImage();
	
	private int playerX, playerY;
	private int playerWidth = player.getWidth(null);
	private int playerHeight = player.getHeight(null);
	private int playerSpeed = 10;
	private int playerHP = 30;
	
	private boolean up, down, left, right, shooting;
	
	ArrayList<PlayerAttack> playerAttackList = new ArrayList<PlayerAttack>(); //������ ���� ����Ʈ
	private PlayerAttack playerAttack; // ArrayList���� ���뿡 ���� �����Ҽ� �ְ��ϴ� ����
	
	@Override
	public void run() {
		// ��ġ �ʱ�ȭ
		cnt = 0;
		playerX = 10;
		playerY = (Main.SCREEN_HEIGHT - playerHeight) / 2;
		
		while (true) {
			//cnt�� delay �и��ʰ� ���������� ����
			pretime = System.currentTimeMillis();
			if (System.currentTimeMillis() - pretime < delay) { // ���� ��Ȯ�� �ֱ⸦ ���� ����ð� - cnt�����ϱ� �� �ð� < delay�� ��� �� ���̸�ŭ Thread�� sleep
				try {
					Thread.sleep(delay - System.currentTimeMillis() + pretime);
					keyProcess();
					playerAttackProcess();
					cnt++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
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
		}
	}
	
	public void gameDraw(Graphics g) { // ���Ӿ��� ��ҵ��� �׷��� �޼ҵ�
		playerDraw(g);
	}
	
	public void playerDraw(Graphics g) { // player�� ���� ��Ҹ� �׸� �޼ҵ�
		g.drawImage(player, playerX, playerY, null);
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			g.drawImage(playerAttack.image, playerAttack.x, playerAttack.y, null);
		}
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
