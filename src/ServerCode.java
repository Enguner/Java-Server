import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerCode 
{
	// Program Constants Used
	final static String SERVER_STARTING = "Server is starting up...\n\n";
	final static String WAITING = "Server is in a waiting state..."; // Server in waiting msg
	final static String ACCEPTED = "Connected to client: %s\n"; // Client connected printf format string
	
	public static void main(String[] args)
	{
		boolean terminateServer = false;			// Responsible for keeping server running
		String[] clientInfo = {"DN","Message"}; 	// DN represents default name
		System.out.print(SERVER_STARTING);			// Server Starting Output Message
		
		System.out.println("Server is ready");		// Useless string
		
		
		while (!terminateServer)
		{
			try (ServerSocket serverSocket = new ServerSocket(12345))
			{
				//System.out.println(WAITING);			// Server Entering Waiting State Message
				
				// Accepting Client Connections
				Socket socket = serverSocket.accept(); // Connection to client accepted
				
				// Set up input and output streams
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Something is happening
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 		// Something is happening
				
				// Receive and print message from client 
				String message = in.readLine();
				clientInfo = message.split("@");
				System.out.printf(ACCEPTED,clientInfo[0]);
				System.out.println( clientInfo[0] + ": " + clientInfo[1]);
				
				// Send a response to the client
				out.println("Message recieved!");
				
				// Close resources 
				in.close();
				out.close();
				socket.close();
				
				if (clientInfo[1].contains("terminate"))
				{
					terminateServer = true;
					System.out.println("Server is now shutting down.");
				}
				
			}
			
			catch (IOException e)
			{
			e.printStackTrace();
			}
		
		}	
	

	}
}
