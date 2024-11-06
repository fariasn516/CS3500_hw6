package cs3500.threetrios.view;

import javax.swing.*;

import cs3500.threetrios.model.ReadOnlyModel;

public class ThreeTriosModelView extends JFrame implements ThreeTriosFrameView {
  private final ThreeTriosPanel panel;
  private final ReadOnlyModel model;

  public ThreeTriosModelView(ReadOnlyModel model) {
    this.panel = new ThreeTriosPanel(model);
    this.model = model;
    this.setSize(600, 600);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.add(panel);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }
}
