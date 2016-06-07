package Monopoly.Logic;

import java.util.HashMap;
import java.util.Vector;

/**
 * Class Player
 */
public class Player {

	private BoardBox pos;
	private int id;
	private String name;
	private int balance;
	private PlayerSymbol symbol;
	private boolean inJail;
	private int cardsJail;
	private Vector<Property> propertiesOwned;
	private int nrOfRolls;
	private int nrOfRollsInJail;
	private HashMap<Integer, Integer> nrPropertyGroup = new HashMap<Integer, Integer>();
	private HashMap<Integer, Boolean> blPropertyGroup = new HashMap<Integer, Boolean>();
	private int dicesValue = 0;
	private int cellsToMove = 0;
	private Position position = new Position();
	private int valuePosittion;
	// Players Initial Positions GUI
	private int deltaX = 105;
	private int deltaY = 81;
	// SOUTH
	private int xPosUpS = 1120;
	private int xPosDownS = 1170;
	private int yPosUpS = 890;
	private int yPosDownS = 930;
	// WEST
	private int xPosUpW = 10;
	private int xPosDownW = 60;
	private int yPosUpW = 775;
	private int yPosDownW = 820;
	// NORTH
	private int xPosUpN = 175;
	private int xPosDownN = 225;
	private int yPosUpN = 5;
	private int yPosDownN = 55;
	// EAST
	private int xPosUpE = 1160;
	private int xPosDownE = 1210;
	private int yPosUpE = 125;
	private int yPosDownE = 170;
	// -----------------------------

	
	private int symbol_1; 
	public Player(String name, int piece, int balance) {
		this.name = name;
		this.symbol_1 = piece;
		this.balance = balance;
		this.inJail = false;
		this.propertiesOwned = new Vector<Property>();
		this.nrOfRolls = 0;
	}

	// --------------------------------------------------------------------
	/**
	 * Constructor of Player
	 * 
	 * @param name
	 *            of player
	 * @param piece
	 *            symbol that identify the player
	 * @param balance
	 *            value that player have in their wallet
	 * @param pos
	 *            position on Board
	 * @param id
	 *            Player
	 */
	public Player(String name, PlayerSymbol piece, int balance, BoardBox pos, int id) {
		this.name = name;
		this.symbol = piece;
		this.balance = balance;
		this.inJail = false;
		this.propertiesOwned = new Vector<Property>();
		this.pos = pos;
		this.nrOfRolls = 0;
		this.id = id;
		this.valuePosittion = pos.getPos();
	}

	/**
	 * Method to update player on graphic window
	 */
	public void updateGUIPosition() { 
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();
		// SOUTH
		if (this.getValuePosition() >= 0 && this.getValuePosition() < 10)
			x -= deltaX;
		// WEST
		if (this.getValuePosition() == 10) {
			if (id == 1) {
				x = xPosUpW;
				y = yPosUpW;
			}
			if (id == 2) {
				x = xPosUpW;
				y = yPosDownW;
			}
			if (id == 3) {
				x = xPosDownW;
				y = yPosUpW;
			}
			if (id == 4) {
				x = xPosDownW;
				y = yPosDownW;
			}
		}
		if (this.getValuePosition() > 10 && this.getValuePosition() < 20)
			y -= deltaY;
		// NORTH
		if (this.getValuePosition() == 20) {
			if (id == 1) {
				x = xPosUpN;
				y = yPosUpN;
			}
			if (id == 2) {
				x = xPosUpN;
				y = yPosDownN;
			}
			if (id == 3) {
				x = xPosDownN;
				y = yPosUpN;
			}
			if (id == 4) {
				x = xPosDownN;
				y = yPosDownN;
			}
		}
		if (this.getValuePosition() > 20 && this.getValuePosition() < 30)
			x += deltaX;
		// EAST
		if (this.getValuePosition() == 30) {
			if (id == 1) {
				x = xPosUpE;
				y = yPosUpE;
			}
			if (id == 2) {
				x = xPosUpE;
				y = yPosDownE;
			}
			if (id == 3) {
				x = xPosDownE;
				y = yPosUpE;
			}
			if (id == 4) {
				x = xPosDownE;
				y = yPosDownE;
			}
		}
		if (this.getValuePosition() > 30 && this.getValuePosition() < 39)
			y += deltaY;

		if (this.getValuePosition() == 39) {
			if (id == 1) {
				x = xPosUpS;
				y = yPosUpS;
			}
			if (id == 2) {
				x = xPosUpS;
				y = yPosDownS;
			}
			if (id == 3) {
				x = xPosDownS;
				y = yPosUpS;
			}
			if (id == 4) {
				x = xPosDownS;
				y = yPosDownS;
			}
			this.setValuePosition(0);
		} else {
			this.setValuePosition(this.getValuePosition() + 1);

		}
		this.setPosition(x, y);
	}

	/**
	 * Get how many boardBox positions need to move
	 * 
	 * @return number of positions to move
	 */
	public int getCellsToMove() {
		return cellsToMove;
	}

	/**
	 * Update how many boardBox positions need to move
	 * 
	 * @param cellsToMove
	 */
	public void setCellsToMove(int cellsToMove) {
		this.cellsToMove = cellsToMove;
	}

	/**
	 * Get (x,y) position of player on graphic window
	 * 
	 * @return position x and y
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Update (x,y) position of player on graphic window
	 * 
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y) {
		this.position.setX(x);
		this.position.setY(y);
	}

	/**
	 * Get BoardBox position value
	 * 
	 * @return int of boardbox position value
	 */
	public int getValuePosition() {
		return valuePosittion;
	}

	/**
	 * Update BoardBox position value
	 * 
	 * @param p
	 */
	public void setValuePosition(int p) {
		valuePosittion = p;
	}

