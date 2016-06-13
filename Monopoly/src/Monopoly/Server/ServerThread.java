package Monopoly.Server;

import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
    
	private Socket socket = null;
	private GameProtocol gp;

    public ServerThread(Socket socket, GameProtocol gp) {
        super("ServerThread");
        this.socket = socket;
        this.gp = gp;
    }
    
    public void run() {

        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        ) {
            String inputLine, outputLine;
            
            while ((inputLine = in.readLine()) != null) {
                outputLine = gp.processInput(inputLine);
                out.println(outputLine);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}