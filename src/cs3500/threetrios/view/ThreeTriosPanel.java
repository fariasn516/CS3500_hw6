package cs3500.threetrios.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.List;

import cs3500.threetrios.model.Card;


import javax.swing.JPanel;

import cs3500.threetrios.model.Cell;
import cs3500.threetrios.model.Direction;
import cs3500.threetrios.model.ReadOnlyModel;

public class ThreeTriosPanel extends JPanel implements ThreeTriosPanelView {
  private final ReadOnlyModel model;

  /**
   *
   * @param model
   */
  public ThreeTriosPanel(ReadOnlyModel model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g.create();

    drawPlayers(g2d);
    drawGrid(g2d);
  }

  /**
   * Draws out the grid for the game, including cars on the grid, card cells, and holes.
   * @param g2d represents the 2d graphics object used
   */
  private void drawGrid(Graphics2D g2d) {
    Cell[][] cells = model.getGrid().getCells();

    for (int row = 0; row < model.getGrid().getNumRows(); row++) {
      for (int col = 0; col < model.getGrid().getNumCols(); col++) {
        Cell cell = cells[row][col];
        if (cell.isHole()) {
          g2d.setColor(Color.YELLOW);
          g2d.fillRect(150, 200, 150, 150);
        }
        else if (!cell.hasCard()) {
          g2d.setColor(Color.LIGHT_GRAY);
          g2d.fillRect(col * 150 + 200, row * 150, 150, 150);
          g2d.setColor(Color.BLACK);
          g2d.drawRect(col * 150 + 200, row * 150, 150, 150);
        }
        else {
          Card card = cell.getCard();
          String color = this.model.getCardOwnerColor(card).toString();
          System.out.println(color);
          if (color.equals("RED")) {
            g2d.setColor(Color.PINK);
          }
          else if (color.equals("BLUE")) {
            g2d.setColor(new Color (96, 166, 245));
          }
          g2d.fillRect(col * 150 + 200, row * 150, 150, 150);
          g2d.setColor(Color.BLACK);
          g2d.drawRect(col * 150 + 200, row * 150, 150, 150);
          drawCard(card, col, row, g2d);
        }
      }
    }
  }

  private void drawCard(Card card, int col, int row, Graphics2D g2d) {
    // north
    g2d.drawString(card.getValueGivenDirection(Direction.NORTH),
            col * 150 + 225 + 50, row * 150 + 150 / 4);
    // south
    g2d.drawString(card.getValueGivenDirection(Direction.SOUTH),
            col * 150 + 225 + 50, row * 150 + 130);
    // east
    g2d.drawString(card.getValueGivenDirection(Direction.EAST),
            col * 150 + 275 + 50, row * 150 + 75);
    // west
    g2d.drawString(card.getValueGivenDirection(Direction.WEST),
            col * 150 + 175 + 50, row * 150 + 75);
  }

  /**
   * Draws out the hand of the two players for the game, their cards and their color represented.
   * @param g2d represents the 2d graphics object used
   */
  private void drawPlayers(Graphics2D g2d) {
    // drawing the red player
    drawRedPlayer(g2d);

    // drawing blue player
    drawBluePlayer(g2d);
  }

  /**
   * Draws the red player's hand.
   * @param g2d represents the 2d graphics object to be drawn with
   */
  private void drawRedPlayer(Graphics2D g2d) {
    List<Card> redCards = model.getRedPlayer();
    int redHeight = getHeight() / model.getRedPlayer().size() + 1;
    int startRedHeight = 0;

    for (int numRedCards = 0; numRedCards < model.getRedPlayer().size(); numRedCards++) {
      g2d.setColor(Color.PINK);
      g2d.fillRect(0, startRedHeight, 150, redHeight);
      g2d.setColor(Color.BLACK);
      g2d.drawRect(0, startRedHeight, 150, redHeight);

      // north
      g2d.drawString(redCards.get(numRedCards).getValueGivenDirection(Direction.NORTH),
              75, startRedHeight + redHeight / 4);
      // south
      g2d.drawString(redCards.get(numRedCards).getValueGivenDirection(Direction.SOUTH),
              75, startRedHeight + redHeight - 20);
      // east
      g2d.drawString(redCards.get(numRedCards).getValueGivenDirection(Direction.EAST),
              125, startRedHeight + redHeight / 2);
      // west
      g2d.drawString(redCards.get(numRedCards).getValueGivenDirection(Direction.WEST),
              25, startRedHeight + redHeight / 2);
      startRedHeight += redHeight;
    }
  }

  /**
   * Draws the blue player's hand.
   * @param g2d represent the 2d graphics object to be drawn with
   */
  private void drawBluePlayer(Graphics2D g2d) {
    List<Card> blueCards = model.getBluePlayer();
    int blueHeight = getHeight() / model.getBluePlayer().size() + 1;
    int startBlueHeight = 0;

    for (int numBlueCards = 0; numBlueCards < model.getBluePlayer().size(); numBlueCards++) {
      Color blueCard = new Color (96, 166, 245);
      g2d.setColor(blueCard);
      g2d.fillRect(getWidth() - 150, startBlueHeight, 150, blueHeight);
      g2d.setColor(Color.BLACK);
      g2d.drawRect(getWidth() - 150, startBlueHeight, 150, blueHeight);

      // north
      g2d.drawString(blueCards.get(numBlueCards).getValueGivenDirection(Direction.NORTH),
              getWidth() - 75, startBlueHeight + blueHeight / 4);
      // south
      g2d.drawString(blueCards.get(numBlueCards).getValueGivenDirection(Direction.SOUTH),
              getWidth() - 75, startBlueHeight + blueHeight - 20);
      // east
      g2d.drawString(blueCards.get(numBlueCards).getValueGivenDirection(Direction.EAST),
              getWidth() - 25, startBlueHeight + blueHeight / 2);
      // west
      g2d.drawString(blueCards.get(numBlueCards).getValueGivenDirection(Direction.WEST),
              getWidth() - 125, startBlueHeight + blueHeight / 2);
      startBlueHeight += blueHeight;
    }
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
