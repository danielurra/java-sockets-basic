package Package_client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientClass {
	public static void main(String[] args) throws Exception {
		try {
			// Create server socket (IP Address and port)
			Socket socket = new Socket("192.168.1.166", 8889);

			// Create streams to read/write data
			DataInputStream inStream = new DataInputStream(socket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());

			Scanner scanner = new Scanner(System.in);
			String clientMessage = "";
			String serverMessage = "";

				// Ask user to type a word
				System.out.println("Type a word: ");
				clientMessage = scanner.nextLine();

				// Send the entered text to the server
				outStream.writeUTF(clientMessage);
				outStream.flush();

				// Read data from socket input stream
				serverMessage = inStream.readUTF();
				System.out.println(serverMessage);

			// Close resources
			outStream.close();
			outStream.close();
			socket.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
