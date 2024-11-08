package cs3500.threetrios.strategy;
import java.util.List;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.Player;
import cs3500.threetrios.model.ThreeTriosModel;

/**
 * A strategy that targets corner positions for card placement.
 */
public class CornerCardStrat implements GameStrategy {

  @Override
  public GameMove chooseMove(ThreeTriosModel model) {
    GameMove bestMove = null;
    Player currentPlayer = model.getCurrentPlayer();

    // corner positions
    List<int[]> corners = List.of(
            new int[]{0, 0},
            new int[]{0, model.getGrid().getNumCols() - 1},
            new int[]{model.getGrid().getNumRows() - 1, 0},
            new int[]{model.getGrid().getNumRows() - 1, model.getGrid().getNumCols() - 1}
    );

    // iterate through each corner
    for (int[] corner : corners) {
      int row = corner[0];
      int col = corner[1];

      // first check if move is legal to play at the corner
      if (model.isLegalToPlay(row, col)) {
        // iterate through each card in hand to find the best one for this corner
        for (Card cardToPlay : currentPlayer.getCardsInHand()) {
          // find which attack values are exposed by this card when placed in the corner

          // figure out strategy that could prioritize placing cards in corner that show best attack values
        }
      }
    }

    return bestMove;
  }
}