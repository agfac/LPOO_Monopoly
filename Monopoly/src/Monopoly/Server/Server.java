package Monopoly.Server;

import java.net.*;
import java.io.*;

public class Server {

	public static void main(String[] args) throws IOException{

		boolean listening = true;

		try (ServerSocket serverSocket = new ServerSocket(4444)){
			GameProtocol gp = new GameProtocol();
			while(listening){
				new ServerThread(serverSocket.accept(), gp).start();
			}
		} catch (IOException e) {
			System.out.println("Could not listen on port: 4444");
			System.exit(-1);
		}

	}
}
