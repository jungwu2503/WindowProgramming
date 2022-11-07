import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame {

	JButton newWindowBtn;
	JLabel imgLabel;
	ImageIcon imageIcon;
	ImageIcon frameIcon;
	LineBorder lb;
	
	MainFrame() {
		//imgLabel = new JLabel(new Icon("../img/suldenlion.jpeg", 30));
		//image = Toolkit.getDefaultToolkit().getImage("suldenlion.jpeg");
		imageIcon = new ImageIcon(MainFrame.class.getResource("img/GUIsuldenlion.png"));
		frameIcon = new ImageIcon(MainFrame.class.getResource("img/SulIcon.png"));
		 
		imgLabel = new JLabel(imageIcon);
		imgLabel.setBounds(70, 60, 350, 200);
		//imgLabel.setPreferredSize(new Dimension(100, 100));
		//imgLabel.setLocation(50, 50);
		
		newWindowBtn = new JButton("게임 시작");
		//newWindowBtn.setSize(200, 150);
		newWindowBtn.setBounds(140, 290, 200, 80);
		newWindowBtn.setFont(new Font(null, Font.BOLD, 30));
		newWindowBtn.setFocusable(false);
		
		lb = new LineBorder(Color.blue, 3);
		newWindowBtn.setBorder(lb);
		
		//add(imgLabel);
		getContentPane().add(imgLabel);
		add(newWindowBtn);
				
		setTitle("SuldenLion의 Freestyle GUI 게임");
		setIconImage(frameIcon.getImage());
		getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setLayout(null);
		//setLayout(new BorderLayout());
		//add(newWindowBtn, BorderLayout.CENTER);
		setVisible(true);
	}
	
}
