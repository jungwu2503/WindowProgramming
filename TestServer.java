import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

class ClientService extends Thread
{
	Socket client;
	HashMap<String,PrintWriter> writers;
	MainPanel wnd;
	ClientService(Socket client,HashMap<String,PrintWriter> writers,MainPanel wnd) {
		this.client = client;
		this.writers = writers;
		this.wnd = wnd;
	}
	public void run() 
	{
		try
		{
			BufferedReader fromClient = 
				new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter toClient = 
				new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
			
			int n;

			String s = fromClient.readLine();
			n = Integer.parseInt(s);
			String ip = client.getInetAddress().getHostAddress();

			if (n == 1) // report mode
			{
				wnd.writeText("[" + ip + "] " + "reported his selling today.");
				String selling = fromClient.readLine();
				wnd.writeText("[" + ip + "] " +  selling + " Won.");
				toClient.println("Thanks!!!");
				toClient.flush();

				fromClient.close();
				toClient.close();
				client.close();
				return;
			} else if (n == 2) // query mode
			{
				int x = (int)(Math.random()*10);

				toClient.println(""+x);
				toClient.flush();
				wnd.writeText("[" + ip + "] " + "asked his total selling.");

				fromClient.close();
				toClient.close();
				client.close();
				return;
			}
			// chatting mode
			writers.put(ip,toClient);
			String msg;
			while(true) {
				msg = fromClient.readLine();
				if (msg.equals("-1"))
				{
					break;
				}
				wnd.writeText("[" + ip + " says] " + msg);
			}

			writers.remove(ip);
			wnd.writeText("Connection with " + ip + " closed.");

			fromClient.close();
			toClient.close();
			client.close();
		}
		catch (IOException ex)
		{
			System.out.println(ex);
		}
	}
}
class ServerRole extends Thread
{
	ServerSocket listenSocket;
	MainPanel wnd;
	HashMap<String,PrintWriter> writers;

	public ServerRole(MainPanel serverWnd) {
		this.wnd = serverWnd;
		writers = new HashMap<String,PrintWriter>();
	}
	public void run()
	{
		try
		{
			listenSocket = new ServerSocket(7000);
			wnd.writeText("Server started...");
			wnd.writeText(wnd.myIP + " on port: 7000");
			while(true) {
				Socket client = listenSocket.accept();
				String ip = client.getInetAddress().getHostAddress();
				wnd.writeText(ip + " is connected...");
				wnd.addComboBoxItem(ip);

				ClientService connection = new ClientService(client,writers,wnd);
				connection.start();
			}
		}
		catch (IOException ex)
		{
			System.out.println(ex);
		}
	}
	public void closeSocket()
	{
		try
		{
			listenSocket.close();
		}
		catch (IOException ex)
		{
			System.out.println(ex);
		}
	}
	public void sendLine(String ip,String msg)
	{
		PrintWriter toClient = writers.get(ip);
		toClient.println(msg);
		toClient.flush();
	}
}

class ButtonPanel extends JPanel
{
	MainPanel serverWnd;
	JButton startButton;
	JButton stopButton;
	JButton salesRecordButton;
	ButtonPanel(MainPanel serverWnd) {
		this.serverWnd = serverWnd;

		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		salesRecordButton = new JButton("Sales Record");

		add(startButton);
		add(stopButton);
		add(salesRecordButton);

		startButton.addActionListener(serverWnd);
		stopButton.addActionListener(serverWnd);
		salesRecordButton.addActionListener(serverWnd);
	}
}
class InputListener extends KeyAdapter
{
	MainPanel serverWnd;
	JTextField textInput;
	InputListener(JTextField textInput,MainPanel serverWnd) {
		this.serverWnd = serverWnd;
		this.textInput = textInput;
	}
	public void keyPressed(KeyEvent ev) {
		int keyCode = ev.getKeyCode();
		if (keyCode == KeyEvent.VK_ENTER)
		{
			String msg = textInput.getText().trim();
			serverWnd.writeText("[I say ] " + msg);
			String clientIP = serverWnd.getSelectedIP();
			serverWnd.sendLine(clientIP,msg);
			textInput.setText("");
		}
	}
}
class InputPanel extends JPanel
{
	MainPanel serverWnd;
	JComboBox<String> ips;
	JTextField textInput;
	InputPanel(MainPanel serverWnd) {
		this.serverWnd = serverWnd;
		setLayout(new GridLayout(2,1));

		ips = new JComboBox<String>();
		textInput = new JTextField();
		textInput.addKeyListener(new InputListener(textInput,serverWnd));

		add(ips);
		add(textInput);
	}
	public void addComboBoxItem(String ip) {
		int n;
		
		ips.removeItem(ip);
		ips.insertItemAt(ip,0);
		ips.setSelectedIndex(0);
	}
	String getSelectedIP() {
		return ips.getItemAt(ips.getSelectedIndex());
	}
}
class MainPanel extends JPanel implements ActionListener
{
	ButtonPanel buttonPanel;
	JTextArea textBox;
	JScrollPane textPane;
	InputPanel inputPanel;
	ServerRole server;
	String myIP;

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
		writeText("Please press start button");

		try
		{
			InetAddress address = InetAddress.getLocalHost();
			myIP = address.getHostAddress();
		}
		catch (Exception ex)
		{
		}
	}
	String getSelectedIP() {
		return inputPanel.getSelectedIP();
	}
	void sendLine(String ip,String msg) {
		server.sendLine(ip,msg);
	}
	public void addComboBoxItem(String ip) {
		inputPanel.addComboBoxItem(ip);
	}
	public void writeText(String msg) {
		textBox.append(msg + "\r\n");
	}
	public void actionPerformed(ActionEvent ev) {
		String command = ev.getActionCommand();
		if (command.equals("Start"))
		{
			server = new ServerRole(this);
			server.start();
		} else if (command.equals("Stop"))
		{
			server.closeSocket();
			server.stop();
		}
	}
}
class ServerFrame extends JFrame
{
	ServerFrame() {
		super("Server");
		setSize(400,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.add(new MainPanel());
	}
}
class TestServer 
{
	public static void main(String[] args) 
	{
		ServerFrame serverFrame = new ServerFrame();
		serverFrame.setVisible(true);
	}
}
