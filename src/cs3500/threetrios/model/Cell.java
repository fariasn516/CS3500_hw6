package cs3500.threetrios.model;

/**
 * Interface representing the contract for a Cell in the grid.
 */
public interface Cell {

  /**
   * Checks if the cell has a card.
   *
   * @return true if the cell contains a card, false otherwise.
   */
  boolean hasCard();

  /**
   * Places a card in the cell. If the cell is a hole or already contains a card,
   * this method will throw an IllegalStateException.
   *
   * @param card the card to place in the cell.
   * @throws IllegalStateException if the cell is a hole or already contains a card.
   */
  void placeCard(Card card);

  /**
   * Removes the card from the cell. If there is no card in the cell, this method will
   * throw an IllegalStateException.
   *
   * @throws IllegalStateException if the cell is a hole or does not have a card.
   */
  void removeCard();

  /**
   * Returns whether the cell is a hole or not.
   *
   * @return true if the cell is a hole, false otherwise.
   */
  boolean isHole();

  /**
   * Returns the card in this cell. If there is no card or if the cell is a hole, this method
   * will throw an IllegalStateException.
   *
   * @return the card in this cell.
   * @throws IllegalStateException if the cell is a hole or there is no card in this cell.
   */
  Card getCard();

  /**
   * Converts the cell into a string representation.
   * For holes, it returns "X". For empty cells, it returns "_". For cells with cards,
   * it returns the first letter of the card's owner (R for Red, B for Blue).
   *
   * @return a string representation of the cell.
   */
  String toString();
}

