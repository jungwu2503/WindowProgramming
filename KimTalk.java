import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.net.*;

class Message
{
	private String who;
	private String message;

	Message(String who, String message) {
		this.who = who;
		this.message = message;
	}

	String getWho() {
		return who;
	}

	String getMessage() {
		return message;
	}
}

class ButtonPanel extends JPanel
{
	MainPanel mainWnd;
	JTextField ip;
	JButton connectButton;

	ButtonPanel(MainPanel mainWnd) {
		this.mainWnd = mainWnd;

		ip = new JTextField(15);
		connectButton = new JButton("Connect");

		ip.setText("localhost");

		add(ip);
		add(connectButton);

		connectButton.addActionListener(mainWnd);
	}

	String getIpAddress() {
		return ip.getText();
	}

	void setActionCommand(String s) {
		connectButton.setActionCommand(s);
		connectButton.setLabel(s);
	}

	void setIPAddress(String address) {
		ip.setText(address);
	}

}

class InputPanel extends JPanel
{
	MainPanel mainWnd;
	private JTextField textInput;

	InputPanel(MainPanel mainWnd) {
		setLayout(new GridLayout(1,1));
		this.mainWnd = mainWnd;

		textInput = new JTextField();
		textInput.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ev) {
				int keyCode = ev.getKeyCode();
				if (keyCode == KeyEvent.VK_ENTER)
				{
					String msg = textInput.getText().trim();
					Message newMessage = new Message("I", msg);
					mainWnd.writeText(newMessage);
					mainWnd.sendLine(msg);
					textInput.setText("");
				}
			}
		});
		textInput.setBorder(BorderFactory.createLoweredBevelBorder());

		add(textInput);
	}

	void setEditable(boolean flag) {
		textInput.setEditable(flag);
	}

}

class ChatPanel extends JPanel
{
	ArrayList<Message> text = new ArrayList<Message>();
	MainPanel mainWnd;

	ChatPanel(MainPanel mainWnd) {
		this.mainWnd = mainWnd;
	}

	void writeText(Message msg) {
		text.add(msg);
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int currentY = 20;
		int deltaY = 20;
		for (int i = 0; i < text.size(); i++)
		{
			Message m = text.get(i);
			String who = m.getWho();
			String s = m.getMessage();

			if (who.equals("I"))
			{
				String tmp = "";
				for (int j = 0; j < s.length(); j++)
				{					
					tmp += s.charAt(j);
					if (j != 0 && j % 10 == 0)
					{
						g.drawString(tmp,10,currentY);
						tmp = "";
						currentY = currentY + deltaY;
					} else if (j % 10 != 0) {
						g.drawString(tmp,10,currentY);						
					}
				}
			} else if (who.equals("U"))
			{
				String tmp = "";
				for (int j = 0; j < s.length(); j++)
				{					
					tmp += s.charAt(j);
					if (j != 0 && j % 10 == 0)
					{
						g.drawString(tmp,250,currentY);
						tmp = "";
						currentY = currentY + deltaY;
					} else if (j % 10 != 0) {
						g.drawString(tmp,250,currentY);						
					}
				}
			}

			//g.drawString(s,10,currentY);
			currentY = currentY + deltaY;	
			
			Dimension sz = getSize();
			if (currentY >= sz.height)
			{
				setPreferredSize(new Dimension(sz.width, currentY+deltaY+1000));
				updateUI();
			}
			mainWnd.scrollDown(currentY);
		}
	}

}

class ServerRole extends Thread
{
	MainPanel mainWnd;

	ServerRole(MainPanel mainWnd) {
		this.mainWnd = mainWnd;
	}

	public void run() {
		try
		{
			int portNumber = 8000;
			ServerSocket listenerSocket = new ServerSocket(portNumber);
			mainWnd.writeText("Server started...");
			mainWnd.writeText(mainWnd.getMyIP() + " on port: " + portNumber);
			
			while(true) {
				Socket client = listenerSocket.accept();
				
				String ip = client.getInetAddress().getHostAddress();
				mainWnd.writeText(ip + " is connected...");
				mainWnd.setEditable(true);
				mainWnd.setIPAddress(ip);
				mainWnd.setActionCommand("Disconnect");

				BufferedReader fromChatClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter toChatClient = new PrintWriter(client.getOutputStream());

				Thread fromClientThread = new Thread() {
					public void run() {
						try
						{
							String msg;
							while (true)
							{
								msg = fromChatClient.readLine();							
								Message m = new Message("U", msg);
								if (msg == null || msg.equals("####"))
								{
									break;
								}
								mainWnd.writeText(m);
							}
						}
						catch (IOException ex)
						{
							System.out.println(ex);
						}
						try
						{
							client.close();
							fromChatClient.close();
							toChatClient.close();
						}
						catch (IOException ex)
						{
						}
						mainWnd.setEditable(false);
						mainWnd.setActionCommand("Connect");
					}
				};
				mainWnd.setClientInfo(fromChatClient, toChatClient, fromClientThread);
				fromClientThread.start();
			}
		}
		catch (IOException ex)
		{
			System.out.println(ex);
		}
		
	}
}

