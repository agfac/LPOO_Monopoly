package Monopoly.Logic;

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
	 * Function to update the player on board
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
			player.setInJail(true);
			player.setPos(board.searchBoardBox(10));
			player.setNrOfRolls(false); // Reset number of rolls
			System.out.println("You go to jail!!!");
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
			player.setPos(board.searchBoardBox((dif - 1)));
			player.setBalance(player.getBalance() + GOVALUE);
		} else
			player.setPos(board.searchBoardBox(atualPlayerPos));

		// Check if the new position is the JailBox, if yes goes to jail.
		if (player.getPos() == board.searchBoardBox(30)) {
			player.setInJail(true);
			player.setPos(board.searchBoardBox(10));
			player.setNrOfRolls(false); // Reset number of rolls
			System.out.println("You go to jail!!!");
		}

		System.out.println("Dice value: " + (dice1.getValue() + dice2.getValue()));
	}

	/**
	 * Function to update the player in Jail
	 * 
	 * @param player
	 *            to be updated on Jail
	 */
	private void updatePlayerInJail(Player player) {
		int totalDiceValue, atualPlayerPos;

		// Store the value of 2 rolled dices
		totalDiceValue = get2RollDices(dice1, dice2);

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
			player.setBalance(player.getBalance() - JAILVALUE);
			player.setNrOfRollsInJail(true); // Reset number of rolls in jail
		}

		// If player leaves the jail, will update it for the next position from
		// rolled dice.
		if (!player.getInJail()) {
			atualPlayerPos = player.getPos().getPos();
			player.setPos(board.searchBoardBox(atualPlayerPos + totalDiceValue));
		}
	}

	/**
	 * Function to buy property
	 * @param player to buy property
	 */
	private void buyProperty(Player player){
		BoardBox boardToBuy = player.getPos();
		
		Scanner s = new Scanner(System.in);
		char option;
		
		if((boardToBuy instanceof Property)){
			if(!((Property)(boardToBuy)).getSold()){
				System.out.print("Do you want to buy >" + boardToBuy.getName() + "< Property for a value of: " + ((Property)(boardToBuy)).getAmount() + " (y or n) >");
				option = s.next().charAt(0);
				if(option == 'y'){
					player.buyProperty(((Property)(boardToBuy)));
				}
				else
				{
					//TODO LEILAOO DO CARALHO!!!!!!
				}
			}
			else
			{
				//TODO PAY THE RENT
				//player.payBill(((Property)(boardToBuy)).getOwner());
			}
		}
		
		
		
		showProperties(player);
	}
	
	/**
	 * 
	 * @param player
	 */
	private void showProperties(Player player){
		for(Property vp : player.getPropertiesOwned()){
			System.out.println("Property name: " + vp.getName());
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

		//buyProperty(player);

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

		Player player1 = new Player("Pedro", game.dog, 10000, game.board.searchBoardBox(0));
		game.addPlayer(player1);
		
		Player player2 = new Player("Faby", game.car, 20000, game.board.searchBoardBox(0));
		game.addPlayer(player2);
		
		while (option != 0) {

			System.out.print("Generate dice Player1 (0 to leave) > ");
			option = s.nextInt();
			game.updateGame(player1);
			
			System.out.print("Generate dice Player2 (0 to leave) > ");
			option = s.nextInt();
			game.updateGame(player2);
			
			System.out.println("======================================================================");
		}
	}
}
