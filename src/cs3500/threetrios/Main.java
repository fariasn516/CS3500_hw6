package cs3500.threetrios;

import cs3500.threetrios.model.ReadOnlyModel;
import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.view.ThreeTriosFrameView;
import cs3500.threetrios.view.ThreeTriosModelView;

public class Main {

  public static void main (String[] args) {
    ReadOnlyModel model = new ThreeTriosModel();
    ThreeTriosFrameView view = new ThreeTriosModelView(model);
    view.makeVisible();
  }
}
