package cs3500.threetrios.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.List;

import javax.swing.JPanel;

import cs3500.threetrios.Controller.Controller;
import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.Cell;
import cs3500.threetrios.model.Direction;
import cs3500.threetrios.model.ReadOnlyModel;

public class ThreeTriosPanel extends JPanel implements ThreeTriosPanelView {
  private final ReadOnlyModel model; // represents the model whose characteristics to be printed
  private boolean clickedAlready; // represents whether a cell has been clicked already
  private int index; // represents the index of a clicked card (is -1 if no card clicked)

  /**
   * Constructor the for panel. Takes in a ReadOnlyModel that is immutable.
   * @param model represent the ReadOnlyModel that the panel takes in
   */
  public ThreeTriosPanel(ReadOnlyModel model) {
    this.model = model;
    this.index = -1;
    this.clickedAlready = false;
  }

  @Override
  public void addClickListener(Controller listener) {
    this.addMouseListener(new ThreeTriosMouseListener(listener));
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
    int cellHeight = getHeight() / model.getGrid().getNumRows();
    int cellWidth = (getWidth() - 400) / model.getGrid().getNumCols();

    for (int row = 0; row < model.getGrid().getNumRows(); row++) {
      for (int col = 0; col < model.getGrid().getNumCols(); col++) {
        Cell cell = cells[row][col];
        if (cell.isHole()) {
          g2d.setColor(Color.YELLOW);
          g2d.fillRect(col * cellWidth + 200, row * cellHeight, cellWidth, cellHeight);
        }
        else if (!cell.hasCard()) {
          g2d.setColor(Color.LIGHT_GRAY);
          g2d.fillRect(col * cellWidth + 200, row * cellHeight, cellWidth, cellHeight);
          g2d.setColor(Color.BLACK);
          g2d.drawRect(col * cellWidth + 200, row * cellHeight, cellWidth, cellHeight);
        }
        else {
          Card card = cell.getCard();
          String color = this.model.getCardOwnerColor(card).toString();
          if (color.equals("RED")) {
            g2d.setColor(Color.PINK);
          }
          else if (color.equals("BLUE")) {
            g2d.setColor(new Color (96, 166, 245));
          }
          g2d.fillRect(col * cellWidth + 200, row * cellHeight, cellWidth, cellHeight);
          g2d.setColor(Color.BLACK);
          g2d.drawRect(col * cellWidth + 200, row * cellHeight, cellWidth, cellHeight);
          drawCard(card, col, row, g2d, cellWidth, cellHeight);
        }
      }
    }
  }

  /**
   * Draws the card that is on the grid at the particular row & col.
   * @param card represents the card to be drawn
   * @param col represents the column of the card
   * @param row represents the row of the card
   * @param g2d represents the 2d graphics object
   */
  private void drawCard(Card card, int col, int row, Graphics2D g2d, int width, int height) {
    // north
    g2d.drawString(card.getValueGivenDirection(Direction.NORTH),
            col * width + width / 2 + 200, row * height + height / 4);
    // south
    g2d.drawString(card.getValueGivenDirection(Direction.SOUTH),
            col * width + width / 2 + 200, row * height + height - 20);
    // east
    g2d.drawString(card.getValueGivenDirection(Direction.EAST),
            col * width + width + 175, row * height + height / 2);
    // west
    g2d.drawString(card.getValueGivenDirection(Direction.WEST),
            col * width + 225, row * height + height / 2);
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
      if (index == numRedCards && !clickedAlready
              && model.getCurrentPlayer().getColor().toString().equals("RED")) {
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(0, startRedHeight, 150, redHeight);
        g2d.setStroke(new BasicStroke(1));
      }
      else {
        g2d.drawRect(0, startRedHeight, 150, redHeight);
      }

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
    private final Controller features; //

    /**
     *
     * @param features
     */
    public ThreeTriosMouseListener(Controller features) {
      this.features = features;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      if (e.getX() <= 150) {
        System.out.println("red player");
        findCardIndex(e.getY(), "red");
      }
      else if (e.getX() >= 200 && e.getX() <= getWidth() - 200) {
        System.out.println("grid");
        findGridIndex(e.getX(), e.getY());
      }
      else if (e.getX() >= getWidth() - 150 && e.getX() <= getWidth()) {
        System.out.println("blue player");
        findCardIndex(e.getY(), "blue");
      }
      features.handleHandClick(index);
    }

    /**
     * Finds the card index give the color of the player and the y-position of the click (indexing
     * starts at 0).
     * @param yClick represents the y-coordinate of the mouse click
     * @param playerColor represents the color of the player that the card belongs to
     */
    private void findCardIndex(int yClick, String playerColor) {
      if (playerColor.equals("red")) {
        if (clickedAlready) {
          clickedAlready = false;
        }
        else if (index == yClick / (getHeight() / model.getRedPlayer().size())) {
          clickedAlready = true;
          index = -1;
          System.err.println(index);
        }
        index = yClick / (getHeight() / model.getRedPlayer().size());
        System.err.println(index);
      }
      else {
        if (index == yClick / (getHeight() / model.getRedPlayer().size())) {
          clickedAlready = true;
          index = -1;
        }
        else if (index != yClick / (getHeight() / model.getRedPlayer().size()) && clickedAlready) {
          clickedAlready = false;
        }
        index = yClick / (getHeight() / model.getBluePlayer().size());
        System.err.println(index);
      }
    }

    /**
     * Finds the indexing of the grid based on the x and y positioning of the click.
     * (0,0) represents the top left-most cell and goes by row-column indexing.
     * @param xClick represents the x coordinate of the click
     * @param yClick represents the y coordinate of the click
     */
    private void findGridIndex(int xClick, int yClick) {
      System.err.println(xClick);
      System.err.println(yClick);

      System.err.println(yClick / (getHeight() / model.getGrid().getNumRows()) + ", "
              + (xClick / ((getWidth() - 400) / model.getGrid().getNumCols()) - 1));
    }

    @Override
    public void mousePressed(MouseEvent e) {
      // no need to implement
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      // no need to implement
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      // no need to implement
    }

    @Override
    public void mouseExited(MouseEvent e) {
      // no need to implement
    }
  }
}
