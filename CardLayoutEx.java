import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.*;

public class CardLayoutEx extends JFrame {
	private JPanel redCard, greenCard, blueCard;
	
	public CardLayoutEx() {
		setTitle("CardLayout");
		setSize(400, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(new CardLayout());
		getContentPane().add("RedCard", getRedCard());
		getContentPane().add("GreenCard", getGreenCard());
		getContentPane().add("BlueCard", getBlueCard());
	}
	
	public JPanel getRedCard() {
		if (redCard == null) {
			redCard = new JPanel();
			redCard.setBackground(Color.red);
		}
		return redCard;
	}
	
	public JPanel getGreenCard() {
		if (greenCard == null) {
			greenCard = new JPanel();
			greenCard.setBackground(Color.green);
		}
		return greenCard;
	}
	
	public JPanel getBlueCard() {
		if (blueCard == null) {
			blueCard = new JPanel();
			blueCard.setBackground(Color.blue);
		}
		return blueCard;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				final CardLayoutEx frame = new CardLayoutEx();
				frame.setVisible(true);
				Thread thread = new Thread() {
					@Override
					public void run() {
						for (int i = 0; i < 10; i++) {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								
							}
							
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									CardLayout cardLayout = (CardLayout)frame.getContentPane().getLayout();
									cardLayout.next(frame.getContentPane());
								}
							});
						}
					}
				};
				thread.start();
			}			
		});
	}
}
