package cs3500.threetrios.view;

import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.Card;

import java.io.IOException;
import java.util.List;

/**
 * Represents the Textual View of the Three Trios Game.
 */
public class ThreeTriosGameTextView implements ThreeTriosGameView {
  private final ThreeTriosModel model;
  private final Appendable output;

  /**
   * Constructs a view with only the model.
   *
   * @param model the game model
   * @throws IllegalArgumentException if the model is null
   */
  public ThreeTriosGameTextView(ThreeTriosModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    this.model = model;
    this.output = null;
  }

  /**
   * Constructs a view with a model and output Appendable.
   *
   * @param model  the game model
   * @param output the appendable to output the view
   * @throws IllegalArgumentException if either the model or output is null
   */
  public ThreeTriosGameTextView(ThreeTriosModel model, Appendable output) {
    if (model == null || output == null) {
      throw new IllegalArgumentException("Model and Appendable cannot be null.");
    }
    this.model = model;
    this.output = output;
  }

  @Override
  public void render() throws IOException {
    if (output == null) {
      throw new IllegalArgumentException("No Appendable provided for rendering.");
    }
    output.append(this.toString());
  }

  @Override
  public String toString() {
    StringBuilder gameState = new StringBuilder();

    gameState.append(renderCurrentPlayer()).append("\n");
    gameState.append(renderGrid());
    gameState.append(renderHand());
    return gameState.toString();
  }

  /**
   * Renders the current player.
   *
   * @return a string representation of the current player's color.
   */
  private String renderCurrentPlayer() {
    return model.getCurrentPlayer().toString();
  }

  /**
   * Renders the grid layout as a string.
   *
   * @return the string representation of the current grid state.
   */
  private String renderGrid() {
    StringBuilder gridState = new StringBuilder("Grid:\n");
    gridState.append(model.getGrid().toString());
    return gridState.toString();
  }

  /**
   * Renders the player's current hand.
   *
   * @return a string representing the current player's hand.
   */
  private String renderHand() {
    List<Card> hand = model.getCurrentPlayer().getCardsInHand();
    StringBuilder handState = new StringBuilder("Hand:\n");
    for (Card card : hand) {
      handState.append(card.toString()).append("\n");
    }
    return handState.toString();
  }
}
