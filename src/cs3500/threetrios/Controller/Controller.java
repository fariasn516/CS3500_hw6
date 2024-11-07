package cs3500.threetrios.Controller;

import java.util.List;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.Model;

/**
 * Controller interface that takes in the user's inputs, feeding to the model and
 * allowing the user to play the game.
 */
public interface Controller {

  /**
   *
   * @param model
   * @param cardFilePath
   * @param gridFilePath
   * @param shuffle
   */
  void playGame(Model model,String cardFilePath, String gridFilePath, boolean shuffle);

  void playGame(Model model, List<Card> deck, Grid grid, boolean shuffle);

  /**
   *
   */
  void handleCellClick();
}
