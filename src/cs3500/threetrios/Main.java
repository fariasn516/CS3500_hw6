package cs3500.threetrios;

import java.util.List;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.GameGrid;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.Model;
import cs3500.threetrios.model.ReadOnlyModel;
import cs3500.threetrios.model.SimpleCard;
import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.model.Value;
import cs3500.threetrios.view.ThreeTriosFrameView;
import cs3500.threetrios.view.ThreeTriosModelView;

public class Main {

  public static void main (String[] args) {
    Card ratCard = new SimpleCard("rat", Value.ACE, Value.ONE, Value.TWO, Value.THREE);
    Card oxCard = new SimpleCard("ox", Value.ACE, Value.ONE, Value.TWO, Value.THREE);
    Card tigerCard = new SimpleCard("tiger", Value.TWO, Value.ONE, Value.FIVE, Value.SIX);
    Card rabbitCard = new SimpleCard("rabbit", Value.FOUR, Value.NINE, Value.ACE, Value.THREE);
    Card dragonCard = new SimpleCard("dragon", Value.ACE, Value.ACE, Value.TWO, Value.ONE);
    Card horseCard = new SimpleCard("horse", Value.TWO, Value.EIGHT, Value.TWO, Value.THREE);
    Card goatCard = new SimpleCard("goat", Value.ACE, Value.SIX, Value.FOUR, Value.SEVEN);
    Card monkeyCard = new SimpleCard("monkey", Value.ACE, Value.ACE, Value.ACE, Value.ACE);
    Card roosterCard = new SimpleCard("rooster", Value.ONE, Value.ONE, Value.ONE, Value.ONE);
    Card dogCard = new SimpleCard("dog", Value.NINE, Value.EIGHT, Value.SEVEN, Value.SIX);

    List<Card> deck = List.of(ratCard, oxCard, tigerCard, rabbitCard, dragonCard, horseCard,
            goatCard, monkeyCard, roosterCard, dogCard);

    Model model = new ThreeTriosModel();

    boolean[][] noHolesLayout = {
            {false, false, false},
            {false, false, false},
            {false, false, false}
    };
    Grid gridWithNoHoles = new GameGrid(3, 3, noHolesLayout);

    model.startGame(deck, false, gridWithNoHoles);
    model.placingPhase(ratCard, 1, 0);

    ReadOnlyModel readModel = model;

    ThreeTriosFrameView view = new ThreeTriosModelView(readModel);
    view.makeVisible();
  }
}
