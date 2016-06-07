package Monopoly.Logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Game {

	private final int JAILVALUE = 500;
	private final int GOVALUE = 2000;
	
	private Dice dice1;
	private Dice dice2;
	private Board board;
	private Vector<Player> players;
	
	private Integer chanceOption = null;
	private Integer communityOption = null;

	// TODO APENAS PARA TESTE APAGAR !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!111
	private PlayerSymbol dog;
	private PlayerSymbol car;
	private PlayerSymbol ship;
	private PlayerSymbol boot;
	private PlayerSymbol hat;
	private PlayerSymbol iron;
	private PlayerSymbol thimble;
	private PlayerSymbol wheelbarrow;
	protected static BufferedImage dogPiece;
	protected static BufferedImage carPiece;
	protected static BufferedImage shipPiece;
	protected static BufferedImage bootPiece;
	protected static BufferedImage hatPiece;
	protected static BufferedImage ironPiece;
	protected static BufferedImage thimblePiece;
	protected static BufferedImage wheelbarrowPiece;
	// FIM CODIGO TESTES-------------------------------------------------------

	public Game() {
		players = new Vector<Player>();

		board = new Board();
		dice1 = new Dice();
		dice2 = new Dice();
	}

	public Game(Vector<Player> players) {
		this.players = players;
		board = new Board();
		dice1 = new Dice();
		dice2 = new Dice();
	}

	/**
	 * Get all players
	 * 
	 * @return all players
	 */
	public Vector<Player> getPlayers() {
		return players;
	}

	/**
	 * Add player
	 * 
	 * @param player
	 *            to be added
	 */
	public void addPlayer(Player player) {
		this.players.add(player);
	}

	/**
	 * Remove player
	 * 
	 * @param player
	 *            to be removed
	 */
	public void removePlayer(Player player) {
		this.players.remove(player);
	}

	/**
	 * Will roll the dice1 and return an integer with value
	 * 
	 * @return dice value
	 */
	private int rollDice(Dice dice) {
		dice.rollDice();
		return dice.getValue();
	}

	/**
	 * Sum of dice 1 and dice 2
	 * 
	 * @param dice1
	 *            dice one
	 * @param dice2
	 *            dice two
	 * @return total of dice1 + dice2
	 */
	private int get2RollDices(Dice dice1, Dice dice2) {
		int dice1Value, dice2Value;

		// Roll dices 2 times and store the value in totalDiceValue
		dice1Value = rollDice(dice1);
		dice2Value = rollDice(dice2);
		return (dice1Value + dice2Value);
	}

	/**
	 * Check if the values generated from dices are the same
	 * 
	 * @param dice1
	 *            Dice one
	 * @param dice2
	 *            Dice two
	 * @return true if they have the same value, if not return false
	 */
	private boolean sameValuesDice(Dice dice1, Dice dice2) {
		return (dice1.getValue() == dice2.getValue()) ? true : false;
	}

	public void verificarSeTemPlayers(Player player) {
		if ((player.getPos() == board.getBoardBox(7)) || (player.getPos() == board.getBoardBox(22) || (player.getPos() == board.getBoardBox(36))) && chanceOption == null) {
			System.out.println("CHANCE BOX");
			gerateChance(player);
		}

		if ((player.getPos() == board.getBoardBox(2)) || (player.getPos() == board.getBoardBox(17) || (player.getPos() == board.getBoardBox(33))) && communityOption == null) {
			System.out.println("COMMUNITY BOX");
			gerateCommunity(player);
		}

		// Check if the new position is the JailBox, if yes goes to jail.
		if (player.getPos() == board.getBoardBox(30)) {
			checkIfPlayerHaveOutOfJailCard(player);
		}
	}
	/*
	 * public void secuenciaDoJogo(Player player) { int dicesValue =
	 * get2RollDices(dice1, dice2); System.out.println("Dice valeu -> " +
	 * dicesValue);
	 * 
	 * dicesValue = 22;
	 * 
	 * player.setCellsToMove(dicesValue);
	 * 
	 * // atualizo posição if (player.getPos().getPos() + dicesValue > 39) {
	 * player.setBalance(GOVALUE);
	 * player.setPos(board.getBoardBox((player.getPos().getPos() + dicesValue -
	 * 39) - 1)); } else {
	 * player.setPos(board.getBoardBox(player.getPos().getPos() + dicesValue));
	 * }
	 * 
	 * // if ((player.getPos() == board.getBoardBox(7)) || (player.getPos() ==
	 * board.getBoardBox(22)) // || (player.getPos() == board.getBoardBox(36)))
	 * { // System.out.println("CHANCE BOX"); // gerateChance(player); // } //
	 * // if ((player.getPos() == board.getBoardBox(2)) || (player.getPos() ==
	 * board.getBoardBox(17)) // || (player.getPos() == board.getBoardBox(33)))
	 * { // System.out.println("COMMUNITY BOX"); // gerateCommunity(player); //
	 * }
	 * 
	 * if ((player.getPos() == board.getBoardBox(4)) || (player.getPos() ==
	 * board.getBoardBox(38))) player.updateBalance(-((TaxBox)
	 * board.getBoardBox(4)).getTaxValue());
	 * 
	 * // ----------------------------------------- if (sameValuesDice(dice1,
	 * dice2)) { nOfTimesGetDoubleValue++; secuenciaDoJogo(player); //
	 * RECURSIVAMENTE } else nOfTimesGetDoubleValue = 0;
	 * 
	 * }
	 */

	/**
	 * Method to update the player on board
	 * 
	 * @param player
	 *            to be update on board
	 */
	public void updatePlayer(Player player) {
		chanceOption = null;
		communityOption = null;
		int totalDiceValue, atualPlayerPos, dif;

		// Store the value of 2 rolled dices
		totalDiceValue = get2RollDices(dice1, dice2);
		
		totalDiceValue = 1;
		player.setDicesValue(totalDiceValue);

		// Update number of tries from player, if get 3 times goes to jail.
		player.setNrOfRolls(sameValuesDice(dice1, dice2));

		// If number of tries from player is equal to 3, player goes to jail
		if (player.getNrOfRolls() == 3) {
			checkIfPlayerHaveOutOfJailCard(player);
		}

		// Get player position and update with the new position from dices if
		// not in jail.
		atualPlayerPos = player.getPos().getPos();
		if (!player.getInJail()) {
			atualPlayerPos += totalDiceValue;
		}

		// Check if pass trough Go DoardBox and store the new player Position.
		if (atualPlayerPos > 39 && !player.getInJail()) {
			dif = atualPlayerPos - 39;
			movePlayerGUI(player, totalDiceValue);
			player.setPos(board.getBoardBox((dif - 1)));
			player.updateBalance(GOVALUE);
		} else {
			movePlayerGUI(player, totalDiceValue);
			player.setPos(board.getBoardBox(atualPlayerPos));
		}

		// // Check if the new position is the JailBox, if yes goes to jail.
		// if (player.getPos() == board.getBoardBox(30)) {
		// checkIfPlayerHaveOutOfJailCard(player);
		// }

		// if ((player.getPos() == board.getBoardBox(7)) || (player.getPos() ==
		// board.getBoardBox(22))
		// || (player.getPos() == board.getBoardBox(36))) {
		// System.out.println("CHANCE BOX");
		// gerateChance(player);
		// }
		//
		// if ((player.getPos() == board.getBoardBox(2)) || (player.getPos() ==
		// board.getBoardBox(17))
		// || (player.getPos() == board.getBoardBox(33))) {
		// System.out.println("COMMUNITY BOX");
		// gerateCommunity(player);
		// }

		if ((player.getPos() == board.getBoardBox(4)) || (player.getPos() == board.getBoardBox(38)))
			player.updateBalance(-((TaxBox) board.getBoardBox(4)).getTaxValue());

		System.out.println("Dice value: " + (dice1.getValue() + dice2.getValue()));
	}

	public void movePlayerGUI(Player player, int moveValeu) {
		player.setCellsToMove(moveValeu);
	}

	/**
	 * Method to update the player in Jail
	 * 
	 * @param player
	 *            to be updated on Jail
	 */
	public void updatePlayerInJail(Player player) {
		int totalDiceValue, atualPlayerPos;

		// Store the value of 2 rolled dices
		totalDiceValue = get2RollDices(dice1, dice2);

		player.setDicesValue(totalDiceValue);

		System.out.println("Dice1 value: " + dice1.getValue() + " Dice2 value: " + dice2.getValue());

		// Increment the tries in jail
		player.setNrOfRollsInJail(sameValuesDice(dice1, dice2));

		// Check if player got double, if yes will exit from jail
		if (sameValuesDice(dice1, dice2)) {
			player.setInJail(false);
			player.setNrOfRollsInJail(true); // Reset number of rolls in jail
		}

		// Check if the number of rolls in jail are 3, if yes exit from jail and
		// pay fee
		if (player.getNrOfRollsInJail() == 3) {
			player.setInJail(false);
			player.updateBalance(-JAILVALUE);
			player.setNrOfRollsInJail(true); // Reset number of rolls in jail
			System.out.println("You must pay " + JAILVALUE + " to go out of jail");
		}

		// If player leaves the jail, will update it for the next position from
		// rolled dice.
		if (!player.getInJail()) {
			atualPlayerPos = player.getPos().getPos();
			movePlayerGUI(player, totalDiceValue);
			player.setPos(board.getBoardBox(atualPlayerPos + totalDiceValue));
		}
	}

	/**
	 * Check if have out of jail card and leave the jail if yes
	 * 
	 * @param player
	 */
	public void checkIfPlayerHaveOutOfJailCard(Player player) {
		if (player.getNrCardJail() != 0) {
			player.updateCardsJail(-1);
			System.out.println("You use one card out of jail");
		} else {
			player.setInJail(true);
			movePlayerGUI(player, calcCellToMove(player, 10));
			//player.muitoAldrabado();
			player.setPos(board.getBoardBox(10));
			player.setNrOfRolls(false); // Reset number of rolls
			System.out.println("You go to jail!!!");
		}
	}

	public int calcCellToMove(Player player, int destinationPos) {// TODO
																	// correguir
		if (player.getPos().getPos() < destinationPos)
			return (destinationPos - player.getPos().getPos());
		else if (player.getPos().getPos() > destinationPos)
			return (40 - player.getPos().getPos() + destinationPos);
		return 0;
	}

	/**
	 * Method to buy property
	 * 
	 * @param player
	 *            to buy property
	 */
	public void buyProperty(Player player) {
		BoardBox boardToBuy = player.getPos();

		Scanner s = new Scanner(System.in);
		char option;
		if ((boardToBuy instanceof Property)) {
			if (!((Property) (boardToBuy)).getSold()) {
				System.out.print("Do you want to buy >" + boardToBuy.getName() + "< Property for a value of: "
						+ ((Property) (boardToBuy)).getAmount() + " (y or n) >");
				option = 'y';// TODO s.next().charAt(0);
				if (option == 'y') {
					player.buyProperty(((Property) (boardToBuy)), board.getMaxPropertiesPerGroup(((Property) (boardToBuy)).getIdGroup()));
				} else {
					// TODO LEILAOO!!!!!!
				}
			} else if (!player.equals(((Property) (boardToBuy)).getOwner())
					&& !((Property) (boardToBuy)).getMortgage()) {
				optimizedPayBill(player, (Property) (boardToBuy));
			}
		}
	}

	/**
	 * Method to pay a bill if a player is on other player property
	 * 
	 * @param player
	 *            that will pay the bill
	 * @param property
	 *            property where player is on
	 */
	public void optimizedPayBill(Player player, Property property) {
		int valueToPay = 0;
		
		if (!player.getPropertiesOwned().contains(property) && !property.getMortgage()) {
			if ((property instanceof NormalProperty))
				valueToPay = ((NormalProperty) (property)).getValueToPay();
			if ((property instanceof RailRoadProperty))
				valueToPay = ((RailRoadProperty) (property)).getValueToPay();
			if ((property instanceof ServiceProperty))
				valueToPay = ((ServiceProperty) (property)).getValueToPay(player.getDicesValue());

			player.updateBalance(-valueToPay);
			property.getOwner().updateBalance(valueToPay);
			System.out.println("WARNING !!!! Value to pay: " + valueToPay);
		}
	}

	/**
	 * Method to show information about properties that a player have.
	 * 
	 * @param player
	 *            to be showed information
	 */
	private void showProperties(Player player) {
		for (Property vp : player.getPropertiesOwned()) {
			System.out.println("Property name: " + vp.getName() + " Nr: " + player.getPropertiesNr(vp));
		}
	}

	public void mortgage(Player player, Property property) {
		if (player.equals(property.getOwner()) && !property.getMortgage()) {
			property.setMortgage(true);
			player.updateBalance(property.getMortgageValue());
		}
	}

	public void unMortgage(Player player, Property property) {
		if (player.equals(property.getOwner()) && property.getMortgage()) {
			property.setMortgage(false);
			player.updateBalance(-property.getMortgageValueBack());
		}
	}

	/**
	 * Method to sell houses
	 * 
	 * @param player
	 *            who will sell the houses
	 * @param nProperty
	 *            to sell the houses
	 * @param n
	 *            number of houses to be sold
	 */
	private void sellHouses(Player player, NormalProperty nProperty, int n) {
		if (player.equals(nProperty.getOwner()) && nProperty.getNrHouses() >= n) {
			nProperty.sellHouse(n);
			player.updateBalance(n * (nProperty.getHouseCost() / 2));
		} else
			System.out.println("You dont have " + n + " houses.");
	}

	/**
	 * Method to sell hotel
	 * 
	 * @param player
	 *            who will sell the hotel
	 * @param nProperty
	 *            to sell the hotel
	 * @param n
	 *            number of hotel to be sold
	 */
	private void sellHotel(Player player, NormalProperty nProperty) {
		if (player.equals(nProperty.getOwner()) && nProperty.getNrHotels() == 1) {
			nProperty.sellHotel();
			player.updateBalance(nProperty.getHotelCost() / 2);
		} else
			System.out.println("You dont have hotel.");
	}

	/**
	 * Method to create houses
	 * 
	 * @param player
	 *            who will buy the houses
	 * @param nProperty
	 *            to place the houses
	 * @param n
	 *            number of houses to be purchased
	 */
	private void createHouses(Player player, NormalProperty nProperty, int n) {
		if (player.haveAllPropertiesGroup(nProperty)) {
			if ((player.getBalance() >= nProperty.getHouseCost() * n) && (n > 0 && n <= 4)
					&& nProperty.canBuildHouse()) {
				player.updateBalance(-nProperty.getHouseCost() * n);
				nProperty.buildHouse(n);
			} else {
				System.out.println("You have no money or enter a wrong number or cant build more");
			}
		}
	}

	/**
	 * Method to create hotels
	 * 
	 * @param player
	 *            who will buy the hotel
	 * @param nProperty
	 *            to place the hotel
	 */
	private void createHotel(Player player, NormalProperty nProperty) {
		if (nProperty.canBuildHotel() && (player.getBalance() >= nProperty.getHotelCost())) {
			player.updateBalance(-nProperty.getHotelCost());
			nProperty.buildHotel();
		} else {
			System.out.println("You cant build Hotel or dont have enought money");
		}
	}

	public int randomGenerate(){
		// Generate the random Chance card to be choose
		Random r = new Random();
		return (r.nextInt(15) + 1);
	}
	/**
	 * Generate random number from 1 to 15 and associate to a chance card,
	 * player will have an action from that card generated.
	 * 
	 * @param player
	 *            that have an action
	 */
	private void gerateChance(Player player) {
		int option = 0;
		// Generate the random Chance card to be choose
		Random r = new Random();
		option = r.nextInt(15) + 1;
		chanceOption = option; 
		switch (option) {
		case 1:
			movePlayerGUI(player, calcCellToMove(player, 0));
			player.setPos(board.getBoardBox(0));
			player.updateBalance(200);
			break;
		case 2:
			 if (player.getPos().getPos() > 24)
			 player.updateBalance(200);
			 movePlayerGUI(player, calcCellToMove(player, 24));
			 player.setPos(board.getBoardBox(24));
			break;
		case 3:
			 if (player.getPos().getPos() > 11)
			 player.updateBalance(200);
			 movePlayerGUI(player, calcCellToMove(player, 11));
			 player.setPos(board.getBoardBox(11));
			break;
		case 4:
			 int aux = 0;
			 if (player.getPos().getPos() == 7)
			 aux = 15;
			 if (player.getPos().getPos() == 22)
			 aux = 25;
			 if (player.getPos().getPos() == 36)
			 aux = 5;
			 movePlayerGUI(player, calcCellToMove(player, aux));
			 player.setPos(board.getBoardBox(aux));
			
			 // If have owner, player will pay 2 times the value of the rent.
			 if (((Property) (board.getBoardBox(aux))).getSold())
			 optimizedPayBill(player, ((Property) (board.getBoardBox(aux))));
			break;
		case 5:
			 int val = 0;
			 if (player.getPos().getPos() == 7 || player.getPos().getPos() ==
			 36)
			 val = 12;
			 if (player.getPos().getPos() == 22)
			 val = 28;
			 movePlayerGUI(player, calcCellToMove(player, val));
			 player.setPos(board.getBoardBox(val));
			
			 // If have owner, player roll dices and pay 10 times the value to
			 // owner
			 if (((Property) (board.getBoardBox(val))).getSold()) {
			 int num = (rollDice(dice1) + rollDice(dice2));
			 ((Property)
			 (board.getBoardBox(val))).getOwner().updateBalance(num * 10);
			 player.updateBalance(-(num * 10));
			 }

			break;
		case 6:
			player.updateBalance(50);
			break;
		case 7:
			player.updateCardsJail(1);
			break;
		case 8:
			 movePlayerGUI(player, calcCellToMove(player,
			 (player.getPos().getPos() - 3)));
			 player.setPos(board.getBoardBox(player.getPos().getPos() - 3));
			break;
		case 9:
			 checkIfPlayerHaveOutOfJailCard(player);
			break;
		case 10:
			payPerEachHouseAndHotels(player, 25, 100);
			break;
		case 11:
			player.updateBalance(-15);
			break;
		case 12:
			 if (player.getPos().getPos() > 5)
			 player.updateBalance(200);
			 movePlayerGUI(player, calcCellToMove(player, 5));
			 player.setPos(board.getBoardBox(5));
			break;
		case 13:
			 movePlayerGUI(player, calcCellToMove(player, 39));
			 player.setPos(board.getBoardBox(39));
			break;
		case 14:
			player.updateBalance(150);
			break;
		case 15:
			for (Player p : this.players) {
				p.updateBalance(50);
				player.updateBalance(-50);
			}
			break;
		}
		System.out.println("CHANCE CARD Nº -> " + option);
		System.out.println("chance option valeu -> " + chanceOption);
	}

	/**
	 * Generate random number from 1 to 15 and associate to a community card,
	 * player will have an action from that card generated.
	 * 
	 * @param player
	 *            that have an action
	 * @throws InterruptedException
	 */
	private void gerateCommunity(Player player) {
		int option = 0;

		// Generate the random Community card to be choose
		Random r = new Random();
		option = r.nextInt(16) + 1;
		communityOption = option;
		switch (option) {
		case 1:
			movePlayerGUI(player, calcCellToMove(player, 0));
			player.setPos(board.getBoardBox(0));
			player.updateBalance(200);
			break;
		case 2:
			player.updateBalance(200);
			break;
		case 3:
			player.updateBalance(-50);
			break;
		case 4:
			player.updateBalance(50);
			break;
		case 5:
			player.updateCardsJail(1);
			break;
		case 6:
			 checkIfPlayerHaveOutOfJailCard(player);
			break;
		case 7:
			player.updateBalance(20);
			break;
		case 8:
			for (Player p : this.players) {
				p.updateBalance(-10);
				player.updateBalance(10);
			}
			break;
		case 9:
			player.updateBalance(100);
			break;
		case 10:
			player.updateBalance(-100);
			break;
		case 11:
			player.updateBalance(-50);
			break;
		case 12:
			player.updateBalance(25);
			break;
		case 13:
			payPerEachHouseAndHotels(player, 40, 115);
			break;
		case 14:
			player.updateBalance(10);
			break;
		case 15:
			player.updateBalance(100);
			break;
		case 16:
			player.updateBalance(100);
			break;
		}
		System.out.println("COMMUNITY CHEST CARD Nº -> " + option);
	}

	/**
	 * For each property owned by player, will check if have houses and hotels,
	 * and pay a value by a number of hoses and hotels
	 * 
	 * @param player
	 * @param valueToPayPerHouse
	 * @param valueToPayPerHotel
	 */
	private void payPerEachHouseAndHotels(Player player, int valueToPayPerHouse, int valueToPayPerHotel) {
		int countHotel = 0;
		int countHouse = 0;
		for (Property np : player.getPropertiesOwned()) {
			if (np.getPos() != 5 && np.getPos() != 15 && np.getPos() != 25 && np.getPos() != 35 && np.getPos() != 12
					&& np.getPos() != 28) {
				countHotel += ((NormalProperty) np).getNrHotels();
				countHouse += ((NormalProperty) np).getNrHouses();
			}
		}
		player.updateBalance(-((valueToPayPerHotel * countHotel) + (valueToPayPerHouse * countHouse)));
	}

	/**
	 * Update all game - MAIN FUNCTION!!!!!
	 */
	public void updateGame(Player player) {

		if (player.getInJail())
			updatePlayerInJail(player);
		else
			updatePlayer(player);

		infoPlayer(player);

		if (player.getCellsToMove() == 0)
			buyProperty(player);

	}
	
	public Integer getCommunityOption() {
		return communityOption;
	}

	public void setCommunityOption(Integer communityOption) {
		this.communityOption = communityOption;
	}
	
	public Integer getChanceOption() {
		return chanceOption;
	}

	public void setChanceOption(Integer chanceOption) {
		this.chanceOption = chanceOption;
	}
	
	public Board getBoard() {
		return board;
	}
	/**
	 * Print player information
	 * 
	 * @param player
	 *            to be showed information
	 */
	public void infoPlayer(Player player) {
		System.out.println("Player balance: " + player.getBalance());
		System.out.println("Player pos: " + player.getPos().getPos());
		System.out.println("Player Double: " + player.getNrOfRolls());
		System.out.println("Player in jail: " + player.getInJail());
		// showProperties(player);
	}

	public static void main(String[] args) {
		Game game = new Game();
		Scanner s = new Scanner(System.in);
		int option = 1;

		Player player1 = new Player("Pedro", game.dog, 10000, game.board.getBoardBox(0), 1);
		game.addPlayer(player1);

		Player player2 = new Player("Faby", game.car, 10000, game.board.getBoardBox(0), 2);
		game.addPlayer(player2);

		while (option != 0) {

			System.out.print("Generate dice " + player1.getName() + " (0 to leave) > ");
			option = s.nextInt();
			game.updateGame(player1);
			System.out.println("====================================");
			System.out.print("Generate dice " + player2.getName() + " (0 to leave) > ");
			option = s.nextInt();
			game.updateGame(player2);

			System.out.println("======================================================================");
		}
	}
}
