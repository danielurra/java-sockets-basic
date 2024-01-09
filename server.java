package Package_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClass {
	public static void main(String[] args) throws Exception {
		try {

			// create an object of ServerSocket and start listening for connections in port 8889
			ServerSocket server = new ServerSocket(8889);
			// call the accept method of that object -- wait for a client to establish a connection
			Socket serverClientSocket = server.accept();
			// once that happens it returns a "Socket" object

			System.out.println("Connection established...");
			
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

