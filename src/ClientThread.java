import java.io.*;
import java.net.*;

public class ClientThread  implements Runnable
{
	private Socket clientSocket;
	
    public ClientThread(Socket socket) 
    {
        this.clientSocket = socket;
    }
    
    @Override
    public void run()
    {
    	try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true))
    	{
    		String message;
    		
    		// Read messages from the client
    		while ((message = in.readLine()) != null)
    		{
    			System.out.println("Received: " + message);
    			// Send a response back to the client
    			out.println("Echo: " + message);
    		}
    	}
    	
    	catch(IOException e)
    	{
    		System.err.println("Client disconnected: " + e.getMessage());
    	}
    	
    	finally
    	{
    		try 
    		{
    			clientSocket.close();
    		}
    		
    		catch (IOException e)
    		{
    			System.err.println("Failed to close socket: " + e.getMessage());
    		}
    	}
    }

}


