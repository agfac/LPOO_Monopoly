package Monopoly.Logic;

import java.util.Scanner;

public class Game {

	Player player;
	PlayerSymbol playersymbol;
	Dice dice1;
	Dice dice2;
	Board board;
	BoardBox boardbox;

	private final int JAILVALUE = 500;
	private final int GOVALUE = 2000;
	
	public Game() {
		board = new Board();
		playersymbol = new PlayerSymbol(2, "Teste");
		player = new Player("Pedro", playersymbol, 10000, board.searchBoardBox(0));
		dice1 = new Dice();
		dice2 = new Dice();

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
	 * Function to move the player on board
	 * 
	 * @param player
	 *            to be moved on board
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
			player.setNrOfRolls(false); //Reset number of rolls
		}

		// Get player position and update with the new position from dices if
		// not in jail.
		atualPlayerPos = player.getPos().getPos();
		if (!player.getInJail()) {
			atualPlayerPos += totalDiceValue;
		}
		
		// Check if pass trough Go DoardBox and store the new player Position.
		if (atualPlayerPos > 39 && !player.getInJail()){
			dif = atualPlayerPos - 39;
			player.setPos(board.searchBoardBox((dif - 1)));
			player.setBalance(player.getBalance()+GOVALUE);
		} 
		else
			player.setPos(board.searchBoardBox(atualPlayerPos));
		
		// Check if the new position is the JailBox
		if(player.getPos() == board.searchBoardBox(30)){
			player.setInJail(true);
		}
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
		if (sameValuesDice(dice1, dice2)){
			player.setInJail(false);
			player.setNrOfRollsInJail(true); //Reset number of rolls in jail
		}

		// Check if the number of rolls in jail are 3, if yes exit from jail
		if (player.getNrOfRollsInJail() == 3) {
			player.setInJail(false);
			player.setBalance(player.getBalance() - JAILVALUE);
			player.setNrOfRollsInJail(true); //Reset number of rolls in jail
		}

		// If player leaves the jail, will update it for the next position.
		if (!player.getInJail()) {
			atualPlayerPos = player.getPos().getPos();
			player.setPos(board.searchBoardBox(atualPlayerPos));
		}
	}
	
	/**
	 * Function to buy property
	 * @param player to buy property
	 */
	private void buyProperty(Player player){
		BoardBox boardToBuy;
		boardToBuy = player.getPos();
		int pos = player.getPos().getPos();
		
		System.out.println("Do you want to buy >" + boardToBuy.getName() + "< Property for a value of: " );
	}

	/**
	 * Update all game - MAIN FUNCTION!!!!!
	 */
	private void updateGame() {

		if (player.getInJail())
			updatePlayerInJail(player);
		
		if (!player.getInJail())
			updatePlayer(player);

	}

	public static void main(String[] args) {
		Game game = new Game();
		Scanner s = new Scanner(System.in);
		int option = 1;
		while (option != 0) {
			System.out.println("Player balance: " + game.player.getBalance());
			System.out.println("Dice value: " + (game.dice1.getValue() + game.dice2.getValue()));
			System.out.println("Player pos: " + game.player.getPos().getPos());
			System.out.println("Player Double: " + game.player.getNrOfRolls());
			System.out.println("Player in jail: " + game.player.getInJail());
			System.out.print("Generate dice... (0 to leave) > ");
			option = s.nextInt();
			game.updateGame();
			
			System.out.println("======================================================================");
		}
	}
}
