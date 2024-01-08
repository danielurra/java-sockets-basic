# Java Sockets and Streams
![java-sockets-and-streams](https://github.com/danielurra/java-sockets-basic/assets/51704179/0a35e2c3-2ec2-49c6-aa54-ba28d2b4d38a)<br>
## Basic Echo console Application
This is a simple `client-server` console application where the server returns an `echo` of the text that is typed on the client side.<br>
The code makes use of the Java **ServerSocket** and also Java **Stream**.
* The first thing to do here is to start the server on one computer.<br>
The server will start listening for connections coming from the network on one particular TCP port, in our case **port 8889**.<br>
You can verify the listening port by running the `netstat` cmd command in windows computers, see below section.<br>
* On another computer, one that is reachable via the network you will run the client code, you will see the console asking to enter a word.<br>
If everything is ok as soon as you write a word the server will echo the word you typed, see animation below:<br>
## Animation
In the animation below you will see both sides (client and server) code, the IDE used is Eclipse and both computers have Windows Operating System.<br>
![java-sockets](https://github.com/danielurra/java-sockets-basic/assets/51704179/b6845891-e43e-49ac-9e64-4b05b02270a5)
## Two computers
Even though both parts of the code (client/server) will perfectly work on the same computer, for better understanding I always prefer having two computers.<br>
One computer will play de **Server** role (by running the server code) and the other computer will play the **Client** role<br>
(by running the client code).
In case you prefer the opposite then simple change the **IP address** (192.168.1.166) in the client code by `Localhost`.<br>
![client-server](https://github.com/danielurra/java-sockets-basic/assets/51704179/58eb45d5-7a2c-4dee-a4d6-e0bc23fe7b3c)<br>
## Server listening for connection on TCP port 8889
Immediately after executing the server code you can check if it is actually running by utilizing the following command.<br>
![netstat-cmd-command](https://github.com/danielurra/java-sockets-basic/assets/51704179/1042c74f-1ac0-4171-98c5-e798b73f5d57)<br>
You'll see now a new TCP port (8889) whose status is **Listening**:<br>
```cmd
netstat -aon | findstr /i 8889
```
<img width="574" alt="cmd-tcp-listening-ports-command" src="https://github.com/danielurra/java-sockets-basic/assets/51704179/a4afe16a-c567-462b-9459-e6518fa6cfd0"><br>
## Server code
```java
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
			// call the accept method of that object -- wait for a client to try to establish a connection
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
```
## Client code
```java
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

```



