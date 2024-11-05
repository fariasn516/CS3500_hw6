package cs3500.threetrios.model;

/**
 * Represents a cell in a game of ThreeTrios.
 */
public class GameCell implements Cell {
  private final boolean isHole; // represents whether this cell is a hole
  private Card card; // represents the card that is placed in this cell

  /**
   * Constructor for GameCell, takes in a boolean that represents whether it is a hole.
   * @param isHole represents whether the cell is a hole
   */
  public GameCell(boolean isHole) {
    this.isHole = isHole;
    // do not need to initialize card field because it will by default be null
    // also keeps cell immutable
  }

  @Override
  public boolean hasCard() {
    return card != null;
  }

  /**
   * Places a card in the cell. If the cell is a hole or already contains a card,
   * this method will throw an IllegalStateException.
   *
   * @param card the card to place in the cell.
   * @throws IllegalStateException if the cell is a hole or already contains a card.
   */
  @Override
  public void placeCard(Card card) {
    if (isHole) {
      throw new IllegalStateException("Cannot place a card in a hole.");
    }
    if (this.card != null) {
      throw new IllegalStateException("Cell already contains a card.");
    }
    this.card = card;
  }

  /**
   * Removes the card from the cell. If there is no card in the cell, this method will
   * throw an IllegalStateException.
   *
   * @throws IllegalStateException if the cell is a hole or does not have a card.
   */
  @Override
  public void removeCard() {
    if (isHole) {
      throw new IllegalStateException("Cards do not exist in a hole.");
    }
    if (card == null) {
      throw new IllegalStateException("No card in this cell to remove.");
    }
    card = null;
  }

  /**
   * Returns whether the cell is a hole or not.
   *
   * @return true if the cell is a hole, false otherwise.
   */
  @Override
  public  boolean isHole() {
    return isHole;
  }

  /**
   * Returns the card in this cell. If there is no card or if the cell is a hole, this method
   * will throw an IllegalStateException.
   *
   * @return the card in this cell.
   * @throws IllegalStateException if the cell is a hole or there is no card in this cell.
   */
  @Override
  public Card getCard() {
    if (isHole) {
      throw new IllegalStateException("Cannot get a card from a hole.");
    }
    if (card == null) {
      throw new IllegalStateException("No card in this cell.");
    }
    return card;
  }

  /**
   * Converts the cell into a string representation.
   * For holes, it returns "X". For empty cells, it returns "_". For cells with cards,
   * it returns the first letter of the card's owner (R for Red, B for Blue).
   *
   * @return a string representation of the cell.
   */
  @Override
  public String toString() {
    if (isHole) {
      return "X";
    }
    else if (card == null) {
      return "_";
    }
    else {
      return card.getColor().toString().substring(0, 1);
    }
  }
}

