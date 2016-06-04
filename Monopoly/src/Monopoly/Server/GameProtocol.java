package Monopoly.Server;

import java.util.Vector;

import Monopoly.Logic.Game;
import Monopoly.Logic.Player;

public class GameProtocol {

	private static final int INIT = 0;
	private static final int WAITINGNUMOFPLAYERS = 1;
	private static final int WAITINGFIRSTPLAYER = 2;
	private static final int WAITINGOTHERPLAYERS = 3;
	private static final int READYTOPLAY = 4;

	private static final int WAITING = 0;
	private static final int SENTKNOCKKNOCK = 1;
	private static final int SENTCLUE = 2;
	private static final int ANOTHER = 3;

	private static int numPlayers;
	private static int balance;
	private static Vector <Player> players;
	private static Game game;
	
	private int state = INIT;
	/*private int currentJoke = 0;

	private String[] clues = { "Turnip", "Little Old Lady", "Atch", "Who", "Who" };
	private String[] answers = { "Turnip the heat, it's cold in here!",
			"I didn't know you could yodel!",
			"Bless you!",
			"Is there an owl in here?",
	"Is there an echo in here?" };*/

	public String processInput(String theInput) {
		String theOutput = null;

		if(state == INIT){
			theOutput = "Hello";
			state = WAITINGNUMOFPLAYERS;
		}
		if(state == WAITINGNUMOFPLAYERS){
			String[] parts = theInput.split(";");
			String numPlayersString = parts[0];
			String balanceString = parts[1];
			
			numPlayers = Integer.parseInt(numPlayersString);
			balance = Integer.parseInt(balanceString);
			
			state = WAITINGFIRSTPLAYER;
		}
		if(state == WAITINGFIRSTPLAYER){
			
			String[] parts = theInput.split(";");
			String name = parts[0];
			String piece = parts[1];
			
			int pieceId = Integer.parseInt(piece);
			Player player = new Player (name, pieceId, balance);
			
			players.addElement(player);
			
			state = WAITINGOTHERPLAYERS;
		}
		if(state == WAITINGOTHERPLAYERS){
			
			if(players.size()<numPlayers){
				String[] parts = theInput.split(";");
				String name = parts[0];
				String piece = parts[1];
				
				int pieceId = Integer.parseInt(piece);
				Player player = new Player (name, pieceId, balance);
				
				players.addElement(player);
			}
			
			if(players.size() == numPlayers){
				game = new Game (players);
				state = READYTOPLAY;
			}		
		}
		
		/*if (state == WAITING) {
			theOutput = "Knock! Knock!";
			state = SENTKNOCKKNOCK;
		} else if (state == SENTKNOCKKNOCK) {
			if (theInput.equalsIgnoreCase("Who's there?")) {
				theOutput = clues[currentJoke];
				state = SENTCLUE;
			} else {
				theOutput = "You're supposed to say \"Who's there?\"! " +
						"Try again. Knock! Knock!";
			}
		} else if (state == SENTCLUE) {
			if (theInput.equalsIgnoreCase(clues[currentJoke] + " who?")) {
				theOutput = answers[currentJoke] + " Want another? (y/n)";
				state = ANOTHER;
			} else {
				theOutput = "You're supposed to say \"" + 
						clues[currentJoke] + 
						" who?\"" + 
						"! Try again. Knock! Knock!";
				state = SENTKNOCKKNOCK;
			}
		} else if (state == ANOTHER) {
			if (theInput.equalsIgnoreCase("y")) {
				theOutput = "Knock! Knock!";
				if (currentJoke == (NUMJOKES - 1))
					currentJoke = 0;
				else
					currentJoke++;
				state = SENTKNOCKKNOCK;
			} else {
				theOutput = "Bye.";
				state = WAITING;
			}
		}*/
		return theOutput;
	}
}

