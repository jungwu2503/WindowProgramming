import java.awt.*;

import javax.swing.*;

public class PlayerAttack {

	Image image = new ImageIcon("player_attack.png").getImage();
	
	// 공격의 이미지, 위치, 공격력 등에 대한 정보 
	int x, y;
	int width = image.getWidth(null); // 공격의 충돌 판정을 위한 이미지 너비와 높이 
	int height = image.getHeight(null);
	int attack = 5;
	
	public PlayerAttack(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void fire() { // 발사 메소드, 공격은 오른쪽으로 나가므로 x값 증가
		this.x += 15;
	}
	
}
