package cs3500.threetrios.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents the card that is used in the game Three Trios.
 */
public class SimpleCard implements Card {
  private final String name; // represents the unique name of this card
  private Color color; // represents the color of this card
  private final Map<Direction, Value> cardValues; // represents the values of this card

  /**
   * Constructor for SimpleCard.
   * @param cardValues represents the Value that this card has at each Direction
   */
  public SimpleCard(String name, Map<Direction, Value> cardValues) {
    if (cardValues == null) {
      throw new IllegalArgumentException("Values cannot be null");
    }
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    if (!validValues(cardValues)) {
      throw new IllegalArgumentException("All directions must be accounted for");
    }
    this.name = name;
    this.cardValues = cardValues;
  }

  /**
   * Another constructor for SimpleCard that takes in the name of the card, its color,
   * and a map of its directions and values.
   * @param name represents the name of this card
   * @param color represents the color of this card
   * @param cardValues represents the values this card has
   */
  public SimpleCard(String name, Color color, Map<Direction, Value> cardValues) {
    if (cardValues == null) {
      throw new IllegalArgumentException("Values cannot be null");
    }
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    if (!validValues(cardValues)) {
      throw new IllegalArgumentException("All directions must be accounted for");
    }

    this.name = name;
    this.color = color;
    this.cardValues = cardValues;
  }

  /**
   * Another constructor for SimpleCard.
   * @param name represents the name of this card
   * @param north represents the value at the north direction
   * @param south represents the value at the south direction
   * @param east represents the value at the east direction
   * @param west represents the value at the west direction
   */
  public SimpleCard(String name, Value north, Value south, Value east, Value west) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    if (north == null || south == null || east == null || west == null) {
      throw new IllegalArgumentException("One of your values is null");
    }
    this.name = name;
    this.cardValues = new HashMap<>();
    cardValues.put(Direction.NORTH, north);
    cardValues.put(Direction.SOUTH, south);
    cardValues.put(Direction.EAST, east);
    cardValues.put(Direction.WEST, west);
  }

  /**
   * Checks if the map has every possible direction.
   * @param values represents the map of directions and values
   * @return true if each direction is accounted for and false if not
   */
  private boolean validValues(Map<Direction, Value> values) {
    for (Direction direction : Direction.values()) {
      if (!values.containsKey(direction)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int compareTo(Card other, Direction direction) {
    if (other == null) {
      throw new IllegalArgumentException("Cannot compare null card");
    }
    if (direction == null) {
      throw new IllegalArgumentException("Cannot compare null direction");
    }

    if (direction.equals(Direction.NORTH)) {
      return compareValues(this.getValueFromDirection(direction),
              other.getValueFromDirection(Direction.SOUTH));
    }

    else if (direction.equals(Direction.EAST)) {
      return compareValues(this.getValueFromDirection(direction),
              other.getValueFromDirection(Direction.WEST));
    }

    else if (direction.equals(Direction.SOUTH)) {
      return compareValues(this.getValueFromDirection(direction),
              other.getValueFromDirection(Direction.NORTH));
    }

    return compareValues(this.getValueFromDirection(direction),
            other.getValueFromDirection(Direction.EAST));
  }

  // compares two integer values
  private int compareValues(int value1, int value2) {
    return Integer.compare(value1, value2);
  }

  @Override
  public void createCardColor(Color color) {
    this.color = color;
  }

  @Override
  public void flipColor() {
    if (this.color == Color.RED) {
      this.color = Color.BLUE;
    }
    else if (this.color == Color.BLUE) {
      this.color = Color.RED;
    }
  }

  @Override
  public int getValueFromDirection(Direction direction) {
    if (direction == null) {
      throw new IllegalArgumentException("Direction cannot be null");
    }
    return cardValues.get(direction).getValue();
  }

  @Override
  public Color getColor() {
    if (color == null) {
      throw new IllegalStateException("Color has not been assigned yet!");
    }
    return color;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Card copy() {
    return new SimpleCard(this.name, this.color, this.cardValues);
  }

  @Override
  public int hashCode() {
    return this.color.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof SimpleCard)) {
      return false;
    }
    if (!this.name.equals(((SimpleCard) obj).name)) {
      return false;
    }
    if (this.color != ((SimpleCard) obj).color) {
      return false;
    }
    for (Direction direction : Direction.values()) {
      if (getValueFromDirection(direction)
              != ((SimpleCard) obj).getValueFromDirection(direction)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder stringRep = new StringBuilder();
    stringRep.append(this.name).append(" ");
    for (Direction direction : Direction.values()) {
      stringRep.append(cardValues.get(direction).toString()).append(" ");
    }
    return stringRep.toString();
  }
}
