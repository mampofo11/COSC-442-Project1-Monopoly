package edu.towson.cis.cosc442.project1.monopoly;

/**
 * A location on the Monopoly board.
 */
public abstract class Cell {
	private boolean available = true;
	private String name;
	/**
	 * The player who owns this cell. A null value means it is unowned.
	 */
	protected Player theOwner;

	/**
	 * Returns this cell's board name.
	 *
	 * @return the cell name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the player who owns this cell.
	 *
	 * @return the owner, or {@code null} if the cell is unowned
	 */
	public Player getTheOwner() {
		return theOwner;
	}
	
	/**
	 * Returns the purchase price of this cell, if applicable.
	 *
	 * @return the cell price, or zero when it has no purchase price
	 */
	public int getPrice() {
		return 0;
	}

	/**
	 * Indicates whether this cell is available for purchase.
	 *
	 * @return {@code true} if the cell is available
	 */
	public boolean isAvailable() {
		return available;
	}
	
	/**
	 * Performs the action associated with a player landing on this cell.
	 */
	public abstract void playAction();

	/**
	 * Sets whether this cell is available for purchase.
	 *
	 * @param available {@code true} when the cell is available
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	/**
	 * Assigns the board name for this cell.
	 *
	 * @param name the cell name
	 */
	void setName(String name) {
		this.name = name;
	}

	/**
	 * Assigns this cell to a player.
	 *
	 * @param theOwner the new owner, or {@code null} when unowned
	 */
	public void setTheOwner(Player theOwner) {
		this.theOwner = theOwner;
	}
    
    /**
     * Returns the board name of this cell.
     *
     * @return the cell name
     */
    public String toString() {
        return name;
    }
}
