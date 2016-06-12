package Monopoly.Server;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import java.util.Vector;

import Monopoly.Logic.BoardBox;
import Monopoly.Logic.ChanceBox;
import Monopoly.Logic.CommunityBox;
import Monopoly.Logic.Game;
import Monopoly.Logic.GoBox;
import Monopoly.Logic.GoJailBox;
import Monopoly.Logic.JailBox;
import Monopoly.Logic.Player;
import Monopoly.Logic.PlayerSymbol;
import Monopoly.Logic.Property;

public class GameProtocol {

	private static final int INIT = 0;
	private static final int SETGAMEPROPERTIES = 1;
	private static final int WAITINGPLAYERS = 2;
	private static final int READYTOPLAY = 3;
	private static final int PLAYING = 4;
	private static final int BUYINGPROPERTY = 5;
	private static final int MANAGEACTIVITY = 6;
	private static final int PAYBILL = 7;

	private static int numPlayers = 0;
	private static boolean gameSettings = false;
	private static int balance;
	private static Vector <Player> players = new Vector <Player>();
	private static Integer idPlayer = 0;
	private static Player currentPlayer;
	
	private static int currentPosition;
	private static int finalPosition;
	private static boolean positionSold = false;
	
	private static Integer[] pieceNumbers = {0,1,2,3,4,5,6,7}; 
	private static Vector pieces = new Vector(Arrays.asList(pieceNumbers));
	
	private static Game game;
	
	private Integer state = INIT;
	
	
	private Vector<PlayerSymbol> playerPieces = new Vector<PlayerSymbol>() ;
	 
	
	public GameProtocol(Game game){
		this.game = game;
		imageLoader();
	}
	
