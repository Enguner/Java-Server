import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client 
{
    public static void main(String[] args) 
    {
        String hostname = "localhost";  // Server's IP or hostname
        int port = 12345;               // Port number to connect to

        try (Socket socket = new Socket(hostname, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) 
        {

            System.out.println("Connected to the server!");

            // Thread to listen for messages from the server
            new Thread(() -> 
            {
                String serverResponse;
                try {
                    while ((serverResponse = in.readLine()) != null) {
                        System.out.println("Server: " + serverResponse);
                    }
                } catch (IOException e) {
                    System.out.println("Server disconnected.");
                }
            }).start();

            // Main thread to send messages to the server
            String userInput;
            System.out.println("Enter messages to send to the server:");
            while (true) 
            {
                userInput = scanner.nextLine();  // Read input from the user
                if ("exit".equalsIgnoreCase(userInput)) 
                {
                    System.out.println("Disconnecting from the server...");
                    break;
                }
                out.println(userInput);  // Send user input to the server
            }
        }
        
        catch (IOException e) 
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
