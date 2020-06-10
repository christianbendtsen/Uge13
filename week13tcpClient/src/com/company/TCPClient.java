package com.company;

import java.io.*;
import java.net.*;
import java.util.*;

public class TCPClient
{
    private static InetAddress host;
    private static final int PORT = 1234;

    public static void main(String[] args)
    {
        try
        {
            host = InetAddress.getLocalHost();
        }
        catch(UnknownHostException uhEx)
        {
            System.out.println("Host ID not found!");
            System.exit(1);
        }

        accessServer();
    }

    private static void accessServer()
    {
        Socket socket = null;      //Step 1.

        try
        {
            socket = new Socket(host,PORT); //Step 1.

            Scanner input = new Scanner(socket.getInputStream());//Step 2.

            PrintWriter output =
                    new PrintWriter(socket.getOutputStream(),true);//Step 2.

            //Set up stream for keyboard entry...
            Scanner scanner = new Scanner(System.in);

            String message, response;


            do
            {
                System.out.print("Intast besked: ");
                message =  scanner.nextLine();
                output.println(message);        //Step 3.
                response = input.nextLine();    //Step 3.
                System.out.println("\nSERVER> " + response);
            }while (!message.equals("lukker forbindelse"));
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }

        finally
        {
            try
            {
                System.out.println("\n* lukker forbindelse... *");
                socket.close();                    //Step 4.
            }
            catch(IOException ioEx)
            {
                System.out.println("Kan ikke oprette forbindelse");
                System.exit(1);
            }
        }
    }
}
