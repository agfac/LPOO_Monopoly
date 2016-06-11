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
			case "Manage activity":
				state = MANAGEACTIVITY;
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
			switch(theInput){
			
			//MORTGAGE
			case "Play":
				state=PLAYING;
				break;
		
			case "Mortgage atlantic_avenue":
                break;
            case "Mortgage baltic_avenue":
                break;
            case "Mortgage bo_railroad":
                break;
            case "Mortgage board_walk":
                break;
            case "Mortgage connecticut_avenue":
                break;
            case "Mortgage electric_company":
                break;
            case "Mortgage illinois_avenue":
                break;
            case "Mortgage indiana_avenue":
                break;
            case "Mortgage kentucky_avenue":
                break;
            case "Mortgage marvin_gardens":
                break;
            case "Mortgage mediterranean_avenue":
                break;
            case "Mortgage new_york_avenue":
                break;
            case "Mortgage north_carolina_avenue":
                break;
            case "Mortgage oriental_avenue":
                break;
            case "Mortgage pacific_avenue":
                break;
            case "Mortgage park_place":
                break;
            case "Mortgage pennsylvania_avenue":
                break;
            case "Mortgage pennsylvania_railroad":
                break;
            case "Mortgage reading_railroad":
                break;
            case "Mortgage short_line_railroad":
                break;
            case "Mortgage st_charles_place":
                break;
            case "Mortgage st_james_place":
                break;
            case "Mortgage states_avenue":
                break;
            case "Mortgage tennesse_avenue":
                break;
            case "Mortgage ventnor_avenue":
                break;
            case "Mortgage vermont_avenue":
                break;
            case "Mortgage virginia_avenue":
                break;
            case "Mortgage water_works":
            	break;
            	
            //UNMORTGAGE
            case "Unmortgage atlantic_avenue":
                break;
            case "Unmortgage baltic_avenue":
                break;
            case "Unmortgage bo_railroad":
                break;
            case "Unmortgage board_walk":
                break;
            case "Unmortgage connecticut_avenue":
                break;
            case "Unmortgage electric_company":
                break;
            case "Unmortgage illinois_avenue":
                break;
            case "Unmortgage indiana_avenue":
                break;
            case "Unmortgage kentucky_avenue":
                break;
            case "Unmortgage marvin_gardens":
                break;
            case "Unmortgage mediterranean_avenue":
                break;
            case "Unmortgage new_york_avenue":
                break;
            case "Unmortgage north_carolina_avenue":
                break;
            case "Unmortgage oriental_avenue":
                break;
            case "Unmortgage pacific_avenue":
                break;
            case "Unmortgage park_place":
                break;
            case "Unmortgage pennsylvania_avenue":
                break;
            case "Unmortgage pennsylvania_railroad":
                break;
            case "Unmortgage reading_railroad":
                break;
            case "Unmortgage short_line_railroad":
                break;
            case "Unmortgage st_charles_place":
                break;
            case "Unmortgage st_james_place":
                break;
            case "Unmortgage states_avenue":
                break;
            case "Unmortgage tennesse_avenue":
                break;
            case "Unmortgage ventnor_avenue":
                break;
            case "Unmortgage vermont_avenue":
                break;
            case "Unmortgage virginia_avenue":
                break;
            case "Unmortgage water_works":
                break; 
                
            //BUILD 1 HOUSE
            case "Build 1 house atlantic_avenue":
                break;
            case "Build 1 house baltic_avenue":
                break;
            case "Build 1 house bo_railroad":
                break;
            case "Build 1 house board_walk":
                break;
            case "Build 1 house connecticut_avenue":
                break;
            case "Build 1 house electric_company":
                break;
            case "Build 1 house illinois_avenue":
                break;
            case "Build 1 house indiana_avenue":
                break;
            case "Build 1 house kentucky_avenue":
                break;
            case "Build 1 house marvin_gardens":
                break;
            case "Build 1 house mediterranean_avenue":
                break;
            case "Build 1 house new_york_avenue":
                break;
            case "Build 1 house north_carolina_avenue":
                break;
            case "Build 1 house oriental_avenue":
                break;
            case "Build 1 house pacific_avenue":
                break;
            case "Build 1 house park_place":
                break;
            case "Build 1 house pennsylvania_avenue":
                break;
            case "Build 1 house pennsylvania_railroad":
                break;
            case "Build 1 house reading_railroad":
                break;
            case "Build 1 house short_line_railroad":
                break;
            case "Build 1 house st_charles_place":
                break;
            case "Build 1 house st_james_place":
                break;
            case "Build 1 house states_avenue":
                break;
            case "Build 1 house tennesse_avenue":
                break;
            case "Build 1 house ventnor_avenue":
                break;
            case "Build 1 house vermont_avenue":
                break;
            case "Build 1 house virginia_avenue":
                break;
            case "Build 1 house water_works":
                break;
                
              //BUILD 2 HOUSES
            case "Build 2 houses atlantic_avenue":
                break;
            case "Build 2 houses baltic_avenue":
                break;
            case "Build 2 houses bo_railroad":
                break;
            case "Build 2 houses board_walk":
                break;
            case "Build 2 houses connecticut_avenue":
                break;
            case "Build 2 houses electric_company":
                break;
            case "Build 2 houses illinois_avenue":
                break;
            case "Build 2 houses indiana_avenue":
                break;
            case "Build 2 houses kentucky_avenue":
                break;
            case "Build 2 houses marvin_gardens":
                break;
            case "Build 2 houses mediterranean_avenue":
                break;
            case "Build 2 houses new_york_avenue":
                break;
            case "Build 2 houses north_carolina_avenue":
                break;
            case "Build 2 houses oriental_avenue":
                break;
            case "Build 2 houses pacific_avenue":
                break;
            case "Build 2 houses park_place":
                break;
            case "Build 2 houses pennsylvania_avenue":
                break;
            case "Build 2 houses pennsylvania_railroad":
                break;
            case "Build 2 houses reading_railroad":
                break;
            case "Build 2 houses short_line_railroad":
                break;
            case "Build 2 houses st_charles_place":
                break;
            case "Build 2 houses st_james_place":
                break;
            case "Build 2 houses states_avenue":
                break;
            case "Build 2 houses tennesse_avenue":
                break;
            case "Build 2 houses ventnor_avenue":
                break;
            case "Build 2 houses vermont_avenue":
                break;
            case "Build 2 houses virginia_avenue":
                break;
            case "Build 2 houses water_works":
                break;
                
              //BUILD 3 HOUSES
            case "Build 3 houses atlantic_avenue":
                break;
            case "Build 3 houses baltic_avenue":
                break;
            case "Build 3 houses bo_railroad":
                break;
            case "Build 3 houses board_walk":
                break;
            case "Build 3 houses connecticut_avenue":
                break;
            case "Build 3 houses electric_company":
                break;
            case "Build 3 houses illinois_avenue":
                break;
            case "Build 3 houses indiana_avenue":
                break;
            case "Build 3 houses kentucky_avenue":
                break;
            case "Build 3 houses marvin_gardens":
                break;
            case "Build 3 houses mediterranean_avenue":
                break;
            case "Build 3 houses new_york_avenue":
                break;
            case "Build 3 houses north_carolina_avenue":
                break;
            case "Build 3 houses oriental_avenue":
                break;
            case "Build 3 houses pacific_avenue":
                break;
            case "Build 3 houses park_place":
                break;
            case "Build 3 houses pennsylvania_avenue":
                break;
            case "Build 3 houses pennsylvania_railroad":
                break;
            case "Build 3 houses reading_railroad":
                break;
            case "Build 3 houses short_line_railroad":
                break;
            case "Build 3 houses st_charles_place":
                break;
            case "Build 3 houses st_james_place":
                break;
            case "Build 3 houses states_avenue":
                break;
            case "Build 3 houses tennesse_avenue":
                break;
            case "Build 3 houses ventnor_avenue":
                break;
            case "Build 3 houses vermont_avenue":
                break;
            case "Build 3 houses virginia_avenue":
                break;
            case "Build 3 houses water_works":
                break;
                
              //BUILD 4 HOUSES
            case "Build 4 houses atlantic_avenue":
                break;
            case "Build 4 houses baltic_avenue":
                break;
            case "Build 4 houses bo_railroad":
                break;
            case "Build 4 houses board_walk":
                break;
            case "Build 4 houses connecticut_avenue":
                break;
            case "Build 4 houses electric_company":
                break;
            case "Build 4 houses illinois_avenue":
                break;
            case "Build 4 houses indiana_avenue":
                break;
            case "Build 4 houses kentucky_avenue":
                break;
            case "Build 4 houses marvin_gardens":
                break;
            case "Build 4 houses mediterranean_avenue":
                break;
            case "Build 4 houses new_york_avenue":
                break;
            case "Build 4 houses north_carolina_avenue":
                break;
            case "Build 4 houses oriental_avenue":
                break;
            case "Build 4 houses pacific_avenue":
                break;
            case "Build 4 houses park_place":
                break;
            case "Build 4 houses pennsylvania_avenue":
                break;
            case "Build 4 houses pennsylvania_railroad":
                break;
            case "Build 4 houses reading_railroad":
                break;
            case "Build 4 houses short_line_railroad":
                break;
            case "Build 4 houses st_charles_place":
                break;
            case "Build 4 houses st_james_place":
                break;
            case "Build 4 houses states_avenue":
                break;
            case "Build 4 houses tennesse_avenue":
                break;
            case "Build 4 houses ventnor_avenue":
                break;
            case "Build 4 houses vermont_avenue":
                break;
            case "Build 4 houses virginia_avenue":
                break;
            case "Build 4 houses water_works":
                break;
                
              //BUILD HOTEL
            case "Build hotel atlantic_avenue":
                break;
            case "Build hotel baltic_avenue":
                break;
            case "Build hotel bo_railroad":
                break;
            case "Build hotel board_walk":
                break;
            case "Build hotel connecticut_avenue":
                break;
            case "Build hotel electric_company":
                break;
            case "Build hotel illinois_avenue":
                break;
            case "Build hotel indiana_avenue":
                break;
            case "Build hotel kentucky_avenue":
                break;
            case "Build hotel marvin_gardens":
                break;
            case "Build hotel mediterranean_avenue":
                break;
            case "Build hotel new_york_avenue":
                break;
            case "Build hotel north_carolina_avenue":
                break;
            case "Build hotel oriental_avenue":
                break;
            case "Build hotel pacific_avenue":
                break;
            case "Build hotel park_place":
                break;
            case "Build hotel pennsylvania_avenue":
                break;
            case "Build hotel pennsylvania_railroad":
                break;
            case "Build hotel reading_railroad":
                break;
            case "Build hotel short_line_railroad":
                break;
            case "Build hotel st_charles_place":
                break;
            case "Build hotel st_james_place":
                break;
            case "Build hotel states_avenue":
                break;
            case "Build hotel tennesse_avenue":
                break;
            case "Build hotel ventnor_avenue":
                break;
            case "Build hotel vermont_avenue":
                break;
            case "Build hotel virginia_avenue":
                break;
            case "Build hotel water_works":
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

