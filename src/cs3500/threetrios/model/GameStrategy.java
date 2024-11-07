package cs3500.threetrios.model;

/**
 * Represents a strategy for making moves in the Three Trios game.
 */
public interface GameStrategy {
  /**
   * Chooses the best move for the given player based on the current state of game.
   *
   * @param player the player using this strategy
   * @param grid   the current grid state
   * @return the best move for the player
   */
  GameMove chooseMove(Player player, Grid grid);
}