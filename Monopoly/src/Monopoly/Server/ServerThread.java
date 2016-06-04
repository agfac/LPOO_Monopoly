package Monopoly.Server;

import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
    
	private Socket socket = null;

    public ServerThread(Socket socket) {
        super("ServerThread");
        this.socket = socket;
    }
    
    public void run() {

        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        ) {
            String inputLine, outputLine;
            GameProtocol gp = new GameProtocol();
            outputLine = gp.processInput(null);
            //System.out.println(outputLine);
            //out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                //System.out.println(outputLine);
                outputLine = gp.processInput(inputLine);
                System.out.println(outputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye"))
                    break;
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}