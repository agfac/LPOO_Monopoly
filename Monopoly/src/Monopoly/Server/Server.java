package Monopoly.Server;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Monopoly.Logic.Player;

public class Server {

	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	private static InputStreamReader inputStreamReader;
	private static OutputStream ouputStream;
	private static BufferedOutputStream bufferedOutputStream;
	private static ObjectOutputStream objectOutputStream;
	private static BufferedReader bufferedReader;
	private static String message;

	public static void main(String[] args) {
		
		try {
			serverSocket = new ServerSocket(4444); // Server socket
	
		} catch (IOException e) {
			System.out.println("Could not listen on port: 4444");
		}
	
		System.out.println("Server started. Listening to the port 4444");
	
		while (true) {
			try {
	
				clientSocket = serverSocket.accept(); // accept the client connection
				
				/*ouputStream = clientSocket.getOutputStream();
				Player p = new Player("Andre");
				SerializationUtil.serialize(p, ouputStream);*/
				
				inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
				bufferedReader = new BufferedReader(inputStreamReader); // get the client message
				message = bufferedReader.readLine();
	
				String[] parts = message.split(";");
				String name = parts[0];
				int pieceId = Integer.parseInt(parts[1]);

				
				Player p = new Player(name, pieceId);
				System.out.println(p.getName());
				System.out.println(p.getPieceId());
				//System.out.println(message);
				inputStreamReader.close();
				clientSocket.close();
				//ouputStream.close();
				clientSocket.close();
				
	
			} catch (IOException ex) {
				System.out.println("Problem in message reading");
				}
			}
	
		}
}