	public void imageLoader(){
		BufferedImage dog, boot, car, hat,  iron, ship, thimble, wheelbarrow;
		try {
			dog = ImageIO.read(new File("resources/images/pieces/dog.png"));
			boot = ImageIO.read(new File("resources/images/pieces/boot.png"));
			car = ImageIO.read(new File("resources/images/pieces/car.png"));
			hat = ImageIO.read(new File("resources/images/pieces/hat.png"));
			iron = ImageIO.read(new File("resources/images/pieces/iron.png"));
			ship = ImageIO.read(new File("resources/images/pieces/ship.png"));
			thimble = ImageIO.read(new File("resources/images/pieces/thimble.png"));
			wheelbarrow = ImageIO.read(new File("resources/images/pieces/wheelbarrow.png"));
			playerPieces.add(new PlayerSymbol(0, "Dog", dog));
			playerPieces.add(new PlayerSymbol(2, "Car", car));	
			playerPieces.add(new PlayerSymbol(5, "Ship", ship)); 
			playerPieces.add(new PlayerSymbol(1, "Boot", boot));
			playerPieces.add(new PlayerSymbol(3, "Hat", hat));
			playerPieces.add(new PlayerSymbol(4, "Iron", iron));
			playerPieces.add(new PlayerSymbol(6, "Thimble", thimble));
			playerPieces.add(new PlayerSymbol(7, "Wheelbarrow", wheelbarrow));
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}
	public String processInput(String theInput) {
		String theOutput = null;

		switch(state){
			
		case INIT:
			
			if(theInput.equalsIgnoreCase("Could play?")){
				theOutput = "Yes, you could set game properties";
				break;
			}
				
			else if(theInput.equalsIgnoreCase("Set game properties begin")){
				state=SETGAMEPROPERTIES;
				break;
			}
		
		case SETGAMEPROPERTIES:
			if (theInput.equalsIgnoreCase("Could play?")){
				theOutput = "Another player defining the game properties. Please wait.";
				break;
			}
			
			else{
				String[] propertiesParts = theInput.split(";");
				String numPlayersString = propertiesParts[0];
				String balanceString = propertiesParts[1];
				
				int numPlayersInt = Integer.parseInt(numPlayersString);
				int balanceInt = Integer.parseInt(balanceString);
				
				numPlayers=numPlayersInt;
				balance = balanceInt;
				
				theOutput = "Game properties set correctly!";
				gameSettings = true;
				state = WAITINGPLAYERS;
				break;
			}
			
		case WAITINGPLAYERS:
			
			switch(theInput){
				case "Could play?":
				if(players.size()<numPlayers){
					theOutput = "Yes, you could set player properties";
					break;
				}
				else
					theOutput = "Sorry, the limit numbers of player have been reached. You have to wait next game.";
				break;
				
				case "1;Is it my turn?":
					theOutput = "It is not your turn";
				break;
					
				case "2;Is it my turn?":
					theOutput = "It is not your turn";
				break;
				
				case "3;Is it my turn?":
					theOutput = "It is not your turn";
				break;
				
				case "4;Is it my turn?":
					theOutput = "It is not your turn";
				break;
			
				default:
					String[] playerParts = theInput.split(";");
					String firstName = playerParts[0];
					String firstPiece = playerParts[1];
					
					Integer firstPieceId = Integer.parseInt(firstPiece);
					
					
					
					if(players.size()<numPlayers){
					
						if(pieces.contains(firstPieceId)){
							players.add(new Player (firstName, playerPieces.get(firstPieceId), balance, ++idPlayer));
							setPlayerInitialPosition();
							pieces.remove(firstPieceId);
							theOutput = "Your ID is "+Integer.toString(idPlayer);
							if(players.size()==numPlayers){
								game.setPlayers(players);
								state=READYTOPLAY;
								break;
							}
							break;
							}
						else{
							theOutput = "That piece symbol already been choose! Please choose another piece.";
							break;
						}
					}
					else{
						theOutput = "Sorry, the limit numbers of player have been reached. You have to wait next game.";
						break;
					}
				
			}
			break;
			
		case READYTOPLAY:
			currentPlayer = game.getCurrentPlayer();
			switch(theInput){
			case "1;Is it my turn?":
				String[] question = theInput.split(";");
				String playerId = question[0];
				
				Integer playerInteger = Integer.parseInt(playerId);
				
				if(players.get(0).getId()==currentPlayer.getId())
					theOutput = "1;It is your turn";
				else
					theOutput = "It is not your turn";
			break;
				
			case "2;Is it my turn?":
				String[] question2 = theInput.split(";");
				String playerId2 = question2[0];
				
				Integer playerInteger2 = Integer.parseInt(playerId2);
				
				if(players.get(1).getId()==currentPlayer.getId())
					theOutput = "2;It is your turn";
				else
					theOutput = "It is not your turn";
			break;
			
			case "3;Is it my turn?":
				String[] question3 = theInput.split(";");
				String playerId3 = question3[0];
				
				Integer playerInteger3 = Integer.parseInt(playerId3);
				
				if(players.get(2).getId()==currentPlayer.getId())
					theOutput = "3;It is your turn";
				else
					theOutput = "It is not your turn";
			break;
			
			case "4;Is it my turn?":
				String[] question4 = theInput.split(";");
				String playerId4 = question4[0];
				
				Integer playerInteger4 = Integer.parseInt(playerId4);
				
				if(players.get(3).getId()==currentPlayer.getId())
					theOutput = "4;It is your turn";
				else
					theOutput = "It is not your turn";
			break;
			
			case "Playing begins":
				game.updateGame(currentPlayer);
				state = PLAYING;
				break;
				
			case "Manage Activity":
				theOutput="Properties:";
				for(Property p:currentPlayer.getPropertiesOwned()){
					if(!(p.equals(currentPlayer.getPropertiesOwned().lastElement()))){
						theOutput+=p.getName();
						theOutput+=";";
					}
					else{
						theOutput+=p.getName();
						theOutput+=".";
					}
						
				}
				state = MANAGEACTIVITY;
				break;
		}
			
			break;
			
		case PLAYING:
			currentPlayer = game.getCurrentPlayer();
			currentPosition = currentPlayer.getValuePosition();
			finalPosition = currentPlayer.getPos().getPos();
			if(currentPlayer.getPos() instanceof Property)
				positionSold = ((Property) (currentPlayer.getPos())).getSold();
			else
				positionSold = true;
			switch(theInput){
			case "1;Is it my turn?":
				String[] question = theInput.split(";");
				String playerId = question[0];
				
				Integer playerInteger = Integer.parseInt(playerId);
				
				if(players.get(0).getId()==currentPlayer.getId())
					theOutput = "1;It is your turn";
				else
					theOutput = "It is not your turn";
			break;
				
			case "2;Is it my turn?":
				String[] question2 = theInput.split(";");
				String playerId2 = question2[0];
				
				Integer playerInteger2 = Integer.parseInt(playerId2);
				
				if(players.get(1).getId()==currentPlayer.getId())
					theOutput = "2;It is your turn";
				else
					theOutput = "It is not your turn";
			break;
			
			case "3;Is it my turn?":
				String[] question3 = theInput.split(";");
				String playerId3 = question3[0];
				
				Integer playerInteger3 = Integer.parseInt(playerId3);
				
				if(players.get(2).getId()==currentPlayer.getId())
					theOutput = "3;It is your turn";
				else
					theOutput = "It is not your turn";
			break;
			
			case "4;Is it my turn?":
				String[] question4 = theInput.split(";");
				String playerId4 = question4[0];
				
				Integer playerInteger4 = Integer.parseInt(playerId4);
				
				if(players.get(3).getId()==currentPlayer.getId())
					theOutput = "4;It is your turn";
				else
					theOutput = "It is not your turn";
			break;
			
			case "1;Which picture may I show?":
				if(players.get(0).getId()==currentPlayer.getId()){
				if ((currentPosition == finalPosition) && !positionSold){
					theOutput = ""+currentPlayer.getId()+";"+"Do you want to buy this property?";
					System.out.println("Queres comprar ou n?");
					state=BUYINGPROPERTY;
					break;
				}else if((currentPosition == finalPosition) && positionSold){
					if ( currentPlayer.getPos() instanceof ChanceBox || currentPlayer.getPos() instanceof CommunityBox || currentPlayer.getPos() instanceof GoJailBox || (currentPlayer.getPos() instanceof JailBox && currentPlayer.getInJail())){
						game.checkSpecialBoardBox(game.getCurrentPlayer());
						currentPosition = currentPlayer.getValuePosition();
						finalPosition = currentPlayer.getPos().getPos();
						theOutput = ""+currentPosition;
						if ( currentPlayer.getCellsToMove() == 0){
							game.updateCurrentPlayer();
							theOutput="Next Player";
							state = READYTOPLAY;
						}
						break;
					}else{
						theOutput = ""+currentPosition;
						state = PAYBILL;
						break;
					}
//					game.checkSpecialBoardBox(game.getCurrentPlayer());
					//theOutput="Next Player";
					//state=READYTOPLAY;
				}
				else{
					theOutput = ""+currentPosition;
					break;
				}
				}
				break;
			case "2;Which picture may I show?":
				if(players.get(1).getId()==currentPlayer.getId()){
				if ((currentPosition == finalPosition) && !positionSold){
					theOutput = ""+currentPlayer.getId()+";"+"Do you want to buy this property?";
					state=BUYINGPROPERTY;
					break;
				}else if((currentPosition == finalPosition) && positionSold){
					if ( currentPlayer.getPos() instanceof ChanceBox || currentPlayer.getPos() instanceof CommunityBox || currentPlayer.getPos() instanceof GoJailBox){
						game.checkSpecialBoardBox(game.getCurrentPlayer());
						currentPosition = currentPlayer.getValuePosition();
						finalPosition = currentPlayer.getPos().getPos();
						theOutput = ""+currentPosition;
						if ( currentPlayer.getCellsToMove() == 0){
							game.updateCurrentPlayer();
							theOutput="Next Player";
							state = READYTOPLAY;
						}
						break;
					}else{
						theOutput = ""+currentPosition;
						state = PAYBILL;
						break;
					}
//					game.checkSpecialBoardBox(game.getCurrentPlayer());
					//theOutput="Next Player";
					//state=READYTOPLAY;
				}
				else{
					theOutput = ""+currentPosition;
					break;
				}
				}
				break;
			case "3;Which picture may I show?":
				if(players.get(2).getId()==currentPlayer.getId()){
				if ((currentPosition == finalPosition) && !positionSold){
					theOutput = ""+currentPlayer.getId()+";"+"Do you want to buy this property?";
					state=BUYINGPROPERTY;
					break;
				}else if((currentPosition == finalPosition) && positionSold){
					if ( currentPlayer.getPos() instanceof ChanceBox || currentPlayer.getPos() instanceof CommunityBox || currentPlayer.getPos() instanceof GoJailBox){
						game.checkSpecialBoardBox(game.getCurrentPlayer());
						currentPosition = currentPlayer.getValuePosition();
						finalPosition = currentPlayer.getPos().getPos();
						theOutput = ""+currentPosition;
						if ( currentPlayer.getCellsToMove() == 0){
							game.updateCurrentPlayer();
							theOutput="Next Player";
							state = READYTOPLAY;
						}
						break;
					}else{
						theOutput = ""+currentPosition;
						state = PAYBILL;
						break;
					}
//					game.checkSpecialBoardBox(game.getCurrentPlayer());
					//theOutput="Next Player";
					//state=READYTOPLAY;
				}
				else{
					theOutput = ""+currentPosition;
					break;
				}
				}
				break;
			case "4;Which picture may I show?":
				if(players.get(3).getId()==currentPlayer.getId()){
				if ((currentPosition == finalPosition) && !positionSold){
					theOutput = ""+currentPlayer.getId()+";"+"Do you want to buy this property?";
					state=BUYINGPROPERTY;
					break;
				}else if((currentPosition == finalPosition) && positionSold){
					if ( currentPlayer.getPos() instanceof ChanceBox || currentPlayer.getPos() instanceof CommunityBox || currentPlayer.getPos() instanceof GoJailBox){
						game.checkSpecialBoardBox(game.getCurrentPlayer());
						currentPosition = currentPlayer.getValuePosition();
						finalPosition = currentPlayer.getPos().getPos();
						theOutput = ""+currentPosition;
						if ( currentPlayer.getCellsToMove() == 0){
							game.updateCurrentPlayer();
							theOutput="Next Player";
							state = READYTOPLAY;
						}
						break;
					}else{
						theOutput = ""+currentPosition;
						state = PAYBILL;
						break;
					}
//					game.checkSpecialBoardBox(game.getCurrentPlayer());
					//theOutput="Next Player";
					//state=READYTOPLAY;
				}
				else{
					theOutput = ""+currentPosition;
					break;
				}
				}
				break;
			
			}
			break;
		case PAYBILL:
			game.optimizedPayBill(currentPlayer, currentPlayer.getPos());
			theOutput="Next Player";
			game.updateCurrentPlayer();
			state=READYTOPLAY;
			break;
		case BUYINGPROPERTY:
			//theOutput = "Do you want to buy this property?";
			//theOutput = ""+currentPosition;
			System.out.println("theInput-> " + theInput);
			switch(theInput){
			case "Yes":
				game.setBuyPropertyOption("yes");
				game.buyProperty(currentPlayer);
				theOutput="Bought";
				game.updateCurrentPlayer();
				state=READYTOPLAY;
				break;
				
			case "No":
				game.setBuyPropertyOption("no");
				game.updateCurrentPlayer();
				theOutput="Not bought";
				state=READYTOPLAY;
				break;
				
			case "1;Is it my turn?":
				String[] question = theInput.split(";");
				String playerId = question[0];
				
				Integer playerInteger = Integer.parseInt(playerId);
				
				if(!(players.get(0).getId()==currentPlayer.getId()))
					theOutput = "It is not your turn";
			break;
				
			case "2;Is it my turn?":
				String[] question2 = theInput.split(";");
				String playerId2 = question2[0];
				
				Integer playerInteger2 = Integer.parseInt(playerId2);
				
				if(!(players.get(1).getId()==currentPlayer.getId()))
					theOutput = "It is not your turn";
			break;
			
			case "3;Is it my turn?":
				String[] question3 = theInput.split(";");
				String playerId3 = question3[0];
				
				Integer playerInteger3 = Integer.parseInt(playerId3);
				
				if(!(players.get(2).getId()==currentPlayer.getId()))
					theOutput = "It is not your turn";
			break;
			
			case "4;Is it my turn?":
				String[] question4 = theInput.split(";");
				String playerId4 = question4[0];
				
				Integer playerInteger4 = Integer.parseInt(playerId4);
				
				if(!(players.get(3).getId()==currentPlayer.getId()))
					theOutput = "It is not your turn";
			break;
				
			case "1;Which picture may I show?":
				if((players.get(0).getId()==currentPlayer.getId())){
				theOutput = ""+currentPosition;
				break;
				}
				break;
			case "2;Which picture may I show?":
				if((players.get(1).getId()==currentPlayer.getId())){
				theOutput = ""+currentPosition;
				break;
				}
				break;
			case "3;Which picture may I show?":
				if((players.get(2).getId()==currentPlayer.getId())){
				theOutput = ""+currentPosition;
				break;
				}
				break;
			case "4;Which picture may I show?":
				if((players.get(3).getId()==currentPlayer.getId())){
				theOutput = ""+currentPosition;
				break;
				}
				break;
			}
			break;
			
		case MANAGEACTIVITY:
			
			String message=theInput;
			String[] parts = message.split(";");
			
			String propertyName = parts[0];
			String action = parts[1];
			
            switch(action){
            case "Mortgage":
            	break;
            case "Unmortgage":
            	break;
            case "BuildHouse":
            	break;
            case "BuildHotel":
            	break;
            }
		
			break;
			
		}
		return theOutput;
	}
	
	public void setPlayerInitialPosition(){
		switch (idPlayer){
		case 1:
			players.get(0).setPosition(1120, 890);
			break;
		case 2:
			players.get(1).setPosition(1120, 930);
			break;
		case 3:
			players.get(2).setPosition(1170, 890);
			break;
		case 4:
			players.get(3).setPosition(1170, 930);
			break;
		}		
	}
	
	public void setCurrentPlayer(){
		
	}
	public Player getCurrentPlayer(){
		return currentPlayer;
	}
	public Integer getState(){
		return state;
	}
	
	public int getNumPlayers() {
		return numPlayers;
	}
	
	public boolean getGameSettings(){
		return gameSettings;
	}
	
	public Vector<Player> getPlayers(){
		return players;
	}
}

