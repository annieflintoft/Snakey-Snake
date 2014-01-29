/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakey.snake;

import environment.Environment;
import environment.GraphicsPalette;
import environment.Grid;
import image.ResourceTools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author macbook
 */
class SnakeEnvironment extends Environment {

    private Grid grid;
    private int score = 0;
    private Snake snake;
    private ArrayList<Point> apples;
    private int speed = 5;
    private int moveCounter = speed;
    private int i;

    public SnakeEnvironment() {
    }

    @Override
    public void initializeEnvironment() {
        this.setBackground(ResourceTools.loadImageFromResource("resources/grass.jpg"));

        this.grid = new Grid();
        this.grid.setColor(Color.BLACK);
        this.grid.setColumns(44);
        this.grid.setRows(15);
        this.grid.setCellHeight(20);
        this.grid.setCellWidth(20);
        this.grid.setPosition(new Point(10, 100));

        this.apples = new ArrayList<Point>();
        this.apples.add(new Point(10, 12));
        this.apples.add(new Point(5, 17));

        this.snake = new Snake();
        this.snake.getBody().add(new Point(5, 5));
        this.snake.getBody().add(new Point(5, 4));
        this.snake.getBody().add(new Point(5, 3));
        this.snake.getBody().add(new Point(4, 3));

    }

    private Point getRandomGridLocation() {
        return new Point((int) (Math.random() * this.grid.getColumns()),
                (int) (Math.random() * this.grid.getRows()));

    }

    @Override
    public void timerTaskHandler() {
        if (snake != null) {
            if (moveCounter <= 0) {
                snake.move();
                moveCounter = speed;
                checkSnakeIntersection();
            } else {
                moveCounter--;
            }
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.score += 100000;

        } else if (e.getKeyCode() == KeyEvent.VK_M) {
            snake.move();

        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            snake.setDirection(Direction.UP);
            snake.move();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            snake.setDirection(Direction.LEFT);

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            snake.setDirection(Direction.DOWN);

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            snake.setDirection(Direction.RIGHT);

        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (this.grid != null) {
            grid.paintComponent(graphics);

            if (this.apples != null) {
                for (int i = 0; i < this.apples.size(); i++) {
                    GraphicsPalette.drawApple(graphics, this.grid.getCellPosition(this.apples.get(i)), this.grid.getCellSize());
                }
            }

            graphics.setColor(Color.PINK);
            Point cellLocation;
            if (snake != null) {
                for (int i = 0; i < snake.getBody().size(); i++) {
                    cellLocation = grid.getCellPosition(snake.getBody().get(i));
                    graphics.fillOval(cellLocation.x, cellLocation.y, grid.getCellWidth(), grid.getCellHeight());
                }
            }
//            } else {
//                //body
//                graphics.setColor(Color.MAGENTA);
//                cellLocation = grid.getCellPosition(snake.getBody().get(j));
//                graphics.fillOval(cellLocation.x, cellLocation.y, grid.getCellWidth(), grid.getCellHeight());
//            }

//                GraphicsPalette.drawPoisonBottle(graphics, new Point (100, 100), new Point (20, 20), Color.yellow);
//                GraphicsPalette.drawUnicorn(graphics, new Point (200, 200), new Point (200, 200), Color.yellow, environment.Direction.NORTH);


//                       cellLocation = grid.getCellPosition(snake.getBody().get(i));
//                       graphics.fillOval(cellLocation.x, cellLocation.y, grid.getCellWidth(), grid.getCellHeight());
//                   }


        }

        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("CenturyGothic", Font.BOLD, 60));
        graphics.drawString("Score: " + this.score, 50, 50);

    }

    private void checkSnakeIntersection() {
        for (int i = 0; i < this.apples.size(); i++) {
            if (snake.getHead().equals(this.apples.get(i))) {

                System.out.println("APPLE CHOOOOOOMP!!!!");
            }
        }
    }
}
