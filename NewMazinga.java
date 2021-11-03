import java.awt.*;
import javax.swing.*;

public class NewMazinga {

	public static void main(String[] args) {
		
		MyFrame frame = new MyFrame();
		frame.setVisible(true);
		
	}

}

class MyFrame extends JFrame {
	
	MyPanel panel;
//	FistPanel fist;
	
	MyFrame() {
		
		setSize(900, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
/*		JPanel skyPanel = new JPanel();
		skyPanel.setBackground(new Color(0,0,180));
		skyPanel.setBounds(0, 0, 250, 250);
		skyPanel.setLayout(null);
		
		add(skyPanel);
*/		
		panel = new MyPanel();
		setContentPane(panel);
		
//		fist = new FistPanel();
//		setContentPane(fist);
		
	}
	
}

class MyPanel extends JPanel {	
	
	MyPanel() {
		//this.setPreferredSize(new Dimension(200,300));
	}
	
	public void draw(Graphics g) {
		g.drawArc(10, 10, 10, 10, 30, 60);
		g.drawLine(30, 30, 230, 300);
	} 
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(new Color(0,0,0));
		
		Image img = Toolkit.getDefaultToolkit().getImage("ma.png");
		g.drawImage(img, 10, 10, this);		
		
		g.setColor(Color.gray);
		g.drawRect(240, 210, 350, 150);
		g.drawRect(300, 360, 250, 150);
		g.drawRect(280, 510, 300, 140);
		g.drawRect(592, 251, 50, 199);
		
		g.setColor(new Color(0,180,180));
		g.fillRect(302, 362, 247, 147);
		g.fillRect(593, 250, 50, 200);
		g.fillRect(611, 429, 50, 50);
		g.fillRect(285, 650, 130, 150);
		g.fillRect(450, 650, 125, 150);
		
		int[] xpoints = new int[3];
		int[] ypoints = new int[3];
		
		xpoints[0] = 250;
		xpoints[1] = 420;
		xpoints[2] = 420;
		
		ypoints[0] = 220;
		ypoints[1] = 300;
		ypoints[2] = 350;
		
		g.setColor(Color.red);
		g.fillPolygon(xpoints, ypoints, 3);
		xpoints[0] = 580;
		xpoints[1] = 420;
		xpoints[2] = 420;
		g.fillPolygon(xpoints, ypoints, 3);
		
		// �Ӹ���
		g.fillOval(380, 80, 80, 80);
		
		g.setColor(Color.black);
		g.fillOval(380, 80, 80, 50);
		
		g.setColor(new Color(0,180,180));
		g.fillOval(380, 120, 80, 80);
		
		g.setColor(new Color(120,180,180));
		g.fillArc(370, 120, 100, 100, 220, 100);
		g.setColor(new Color(0,180,180));
		g.fillArc(395, 145, 50, 50, 220, 100);
		
		g.setColor(Color.black);
		g.drawLine(410, 195, 395, 215);
		g.drawLine(420, 195, 420, 218);
		g.drawLine(430, 195, 445, 215);
		
		g.setColor(new Color(120,180,180));
		g.fillArc(380, 128, 80, 50, 20, 140);
		g.setColor(new Color(0,180,180));
		g.fillArc(380, 143, 80, 40, 20, 140);
		g.setColor(Color.black);
		g.drawArc(380, 128, 80, 50, 20, 140);
		g.drawArc(380, 143, 80, 40, 20, 140);
		
		xpoints[0] = 410;
		xpoints[1] = 420;
		xpoints[2] = 430;
		
		ypoints[0] = 170;
		ypoints[1] = 155;
		ypoints[2] = 170;
		
		g.drawPolygon(xpoints, ypoints, 3);
		
		g.drawLine(386, 150, 386, 180);
		g.drawLine(407, 152, 407, 180);
		g.drawLine(435, 150, 435, 180);
		g.drawLine(454, 152, 454, 180);
		
		xpoints[0] = 386;
		xpoints[1] = 420;
		xpoints[2] = 405;
		
		ypoints[0] = 150;
		ypoints[1] = 142;
		ypoints[2] = 158;
		g.fillPolygon(xpoints, ypoints, 3);
		
		xpoints[0] = 456;
		xpoints[1] = 420;
		xpoints[2] = 435;
		
		ypoints[0] = 150;
		ypoints[1] = 142;
		ypoints[2] = 158;
		g.fillPolygon(xpoints, ypoints, 3);
		
