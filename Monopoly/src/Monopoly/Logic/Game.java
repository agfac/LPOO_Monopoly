package Monopoly.Logic;

public class Game {
	
	Player player;
	PlayerSymbol playersymbol;
	Dice dice;
	Board board;
	BoardBox boardbox;
	
	public Game(){
		board = new Board();
		playersymbol = new PlayerSymbol(2, "Teste");
		player = new Player("Pedro",playersymbol,2000, board.searchBoardBox(0));
		dice = new Dice();
		
	}
	
	/**
	 * Will roll the dice and return an int with value
	 * @return dice value
	 */
	private int rollDice(){
		dice.rollDice();
		return dice.getValue();
	}
	
	/**
	 * Function to move the player on board
	 * @param player to be moved on board
	 */
	private void movePlayer(Player player){
		int diceValue = rollDice();
		int atualPlayerPos = player.getPos().getPos();
		atualPlayerPos += diceValue;
		player.setPos(board.searchBoardBox(atualPlayerPos));
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		int n = game.player.getPos().getPos();
		System.out.println(n);
		game.movePlayer(game.player);
		System.out.println(game.player.getPos().getPos());
	}
}
