package cs3500.threetrios.strategy;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.Player;
import cs3500.threetrios.model.ThreeTriosModel;

/**
 * A strategy that prioritizes flipping the maximum number of opponent cards.
 */
public class MaxFlippedCardsStrat implements GameStrategy {

  @Override
  public GameMove chooseMove(ThreeTriosModel model) {
    GameMove bestMove = null;
    int maxFlips = 0;
    Player currentPlayer = model.getCurrentPlayer();

    // iterate through all cards in hand
    for (Card cardToPlay : currentPlayer.getCardsInHand()) {
      // check all valid positions on the grid
      for (int row = 0; row < model.getGrid().getNumRows(); row++) {
        for (int col = 0; col < model.getGrid().getNumCols(); col++) {
          if (model.isLegalToPlay(row, col)) {
            // calculate how many opponent cards can be flipped with given card
            int flips = model.howManyWillFlip(cardToPlay, row, col);
            // if this move produces more flips, update bestMove
            if (flips > maxFlips) {
              maxFlips = flips;
              bestMove = new GameMove(cardToPlay, row, col);
            }
          }
        }
      }
    }
    return bestMove;
  }
}