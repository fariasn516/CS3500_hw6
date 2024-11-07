package cs3500.threetrios.model;

/**
 * Represents a move in the Three Trios game.
 */
public class GameMove {
  private final Card card;
  private final int row;
  private final int col;

  /**
   * Constructs a Move with a card and position.
   *
   * @param card the card being played
   * @param row  the row position
   * @param col  the column position
   */
  public GameMove(Card card, int row, int col) {
    this.card = card;
    this.row = row;
    this.col = col;
  }

  public Card getCard() {
    return card;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }
}
