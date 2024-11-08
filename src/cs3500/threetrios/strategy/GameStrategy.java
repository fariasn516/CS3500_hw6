package cs3500.threetrios.strategy;

import cs3500.threetrios.model.ThreeTriosModel;

/**
 * Represents a strategy for making moves in the Three Trios game.
 */
public interface GameStrategy {
  /**
   * Chooses the best move for the current player based on the current game state.
   *
   * @param model the current game model, which contains the state of the game,
   *              including the grid and players.
   * @return a GameMove representing the best move for the player, or null
   *         if no valid moves are possible.
   */
  GameMove chooseMove(ThreeTriosModel model);
}