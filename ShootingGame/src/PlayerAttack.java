import java.awt.*;

import javax.swing.*;

public class PlayerAttack {

	Image image = new ImageIcon("player_attack.png").getImage();
	
	// ������ �̹���, ��ġ, ���ݷ� � ���� ���� 
	int x, y;
	int width = image.getWidth(null); // ������ �浹 ������ ���� �̹��� �ʺ�� ���� 
	int height = image.getHeight(null);
	int attack = 5;
	
	public PlayerAttack(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void fire() { // �߻� �޼ҵ�, ������ ���������� �����Ƿ� x�� ����
		this.x += 15;
	}
	
}
