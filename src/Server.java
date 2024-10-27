/*
Steps to Handle Each Client in a Thread:

    Create a Server Socket:
    The server listens for incoming connections using a ServerSocket.

    Accept Clients:
    For each connected client, spawn a new thread to handle the communication.

    Create a ClientHandler Class:
    This class will implement Runnable or extend Thread to process individual client requests.

    Start a New Thread for Each Client:
    When a client connects, create an instance of ClientHandler and start it in a new thread.
*/

import java.io.*;
import java.net.*;

public class Server 
{
	final static public String VERSION_STRING = "0.0.1";
	final static public String SERVER_START_MSG = "Enguner Java-Server Program\nVersion: %s\nServer Traffic:\n\n";
	public static boolean terminateServer = false;
	
	public static void main (String[] args)
	{
		int portNumber = 12345;
		
		try (ServerSocket serverSocket = new ServerSocket(portNumber))
		{
			InetAddress IPAddress;
			System.out.printf(SERVER_START_MSG,VERSION_STRING);
			
			while (!terminateServer)
			{
				// Attempt to connect new clients
				Socket clientSocket = serverSocket.accept();
				IPAddress = clientSocket.getInetAddress();
				System.out.println("New client connected: " + IPAddress);
				
				// Create new thread to handle new client
				ClientThread clientThread = new ClientThread(clientSocket);
				new Thread(clientThread).start();
				
			}
			
			
		}
		
		catch (IOException e)
		{
			System.err.println("Error: " + e.getMessage());
		}
		
	}
}
