package cs3500.threetrios.model;

/**
 * Represents the possible values the card can have.
 */
public enum Value {
  ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"),
  EIGHT("8"), NINE("9"), ACE("A");
  private final String value;

  Value(String value) {
    this.value = value;
  }

  /**
   * Observer that returns the integer value of this Value.
   * @return the integer value of this Value.
   */
  public int getValue() {
    if (this.equals(Value.ACE)) {
      return 10;
    }
    else {
      return Integer.parseInt(value);
    }
  }

  @Override
  public String toString() {
    return value;
  }

}
