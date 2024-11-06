package cs3500.threetrios.model;

import java.util.List;

/**
 * The model interface for our game model, contains all the mutator methods.
 */
public interface Model extends ReadOnlyModel {

  /**
   * Initializes the game with the given deck of cards, shuffle option, and grid configuration.
   * Deals the cards to players and sets the starting player.
   *
   * @param cards   the list of cards for the game.
   * @param shuffle true if the cards should be shuffled.
   * @param grid    the grid to use for the game.
   * @throws IllegalStateException    if the game has already started or ended.
   * @throws IllegalArgumentException if the cards list is null, grid has an even number of card
   *                                  cells, or the number of cards is incorrect.
   */
  <C extends Card> void startGame(List<C> cards, boolean shuffle, Grid grid);

  /**
   * Plays the placing phase of the turn by placing a card on the specified grid cell.
   *
   * @param card the card to place on the grid.
   * @param row  the row on the grid where the card is placed.
   * @param col  the column on the grid where the card is placed.
   * @throws IllegalStateException    if the game has not started or has already ended.
   * @throws IllegalArgumentException if the cell is not a valid placement cell.
   */
  void placingPhase(Card card, int row, int col);

  /**
   * Plays the battling phase of the turn after a card has been placed. Determines who wins the
   * battle between the placed card and its adjacent cards based on their direction-based
   * attack values.
   *
   * @param row the row of the placed card.
   * @param col the column of the placed card.
   */
  void battlingPhase(int row, int col);

  /**
   * Plays a complete turn for the current player. This includes placing a card on the grid,
   * battling with adjacent cards, and switching turns.
   *
   * @param card the card to place on the grid.
   * @param row  the row where the card is placed.
   * @param col  the column where the card is placed.
   * @throws IllegalStateException    if the game has not started or has ended.
   * @throws IllegalArgumentException if the given card is not in the player's hand.
   */
  void takeTurn(Card card, int row, int col);
}
