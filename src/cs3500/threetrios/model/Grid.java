package cs3500.threetrios.model;

import java.util.Map;

/**
 * Interface that represents the grid for the game Three Trios.
 */
public interface Grid {

  /**
   * Places a card at the specified row and column on the grid if the cell is empty and not a hole.
   *
   * @param card the card to place on the grid
   * @param row the row where the card is to be placed
   * @param col the column where the card is to be placed
   * @throws IllegalArgumentException if the row or column are out of bounds
   * @throws IllegalStateException if the cell is a hole or already contains a card
   */
  void placeCard(Card card, int row, int col);

  /**
   * Checks if the cell at the specified row and column is empty and not a hole.
   *
   * @param row the row of the cell to check
   * @param col the column of the cell to check
   * @return true if the cell is empty and not a hole, false otherwise
   */
  boolean validCell(int row, int col);

  /**
   * Returns a list of adjacent cards along with their relative direction.
   *
   * @param row the row of the reference cell
   * @param col the column of the reference cell
   * @return a list of CardDirection objects representing each adjacent card and its direction
   */
  Map<String, Direction> getAdjacentCardsWithDirections(int row, int col);

  /**
   * Returns the number of card cells (non-hole cells) in the grid.
   *
   * @return the count of card cells within the grid.
   */
  int getCardCellCount();

  /**
   * Returns a copy of the grid as a 2D array of cells.
   *
   * @return a copy of the grid of cells
   */
  Cell[][] getCells();

  /**
   * Returns the number of rows in the grid.
   *
   * @return the number of rows.
   */
  int getNumRows();

  /**
   * Returns the number of columns in the grid.
   *
   * @return the number of columns.
   */
  int getNumCols();

  /**
   * Returns the card at the specified position on the grid.
   *
   * @param row the row position of the cell
   * @param col the column position of the cell
   * @return the card in the specified cell
   * @throws IllegalArgumentException if the specified row or column is out of bounds
   *                                  if the cell is a hole or if it does not contain a card
   */
  Card getCard(int row, int col);

  /**
   * Returns a copy of the hole layout.
   *
   * @return a 2D boolean array where `true` indicates a hole and `false` indicates a card cell.
   */
  boolean[][] getHoleLayout();
  
  /**
   * Converts the grid into a string representation.
   *
   * @return a string representation of the grid
   */
  String toString();


}

