package edu.towson.cis.cosc442.project1.monopoly;

/**
 * A purchasable Monopoly property with a color group and optional houses.
 */
public class PropertyCell extends Cell {
	private String colorGroup;
	private int housePrice;
	private int numHouses;
	private int rent;
	private int sellPrice;

	/**
	 * Returns this property's color group.
	 *
	 * @return the color group name
	 */
	public String getColorGroup() {
		return colorGroup;
	}

	/**
	 * Returns the cost of one house for this property.
	 *
	 * @return the house price
	 */
	public int getHousePrice() {
		return housePrice;
	}

	/**
	 * Returns the number of houses built on this property.
	 *
	 * @return the number of houses
	 */
	public int getNumHouses() {
		return numHouses;
	}
    
	/**
	 * Returns the purchase price of this property.
	 *
	 * @return the property price
	 */
    public int getPrice() {
		return sellPrice;
	}

	/**
	 * Calculates the rent due for this property, including any monopoly or
	 * house increase.
	 *
	 * @return the rent amount
	 */
	public int getRent() {
		int rentToCharge = calculateMonopoliesRent();
		if(numHouses > 0) {
			rentToCharge = rent * (numHouses + 1);
		}
		return rentToCharge;
	}

	private int calculateMonopoliesRent() {
		int rentToCharge = rent;
		String [] monopolies = theOwner.getMonopolies();
		for(int i = 0; i < monopolies.length; i++) {
			if(monopolies[i].equals(colorGroup)) {
				rentToCharge = rent * 2;
			}
		}
		return rentToCharge;
	}

	/**
	 * Applies rent when another player lands on this owned property.
	 */
	public void playAction() {
		Player currentPlayer = null;
		if(!isAvailable()) {
			currentPlayer = GameMaster.instance().getCurrentPlayer();
			if(theOwner != currentPlayer) {
				currentPlayer.payRentTo(theOwner, getRent());
			}
		}
	}

	/**
	 * Sets the color group for this property.
	 *
	 * @param colorGroup the color group name
	 */
	public void setColorGroup(String colorGroup) {
		this.colorGroup = colorGroup;
	}

	/**
	 * Sets the cost of one house for this property.
	 *
	 * @param housePrice the house price
	 */
	public void setHousePrice(int housePrice) {
		this.housePrice = housePrice;
	}

	/**
	 * Sets the number of houses built on this property.
	 *
	 * @param numHouses the number of houses
	 */
	public void setNumHouses(int numHouses) {
		this.numHouses = numHouses;
	}

	/**
	 * Sets the purchase price of this property.
	 *
	 * @param sellPrice the property price
	 */
	public void setPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	/**
	 * Sets the base rent for this property.
	 *
	 * @param rent the base rent
	 */
	public void setRent(int rent) {
		this.rent = rent;
	}
}
