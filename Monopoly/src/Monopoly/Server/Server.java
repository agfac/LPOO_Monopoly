package Monopoly.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import Monopoly.Gui.GraphicsWindow;
import Monopoly.Logic.Game;

public class Server {
//	public Server(){
//		
//	}
//	
//	public void start() throws IOException{
//
//		boolean listening = true;
//				
//		try (ServerSocket serverSocket = new ServerSocket(4444)){
//			GameProtocol gp = new GameProtocol();
//			while(listening){
//				new ServerThread(serverSocket.accept(), gp).start();
//			}
//		} catch (IOException e) {
//			System.out.println("Could not listen on port: 4444");
//			System.exit(-1);
//		}
//
//	}
	
	public static void main(String[] args) throws IOException {
		Game game = new Game (null);
		ArrayList<ServerThread> playerList = new ArrayList <ServerThread>();
		int numOfPlayers = 4;
		
			try {
				GraphicsWindow window = new GraphicsWindow( game );
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		boolean listening = true;

		try (ServerSocket serverSocket = new ServerSocket(4444)) {
			GameProtocol gp = new GameProtocol(game);
			do{
				ServerThread player= new ServerThread(serverSocket.accept(), gp);
				playerList.add(player);
				player.start();
				
				if (gp.getNumPlayers() != 0){
					numOfPlayers = gp.getNumPlayers();
					System.out.println("ja sou maior que 0");
				}
			}while (playerList.size() < numOfPlayers) ;
//			}while (game.getPlayers() != null);
			
		} catch (IOException e) {
			System.out.println("Could not listen on port: 4444");
			System.exit(-1);
		}

	}
}
