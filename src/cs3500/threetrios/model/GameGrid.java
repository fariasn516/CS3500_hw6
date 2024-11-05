package cs3500.threetrios.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the grid in a game of ThreeTrios.
 */
public class GameGrid implements Grid {
  private final int numRows; // represents the number of rows the grid has
  private final int numCols; // represent the number of columns the grid has
  private final boolean[][] holeLayout; // represents the layout of holes
  private final Cell[][] grid; // represents the actual grid layout

  /**
   * Constructor for game grid, takes in the number of rows, columns,
   * and a 2D array for hole layout.
   * @param numRows represents the number of rows
   * @param numCols represents the number of columns
   * @param holeLayout represents the layout of the holes
   */
  public GameGrid(int numRows, int numCols, boolean[][] holeLayout) {
    this.numRows = numRows;
    this.numCols = numCols;
    this.holeLayout = holeLayout;
    this.grid = new Cell[numRows][numCols];

    int cardCellCount = 0;

    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
        boolean isHole = holeLayout[i][j];
        grid[i][j] = new GameCell(isHole);
        if (!isHole) {
          cardCellCount++;
        }
      }
    }

    if (cardCellCount % 2 == 0) {
      throw new IllegalArgumentException("The grid must have an odd number of card cells.");
    }
  }

  /**
   * Another constructor for the grid, takes in the number of rows and columns and a 2d array for
   * the layout of cells.
   * @param numRows represents the number of rows
   * @param numCols represents the number of columns
   * @param grid represents the layout of the cells
   */
  public GameGrid(int numRows, int numCols, Cell[][] grid) {
    this.numRows = numRows;
    this.numCols = numCols;
    this.grid = grid;
    this.holeLayout = new boolean[numRows][numCols];
  }

  @Override
  public void placeCard(Card card, int row, int col) {
    if (!validCell(row, col)) {
      throw new IllegalStateException("Cell is not valid for placing a card.");
    }

    grid[row][col].placeCard(card);
  }

  @Override
  public boolean validCell(int row, int col) {
    if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
      throw new IllegalArgumentException("Row or column out of bounds.");
    }

    return !grid[row][col].isHole() && !grid[row][col].hasCard();
  }

  @Override
  public Map<String, Direction> getAdjacentCardsWithDirections(int row, int col) {
    Map<String, Direction> adjacentCardsWithDirections = new HashMap<>();

    if (row > 0 && grid[row - 1][col].hasCard()) {
      adjacentCardsWithDirections.put(grid[row - 1][col].getCard().getName(), Direction.NORTH);
    }

    if (row < numRows - 1 && grid[row + 1][col].hasCard()) {
      adjacentCardsWithDirections.put(grid[row + 1][col].getCard().getName(), Direction.SOUTH);
    }

    if (col > 0 && grid[row][col - 1].hasCard()) {
      adjacentCardsWithDirections.put(grid[row][col - 1].getCard().getName(), Direction.WEST);
    }

    if (col < numCols - 1 && grid[row][col + 1].hasCard()) {
      adjacentCardsWithDirections.put(grid[row][col + 1].getCard().getName(), Direction.EAST);
    }

    return adjacentCardsWithDirections;
  }

  @Override
  public int getCardCellCount() {
    int cardCellCount = 0;
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        if (!grid[row][col].isHole()) {
          cardCellCount++;
        }
      }
    }
    return cardCellCount;
  }

  @Override
  public int getNumRows() {
    return this.numRows;
  }

  @Override
  public int getNumCols() {
    return this.numCols;
  }

  @Override
  public Cell[][] getCells() {
    Cell[][] gridCopy = new Cell[numRows][numCols];

    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
        gridCopy[i][j] = grid[i][j];
      }
    }

    return gridCopy;
  }

  @Override
  public Card getCard(int row, int col) {
    if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
      throw new IllegalArgumentException("Row or column out of bounds.");
    }

    if (grid[row][col].isHole()) {
      throw new IllegalArgumentException("Cannot retrieve a card from a hole.");
    }

    if (!grid[row][col].hasCard()) {
      throw new IllegalArgumentException("No card in this cell.");
    }

    return grid[row][col].getCard();
  }

  @Override
  public boolean[][] getHoleLayout() {
    boolean[][] layoutCopy = new boolean[numRows][numCols];
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
        layoutCopy[i][j] = holeLayout[i][j];
      }
    }
    return layoutCopy;
  }

  @Override
  public String toString() {
    StringBuilder stringRep = new StringBuilder();
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        Cell cell = grid[row][col];
        if (cell.isHole()) {
          stringRep.append(" ");
        }
        else if (cell.hasCard()) {
          Card card = cell.getCard();
          stringRep.append(card.getColor() == Color.RED ? "R" : "B");
        }
        else {
          stringRep.append("_");
        }
      }
      stringRep.append("\n");
    }
    return stringRep.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof GameGrid)) {
      return false;
    }
    GameGrid other = (GameGrid) obj;

    if (this.numRows != other.numRows || this.numCols != other.numCols) {
      return false;
    }

    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        if (this.holeLayout[row][col] != other.holeLayout[row][col]) {
          return false;
        }
      }
    }

    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        Cell cell1 = this.grid[row][col];
        Cell cell2 = other.grid[row][col];

        if (cell1.isHole() != cell2.isHole()) {
          return false;
        }

        if (cell1.hasCard() != cell2.hasCard()) {
          return false;
        }

        if (cell1.hasCard()) {
          Card card1 = cell1.getCard();
          Card card2 = cell2.getCard();

          if (!card1.equals(card2)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = Integer.hashCode(numRows);
    result = 31 * result + Integer.hashCode(numCols);

    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        result = 31 * result + Boolean.hashCode(holeLayout[row][col]);
      }
    }

    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        Cell cell = grid[row][col];
        result = 31 * result + cell.hashCode();
      }
    }
    return result;
  }
}