	/**
	 * Get ID of the player
	 * 
	 * @return ID of the player
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Get player name
	 * 
	 * @return name of the player
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get player balance
	 * 
	 * @return player balance
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * Update all player balance
	 * 
	 * @param balance
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}

	/**
	 * Update actual player balance
	 * 
	 * @param value
	 *            to be updated on balance
	 */
	public void updateBalance(int value) {
		this.balance += value;
	}

	/**
	 * Get player symbol
	 * 
	 * @return Symbol of the player
	 */
	public PlayerSymbol getSymbol() {
		return symbol;
	}

	/**
	 * Update player symbol
	 * 
	 * @param symbol
	 *            of the player
	 */
	public void setSymbol(PlayerSymbol symbol) {
		this.symbol = symbol;
	}

	/**
	 * Get BoardBox position of player
	 * 
	 * @return BoardBox
	 */
	public BoardBox getPos() {
		return pos;
	}

	/**
	 * Update BoardBox position of player
	 * 
	 * @param pos
	 *            BoardBox
	 */
	public void setPos(BoardBox pos) {
		this.pos = pos;
	}

	/**
	 * Get player in jail boolean
	 * 
	 * @return True if player is in Jail, false if player not in jail
	 */
	public boolean getInJail() {
		return inJail;
	}

	/**
	 * Update player in jail boolean
	 * 
	 * @param inJail
	 *            True if player goes to the jail, false if leaves the jail
	 */
	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}

	/**
	 * Get number of cards from out of jail
	 * 
	 * @return number of cards from out of jail
	 */
	public int getNrCardJail() {
		return cardsJail;
	}

	/**
	 * Update the cards from out of jail
	 * 
	 * @param n
	 *            1 if receive a jail card, -1 if use the card
	 */
	public void updateCardsJail(int n) {
		cardsJail += n;
	}

	/**
	 * Method to mortgage the property
	 * 
	 * @param property
	 *            to be mortgage
	 */
	public void mortgageProperty(Property property) {
		if (propertiesOwned.contains(property)) {
			balance += property.getMortgageValue();
			property.setMortgage(true);
		}
	}

	/**
	 * Method to buy property
	 * 
	 * @param property
	 *            that will be purchased from Player
	 * @param idGroup
	 *            Group ID
	 */
	public void buyProperty(Property property, int idGroup) {
		if (balance >= property.getAmount()) {
			balance -= property.getAmount();
			propertiesOwned.add(property);
			property.setSold(true);
			property.setOwner(this);
			int count = nrPropertyGroup.containsKey(property.getIdGroup()) ? nrPropertyGroup.get(property.getIdGroup())
					: 0;
			nrPropertyGroup.put(property.getIdGroup(), count + 1);
			this.updateBlPropertyGroup(property.getIdGroup(), idGroup);
		}
	}

	/**
	 * Method to sell the property
	 * 
	 * @param property
	 *            to be sold
	 * @param amount
	 *            to be sold
	 */
	public void sellProperty(Property property, int amount) {
		if (propertiesOwned.contains(property)) {
			balance += amount;
			propertiesOwned.remove(property);
			property.setSold(false);
			property.setOwner(null);
			int aux = nrPropertyGroup.get(property.getIdGroup());
			nrPropertyGroup.put(property.getIdGroup(), --aux);
			blPropertyGroup.put(property.getIdGroup(), false);
		}
	}

	/**
	 * Get all owned properties 
	 * 
	 * @return owned properties 
	 */
	public Vector<Property> getPropertiesOwned() {
		return propertiesOwned;
	}

	/**
	 * Number of tries rolled dices by user
	 * 
	 * @return Number of tries rolled dices by user.
	 */
	public int getNrOfRolls() {
		return nrOfRolls;
	}

	/**
	 * Update the number of tries that user use to roll the dice
	 * 
	 * @param numberTries
	 *            Number of tries.
	 */
	public void setNrOfRolls(Boolean bool) {
		if (bool) {
			this.nrOfRolls++;
		} else
			this.nrOfRolls = 0;
	}

	/**
	 * Get number of roll dices tries by user in jail
	 * 
	 * @return Number of roll dices tries by user.
	 */
	public int getNrOfRollsInJail() {
		return nrOfRollsInJail;
	}

	/**
	 * Update the number of tries that user use to roll the dice in jail
	 * 
	 * @param numberTries
	 *            Number of tries.
	 */
	public void setNrOfRollsInJail(Boolean bool) {
		if (!bool) {
			this.nrOfRollsInJail++;
		} else
			this.nrOfRollsInJail = 0;
	}

	/**
	 * Get number of properties per group
	 * 
	 * @param property
	 * @return
	 */
	public int getPropertiesNr(Property property) {
		return nrPropertyGroup.get(property.getIdGroup());
	}

	/**
	 * Verify if have all property
	 * 
	 * @param key
	 *            value of properties to be compared
	 * @param value
	 *            max of properties
	 */
	private void updateBlPropertyGroup(int key, int value) {
		if (nrPropertyGroup.get(key) == value) {
			blPropertyGroup.put(key, true);
		} else
			blPropertyGroup.put(key, false);
	}

	/**
	 * Return true or false if have all properties or not
	 * 
	 * @param property
	 * @return true if have all properties, false if not
	 */
	public boolean haveAllPropertiesGroup(Property property) {
		return blPropertyGroup.get(property.getIdGroup());
	}

	/**
	 * Get dices value
	 * @return dices value
	 */
	public int getDicesValue() {
		return dicesValue;
	}

	/**
	 * Update dice values
	 * @param dicesValue
	 */
	public void setDicesValue(int dicesValue) {
		this.dicesValue = dicesValue;
	}

}