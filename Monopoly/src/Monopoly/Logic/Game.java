package Monopoly.Logic;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Game {

	Dice dice1;
	Dice dice2;
	Board board;
	BoardBox boardbox;
	PlayerSymbol dog;
	PlayerSymbol car;
	PlayerSymbol ship;
	PlayerSymbol boot;
	PlayerSymbol hat;
	PlayerSymbol iron;
	PlayerSymbol thimble;
	PlayerSymbol wheelbarrow;

	Vector<Player> player;

	private final int JAILVALUE = 500;
	private final int GOVALUE = 2000;

	public Game() {
		player = new Vector<Player>();

		dog = new PlayerSymbol(1, "Dog");
		car = new PlayerSymbol(2, "Car");
		ship = new PlayerSymbol(3, "Ship");
		boot = new PlayerSymbol(4, "Boot");
		hat = new PlayerSymbol(5, "Hat");
		iron = new PlayerSymbol(6, "Iron");
		thimble = new PlayerSymbol(7, "Thimble");
		wheelbarrow = new PlayerSymbol(8, "Wheelbarrow");

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
		return player;
	}

	/**
	 * Add player
	 * 
	 * @param player
	 *            to be added
	 */
	public void addPlayer(Player player) {
		this.player.add(player);
	}

	/**
	 * Remove player
	 * 
	 * @param player
	 *            to be removed
	 */
	public void removePlayer(Player player) {
		this.player.remove(player);
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

	/**
	 * Method to update the player on board
	 * 
	 * @param player
	 *            to be update on board
	 */
	private void updatePlayer(Player player) {
		int totalDiceValue, atualPlayerPos, dif;

		// Store the value of 2 rolled dices
		totalDiceValue = get2RollDices(dice1, dice2);

		// Update number of tries from player, if get 3 times goes to jail.
		player.setNrOfRolls(sameValuesDice(dice1, dice2));
		if (player.getNrOfRolls() == 3) {
			// Check if have out of jail card and leave the jail if yes
			if (player.getNrCardJail() != 0) {
				player.updateCardsJail(-1);
				System.out.println("You use one card out of jail");
			} else {
				player.setInJail(true);
				player.setPos(board.getBoardBox(10));
				player.setNrOfRolls(false); // Reset number of rolls
				System.out.println("You go to jail!!!");
			}
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
			player.setPos(board.getBoardBox((dif - 1)));
			player.updateBalance(GOVALUE);
		} else
			player.setPos(board.getBoardBox(atualPlayerPos));

		// Check if the new position is the JailBox, if yes goes to jail.
		if (player.getPos() == board.getBoardBox(30)) {
			// Check if have out of jail card and leave the jail if yes
			if (player.getNrCardJail() != 0) {
				player.updateCardsJail(-1);
				System.out.println("You use one card out of jail");
			} else {
				player.setInJail(true);
				player.setPos(board.getBoardBox(10));
				player.setNrOfRolls(false); // Reset number of rolls
				System.out.println("You go to jail!!!");
			}
		}

		if ((player.getPos() == board.getBoardBox(7)) || (player.getPos() == board.getBoardBox(22))
				|| (player.getPos() == board.getBoardBox(36))) {
			gerateChance(player);
			System.out.println("CHANCE BOX");
		}

		if ((player.getPos() == board.getBoardBox(2)) || (player.getPos() == board.getBoardBox(17))
				|| (player.getPos() == board.getBoardBox(33))) {
			gerateCommunity(player);
			System.out.println("COMMUNITY BOX");
		}

		if ((player.getPos() == board.getBoardBox(4)) || (player.getPos() == board.getBoardBox(38)))
			player.updateBalance(-200);

		System.out.println("Dice value: " + (dice1.getValue() + dice2.getValue()));
	}

	/**
	 * Method to update the player in Jail
	 * 
	 * @param player
	 *            to be updated on Jail
	 */
	private void updatePlayerInJail(Player player) {
		int totalDiceValue, atualPlayerPos;

		// Store the value of 2 rolled dices
		totalDiceValue = get2RollDices(dice1, dice2);

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
		}

		// If player leaves the jail, will update it for the next position from
		// rolled dice.
		if (!player.getInJail()) {
			atualPlayerPos = player.getPos().getPos();
			player.setPos(board.getBoardBox(atualPlayerPos + totalDiceValue));
		}
	}

	/**
	 * Method to buy property
	 * 
	 * @param player
	 *            to buy property
	 */
	private void buyProperty(Player player) {
		BoardBox boardToBuy = player.getPos();

		Scanner s = new Scanner(System.in);
		char option;

		if ((boardToBuy instanceof Property)) {
			if (!((Property) (boardToBuy)).getSold()) {
				System.out.print("Do you want to buy >" + boardToBuy.getName() + "< Property for a value of: "
						+ ((Property) (boardToBuy)).getAmount() + " (y or n) >");
				option = s.next().charAt(0);
				if (option == 'y') {
					player.buyProperty(((Property) (boardToBuy)));
					player.updateBlPropertyGroup(((Property) (boardToBuy)).getIdGroup(),
							board.getMaxPropertiesPerGroup(((Property) (boardToBuy)).getIdGroup()));
				} else {
					// TODO LEILAOO!!!!!!
				}
			} else if (!player.equals(((Property) (boardToBuy)).getOwner())) {
				payBill(player, ((Property) (boardToBuy)));
			}
		}
		showProperties(player);
	}

	/**
	 * Method to pay a bill if a player is on other player property
	 * 
	 * @param player
	 *            that will pay the bill
	 * @param property
	 *            property where player is on
	 */
	private void payBill(Player player, Property property) {
		if ((property instanceof NormalProperty)) {
			player.updateBalance(-((NormalProperty) (property)).getValueToPay());
			((NormalProperty) (property)).getOwner().updateBalance(((NormalProperty) (property)).getValueToPay());
			System.out.println("WARNING !!!! Value to pay: " + ((NormalProperty) (property)).getValueToPay());
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

	private void gerateChance(Player player) {
		int option = 0;

		// Generate the random Chance card to be choose
		Random r = new Random();
		option = r.nextInt(15) + 1;

		switch (option) {
		case 1:
			player.setPos(board.getBoardBox(0));
			player.updateBalance(200);
			break;
		case 2:
			if (player.getPos().getPos() > 24)
				player.updateBalance(200);
			player.setPos(board.getBoardBox(24));
			break;
		case 3:
			if (player.getPos().getPos() > 11)
				player.updateBalance(200);
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
			player.setPos(board.getBoardBox(aux));

			// If have owner, player will pay 2 times the value of the rent.
			if (((Property) (board.getBoardBox(aux))).getSold())
				payBill(player, ((Property) (board.getBoardBox(aux))));
			break;
		case 5:
			int val = 0;
			if (player.getPos().getPos() == 7 || player.getPos().getPos() == 36)
				val = 12;
			if (player.getPos().getPos() == 22)
				val = 28;
			player.setPos(board.getBoardBox(val));

			// If have owner, player roll dices and pay 10 times the value to
			// owner
			if (((Property) (board.getBoardBox(val))).getSold()) {
				int num = (rollDice(dice1) + rollDice(dice2));
				((Property) (board.getBoardBox(val))).getOwner().updateBalance(num * 10);
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
			player.setPos(board.getBoardBox(player.getPos().getPos() - 3));
			break;
		case 9:
			player.setPos(board.getBoardBox(10));
			break;
		case 10:
			int countHotel = 0;
			int countHouse = 0;
			for (Property np : player.getPropertiesOwned()) {
				if (np.getPos() != 5 && np.getPos() != 15 && np.getPos() != 25 && np.getPos() != 35 && np.getPos() != 12
						&& np.getPos() != 28) {
					countHotel += ((NormalProperty) np).getNrHotels();
					countHouse += ((NormalProperty) np).getNrHouses();
				}
			}
			player.updateBalance(-((100 * countHotel) + (25 * countHouse)));
			break;
		case 11:
			player.updateBalance(-15);
			break;
		case 12:
			if (player.getPos().getPos() > 5)
				player.updateBalance(200);
			player.setPos(board.getBoardBox(5));
			break;
		case 13:
			player.setPos(board.getBoardBox(39));
			break;
		case 14:
			player.updateBalance(150);
			break;
		case 15:
			for (Player p : this.player) {
				p.updateBalance(50);
				player.updateBalance(-50);
			}
			break;
		}
	}

	private void gerateCommunity(Player player) {
		int option = 0;

		// Generate the random Community card to be choose
		Random r = new Random();
		option = r.nextInt(16) + 1;

		switch (option) {
		case 1:
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
			player.setPos(board.getBoardBox(10));
			break;
		case 7:
			player.updateBalance(20);
			break;
		case 8:
			for (Player p : this.player) {
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
			int countHotel = 0;
			int countHouse = 0;
			for (Property np : player.getPropertiesOwned()) {
				if (np.getPos() != 5 && np.getPos() != 15 && np.getPos() != 25 && np.getPos() != 35 && np.getPos() != 12
						&& np.getPos() != 28) {
					countHotel += ((NormalProperty) np).getNrHotels();
					countHouse += ((NormalProperty) np).getNrHouses();
				}
			}
			player.updateBalance(-((115 * countHotel) + (40 * countHouse)));
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
	}

	/**
	 * Update all game - MAIN FUNCTION!!!!!
	 */
	private void updateGame(Player player) {

		if (player.getInJail())
			updatePlayerInJail(player);
		else
			updatePlayer(player);

		infoPlayer(player);

		buyProperty(player);

	}

	/**
	 * Print player information
	 * 
	 * @param player
	 *            to be showed information
	 */
	private void infoPlayer(Player player) {
		System.out.println("Player balance: " + player.getBalance());
		System.out.println("Player pos: " + player.getPos().getPos());
		System.out.println("Player Double: " + player.getNrOfRolls());
		System.out.println("Player in jail: " + player.getInJail());
	}

	public static void main(String[] args) {
		Game game = new Game();
		Scanner s = new Scanner(System.in);
		int option = 1;

		Player player1 = new Player("Pedro", game.dog, 10000, game.board.getBoardBox(0));
		game.addPlayer(player1);

		Player player2 = new Player("Faby", game.car, 10000, game.board.getBoardBox(0));
		game.addPlayer(player2);

		while (option != 0) {

			System.out.print("Generate dice Player1 (0 to leave) > ");
			option = s.nextInt();
			game.updateGame(player1);
			System.out.println("====================================");
			System.out.print("Generate dice Player2 (0 to leave) > ");
			option = s.nextInt();
			game.updateGame(player2);

			System.out.println("======================================================================");
		}
	}
}
