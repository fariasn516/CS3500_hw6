package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * The model class for the game Three Trios, this is where all the rules of gameplay is handled.
 */
public class ThreeTriosModel implements Model {
  private Player bluePlayer; // the blue player
  private Player redPlayer; // the red player
  private Player currentPlayer;
  private Grid grid; // the grid for the game
  private boolean started; // determines whether the game has started or not
  private boolean gameEnded; // determines whether the game has ended or not
  private final Random rand; // helps with shuffling of the cards if needed

  // INVARIANT: The number of card cells on the grid is always one less than the total amount of
  // cards that are being played with.

  /**
   * Constructor for the model, does not take in anything.
   */
  public ThreeTriosModel() {
    this.rand = new Random();
  }

  /**
   * Constructor for the model and takes in a random.
   *
   * @param rand represents the random used for shuffling
   */
  public ThreeTriosModel(Random rand) {
    if (rand == null) {
      throw new IllegalArgumentException("Random cannot be null");
    }
    this.rand = rand;
  }

  @Override
  public <C extends Card> void startGame(List<C> cards, boolean shuffle, Grid grid) {
    if (started) {
      throw new IllegalStateException("Game already started!");
    }
    if (cards == null) {
      throw new IllegalArgumentException("Cards cannot be null");
    }
    if (allCardsNotUnique(cards)) {
      throw new IllegalArgumentException("Cards must be unique!");
    }
    if (invalidCardCount(cards, grid)) {
      throw new IllegalArgumentException("Card count must be one more than card cell count.");
    }

    if (shuffle) {
      cards = new ArrayList<>(cards);
      Collections.shuffle(cards, this.rand);
    }
    this.started = true;
    this.grid = grid;
    this.dealCards(cards);
    this.currentPlayer = redPlayer;
  }

