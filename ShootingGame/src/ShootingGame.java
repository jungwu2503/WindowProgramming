import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class ShootingGame extends JFrame {

	private Image bufferImage;
	private Graphics screenGraphic;
	
	private Image mainScreen = new ImageIcon("main_screen.png").getImage(); 
	private Image loadingScreen = new ImageIcon("loading_screen.png").getImage();
	private Image gameScreen = new ImageIcon("game_screen.png").getImage();
	
	private boolean isMainScreen, isLoadingScreen, isGameScreen;
	
	private Game game = new Game(); // Game Ŭ������ ��ü �߰�
	
	//private Audio backgroundMusic;
	
	public ShootingGame() {
		setTitle("Shooting Game");
		setUndecorated(true);
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		
		init();
	}
	
	private void init() { // �ʱ�ȭ�� ���� init�޼ҵ�, isMainScreen�� true
		isMainScreen = true; 
		isLoadingScreen = false;
		isGameScreen = false;
		
		//backgroundMusic = new Audio("zerg_3.mp3", true);
		//backgroundMusic.start();
		
		addKeyListener(new KeyListener()); // ���� KeyListener �߰�
	}
	
	private void gameStart() {
		isMainScreen = false;
		isLoadingScreen = true;
		
		Timer loadingTimer = new Timer();
		TimerTask loadingTask = new TimerTask() {
			@Override
			public void run() {
				//backgroundMusic.stop(); // ����ȭ������ �Ѿ���� ������� ���� �ߴ�
				isLoadingScreen = false;
				isGameScreen = true;
				game.start();
			}
		};
		
		loadingTimer.schedule(loadingTask, 3000); // ���� Timer�� TimerTask�� �̿��� �ε�ȭ�鿡�� 3�� �� ����ȭ������ �Ѿ�� ��
		
		//game.start(); // ������� GameŬ������ �����带 ����
	}
	
	public void paint(Graphics g) { // ���� �̹����� ����� �̸� ȭ�鿡 �ѷ������ν� �������� �ּ�ȭ�� 
		bufferImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = bufferImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(bufferImage, 0, 0, null);		
	}
	
	public void screenDraw(Graphics g) { //�ʿ��� ��� �׸���
		if (isMainScreen) {
			g.drawImage(mainScreen, 0, 0, null);
		}
		if (isLoadingScreen) {
			g.drawImage(loadingScreen, 0, 0, null);
		}
		if (isGameScreen) {
			g.drawImage(gameScreen, 0, 0, null);
			game.gameDraw(g); 
		}
		repaint();		
	}
	
	class KeyListener extends KeyAdapter { // Esc�� ������ ������ �� �ִ� ��� �߰�, Ű �������� �޾��� KeyListener Class
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_W:
					game.setUp(true);
					break;
				case KeyEvent.VK_S:
					game.setDown(true);
					break;
				case KeyEvent.VK_A:
					game.setLeft(true);
					break;
				case KeyEvent.VK_D:
					game.setRight(true);
					break;
				case KeyEvent.VK_R:
					if (game.isOver()) game.reset();
					break;
				case KeyEvent.VK_SPACE:
					game.setShooting(true);
					break;
				case KeyEvent.VK_ENTER: // Enter������ �ε�, ����ȭ�� �Ѿ�� �ϱ�
					if (isMainScreen) {
						gameStart();
					}
					break;
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;
			}
		}
		
		public void keyReleased(KeyEvent e) { // Ű�� ��� false
			switch(e.getKeyCode()) {
				case KeyEvent.VK_W:
					game.setUp(false);
					break;
				case KeyEvent.VK_S:
					game.setDown(false);
					break;
				case KeyEvent.VK_A:
					game.setLeft(false);
					break;
				case KeyEvent.VK_D:
					game.setRight(false);
					break;
				case KeyEvent.VK_SPACE:
					game.setShooting(false);
					break;
			}
		}
	}
	
}
