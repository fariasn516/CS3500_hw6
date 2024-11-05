package cs3500.threetrios;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Random;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.GameGrid;
import cs3500.threetrios.model.SimpleCard;
import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.Value;
import cs3500.threetrios.view.ThreeTriosGameTextView;

import static org.junit.Assert.assertEquals;

/**
 * Tests everything related to the view.
 */
public class ThreeTriosTextViewTest {
  private ThreeTriosModel model;
  private ThreeTriosGameTextView view;
  private List<Card> deck;

  @Before
  public void setUp() {
    deck = List.of(
            new SimpleCard("rat", Value.ACE, Value.ONE, Value.TWO, Value.THREE),
            new SimpleCard("ox", Value.TWO, Value.FOUR, Value.FOUR, Value.FOUR),
            new SimpleCard("tiger", Value.THREE, Value.FOUR, Value.FIVE, Value.SIX),
            new SimpleCard("rabbit", Value.NINE, Value.EIGHT, Value.SEVEN, Value.SIX)
    );

    model = new ThreeTriosModel(new Random(42));
    model.startGame(deck, true, new GameGrid(2, 2, new boolean[][]{
            {false, true},
            {false, false}
    }));
    view = new ThreeTriosGameTextView(model);
  }

  @Test
  public void testInitialViewState() {
    String expectedView = "Player: RED\n" +
            "Grid:\n" +
            "_ \n" +
            "__\n" +
            "Hand:\n" +
            "rabbit 9 8 7 6 \n" +
            "rat A 1 2 3 \n";
    assertEquals(expectedView, view.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testViewWithNullModel() {
    ThreeTriosGameTextView newView = new ThreeTriosGameTextView(null);
  }

  @Test
  public void testViewAfterPlacingCard() {
    Card cardToPlace = model.getCurrentPlayer().getCardsInHand().get(0);
    model.takeTurn(cardToPlace, 0, 0);

    String expectedView = "Player: BLUE\n" +
            "Grid:\n" + model.getGrid().toString() +
            "Hand:\n" +
            "ox 2 4 4 4 \n" +
            "tiger 3 4 5 6 \n";
    assertEquals(expectedView, view.toString());
  }

  @Test
  public void testViewAfterBattlingPhase() {
    model.takeTurn(deck.get(0), 0, 0);
    model.takeTurn(deck.get(1), 1, 0);

    String expectedView = "Player: RED\n" +
            "Grid:\n" +
            "B \n" +
            "B_\n" +
            "Hand:\n" +
            "rabbit 9 8 7 6 \n";
    assertEquals(expectedView, view.toString());
  }

  @Test
  public void testViewAfterFullTurn() {
    Card cardToPlace = model.getCurrentPlayer().getCardsInHand().get(0);
    model.takeTurn(cardToPlace, 0, 0);

    String expectedView = "Player: BLUE\n" +
            "Grid:\n" +
            "R \n" +
            "__\n" +
            "Hand:\n" +
            "ox 2 4 4 4 \n" +
            "tiger 3 4 5 6 \n";
    assertEquals(expectedView, view.toString());
  }

  @Test
  public void testViewAfterGameOver() {
    model.takeTurn(deck.get(0), 0, 0);
    model.takeTurn(deck.get(1), 1, 0);
    model.takeTurn(deck.get(3), 1, 1);

    String expectedView = "Player: RED\n" +
            "Grid:\n" +
            "B \n" +
            "RR\n" +
            "Hand:\n";
    assertEquals(expectedView, view.toString());
    assertEquals("Tie", model.winner());
  }


  @Test
  public void testViewWithEmptyHand() {
    Card cardToPlace = model.getCurrentPlayer().getCardsInHand().get(0);
    model.takeTurn(cardToPlace, 0, 0);
    Card cardToPlace1 = model.getCurrentPlayer().getCardsInHand().get(0);
    model.takeTurn(cardToPlace1, 1, 0);
    Card cardToPlace2 = model.getCurrentPlayer().getCardsInHand().get(0);
    model.takeTurn(cardToPlace2, 1, 1);

    String expectedView = "Player: RED\n" +
            "Grid:\n" + model.getGrid().toString() +
            "Hand:\n";
    assertEquals(expectedView, view.toString());
  }
}