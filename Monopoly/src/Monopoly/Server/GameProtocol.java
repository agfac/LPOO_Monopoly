package Monopoly.Server;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Vector;

import Monopoly.Logic.Game;
import Monopoly.Logic.GoBox;
import Monopoly.Logic.Player;
import Monopoly.Logic.PlayerSymbol;

public class GameProtocol {

	private static final int INIT = 0;
	private static final int SETGAMEPROPERTIES = 1;
	private static final int WAITINGNUMOFPLAYERS = 2;
	private static final int WAITINGFIRSTPLAYER = 3;
	private static final int WAITINGOTHERPLAYERS = 4;
	private static final int READYTOPLAY = 5;

	/*private static final int WAITING = 0;
	private static final int SENTKNOCKKNOCK = 1;
	private static final int SENTCLUE = 2;
	private static final int ANOTHER = 3;*/

	private static int numPlayers;
	private static int balance;
	private static Vector <Player> players = new Vector <>();
	
	private static Integer[] pieceNumbers = {1,2,3,4,5,6,7,8}; 
	private static Vector pieces = new Vector(Arrays.asList(pieceNumbers));
	
	private static Game game;
	
	private int state = INIT;
	private boolean gamePropertiesBegin = false;
	
	/*private int currentJoke = 0;

	private String[] clues = { "Turnip", "Little Old Lady", "Atch", "Who", "Who" };
	private String[] answers = { "Turnip the heat, it's cold in here!",
			"I didn't know you could yodel!",
			"Bless you!",
			"Is there an owl in here?",
	"Is there an echo in here?" };*/

	
	public String processInput(String theInput) {
		String theOutput = null;

		switch(state){
			
		case INIT:
			if(!gamePropertiesBegin){
				gamePropertiesBegin=true;
				state = SETGAMEPROPERTIES;
				theOutput = "Game properties begin";
				break;
			}
				
			else{
				theOutput = "Another player defining the game properties. Please wait.";
				break;
			}
			/*
			String[] parts = theInput.split(";");
			String numPlayersString = parts[0];
			String balanceString = parts[1];
			
			numPlayers = Integer.parseInt(numPlayersString);
			balance = Integer.parseInt(balanceString);
			
			System.out.println(state);
			System.out.println(numPlayers);
			System.out.println(balance);

			theOutput="sai";
			state = WAITINGFIRSTPLAYER;*/
			//break;
		
		case WAITINGFIRSTPLAYER:
			
			String[] firstParts = theInput.split(";");
			String firstName = firstParts[0];
			String firstPiece = firstParts[1];
			
			Integer firstPieceId = Integer.parseInt(firstPiece);
			
			Player firstPlayer;
			
			if(pieces.contains(firstPieceId)){
				firstPlayer = new Player (firstName, firstPieceId, balance);
				players.add(firstPlayer);
				pieces.remove(firstPieceId);
				state = WAITINGOTHERPLAYERS;
				break;
			}
			
			else{
				theOutput = "That piece symbol already been choose!";
				break;
			}
		
		case WAITINGOTHERPLAYERS:
			
			String[] ohterParts = theInput.split(";");
			String otherName = ohterParts[0];
			String otherPiece = ohterParts[1];
			
			Integer otherPieceId = Integer.parseInt(otherPiece);
			
			Player otherPlayer;
			
			if(pieces.contains(otherPieceId)){
				otherPlayer = new Player (otherName, otherPieceId, balance);
				players.add(otherPlayer);
				pieces.remove(otherPieceId);
			}
			
			if(players.size() == numPlayers){
				game = new Game (players);
				state = READYTOPLAY;
			}		
			break;
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

