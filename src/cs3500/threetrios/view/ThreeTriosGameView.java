package cs3500.threetrios.view;

import java.io.IOException;

/**
 * The interface for the view of the Three Trios game, puts everything into a text view
 * so that the user can see it easier.
 */
public interface ThreeTriosGameView {

  /**
   * Renders the game state by appending the toString representation to output.
   *
   * @throws IOException if an error occurs while appending.
   */
  void render() throws IOException;

  /**
   * Returns a string representation of the current game state. This is the grid layout,
   * the current player, and other relevant details of the game like the cards in the
   * player's hand.
   *
   * @return a string that represents the game state in a readable format.
   */
  String toString();
}
