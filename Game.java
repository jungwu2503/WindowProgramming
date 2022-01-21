import java.awt.*;
import java.util.*;

import javax.swing.*;

public class Game extends Thread {
	private int delay = 20;
	private long pretime;
	private int cnt; // 이벤트 발생 주기를 컨트롤
	
	private Image player = new ImageIcon("player.png").getImage();
	
	private int playerX, playerY;
	private int playerWidth = player.getWidth(null);
	private int playerHeight = player.getHeight(null);
	private int playerSpeed = 10;
	private int playerHP = 30;
	
	private boolean up, down, left, right, shooting;
	
	ArrayList<PlayerAttack> playerAttackList = new ArrayList<PlayerAttack>(); //공격을 담은 리스트
	private PlayerAttack playerAttack; // ArrayList안의 내용에 쉽게 접근할수 있게하는 변수
	
	@Override
	public void run() {
		// 위치 초기화
		cnt = 0;
		playerX = 10;
		playerY = (Main.SCREEN_HEIGHT - playerHeight) / 2;
		
		while (true) {
			//cnt를 delay 밀리초가 지날떄마다 증가
			pretime = System.currentTimeMillis();
			if (System.currentTimeMillis() - pretime < delay) { // 좀더 정확한 주기를 위해 현재시간 - cnt증가하기 전 시간 < delay일 경우 그 차이만큼 Thread에 sleep
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
		}
	}
	
	public void gameDraw(Graphics g) { // 게임안의 요소들을 그려줄 메소드
		playerDraw(g);
	}
	
	public void playerDraw(Graphics g) { // player에 관한 요소를 그릴 메소드
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
