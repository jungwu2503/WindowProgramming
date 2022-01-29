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
	
	private Game game = new Game(); // Game 클래스의 객체 추가
	
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
	
	private void init() { // 초기화를 해줄 init메소드, isMainScreen만 true
		isMainScreen = true; 
		isLoadingScreen = false;
		isGameScreen = false;
		
		//backgroundMusic = new Audio("zerg_3.mp3", true);
		//backgroundMusic.start();
		
		addKeyListener(new KeyListener()); // 만든 KeyListener 추가
	}
	
	private void gameStart() {
		isMainScreen = false;
		isLoadingScreen = true;
		
		Timer loadingTimer = new Timer();
		TimerTask loadingTask = new TimerTask() {
			@Override
			public void run() {
				//backgroundMusic.stop(); // 게임화면으로 넘어갈때는 재생중인 파일 중단
				isLoadingScreen = false;
				isGameScreen = true;
				game.start();
			}
		};
		
		loadingTimer.schedule(loadingTask, 3000); // 만든 Timer와 TimerTask를 이용해 로딩화면에서 3초 후 게임화면으로 넘어가게 함
		
		//game.start(); // 만들어진 Game클래스의 쓰레드를 시작
	}
	
	public void paint(Graphics g) { // 버퍼 이미지를 만들고 이를 화면에 뿌려줌으로써 깜빡임을 최소화함 
		bufferImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = bufferImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(bufferImage, 0, 0, null);		
	}
	
	public void screenDraw(Graphics g) { //필요한 요소 그리기
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
	
	class KeyListener extends KeyAdapter { // Esc를 누르면 종료할 수 있는 기능 추가, 키 움직임을 받아줄 KeyListener Class
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
				case KeyEvent.VK_ENTER: // Enter누르면 로딩, 게임화면 넘어가게 하기
					if (isMainScreen) {
						gameStart();
					}
					break;
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;
			}
		}
		
		public void keyReleased(KeyEvent e) { // 키를 뗴면 false
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
