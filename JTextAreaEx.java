import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class JTextAreaEx extends JFrame {
	private JTextArea textDisplay;
	private JPanel pSouth;
	private JTextField textInput;
	private JButton btnSend;
	
	JTextAreaEx() {
		setTitle("JTextAreaEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(new JScrollPane(getTextDisplay()), 
				BorderLayout.CENTER);
		getContentPane().add(getPSouth(), BorderLayout.SOUTH);
		setSize(300, 200);
	}
	
	public JTextArea getTextDisplay() {
		if (textDisplay == null) {
			textDisplay = new JTextArea();
			textDisplay.setEditable(false);
		}
		return textDisplay;
	}
	
	public JPanel getPSouth() {
		if (pSouth == null) {
			pSouth = new JPanel();
			pSouth.setLayout(new BorderLayout());
			pSouth.add(getTextInput(), BorderLayout.CENTER);
			pSouth.add(getBtnSend(), BorderLayout.EAST);
		}
		return pSouth;
	}
	
	public JTextField getTextInput() {
		if (textInput == null) textInput = new JTextField();
		
		return textInput;
	}
	
	public JButton getBtnSend() {
		if (btnSend == null) {
			btnSend = new JButton();
			btnSend.setText("Àü¼Û");
			btnSend.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getTextDisplay().append(getTextInput().getText() + "\n");
					getTextInput().setText("");
				}
			});
		}
		return btnSend;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JTextAreaEx frame = new JTextAreaEx();
				frame.setVisible(true);
			}			
		});
	}

}
