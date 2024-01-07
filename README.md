# Java Sockets basic
Simple client-server echo using TCP and Java sockets
## Two computers
Even though both parts of the code will run on the same computer, for better understanding I always prefer having two computers.<br>
One computer will play de **Server** role, running the server code and the other computer will play the **Client** role<br>
by running the client code.
In case you prefer the opposite then change the IP address in the client code by `Localhost`.
## Server computer listen for connection on TCP port 8889
Immediately after executing the server code you can check by utisng the following command.<br>
You'll see now a new TCP port whose status is **Listening**:<br>
```cmd
netstat -aon | findstr /i 8889
```
<img width="574" alt="cmd-tcp-listening-ports-command" src="https://github.com/danielurra/java-sockets-basic/assets/51704179/a4afe16a-c567-462b-9459-e6518fa6cfd0"><br>
## Server code
```java
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
```


