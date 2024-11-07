package cs3500.threetrios.view;

public interface ThreeTriosFrameView {
  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   *
   */
  void addClickListener();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();
}
