package cs3500.threetrios.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import cs3500.threetrios.model.ReadOnlyModel;

public class ThreeTriosPanel extends JPanel {

  /**
   *
   * @param model
   */
  public ThreeTriosPanel(ReadOnlyModel model) {
  }

  // make sure this is singular and NOT plural (component and not components)
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g.create();

    drawGrid(g2d);
  }

  private void drawGrid(Graphics2D g2d) {
    // draw vertical lines
    g2d.setColor(Color.BLACK);
    g2d.fillOval(0, 0, getWidth(), getHeight());
  }


  /**
   *
   */
  class ThreeTriosMouseListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
  }
}
