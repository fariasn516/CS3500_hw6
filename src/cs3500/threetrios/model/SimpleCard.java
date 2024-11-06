package cs3500.threetrios.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class that represents the card that is used in the game Three Trios.
 */
public class SimpleCard implements Card {
  private final String name; // represents the unique name of this card
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
  public int getValueFromDirection(Direction direction) {
    if (direction == null) {
      throw new IllegalArgumentException("Direction cannot be null");
    }
    return cardValues.get(direction).getValue();
  }

  @Override
  public String getValueGivenDirection(Direction direction) {
    if (direction == null) {
      throw new IllegalArgumentException("Direction cannot be null");
    }
    return cardValues.get(direction).toString();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Card copy() {
    return new SimpleCard(this.name, this.cardValues);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, cardValues);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof SimpleCard)) {
      return false;
    }
    if (!this.name.equals(((SimpleCard) obj).name)) {
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
