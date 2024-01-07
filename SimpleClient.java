package Examples;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {
	public static void main(String[] args) throws Exception {
		try {
			// Create connection to server socket
			Socket socket = new Socket("localhost", 8798);

			// Create streams to read/write data
			DataInputStream inStream = new DataInputStream(socket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());

			Scanner scanner = new Scanner(System.in);
			String clientMessage = "";
			String serverMessage = "";

				// Prompt user to enter some number or 'end'
				System.out.println("Type a word: ");
				clientMessage = scanner.nextLine();

				// Send the entered number to server
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
