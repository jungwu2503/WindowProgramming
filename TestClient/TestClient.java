import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.rmi.*;

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
	InputListener(JTextField textInput,MainPanel clientWnd) {
		this.clientWnd = clientWnd;
		this.textInput = textInput;
	}
	public void keyPressed(KeyEvent ev) {
		int keyCode = ev.getKeyCode();
		if (keyCode == KeyEvent.VK_ENTER)
		{
			String msg = textInput.getText().trim();
			clientWnd.writeText("[I say] " + msg);
			clientWnd.sendLine(msg);
			textInput.setText("");
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
	InputPanel(MainPanel clientWnd) {
		this.clientWnd = clientWnd;
		setLayout(new GridLayout(2,1));

		textInput = new JTextField();
		textInput.addKeyListener(new InputListener(textInput,clientWnd));
		reportPanel = new ReportPanel(clientWnd);

		add(textInput);
		add(reportPanel);
	}
	String getReportText() {
		return reportPanel.getReportText();
	}
}
class MainPanel extends JPanel implements ActionListener
{
	String ipAddress = "localhosr";
	PrintWriter toChatServer;
	Thread chatThread;
	String myIP;

	ButtonPanel buttonPanel;
	InputPanel inputPanel;
	JTextArea textBox;
	JScrollPane textPane;
	MainPanel() {
		buttonPanel = new ButtonPanel(this);
		setLayout(new BorderLayout());

		textBox = new JTextArea();
		textPane = new JScrollPane(textBox);
		textBox.setBorder(BorderFactory.createLoweredBevelBorder());
		inputPanel = new InputPanel(this);

		add(buttonPanel,BorderLayout.NORTH);
		add(textPane,BorderLayout.CENTER);
		add(inputPanel,BorderLayout.SOUTH);

		try
		{
			InetAddress address = InetAddress.getLocalHost();
			myIP = address.getHostAddress();
			System.out.println(myIP);
		}
		catch (Exception ex)
		{
		}
	}
	public void writeText(String msg) {
		textBox.append(msg + "\r\n");
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
				String remoteHost = "rmi://" + ipAddress + "/RemoteServer";
				Server s = (Server)Naming.lookup(remoteHost);
				int data = Integer.parseInt(inputPanel.getReportText());
				String answer = s.report(myIP,data);
				writeText("[Server says] " + answer);
			}
			catch (Exception ex)
			{
			}
			
		} else if (cmd.equals("Query"))
		{
			
		} else if (cmd.equals("Connect"))
		{			
			chatThread = new Thread() {
				public void run() {
					
				}
			};
			chatThread.start();			
		} else if (cmd.equals("Close"))
		{			
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
