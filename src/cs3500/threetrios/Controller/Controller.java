package cs3500.threetrios.Controller;

import java.util.List;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.Model;

/**
 * Controller interface that represents the methods that each controller needs.
 */
public interface Controller {

  /**
   * Plays the three trios game given the model, along with two strings representing filepaths for
   * the configuration of the grid and deck, and a boolean that represents whether the
   * cards are shuffled or not.
   * @param model represents the model for the game to be played
   * @param cardFilePath represents the file path for the deck of cards to be created
   * @param gridFilePath represents the file path for the grid to be created
   * @param shuffle represents if the deck of cards need to be shuffled
   */
  void playGame(Model model, String cardFilePath, String gridFilePath, boolean shuffle);

  /**
   * Plays the three trios game given the model, along with a list of cards representing the deck
   * of cards to be played with, the grid that the game is to be played on, and a boolean
   * that represents whether the cards are shuffled or not.
   * This method is an overloaded version of the above method, as it makes testing easier and
   * seems like a good way to play the game other than just using card/grid configurations.
   * @param model represents the model for the game to be played
   * @param deck represents the deck of cards to be played with
   * @param grid represents the grid for the game to be played on
   * @param shuffle represents if the deck of cards need to be shuffled
   */
  void playGame(Model model, List<Card> deck, Grid grid, boolean shuffle);

  /**
   * Handles when a cell on the grid is clicked.
   * @param row represents the row of the cell that was clicked
   * @param column represents the column of the cell that was clicked
   */
  void handleCellClick(int row, int column);

  /**
   * Handles when the card of a player is clicked.
   * @param index represents the card index that was clicked
   */
  void handleHandClick(int index);
}
