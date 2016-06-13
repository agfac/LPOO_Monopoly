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
	private String buyPropertyOption = null;
	private int timeToShowChanceCard = 0;
	private int timeToShowCommunityCard = 0;
	private int timeToShowDice = 0;
	private Player currentPlayer;

	/**
	 * Constructor of game
	 * 
	 * @param players
	 *            vector
	 */
	public Game(Vector<Player> players) {
		this.players = players;
		board = new Board();
		dice1 = new Dice();
		dice2 = new Dice();
	}

	/**
	 * Change between players
	 */
	public void updateCurrentPlayer() {
		int aux = players.indexOf(currentPlayer);
		if (aux == players.size() - 1)
			currentPlayer = players.get(0);
		else
			currentPlayer = players.get(aux + 1);
	}

	/**
	 * Get the current player
	 * @return player
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * Get the time to show a chance card on graphic window
	 * @return time to show a chance card on graphic window
	 */
	public int getTimeToShowChanceCard() {
		return timeToShowChanceCard;
	}

	/**
	 * Method to decrement time when showing a chance card
	 */
	public void decTimeToShowChanceCard() {
		this.timeToShowChanceCard --;
	}

	/**
	 * Get the time to show a community card on graphic window
	 * @return time to show a community card on graphic window
	 */
	public int getTimeToShowCommunityCard() {
		return timeToShowCommunityCard;
	}
	
	/**
	 * Method to decrement time when showing a community card
	 */
	public void decTimeToShowCommunityCard() {
		this.timeToShowCommunityCard --;
	}
	
	/**
	 * Get time when showing dices values on graphic window
	 * @return
	 */
	public int getTimeToShowDice() {
		return timeToShowDice;
	}
	
	/**
	 * Method to decrement time when showing dices
	 */
	public void decTimeToShowDice() {
		this.timeToShowDice --;
	}
	
	/**
	 * Get dice 1
	 * @return Dice 1
	 */
	public Dice getDice1() {
		return dice1;
	}

	/**
	 * Get dice 2
	 * @return Dice 2
	 */
	public Dice getDice2() {
		return dice2;
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
	 * Method to generate randomly the 1st player to start the game.
	 * @param players Vector of players in game
	 */
	public void setPlayers(Vector<Player> players) {
		this.players = players;

		Random generator = new Random();
		int randomInt = generator.nextInt(players.size());
		this.currentPlayer = players.get(randomInt);
	}

	/**
	 * Method to receive the request from server to buy property
	 * @param buyPropertyOption Receive Yes or no as reply
	 */
	public void setBuyPropertyOption(String buyPropertyOption) {
		this.buyPropertyOption = buyPropertyOption;
	}

	/**
	 * Get the option choose on setBuyPropertyOption
	 * @return String yes or no
	 */
	public String getBuyPropertyOption() {
		return buyPropertyOption;
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
	public int rollDice(Dice dice) {
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
	public int get2RollDices(Dice dice1, Dice dice2) {
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
	public boolean sameValuesDice(Dice dice1, Dice dice2) {
		return (dice1.getValue() == dice2.getValue()) ? true : false;
	}

	/**
	 * Check if player is on a special boardBox
	 * 
	 * @param player
	 */
	public void checkSpecialBoardBox(Player player) {
		if ((player.getPos() == board.getBoardBox(7))
				|| (player.getPos() == board.getBoardBox(22) || (player.getPos() == board.getBoardBox(36)))
						&& chanceOption == null) {
			timeToShowChanceCard = 20;
			player.setMensage("CHANCE BOX");
			gerateChance(player);
		}

		if ((player.getPos() == board.getBoardBox(2))
				|| (player.getPos() == board.getBoardBox(17) || (player.getPos() == board.getBoardBox(33)))
						&& communityOption == null) {
			timeToShowCommunityCard = 20;
			player.setMensage("COMMUNITY BOX");
			gerateCommunity(player);
		}

		// Check if the new position is the JailBox, if yes goes to jail.
		if (player.getPos() == board.getBoardBox(30)) {
			checkIfPlayerHaveOutOfJailCard(player);
		}
	}

	/**
	 * Method to update the player on board
	 * 
	 * @param player
	 *            to be update on board
	 */
	public void updatePlayer(Player player) {
		chanceOption = null;
		communityOption = null;
		player.setMensage("");
		player.setDirection("forward");
		timeToShowDice = 20;
		int totalDiceValue, atualPlayerPos, dif;
		
		// Store the value of 2 rolled dices
		totalDiceValue = get2RollDices(dice1, dice2);

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

		if ((player.getPos() == board.getBoardBox(4)) || (player.getPos() == board.getBoardBox(38))){
			player.setMensage("!!!WARNING!!! Valeu to pay: " + ((TaxBox) board.getBoardBox(4)).getTaxValue());
			player.updateBalance(-((TaxBox) board.getBoardBox(4)).getTaxValue());
		}
	}

	/**
	 * Method to update payer on graphic window
	 * 
	 * @param player
	 *            to be moved
	 * @param moveValeu
	 *            to be moved
	 */
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
		player.setMensage("");
		int totalDiceValue, atualPlayerPos;

		// Store the value of 2 rolled dices
		totalDiceValue = get2RollDices(dice1, dice2);

		player.setDicesValue(totalDiceValue);

		// Increment the tries in jail
		player.setNrOfRollsInJail(sameValuesDice(dice1, dice2));

		// Check if player got double, if yes will exit from jail
		if (sameValuesDice(dice1, dice2)) {
			player.setInJail(false);
			player.setNrOfRollsInJail(true); // Reset number of rolls in jail
			player.setMensage("You got double, you can leave jail now.");
		}

		// Check if the number of rolls in jail are 3, if yes exit from jail and
		// pay fee
		if (player.getNrOfRollsInJail() == 3) {
			player.setInJail(false);
			player.updateBalance(-JAILVALUE);
			player.setNrOfRollsInJail(true); // Reset number of rolls in jail
			player.setMensage("!!!WARNING!!! You must pay " + JAILVALUE + " to go out of jail");
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
			player.setMensage("!!!WARNING!!! You use one card out of jail");
		} else {
			player.setInJail(true);
			movePlayerGUI(player, calcCellToMove(player, 10));
			// player.muitoAldrabado();
			player.setPos(board.getBoardBox(10));
			player.setNrOfRolls(false); // Reset number of rolls
			player.setMensage("!!!WARNING!!! You go to jail!!!");
		}
	}

	/**
	 * Method to determinate how many boarbox (Positions) need to move a player
	 * 
	 * @param player
	 *            to be moved
	 * @param destinationPos
	 *            to be moved
	 * @return number of boarbox (Positions) to move a player
	 */
	public int calcCellToMove(Player player, int destinationPos) {
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

		if ((boardToBuy instanceof Property)) {
			if (!((Property) (boardToBuy)).getSold()) {
				if (buyPropertyOption.equals("yes")) {
					player.setMensage("Property bought!");
					player.buyProperty(((Property) (boardToBuy)), board.getMaxPropertiesPerGroup(((Property) (boardToBuy)).getIdGroup()));
					buyPropertyOption = null;
					player.setMensage("Property purchased successful!");
				}
			}
			else
				player.setMensage("!!!WARNING!!! You can't buy this property!");
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
	public void optimizedPayBill(Player player, BoardBox property) {

		if ((property instanceof Property)) {
			if (!player.equals(((Property)(property)).getOwner()) && !((Property)(property)).getMortgage()) {
				int valueToPay = 0;

				if (!player.getPropertiesOwned().contains(property) && !((Property)(property)).getMortgage()) {
					if ((property instanceof NormalProperty))
						valueToPay = ((NormalProperty) (property)).getValueToPay();
					if ((property instanceof RailRoadProperty))
						valueToPay = ((RailRoadProperty) (property)).getValueToPay();
					if ((property instanceof ServiceProperty))
						valueToPay = ((ServiceProperty) (property)).getValueToPay(player.getDicesValue());

					player.updateBalance(-valueToPay);
					((Property)(property)).getOwner().updateBalance(valueToPay);
					player.setMensage("!!!WARNING!!! Value to pay: " + valueToPay);
					
				}
			}
		}
	}

	/**
	 * Method to mortgage a property
	 * 
	 * @param player
	 *            to mortgage the property
	 * @param property
	 *            to be mortgage
	 */
	public boolean mortgage(Player player, Property property) {
		if (player.equals(property.getOwner()) && !property.getMortgage()) {
			if ( property instanceof NormalProperty && (((NormalProperty )property).getNrHotels() != 0 || ((NormalProperty )property).getNrHouses() != 0 )){
				player.setMensage("!!!WARNING!!! You can't mortgage this property.");
				return false;
			}
			else {
				property.setMortgage(true);
				player.updateBalance(property.getMortgageValue());
				player.setMensage("Property mortgage successful!");
				return true;
			}
		}
		player.setMensage("!!!WARNING!!! You can't mortgage this property.");
		return false;
	}

	/**
	 * Method to 'unmortgage' a property
	 * 
	 * @param player
	 *            to 'unmortgage' the property
	 * @param property
	 *            to be 'unmortgage'
	 */
	public boolean unMortgage(Player player, Property property) {
		if (player.equals(property.getOwner()) && property.getMortgage()) {
			property.setMortgage(false);
			player.updateBalance(-property.getMortgageValueBack());
			player.setMensage("Property unmortgage successful");
			return true;
		}
		player.setMensage("!!!WARNING!!! You can't unmortgage this property.");
		return false;
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
	public boolean sellHouses(Player player, NormalProperty nProperty, int n) {
		if (player.equals(nProperty.getOwner()) && nProperty.getNrHouses() >= n) {
			nProperty.sellHouse(n);
			player.updateBalance(n * (nProperty.getHouseCost() / 2));
			player.setMensage("House sold successful!");
			return true;
		} else
			player.setMensage("!!!WARNING!!! You don't have houses.");
		return false;
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
	public boolean sellHotel(Player player, NormalProperty nProperty) {
		if (player.equals(nProperty.getOwner()) && nProperty.getNrHotels() == 1) {
			nProperty.sellHotel();
			nProperty.setNrHouses(4);
			player.updateBalance(nProperty.getHotelCost() / 2);
			player.setMensage("Hotel sold successful");
			return true;
		} else
			player.setMensage("!!!WARNING!!! You don't have hotel.");
		return false;
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
	public boolean createHouses(Player player, NormalProperty nProperty, int n) {
		if (player.haveAllPropertiesGroup(nProperty) && !(nProperty.getMortgage()) && nProperty.getNrHotels() == 0) {
			if ((player.getBalance() >= nProperty.getHouseCost() * n) && (n > 0 && n <= 4)
					&& nProperty.canBuildHouse()) {
				player.updateBalance(-nProperty.getHouseCost() * n);
				nProperty.buildHouse(n);
				player.setMensage("House purchased successful");
				return true;
			} else {
				player.setMensage("!!!WARNING!!! You don't have money or can't build more");
				return false;
			}
		}
		return false;
	}

	/**
	 * Method to create hotels
	 * 
	 * @param player
	 *            who will buy the hotel
	 * @param nProperty
	 *            to place the hotel
	 */
	public boolean createHotel(Player player, NormalProperty nProperty) {
		if (nProperty.canBuildHotel() && (player.getBalance() >= nProperty.getHotelCost()) && !(nProperty.getMortgage())) {
			nProperty.setNrHouses(0);
			player.updateBalance(-nProperty.getHotelCost());
			nProperty.buildHotel();
			player.setMensage("Hotel purchased successful");
			return true;
		} else {
			player.setMensage("!!!WARNING!!! You can't build Hotel or don't have enought money");
			return false;
		}
	}

	/**
	 * Generate random number from 1 to 15 and associate to a chance card,
	 * player will have an action from that card generated.
	 * 
	 * @param player
	 *            that have an action
	 */
	public void gerateChance(Player player) {
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
			player.setMensage("CHANCE BOX: Advance to Go and collect 200$");
			break;
		case 2:
			if (player.getPos().getPos() > 24)
				player.updateBalance(200);
			movePlayerGUI(player, calcCellToMove(player, 24));
			player.setPos(board.getBoardBox(24));
			player.setMensage("CHANCE BOX: Advance to Illinois Avenue");
			break;
		case 3:
			if (player.getPos().getPos() > 11)
				player.updateBalance(200);
			movePlayerGUI(player, calcCellToMove(player, 11));
			player.setPos(board.getBoardBox(11));
			player.setMensage("CHANCE BOX: Advance to St. Charles Place");
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
			player.setMensage("CHANCE BOX: Advance to nearest RailRoad");
			break;
		case 5:
			int val = 0;
			if (player.getPos().getPos() == 7 || player.getPos().getPos() == 36)
				val = 12;
			if (player.getPos().getPos() == 22)
				val = 28;
			movePlayerGUI(player, calcCellToMove(player, val));
			player.setPos(board.getBoardBox(val));

			// If have owner, player roll dices and pay 10 times the value to
			// owner
			if (((Property) (board.getBoardBox(val))).getSold()) {
				int num = (rollDice(dice1) + rollDice(dice2));
				((Property) (board.getBoardBox(val))).getOwner().updateBalance(num * 10);
				player.updateBalance(-(num * 10));
			}
		player.setMensage("CHANCE BOX: Advance to nearest Utility");
			break;
		case 6:
			player.updateBalance(50);
			player.setMensage("CHANCE BOX: Receive 50$");
			break;
		case 7:
			player.updateCardsJail(1);
			player.setMensage("CHANCE BOX: You got an out of jail card");
			break;
		case 8:
			player.setDirection("back");
//			movePlayerGUI(player, calcCellToMove(player, (player.getPos().getPos() - 3)));
			movePlayerGUI(player, 3);
			player.setPos(board.getBoardBox(player.getPos().getPos() - 3));
			player.setMensage("CHANCE BOX: Go Back 3 spaces");
			break;
		case 9:
			checkIfPlayerHaveOutOfJailCard(player);
			player.setMensage("CHANCE BOX: Go to Jail");
			break;
		case 10:
			payPerEachHouseAndHotels(player, 25, 100);
			player.setMensage("CHANCE BOX: Pay 25$ for each house and 100$ for each hotel");
			break;
		case 11:
			player.updateBalance(-15);
			player.setMensage("CHANCE BOX: Pay 15$");
			break;
		case 12:
			if (player.getPos().getPos() > 5)
				player.updateBalance(200);
			movePlayerGUI(player, calcCellToMove(player, 5));
			player.setPos(board.getBoardBox(5));
			player.setMensage("CHANCE BOX: Go to Reading RailRoad");
			break;
		case 13:
			movePlayerGUI(player, calcCellToMove(player, 39));
			player.setPos(board.getBoardBox(39));
			player.setMensage("CHANCE BOX: Go to BoardWalk");
			break;
		case 14:
			player.updateBalance(150);
			player.setMensage("CHANCE BOX: Receive 150$");
			break;
		case 15:
			for (Player p : this.players) {
				p.updateBalance(50);
				player.updateBalance(-50);
			}
			player.setMensage("CHANCE BOX: Pay each player 50$");
			break;
		}
	}

	/**
	 * Generate random number from 1 to 15 and associate to a community card,
	 * player will have an action from that card generated.
	 * 
	 * @param player
	 *            that have an action
	 * @throws InterruptedException
	 */
	public void gerateCommunity(Player player) {
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
			player.setMensage("COMMUNITY CHEST: Advance to Go and collect 200$");
			break;
		case 2:
			player.updateBalance(200);
			player.setMensage("COMMUNITY CHEST: Receive 200$");
			break;
		case 3:
			player.updateBalance(-50);
			player.setMensage("COMMUNITY CHEST: Pay 50$");
			break;
		case 4:
			player.updateBalance(50);
			player.setMensage("COMMUNITY CHEST: Receive 50$");
			break;
		case 5:
			player.updateCardsJail(1);

			player.setMensage("COMMUNITY CHEST: You got an out of jail card");
			break;
		case 6:
			checkIfPlayerHaveOutOfJailCard(player);

			player.setMensage("COMMUNITY CHEST: Go to jail");
			break;
		case 7:
			player.updateBalance(20);
			player.setMensage("COMMUNITY CHEST: Receive 20$");
			break;
		case 8:
			for (Player p : this.players) {
				p.updateBalance(-10);
				player.updateBalance(10);
			}
			player.setMensage("COMMUNITY CHEST: Receive 10$ from each player");
			break;
		case 9:
			player.updateBalance(100);
			player.setMensage("COMMUNITY CHEST: Receive 100$");
			break;
		case 10:
			player.updateBalance(-100);
			player.setMensage("COMMUNITY CHEST: Pay 100$");
			break;
		case 11:
			player.updateBalance(-50);
			player.setMensage("COMMUNITY CHEST: Pay 50$");
			break;
		case 12:
			player.updateBalance(25);
			player.setMensage("COMMUNITY CHEST: Receive 25$");
			break;
		case 13:
			payPerEachHouseAndHotels(player, 40, 115);
			player.setMensage("COMMUNITY CHEST: Pay 25$ for each house and 100$ for each hotel");
			break;
		case 14:
			player.updateBalance(10);
			player.setMensage("COMMUNITY CHEST: Receive 10$");
			break;
		case 15:
			player.updateBalance(100);
			player.setMensage("COMMUNITY CHEST: Receive 100$");
			break;
		case 16:
			player.updateBalance(100);
			player.setMensage("COMMUNITY CHEST: Receive 100$");
			break;
		}
	}

	/**
	 * For each property owned by player, will check if have houses and hotels,
	 * and pay a value by a number of hoses and hotels
	 * 
	 * @param player
	 * @param valueToPayPerHouse
	 * @param valueToPayPerHotel
	 */
	public void payPerEachHouseAndHotels(Player player, int valueToPayPerHouse, int valueToPayPerHotel) {
		int countHotel = 0;
		int countHouse = 0;
		for (Property np : player.getPropertiesOwned()) {
			if (np.getPos() != 5 && np.getPos() != 15 && np.getPos() != 25 && np.getPos() != 35 && np.getPos() != 12
					&& np.getPos() != 28) {
				countHotel += ((NormalProperty) np).getNrHotels();
				countHouse += ((NormalProperty) np).getNrHouses();
			}
		}
		player.setMensage("!!!WARNING!!! Total value to pay: " + ((valueToPayPerHotel * countHotel) + (valueToPayPerHouse * countHouse)));
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

	}

	/**
	 * Get Community card generated
	 * 
	 * @return id of community card generated
	 */
	public Integer getCommunityOption() {
		return communityOption;
	}

	/**
	 * Update Community card
	 */
	public void setCommunityOption(Integer communityOption) {
		this.communityOption = communityOption;
	}

	/**
	 * Get Chance card generated
	 * 
	 * @return id of chance card generated
	 */
	public Integer getChanceOption() {
		return chanceOption;
	}

	/**
	 * Update Chance card
	 */
	public void setChanceOption(Integer chanceOption) {
		this.chanceOption = chanceOption;
	}

	/**
	 * Get the board of game
	 * 
	 * @return board
	 */
	public Board getBoard() {
		return board;
	}

}
