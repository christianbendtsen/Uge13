package com.company;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer
{
    private static ServerSocket servSock;
    private static final int PORT = 1234;

    public static void main(String[] args)
    {
        System.out.println("Opretter forbindelse...\n");
        try
        {
            servSock = new ServerSocket(PORT);      //Step 1.
        }
        catch(IOException ioEx)
        {
            System.out.println("Kan ikke oprette forbindelse");
            System.exit(1);
        }
        do
        {
            handleClient();
        }
        while (true);
    }

    private static void handleClient()
    {
        Socket socket = null;                     			//Step 2.
        try
        {
            socket = servSock.accept();        				//Step 2.

            Scanner input = new Scanner(socket.getInputStream()); 	//Step 3.
            PrintWriter output =
                    new PrintWriter(socket.getOutputStream(),true); 	//Step 3.

            int numMessages = 0;
            String message = input.nextLine();      			//Step 4.
            while (!message.equals("forbindelse lukket"))
            {
                System.out.println("Besked modtaget.");
                numMessages++;
                output.println("Besked " +
                        numMessages + ": " + message);   		//Step 4.
                message = input.nextLine();
            }
            output.println(numMessages + " Besked modatget."); 	//Step 4.
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally
        {
            try
            {
                System.out.println( "\n* lukker forbindelse... *");
                socket.close();                    //Step 5.
            }
            catch(IOException ioEx)
            {
                System.out.println("kan ikke oprette forbindelse");
                System.exit(1);
            }
        }
    }
}