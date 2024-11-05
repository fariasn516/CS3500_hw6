package cs3500.threetrios.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class used to parse a file for all the necessary things for the grid.
 */
public class GridFileParser {
  private final File file; // represents the file to be parsed through

  /**
   * Constructor for GridFileParser, takes in a file.
   * @param file represents the file to be parsed through
   * @throws FileNotFoundException when the file cannot be found
   */
  public GridFileParser(File file) throws FileNotFoundException {
    this.file = file;
  }

  /**
   * Parses the grid configuration file and creates a new GameGrid instance.
   *
   * @return a new GameGrid object based on the parsed configuration file.
   * @throws FileNotFoundException    if the file is not found.
   * @throws IllegalArgumentException if the file format is invalid.
   */
  public GameGrid createGridFromFile() throws FileNotFoundException {
    Scanner scanner = new Scanner(this.file);
    int numRows = readDimension(scanner, "Invalid row count.");
    int numCols = readDimension(scanner, "Invalid column count.");
    scanner.nextLine();
    boolean[][] holeLayout = readHoleLayout(scanner, numRows, numCols);

    return new GameGrid(numRows, numCols, holeLayout);
  }

  private int readDimension(Scanner scanner, String errorMessage) {
    if (!scanner.hasNextInt()) {
      throw new IllegalArgumentException(errorMessage);
    }
    return scanner.nextInt();
  }

  private boolean[][] readHoleLayout(Scanner scanner, int numRows, int numCols) {
    boolean[][] holeLayout = new boolean[numRows][numCols];
    int row = 0;

    while (scanner.hasNextLine() && row < numRows) {
      String line = scanner.nextLine();
      validRowLength(line, numCols);
      holeLayout[row] = readRow(line, numCols);
      row++;
    }

    if (row != numRows) {
      throw new IllegalArgumentException("Grid row count mismatch.");
    }

    return holeLayout;
  }

  private void validRowLength(String line, int expectedLength) {
    if (line.length() != expectedLength) {
      throw new IllegalArgumentException("Number of columns format invalid.");
    }
  }

  private boolean[] readRow(String line, int numCols) {
    boolean[] rowLayout = new boolean[numCols];
    for (int col = 0; col < numCols; col++) {
      char cell = line.charAt(col);
      rowLayout[col] = readCell(cell);
    }
    return rowLayout;
  }

  private boolean readCell(char cell) {
    if (cell == 'X') {
      return true;
    }
    if (cell == 'C') {
      return false;
    }
    else {
      throw new IllegalArgumentException("Invalid character in grid layout: " + cell);
    }
  }
}
