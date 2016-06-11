package Monopoly.Server;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import Monopoly.Gui.GraphicsWindow;
import Monopoly.Logic.Game;

public class Server {
	
	public static void main(String[] args) throws IOException {
		Game game = new Game (null);
		ArrayList<ServerThread> playerList = new ArrayList <ServerThread>();
		int numOfPlayers = 4;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicsWindow window = new GraphicsWindow(game);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		boolean listening = true;

		try (ServerSocket serverSocket = new ServerSocket(4444)) {
			GameProtocol gp = new GameProtocol(game);
			//first time
			ServerThread player= new ServerThread(serverSocket.accept(), gp);
			playerList.add(player);
			player.start();
			
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (gp.getGameSettings() && playerList.size() < gp.getNumPlayers() ){
					ServerThread player2 = new ServerThread(serverSocket.accept(), gp);
					playerList.add(player2);
					player2.start();
				}
				if ( playerList.size() == gp.getNumPlayers()){
					break;
				}			
			}

			
		} catch (IOException e) {
			System.out.println("Could not listen on port: 4444");
			System.exit(-1);
		}

	}
}
