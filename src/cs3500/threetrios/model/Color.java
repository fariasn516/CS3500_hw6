package cs3500.threetrios.model;

/**
 * Represents the color that the players can be.
 */
public enum Color {
  RED("red"), BLUE("blue");

  private final String color; // string version of the color

  Color(String color) {
    this.color = color;
  }

  /**
   * Observation that returns the string representation of this color.
   * @return the String representation of this color
   */
  public String getColor() {
    return color;
  }
}
