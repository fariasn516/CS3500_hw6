package cs3500.threetrios.Controller;

import java.io.FileNotFoundException;
import java.util.List;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.CardFileParser;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.GridFileParser;
import cs3500.threetrios.model.Model;
import cs3500.threetrios.view.ThreeTriosFrameView;
import cs3500.threetrios.view.ThreeTriosModelView;

public class ThreeTriosSwingController implements Controller {
  private ThreeTriosFrameView view;
  private Model model;

  public ThreeTriosSwingController(ThreeTriosFrameView view) {
    this.view = view;
  }

  @Override
  public void playGame(Model model, String cardFilePath, String gridFilePath, boolean shuffle) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    this.model = model;
    CardFileParser deckParse = new CardFileParser(cardFilePath);
    GridFileParser gridParse = new GridFileParser(gridFilePath);
    List<Card> deck;
    Grid grid;

    try {
      deck = deckParse.createDeck();
      grid = gridParse.createGridFromFile();
      model.startGame(deck, shuffle, grid);
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("One or more of your files cannot be found.");
    }
    catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    catch (IllegalStateException e) {
      throw new IllegalStateException(e.getMessage());
    }
    view.addClickListener(this);
    view.makeVisible();

  }

  /**
   * Overloaded method in order to make testing easier.
   * @param model
   * @param deck
   * @param grid
   * @param shuffle
   */
  public void playGame(Model model, List<Card> deck, Grid grid, boolean shuffle) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    if (deck == null) {
      throw new IllegalArgumentException("Deck cannot be null.");
    }
    if (grid == null) {
      throw new IllegalArgumentException("Grid cannot be null.");
    }

    try {
      model.startGame(deck, shuffle, grid);
    }
    catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    view.addClickListener(this);
    view.makeVisible();
  }

  @Override
  public void handleCellClick() {
    view.refresh();
  }
}
