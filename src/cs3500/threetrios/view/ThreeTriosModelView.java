package cs3500.threetrios.view;

import javax.swing.JFrame;

import cs3500.threetrios.Controller.Controller;
import cs3500.threetrios.model.ReadOnlyModel;

public class ThreeTriosModelView extends JFrame implements ThreeTriosFrameView {
  private final ThreeTriosPanel panel;
  private final ReadOnlyModel model;

  /**
   *
   * @param model
   */
  public ThreeTriosModelView(ReadOnlyModel model) {
    this.panel = new ThreeTriosPanel(model);
    this.model = model;
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.add(panel);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void addClickListener(Controller listener) {
    panel.addClickListener(listener);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }
}
