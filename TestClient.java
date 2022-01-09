import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

class ButtonPanel extends JPanel
{
	MainPanel clientWnd;
	JTextField ip;
	JButton connectButton;
	JButton closeButton;
	ButtonPanel(MainPanel clientWnd) {
		this.clientWnd = clientWnd;

		ip = new JTextField(15);
		connectButton = new JButton("Connect");
		closeButton = new JButton("Close");

		ip.setText("localhost");

		add(ip);
		add(connectButton);
		add(closeButton);

		connectButton.addActionListener(clientWnd);
		closeButton.addActionListener(clientWnd);
	}
	String getIpAddress() {
		return ip.getText();
	}
}
class InputListener extends KeyAdapter
{
	MainPanel clientWnd;
	JTextField textInput;
	JLabel msgLabel;
	int x, y;
	
	InputListener(JTextField textInput,MainPanel clientWnd, JLabel msgLabel) {
		this.clientWnd = clientWnd;
		this.textInput = textInput;
		this.msgLabel = msgLabel;
		x = 300;
		y = 400;
	}
	public void keyPressed(KeyEvent ev) {
		int keyCode = ev.getKeyCode();
		if (keyCode == KeyEvent.VK_ENTER)
		{
			String msg = textInput.getText().trim();
			clientWnd.writeText("[I say] " + msg);
			clientWnd.sendLine(msg);
			textInput.setText("");
			msgLabel.setBounds(x, y, 50, 50);
			clientWnd.add(msgLabel);
			y -= 50;
		}
	}
}
class ReportPanel extends JPanel
{
	MainPanel clientWnd;
	JTextField reportText;
	JButton reportButton;
	JButton queryButton;
	ReportPanel(MainPanel clientWnd) {
		this.clientWnd = clientWnd;

		reportText = new JTextField(15);
		reportButton = new JButton("Report");
		queryButton = new JButton("Query");

		add(reportText);
		add(reportButton);
		add(queryButton);

		reportButton.addActionListener(clientWnd);
		queryButton.addActionListener(clientWnd);
	}
	String getReportText() {
		return reportText.getText();
	}
}
class InputPanel extends JPanel
{
	MainPanel clientWnd;
	JTextField textInput;
	ReportPanel reportPanel;
	JLabel msgLabel;
	
	InputPanel(MainPanel clientWnd) {
		this.clientWnd = clientWnd;
		setLayout(new GridLayout(2,1));

		textInput = new JTextField();
		msgLabel = new JLabel();
		textInput.addKeyListener(new InputListener(textInput,clientWnd,msgLabel));
		reportPanel = new ReportPanel(clientWnd);

		add(textInput);
		add(reportPanel);
		//add(msgLabel);
	}
	String getReportText() {
		return reportPanel.getReportText();
	}
}
class MainPanel extends JPanel implements ActionListener
{
	String ipAddress = "localhosr";
	Socket chatClient;
	BufferedReader fromChatServer;
	PrintWriter toChatServer;
	Thread chatThread;

	ButtonPanel buttonPanel;
	InputPanel inputPanel;
	JTextArea textBox;
	JScrollPane textPane;
	
	JLabel sendMsgLabel;
	
	MainPanel() {
		buttonPanel = new ButtonPanel(this);
		setLayout(new BorderLayout());
		//setLayout(null);

		//textBox = new JTextArea();
		textPane = new JScrollPane(textBox);
		//textBox.setBorder(BorderFactory.createLoweredBevelBorder());
		inputPanel = new InputPanel(this);
		sendMsgLabel = new JLabel();
		sendMsgLabel.setBounds(200, 300, 50, 50);

		add(buttonPanel,BorderLayout.NORTH);
		add(textPane,BorderLayout.CENTER);
		add(inputPanel,BorderLayout.SOUTH);
		add(sendMsgLabel, BorderLayout.LINE_END);
	}
	public void writeText(String msg) {
		//textBox.append(msg + "\r\n");
		sendMsgLabel.setText(msg + "\r\n");
	}
	public void sendLine(String msg) {
		toChatServer.println(msg);
		toChatServer.flush();
	}
	public void actionPerformed(ActionEvent ev) {
		String cmd = ev.getActionCommand();
		String ipAddress = buttonPanel.getIpAddress();
		if (cmd.equals("Report"))
		{
			try
			{
				Socket client = new Socket(ipAddress,7000);
				writeText("Reporting...");
				BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter toServer = new PrintWriter(client.getOutputStream());

				toServer.println("1");
				toServer.flush();

				String s = inputPanel.getReportText();
				toServer.println(s);
				toServer.flush();

				s = fromServer.readLine();
				writeText("[Server says] " + s);
			}
			catch (IOException ex)
			{
				System.out.println(ex);
			}
		} else if (cmd.equals("Query"))
		{
			try
			{
				Socket client = new Socket(ipAddress,7000);
				writeText("Asking...");
				BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter toServer = new PrintWriter(client.getOutputStream());

				toServer.println("2");
				toServer.flush();

				String s = fromServer.readLine();
				writeText("[Server answered] " + s + " Won.");
			}
			catch (IOException ex)
			{
				System.out.println(ex);
			}
		} else if (cmd.equals("Connect"))
		{
			try
			{
				chatClient = new Socket(ipAddress,7000);
				writeText("Connected...");
				fromChatServer = new BufferedReader(new InputStreamReader(chatClient.getInputStream()));
				toChatServer = new PrintWriter(chatClient.getOutputStream());

				toChatServer.println("0");
				toChatServer.flush();

				chatThread = new Thread() {
					public void run() {
						try
						{
							String msg;
							while(true) {
								msg = fromChatServer.readLine();
								writeText("[Server says] " + msg);
							}
						}
						catch (IOException ex)
						{
							System.out.println(ex);
						}
					}
				};
				chatThread.start();
			}
			catch (IOException ex)
			{
				System.out.println(ex);
				chatThread.stop();
			}
		} else if (cmd.equals("Close"))
		{
			try
			{
				fromChatServer.close();
				toChatServer.close();
				chatClient.close();
				chatThread.stop();
			}
			catch (IOException ex)
			{
				System.out.println(ex);
			}
		}
	}
}
class ClientFrame extends JFrame
{
	ClientFrame() {
		super("Client");
		setSize(400,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.add(new MainPanel());
	}
}
class TestClient 
{
	public static void main(String[] args) 
	{
		ClientFrame clientFrame = new ClientFrame();
		clientFrame.setVisible(true);
	}
}