  /**
   * Makes sure that all the cards have a unique identifier.
   *
   * @param cards represents the list of cards that is attempting to be used as the deck
   * @return true if all cards have a unique identifier, and false if not
   */
  private <C extends Card> boolean allCardsNotUnique(List<C> cards) {
    for (int i = 0; i < cards.size() - 1; i++) {
      for (int j = i + 1; j < cards.size(); j++) {
        if (cards.get(i).getName().equals(cards.get(j).getName())) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Determines if the number of cards adheres to the game's rules (ie one more than the number of
   * card cells in the grid).
   * @param cards represents the list of cards in the game
   * @param grid represents the grid in the game
   * @return true if the number of cards is correct and false otherwise
   */
  private <C extends Card> boolean invalidCardCount(List<C> cards, Grid grid) {
    return cards.size() != grid.getCardCellCount() + 1;
  }

  /**
   * Deals the cards to the two players and creates the two players.
   *
   * @param cards represent the deck of cards to be dealt
   */
  private <C extends Card> void dealCards(List<C> cards) {
    List<Card> redHand = new ArrayList<>();
    List<Card> blueHand = new ArrayList<>();
    for (int allCards = 0; allCards < cards.size(); allCards++) {
      if (allCards % 2 == 0) {
        cards.get(allCards).createCardColor(Color.RED);
        redHand.add(cards.get(allCards));
      } else {
        cards.get(allCards).createCardColor(Color.BLUE);
        blueHand.add(cards.get(allCards));
      }
    }

    this.redPlayer = new HumanPlayer(redHand, Color.RED);
    this.bluePlayer = new HumanPlayer(blueHand, Color.BLUE);

  }

  @Override
  public void placingPhase(Card card, int row, int col) {
    if (!started) {
      throw new IllegalStateException("Game not started!");
    }
    if (gameEnded) {
      throw new IllegalStateException("Game already ended!");
    }
    currentPlayer.removeFromHand(card);
    currentPlayer.addToOwnership(card);
    grid.placeCard(card, row, col);
  }

  @Override
  public void battlingPhase(int row, int col) {
    if (!started) {
      throw new IllegalStateException("Game not started!");
    }
    if (gameEnded) {
      throw new IllegalStateException("Game already ended!");
    }
    Card placedCard = grid.getCard(row, col);
    Map<String, Direction> adjacentCards = grid.getAdjacentCardsWithDirections(row, col);

    for (Map.Entry<String, Direction> card : adjacentCards.entrySet()) {
      String adjCardName = card.getKey();
      // direction of adjacent card relative to placed card
      Direction direction = card.getValue();
      // direction of adjacent card whose value will be used for battle
      Direction oppositeDirection = getOppositeDirection(direction);

      Card adjCard = findCardByName(adjCardName);

      if (adjCard.getColor() != placedCard.getColor()) {
        if (placedCard.getValueFromDirection(direction)
                > adjCard.getValueFromDirection(oppositeDirection)) {
          adjCard.flipColor();
          currentPlayer.addToOwnership(adjCard);
          if (currentPlayer.getColor() == Color.BLUE) {
            redPlayer.removeFromOwnership(adjCard);
          }
          else {
            bluePlayer.removeFromOwnership(adjCard);
          }
        }
      }
    }

  }

  /**
   * Returns the card based on the name given.
   * @param name String of the name of the card
   * @return the card given the name
   * @throws IllegalArgumentException if the card is not found in the grid
   */
  private Card findCardByName(String name) {
    Cell[][] cells = grid.getCells();

    for (int row = 0; row < grid.getNumRows(); row++) {
      for (int col = 0; col < grid.getNumCols(); col++) {
        Cell cell = cells[row][col];

        if (!cell.isHole() && cell.hasCard()) {
          Card card = cell.getCard();
          if (card.getName().equals(name)) {
            return card;
          }
        }
      }
    }
    throw new IllegalArgumentException("Card not found in grid: " + name);
  }

  /**
   * Returns the opposite direction given the direction.
   * @param direction represents the Direction that to obtain the opposite of
   * @return the opposite direction of the given direction
   */
  private Direction getOppositeDirection(Direction direction) {
    switch (direction) {
      case NORTH:
        return Direction.SOUTH;
      case SOUTH:
        return Direction.NORTH;
      case EAST:
        return Direction.WEST;
      case WEST:
        return Direction.EAST;
      default:
        throw new IllegalArgumentException("Invalid direction");
    }
  }

  @Override
  public void takeTurn(Card card, int row, int col) {
    if (!started) {
      throw new IllegalStateException("Game has not started yet!");
    }
    if (gameEnded) {
      throw new IllegalStateException("Game has already ended.");
    }
    if (!currentPlayer.getCardsInHand().contains(card)) {
      throw new IllegalArgumentException("Card does not belong to current player.");
    }

    placingPhase(card, row, col);

    battlingPhase(row, col);

    if (!isGameOver()) {
      currentPlayer = (currentPlayer == bluePlayer) ? redPlayer : bluePlayer;
    }
  }

  @Override
  public boolean isGameOver() {
    if (!started) {
      throw new IllegalStateException("Game not started!");
    }

    gameEnded = allCardCellsFilled();
    return gameEnded;
  }

  /**
   * Determines if all the card cells on the grid has been filled.
   * @return true if all card cells are filled, false otherwise
   */
  private boolean allCardCellsFilled() {
    for (Cell[] row : grid.getCells()) {
      for (Cell cell : row) {
        if (!cell.isHole() && !cell.hasCard()) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public String winner() {
    if (!this.started) {
      throw new IllegalStateException("Game not started!");
    }
    if (!this.gameEnded) {
      throw new IllegalStateException("Game has not ended!");
    }
    if (this.bluePlayer.getNumberCardsOwned() > this.redPlayer.getNumberCardsOwned()) {
      return "Blue Player";
    }
    else if (this.bluePlayer.getNumberCardsOwned() == this.redPlayer.getNumberCardsOwned()) {
      return "Tie";
    }
    else {
      return "Red Player";
    }
  }

  @Override
  public Grid getGrid() {
    Grid newGrid;
    newGrid = this.grid;
    return newGrid;
  }

  @Override
  public Player getCurrentPlayer() {
    return new HumanPlayer(this.currentPlayer.getCardsInHand(), this.currentPlayer.getColor());
  }

  @Override
  public boolean hasStarted() {
    return started;
  }
}
