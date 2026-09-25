package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Stores the cells and card decks that make up a Monopoly game board.
 */
public class GameBoard {

	private ArrayList<Cell> cells = new ArrayList<Cell>();
    private ArrayList<Card> chanceCards = new ArrayList<Card>();
	//the key of colorGroups is the name of the color group.
	private Hashtable<String, Integer> colorGroups = new Hashtable<String, Integer>();
	private ArrayList<Card> communityChestCards = new ArrayList<Card>();
	/**
	 * Creates a board containing the starting Go cell.
	 */
	public GameBoard() {
		Cell go = new GoCell();
		addCell(go);
	}

	/**
	 * Adds a card to the deck indicated by its card type.
	 *
	 * @param card the card to add
	 */
	public void addCard(Card card) {
        if(card.getCardType() == Card.TYPE_CC) {
            communityChestCards.add(card);
        } else {
            chanceCards.add(card);
        }
    }
	
	/**
	 * Adds a non-property cell to the board.
	 *
	 * @param cell the cell to add
	 */
	public void addCell(Cell cell) {
		cells.add(cell);
	}
	
	/**
	 * Adds a property cell and updates its color-group count.
	 *
	 * @param cell the property to add
	 */
	public void addCell(PropertyCell cell) {
		String colorGroup = cell.getColorGroup();
		int propertyNumber = getPropertyNumberForColor(colorGroup);
		colorGroups.put(colorGroup, new Integer(propertyNumber + 1));
        cells.add(cell);
	}

    /**
     * Draws and recycles the next Community Chest card.
     *
     * @return the drawn card
     */
    public Card drawCCCard() {
        Card card = (Card)communityChestCards.get(0);
        communityChestCards.remove(0);
        addCard(card);
        return card;
    }

	/**
	 * Draws and recycles the next Chance card.
	 *
	 * @return the drawn card
	 */
    public Card drawChanceCard() {
        Card card = (Card)chanceCards.get(0);
        chanceCards.remove(0);
        addCard(card);
        return card;
    }

	/**
	 * Returns the cell at the specified board index.
	 *
	 * @param newIndex the zero-based cell index
	 * @return the cell at that index
	 */
	public Cell getCell(int newIndex) {
		return (Cell)cells.get(newIndex);
	}
	
	/**
	 * Returns the number of cells on this board.
	 *
	 * @return the cell count
	 */
	public int getCellNumber() {
		return cells.size();
	}
	
	/**
	 * Returns the properties belonging to a color group.
	 *
	 * @param color the color-group name
	 * @return the properties in that group
	 */
	public PropertyCell[] getPropertiesInMonopoly(String color) {
		PropertyCell[] monopolyCells = 
			new PropertyCell[getPropertyNumberForColor(color)];
		int counter = 0;
		for (int i = 0; i < getCellNumber(); i++) {
			Cell c = getCell(i);
			if(c instanceof PropertyCell) {
				PropertyCell pc = (PropertyCell)c;
				if(pc.getColorGroup().equals(color)) {
					monopolyCells[counter] = pc;
					counter++;
				}
			}
		}
		return monopolyCells;
	}
	
	/**
	 * Returns the number of property cells registered for a color group.
	 *
	 * @param name the color-group name
	 * @return the registered property count
	 */
	public int getPropertyNumberForColor(String name) {
		Integer number = (Integer)colorGroups.get(name);
		if(number != null) {
			return number.intValue();
		}
		return 0;
	}

	/**
	 * Finds a cell by its board name.
	 *
	 * @param string the board name to find
	 * @return the matching cell, or {@code null} if no cell matches
	 */
	public Cell queryCell(String string) {
		for(int i = 0; i < cells.size(); i++){
			Cell temp = (Cell)cells.get(i); 
			if(temp.getName().equals(string)) {
				return temp;
			}
		}
		return null;
	}
	
	/**
	 * Finds the board index of a cell by name.
	 *
	 * @param string the board name to find
	 * @return the matching index, or {@code -1} if no cell matches
	 */
	public int queryCellIndex(String string){
		for(int i = 0; i < cells.size(); i++){
			Cell temp = (Cell)cells.get(i); 
			if(temp.getName().equals(string)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Clears the Community Chest deck.
	 */
	public void removeCards() {
        communityChestCards.clear();
    }
}
