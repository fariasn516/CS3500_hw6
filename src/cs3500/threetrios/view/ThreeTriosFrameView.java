package cs3500.threetrios.view;

import cs3500.threetrios.Controller.Controller;

public interface ThreeTriosFrameView {
  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   *
   */
  void addClickListener(Controller listener);

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();
}
