package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a human player, aka a player of the game Three Trios.
 */
public class HumanPlayer implements Player {
  private final List<Card> cardsInHand; // represents the cards in the hand
  private final List<Card> ownedCardsOnGrid; // represents the cards that are owned and on the grid
  private final Color color; // represents this player's color

  /**
   * Constructor for the HumanPlayer class.
   * @param cardsInHand represents the list of playable cards that are in the player's hand
   * @param color represents the color of this player
   */
  public HumanPlayer(List<Card> cardsInHand, Color color) {
    if (cardsInHand == null) {
      throw new IllegalArgumentException("Cards cannot be null");
    }
    if (color == null) {
      throw new IllegalArgumentException("Color cannot be null");
    }
    this.color = color;
    if (!checkCorrectColor(cardsInHand)) {
      throw new IllegalArgumentException("Some cards are not the correct color");
    }
    this.cardsInHand = cardsInHand;
    this.ownedCardsOnGrid = new ArrayList<>();
  }

  /**
   * Makes sure that all the cards given are the correct color.
   * @param cards represents the cards in hand
   * @return whether the cards are all the correct color
   */
  private boolean checkCorrectColor(List<Card> cards) {
    for (Card card : cards) {
      if (card.getColor() != this.color) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void removeFromHand(Card card) {
    if (!cardsInHand.contains(card)) {
      throw new IllegalArgumentException("The card is not in the player's hand.");
    }
    cardsInHand.remove(card);
  }


  @Override
  public void removeFromOwnership(Card card) {
    if (!ownedCardsOnGrid.contains(card)) {
      throw new IllegalArgumentException("The card is not in the player's grid yet.");
    }
    ownedCardsOnGrid.remove(card);
  }

  @Override
  public void addToOwnership(Card cards) {
    this.ownedCardsOnGrid.add(cards);
  }

  @Override
  public List<Card> getOwnedCardsOnGrid() {
    return new ArrayList<>(this.ownedCardsOnGrid);
  }

  @Override
  public List<Card> getCardsInHand() {
    return new ArrayList<>(this.cardsInHand);
  }

  @Override
  public int getNumberCardsOwned() {
    return this.ownedCardsOnGrid.size() + this.cardsInHand.size();
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public String toString() {
    return "Player: " + color;
  }
}
