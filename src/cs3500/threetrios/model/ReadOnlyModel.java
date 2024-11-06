package cs3500.threetrios.model;

import java.util.List;

/**
 * The read-only interface for our game model, contains all the observation methods.
 */
public interface ReadOnlyModel {

  /**
   * Returns the current state of the grid.
   * @return the current grid state
   */
  Grid getGrid();

  /**
   * Returns the current player.
   * @return the current player
   */
  Player getCurrentPlayer();

  /**
   * Checks if the game has been started and returns as such.
   * @return if the game has started
   */
  boolean hasStarted();

  /**
   * Returns a specific card's owner's color.
   * @return owner's color
   */
  Color getCardOwnerColor(Card card);

  /**
   * Checks whether the game is over by finding if all card cells are filled.
   * @return true if the game is over
   * @throws IllegalStateException if the game has not started.
   */
  boolean isGameOver();

  /**
   * Returns the winner of the game based on the number of cards each player owns on the grid.
   * @return a string representing the winner: "Blue Player" or "Red Player".
   * @throws IllegalStateException if the game has not started or is not yet over.
   */
  String winner();

  /**
   * Returns the list of cards in the blue player's hand.
   * @return the list of cards in the blue player's hand
   */
  List<Card> getBluePlayer();

  /**
   * Returns the list of cards in the red player's hand.
   * @return the list of cards in the red player's hand
   */
  List<Card> getRedPlayer();
}