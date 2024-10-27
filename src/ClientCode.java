import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.net.ConnectException;

public class ClientCode 
{
	final static Scanner INPUT_DEVICE = new Scanner(System.in);
	final static String PROMPT_INPUT = "Please enter a username for the server >> ";
	final static String PROMPT_MESSAGE = "Please enter a message for the server >> ";
	final static String USER_NAME = "JD";
	
    public static void main(String[] args) 
    {	
    	// Receiving Client Name 
    	//System.out.print(PROMPT_INPUT);
    	//String clientName = INPUT_DEVICE.nextLine();
    	String clientName = USER_NAME;
    	
    	// Receiving Client Message
    	System.out.print(PROMPT_MESSAGE);
    	String message = INPUT_DEVICE.nextLine();
    	
    	
        try (Socket socket = new Socket("localhost", 12345)) 
        {
            // Set up input and output streams
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Send message to server
            message = clientName + "@" + message;
            out.println(message);
            System.out.println("Sent to server: " + message);

            // Receive and print response from server
            String response = in.readLine();
            System.out.println("Server: " + response);

            // Close resources
            in.close();
            out.close();
        } 
        
        catch (ConnectException e)
        {
        	System.out.println("The server is unreachable . . . ");
        }
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
    }
}
