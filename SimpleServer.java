package Examples;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
	public static void main(String[] args) throws Exception {
		try {

			// Listen to port
			// create an object of ServerSocket 
			ServerSocket server = new ServerSocket(8889);
			// call the accept method of that object -- wait for a client to try to establish a connection
			// once that happens it returns a "Socket" object
			Socket serverClientSocket = server.accept();

			System.out.println("Server started...");
			
			DataInputStream inStream = new DataInputStream(serverClientSocket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(serverClientSocket.getOutputStream());

			outStream.writeUTF("Echo of what was received: " + inStream.readUTF());
			outStream.flush();
			
			// close and release resources
			inStream.close();
			outStream.close();
			serverClientSocket.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
