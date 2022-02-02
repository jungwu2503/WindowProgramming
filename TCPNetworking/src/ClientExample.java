import java.io.*;
import java.net.*;

public class ClientExample {

	public static void main(String[] args) {
		Socket socket = null;
				
		try {
			socket = new Socket();
			System.out.println("[Request connect]");
			socket.connect(new InetSocketAddress("localhost", 5001));
			System.out.println("[Success connect]");
			
			byte[] bytes = null;
			String message = null;
			
			OutputStream os = socket.getOutputStream();
			message = "Hello Server~";
			bytes = message.getBytes("UTF-8");
			os.write(bytes);
			os.flush();
			System.out.println("[Data sended]");
			
			InputStream is = socket.getInputStream();
			bytes = new byte[128];
			int readByteCount = is.read(bytes);
			message = new String(bytes, 0, readByteCount, "UTF-8");
			System.out.println("[Data received] " + message);
			
			os.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (!socket.isClosed()) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
