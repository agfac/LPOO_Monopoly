package Monopoly.Test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

import Monopoly.Logic.*;

public class TestRandom {
	protected  BufferedImage dogPiece;
	
	
	Vector<Player> players = new Vector<Player>();
	PlayerSymbol dog = new PlayerSymbol(1, "Dog", dogPiece);
	Board board = new Board();
	
	@Before
	public void mainTeste(){
		try {
			dogPiece = ImageIO.read(new File("resources/images/pieces/dog.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		players = new Vector<Player>();
		players.add(new Player("Pedro", dog, 10000, board.getBoardBox(0), 1));
		players.get(0).setPosition(1120, 890);
		players.add(new Player("Faby", dog, 10000, board.getBoardBox(0), 2));
		players.get(1).setPosition(1120, 930);
		
	}
	
	
	
	Game game = new Game(players);
	
	/**
	 * Test for test dices value, need to be between 2 and 12 at max. test for dices 0, 1 and 13
	 */
	@Test
	public void testDice() {
		game.updatePlayer(players.get(0));
		assertNotEquals(0, players.get(0).getDicesValue());
		assertNotEquals(1, players.get(0).getDicesValue());
		assertNotEquals(13, players.get(0).getDicesValue());
	}
	
	
	/**
	 * Test for player movement, should be the same value as the dice.
	 */
	@Test
	public void testPlayerMovement(){
		game.updatePlayer(players.get(0));
		assertEquals(players.get(0).getPos().getPos(), players.get(0).getDicesValue());
	}
	
	/**
	 * Test for player buy a property on position 1.
	 */
	@Test
	public void testBuyProperty(){
		players.get(0).setPos(board.getBoardBox(1));
		game.buyProperty(players.get(0));
		assertEquals(true, players.get(0).getPropertiesOwned().contains(board.getBoardBox(1)));
	}
	
	/**
	 * Test for player buy a property on position 1 with no money
	 */
	@Test
	public void testBuyPropertyWithoutSufficientMoney(){
		
		players.get(0).setBalance(10);
		players.get(0).setPos(board.getBoardBox(1));
		game.buyProperty(players.get(0));
		
		assertEquals(false, players.get(0).getPropertiesOwned().contains(board.getBoardBox(1)));
	}
	
	/**
	 * Test if player are in jail
	 */
	@Test
	public void testGoToJail(){
		game.checkIfPlayerHaveOutOfJailCard(players.get(0));
		assertEquals(true, players.get(0).getInJail());
	}
	
	/**
	 * Test if player can change position in jail.
	 */
	@Test
	public void testPlayerMovementInJail(){
		game.checkIfPlayerHaveOutOfJailCard(players.get(0));
		game.updatePlayer(players.get(0));
		assertEquals(10, players.get(0).getPos().getPos());
	}
	
	/**
	 * Test if player leaves the jail
	 */
	@Test
	public void testPlayerGetOutofJail(){
		game.checkIfPlayerHaveOutOfJailCard(players.get(0));
		do{
			game.updatePlayerInJail(players.get(0));
		}while(players.get(0).getInJail());
		assertEquals(false, players.get(0).getInJail());
	}
	
	/**
	 * Test if player leaves the jail if have an out of jail card
	 */
	@Test
	public void testPlayerGetOutofJailWithCard(){
		players.get(0).updateCardsJail(1);
		game.checkIfPlayerHaveOutOfJailCard(players.get(0));
		assertEquals(false, players.get(0).getInJail());
	}
	
	/**
	 * Test if player update balance when in a taxbox
	 */
	@Test
	public void testTaxBox(){
		do{
			game.updatePlayer(players.get(0));
		}while(players.get(0).getPos().getPos()!=((TaxBox) board.getBoardBox(4)).getPos());
		assertEquals(players.get(0).getPos().getPos(), ((TaxBox) board.getBoardBox(4)).getPos());
	}
	
	/**
	 * Test if player pay to other player a rent
	 */
	@Test
	public void testRentValues(){
		int valuePlayer2 = players.get(1).getBalance();
		players.get(0).setPos(board.getBoardBox(1));
		game.buyProperty(players.get(0));
		game.optimizedPayBill(players.get(1), ((Property) board.getBoardBox(1)));
		assertEquals(valuePlayer2-((NormalProperty) board.getBoardBox(1)).getValueToPay(), players.get(1).getBalance());
	}
	
	
	/**
	 * Test if player pay to other player a House rent
	 */
	@Test
	public void testRentValuesWithHouse(){
		int initialBalancePlayer2 = players.get(1).getBalance();
		
		//Player 1 buy property 1
		players.get(0).setPos(board.getBoardBox(1));
		game.buyProperty(players.get(0));
		players.get(0).setPos(board.getBoardBox(3));
		game.buyProperty(players.get(0));
		
		//Player 1 buy 3 houses
		game.createHouses(players.get(0), ((NormalProperty) board.getBoardBox(1)), 3);

		//Player 2 pay for the 3 Houses rent
		game.optimizedPayBill(players.get(1), ((Property) board.getBoardBox(1)));
		
		assertNotEquals(initialBalancePlayer2, players.get(1).getBalance());
	}
	
	
	/**
	 * Test if player pay to other player a Hotel rent
	 */
	@Test
	public void testRentValuesWithHotels(){
		int initialBalancePlayer2 = players.get(1).getBalance();
		
		//Player 1 buy property 1 and 3
		players.get(0).setPos(board.getBoardBox(1));
		game.buyProperty(players.get(0));
		players.get(0).setPos(board.getBoardBox(3));
		game.buyProperty(players.get(0));
		
		//Player 1 buy 4 houses and 1 hotel
		game.createHouses(players.get(0), ((NormalProperty) board.getBoardBox(1)), 4);
		game.createHotel(players.get(0), ((NormalProperty) board.getBoardBox(1)));
		
		//Player 2 pay for the Hotel rent
		game.optimizedPayBill(players.get(1), ((Property) board.getBoardBox(1)));
		
		assertNotEquals(initialBalancePlayer2, players.get(1).getBalance());
	}
	
	/**
	 * Test if player can create an house without having all properties of the group
	 */
	@Test
	public void testBuildHouseWithoutPermission(){
		//Player will only buy 1 property of this group, when to create a house, need to have all properties of this group.
		players.get(0).setPos(board.getBoardBox(1));
		game.buyProperty(players.get(0));
		
		game.createHouses(players.get(0), ((NormalProperty) board.getBoardBox(1)), 3);
		
		assertEquals(0, ((NormalProperty) board.getBoardBox(1)).getNrHouses());
	}
	
	/**
	 * Test if player can create an hotel without having all properties of the group
	 */
	@Test
	public void testBuildHotelWithoutPermission(){
		players.get(0).setPos(board.getBoardBox(1));
		game.buyProperty(players.get(0));
		
		game.createHotel(players.get(0), ((NormalProperty) board.getBoardBox(1)));
		
		assertEquals(0, ((NormalProperty) board.getBoardBox(1)).getNrHotels());
	}
	
	/**
	 * Test if player goes to a ChanceBox
	 */
	@Test
	public void testChanceBox(){
		do{
			game.updateGame(players.get(0));
			game.checkSpecialBoardBox(players.get(0));
		}while((players.get(0).getPos().getPos()!=((ChanceBox) board.getBoardBox(7)).getPos()) );
		assertEquals(players.get(0).getPos().getPos(), ((ChanceBox) board.getBoardBox(7)).getPos());
	}
	
	/**
	 * Test if player goes to a CommunityBox
	 */
	@Test
	public void testCommunityBox(){
		do{
			game.updateGame(players.get(0));
			game.checkSpecialBoardBox(players.get(0));
		}while((players.get(0).getPos().getPos()!=((CommunityBox) board.getBoardBox(2)).getPos()) );
		assertEquals(players.get(0).getPos().getPos(), ((CommunityBox) board.getBoardBox(2)).getPos());
	}
	
	/**
	 * Test if a purchased property ask the rent to the owner of that property
	 */
	@Test
	public void testPlayerStayInPropertyOfSamePlayer(){
		
		players.get(0).setPos(board.getBoardBox(1));
		game.buyProperty(players.get(0));
		
		int value = players.get(0).getBalance();
		
		game.optimizedPayBill(players.get(0), ((NormalProperty) board.getBoardBox(1)));
		
		assertEquals(value, players.get(0).getBalance());
	}
	
	/**
	 * Test if a player receive the value of mortgage property
	 */
	@Test
	public void testMortgageProperty(){
		players.get(0).setPos(board.getBoardBox(1));
		game.buyProperty(players.get(0));
		int value = players.get(0).getBalance();
		game.mortgage(players.get(0), ((NormalProperty) board.getBoardBox(1)));
		assertEquals(value + ((NormalProperty) board.getBoardBox(1)).getMortgageValue(), players.get(0).getBalance());
	}
	
	/**
	 * Test if a player pay a rent on a mortgage property
	 */
	@Test
	public void testValueRentOnMortagageProperty(){
		//Player 1 buy property 1 and mortgage it.
		players.get(0).setPos(board.getBoardBox(1));
		game.buyProperty(players.get(0));
		game.mortgage(players.get(0), ((NormalProperty) board.getBoardBox(1)));
		
		//Player 2 goes to a mortgage property
		players.get(1).setPos(board.getBoardBox(1));
		int value = players.get(1).getBalance();
		//Player 2 try to pay a mortgage property
		game.optimizedPayBill(players.get(1), ((NormalProperty) board.getBoardBox(1)));
		
		assertEquals(value , players.get(1).getBalance());
	}
	
	/**
	 * Test to buy a property 2 times.
	 */
	@Test
	public void testBuy2times(){
		players.get(0).setPos(board.getBoardBox(1));
		game.buyProperty(players.get(0));
		int value = players.get(0).getBalance();
		game.buyProperty(players.get(0));
		assertEquals(value, players.get(0).getBalance());
	}
	
	/**
	 * Test to sell a property not owned.
	 */
	@Test
	public void testSellPropertyNotOwned(){
		int value = players.get(0).getBalance();
		players.get(0).sellProperty(((NormalProperty) board.getBoardBox(1)), 50);
		assertEquals(value, players.get(0).getBalance());
	}
	
	/**
	 * Test to sell a property owned.
	 */
	@Test
	public void testSellPropertyOwned(){
		players.get(0).setPos(board.getBoardBox(1));
		game.buyProperty(players.get(0));
		players.get(0).sellProperty(((NormalProperty) board.getBoardBox(1)), 50);
		assertEquals(false, players.get(0).getPropertiesOwned().contains(board.getBoardBox(1)));
	}
	
	/**
	 * Test if a player 'unmortgage' a property
	 */
	@Test
	public void testUnmortgageProperty(){
		players.get(0).setPos(board.getBoardBox(1));
		game.buyProperty(players.get(0));
		game.mortgage(players.get(0), ((NormalProperty) board.getBoardBox(1)));
		assertEquals(true, ((NormalProperty) board.getBoardBox(1)).getMortgage());
		game.unMortgage(players.get(0), ((NormalProperty) board.getBoardBox(1)));
		assertEquals(false, ((NormalProperty) board.getBoardBox(1)).getMortgage());
	}
	
	/**
	 * Test if player sell houses
	 */
	@Test
	public void testSellHouses(){

		//Player 1 buy property 1 and 3. Idgroup 1
		players.get(0).setPos(board.getBoardBox(1));
		game.buyProperty(players.get(0));
		players.get(0).setPos(board.getBoardBox(3));
		game.buyProperty(players.get(0));
		
		//Player 1 buy 3 houses
		game.createHouses(players.get(0), ((NormalProperty) board.getBoardBox(1)), 3);
		//Player 1 sell 3 houses
		game.sellHouses(players.get(0), ((NormalProperty) board.getBoardBox(1)), 3);
		
		assertEquals(0, ((NormalProperty) board.getBoardBox(1)).getNrHouses());
	}
	
	/**
	 * Test if player sell hotel
	 */
	@Test
	public void testSellHotel(){

		//Player 1 buy property 1 and 3. Idgroup 1
		players.get(0).setPos(board.getBoardBox(1));
		game.buyProperty(players.get(0));
		players.get(0).setPos(board.getBoardBox(3));
		game.buyProperty(players.get(0));
		
		//Player 1 buy 4 houses and 1 hotel
		game.createHouses(players.get(0), ((NormalProperty) board.getBoardBox(1)), 4);
		game.createHouses(players.get(0), ((NormalProperty) board.getBoardBox(3)), 4);
		game.createHotel(players.get(0), ((NormalProperty) board.getBoardBox(3)));
		assertEquals(1, ((NormalProperty) board.getBoardBox(3)).getNrHotels());
		
		//Player 1 sell 1 hotel
		game.sellHotel(players.get(0), ((NormalProperty) board.getBoardBox(3)));
		
		assertEquals(0, ((NormalProperty) board.getBoardBox(3)).getNrHotels());
	}
	
	/**
	 * Test railRoads
	 */
	@Test
	public void testRailRoads(){
		players.get(0).setPos(board.getBoardBox(5));
		game.buyProperty(players.get(0));
		players.get(0).setPos(board.getBoardBox(15));
		game.buyProperty(players.get(0));
		players.get(0).setPos(board.getBoardBox(25));
		game.buyProperty(players.get(0));
		players.get(0).setPos(board.getBoardBox(35));
		game.buyProperty(players.get(0));
		
		int value = players.get(1).getBalance();
		game.optimizedPayBill(players.get(1), ((Property) board.getBoardBox(5)));
		assertEquals(value-((RailRoadProperty) board.getBoardBox(5)).getValueToPay(), players.get(1).getBalance());
	}
	
	/**
	 * Test ServiceProperties
	 */
	@Test
	public void testServiceProperties(){
		players.get(0).setPos(board.getBoardBox(12));
		game.buyProperty(players.get(0));
		players.get(0).setPos(board.getBoardBox(28));
		game.buyProperty(players.get(0));
		
		int value = players.get(1).getBalance();
		players.get(1).setDicesValue(10);
		game.optimizedPayBill(players.get(1), ((Property) board.getBoardBox(28)));
		assertEquals(value-((ServiceProperty) board.getBoardBox(12)).getValueToPay(10), players.get(1).getBalance());
	}
}