		g.setColor(Color.red);
		g.fillRect(400, 175, 40, 20);
		g.fillOval(390, 138, 7, 7);
		g.fillOval(417, 133, 7, 7);
		g.fillOval(440, 138, 7, 7);
		g.fillOval(400, 148, 5, 5);
		g.fillOval(435, 148, 5, 5);
		
		g.setColor(Color.yellow);
		g.fillOval(415, 180, 10, 10);
		
		xpoints[0] = 360;
		xpoints[1] = 380;
		xpoints[2] = 380;
		
		ypoints[0] = 160;
		ypoints[1] = 152;
		ypoints[2] = 168;
		g.fillPolygon(xpoints, ypoints, 3);
		
		xpoints[0] = 480;
		xpoints[1] = 460;
		xpoints[2] = 460;
		
		ypoints[0] = 160;
		ypoints[1] = 152;
		ypoints[2] = 168;
		g.fillPolygon(xpoints, ypoints, 3);
		
		g.setColor(new Color(0,180,180));
		g.fillRect(223, 353, 155, 95);
		g.setColor(new Color(150,200,200));
		g.fillArc(390, 440, 80, 140, 0, 180);
		
		g.setColor(Color.gray);

		g.drawRect(610, 430, 50, 50);
		g.drawLine(640, 430, 640, 445);
		g.drawLine(640, 445, 660, 445);
		g.drawLine(610, 450, 660, 450);
		g.drawLine(623, 450, 623, 480);
		g.drawLine(635, 450, 635, 480);
		g.drawLine(648, 450, 648, 480);
		g.drawRect(285, 650, 130, 150);
		g.drawRect(450, 650, 125, 150);
		
		g.drawArc(390, 440, 80, 140, 0, 180);
		Graphics2D g2D = (Graphics2D)g;
		//fist
		g2D.setStroke(new BasicStroke(5));
		g2D.drawLine(150, 300, 150, 400);
		g2D.drawLine(150, 300, 200, 300);
		g2D.drawLine(200, 280, 200, 400);
		g2D.drawLine(200, 280, 250, 280);
		g2D.drawLine(250, 250, 250, 400);
		g2D.drawLine(250, 250, 300, 250);
		g2D.drawLine(300, 250, 300, 400);
		g2D.drawLine(300, 280, 350, 280);
		g2D.drawLine(350, 280, 350, 400);
		g2D.drawLine(150, 400, 350, 400);
		g2D.drawLine(220, 400, 220, 450);
		g2D.drawLine(220, 450, 380, 450);
		g2D.drawLine(350, 350, 380, 350);
		g2D.drawLine(380, 350, 380, 450); 
		
		g.setColor(new Color(0,180,180));
//		g.fillRect(223, 353, 155, 95);
		g.fillRect(153, 303, 45, 95);
		g.fillRect(203, 283, 45, 115);
		g.fillRect(253, 253, 45, 145);
		g.fillRect(303, 283, 45, 115);
		
		g.setColor(Color.cyan);
		g.setFont(new Font("�ü�ü", Font.BOLD, 20));
		g2D.drawString("��¡��", 650, 50);
		g.setColor(Color.green);
		g.setFont(new Font("consolas", Font.ITALIC, 20));
		g2D.drawString("made by", 650, 90);
		g.setColor(Color.magenta);
		g.setFont(new Font("���� ����", Font.ITALIC, 15));
		g2D.drawString("������", 740, 110);
	}
	
}

/*
class FistPanel extends JPanel {
	
	FistPanel() {
		
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.magenta);
		Graphics2D g2D = (Graphics2D)g;
		//fist
		g2D.setStroke(new BasicStroke(5));
		g2D.drawLine(150, 300, 150, 400);
		g2D.drawLine(150, 300, 200, 300);
		g2D.drawLine(200, 280, 200, 400);
		g2D.drawLine(200, 280, 250, 280);
		g2D.drawLine(250, 250, 250, 400);
		g2D.drawLine(250, 250, 300, 250);
		g2D.drawLine(300, 250, 300, 400);
		g2D.drawLine(300, 280, 350, 280);
		g2D.drawLine(350, 280, 350, 400);
		g2D.drawLine(150, 400, 350, 400);
		g2D.drawLine(220, 400, 220, 450);
		g2D.drawLine(220, 450, 380, 450);
		g2D.drawLine(350, 350, 380, 350);
		g2D.drawLine(380, 350, 380, 450);
	}
} */