class MainPanel extends JPanel implements ActionListener
{
	boolean amIServer = true;
	String myIP;
	String ipAddress = "localhost";
	ButtonPanel buttonPanel;
	ChatPanel chatPanel;
	InputPanel inputPanel;
	JScrollPane chatPane;

	Socket chatClient;
	BufferedReader fromChatServer;
	PrintWriter toChatServer;
	Thread fromServerThread;

	private BufferedReader fromChatClient;
	private PrintWriter toChatClient;
	private Thread fromClientThread;

	void setClientInfo(BufferedReader fromChatClient, PrintWriter toChatClient, Thread fromClientThread) {
		this.fromChatClient = fromChatClient;
		this.toChatClient = toChatClient;
		this.fromClientThread = fromClientThread;
	}

	void sendLine(String msg) {
		if (amIServer)
		{
			toChatClient.println(msg);
			toChatClient.flush();
		} else {
			toChatServer.println(msg);
			toChatServer.flush();
		}
	}

	void setIPAddress(String address) {
		ipAddress = address;
		buttonPanel.setIPAddress(address);
	}

	MainPanel() {
		setLayout(new BorderLayout());

		buttonPanel = new ButtonPanel(this);

		chatPanel = new ChatPanel(this);
		chatPane = new JScrollPane(chatPanel);
		chatPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		inputPanel = new InputPanel(this);
		inputPanel.setEditable(false);

		add(buttonPanel, BorderLayout.NORTH);
		add(chatPanel, BorderLayout.CENTER);
		add(inputPanel, BorderLayout.SOUTH);

		try
		{
			InetAddress address = InetAddress.getLocalHost();
			myIP = address.getHostAddress();
		}
		catch (Exception ex)
		{
		}

		ServerRole server = new ServerRole(this);
		server.start();
	}

	void scrollDown(int x) {
		chatPane.getVerticalScrollBar().setValue(x+100);
		chatPane.updateUI();
	}

	void writeText(Message msg) {
		chatPanel.writeText(msg);
	}

	void writeText(String s) {
		Message m = new Message("I", s);
		chatPanel.writeText(m);
	}

	String getMyIP() {
		return myIP;
	}

	void setActionCommand(String s) {
		buttonPanel.setActionCommand(s);
	}

	void setEditable(boolean flag) {
		inputPanel.setEditable(flag);
	}

	public void actionPerformed(ActionEvent ev) {
		String cmd = ev.getActionCommand();
		String ipAddress = buttonPanel.getIpAddress();
		if (cmd.equals("Connect"))
		{
			amIServer = false;
			setEditable(true);
			setActionCommand("Disconnect");
			try
			{
				chatClient = new Socket(ipAddress, 7000);
				writeText("Connected...");
				fromChatServer = new BufferedReader(new InputStreamReader(chatClient.getInputStream()));
				toChatServer = new PrintWriter(chatClient.getOutputStream());

				fromServerThread = new Thread() {
					public void run() {
						try
						{
							String msg;
							while (true)
							{
								msg = fromChatServer.readLine();
								Message newMessage = new Message("U", msg);
								if (msg == null || msg.equals("####"))
								{
									break;
								}
								writeText(newMessage);
							}
						}
						catch (IOException ex)
						{
							System.out.println(ex);
						}
						amIServer = true;
						setEditable(false);
						setActionCommand("Connect");
					}
				};
				fromServerThread.start();
			}
			catch (IOException ex)
			{
				System.out.println(ex);
				fromServerThread.stop();
			}

		} else if (cmd.equals("Disconnect"))
		{
			sendLine("####");
			if (amIServer)
			{
				try
				{
					fromChatClient.close();
					toChatClient.close();					
					fromClientThread.stop();
				}
				catch (IOException ex)
				{
				}
			} else {
				try
				{					
					fromChatServer.close();
					toChatServer.close();
					chatClient.close();
					fromServerThread.stop();
				}
				catch (IOException ex)
				{
				}
			}
			amIServer = true;
			setEditable(false);
			setActionCommand("Connect");
		}
	}

}

class KimTalkFrame extends JFrame
{
	KimTalkFrame() {
		super("KimTalk");
		setSize(400,600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.add(new MainPanel());
	}
}

class KimTalk 
{
	public static void main(String[] args) 
	{
		KimTalkFrame frame = new KimTalkFrame();
		frame.setVisible(true);
	}
}
