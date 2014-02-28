/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakey.snake;

import audio.AudioPlayer;
import environment.Environment;
import environment.GraphicsPalette;
import environment.Grid;
import image.ResourceTools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

//
//        // draw stem
/**
 *
 * @author macbook
 */
class SnakeEnvironment extends Environment {

    private Grid grid;
    private int score = 0;
    private Snake snake;
    private int speed = 2;
    private int moveCounter = speed;
    private int i;
    private GameState gameState;
    private ArrayList<Point> bombs;
    private ArrayList<Point> lollipops;
    private ArrayList<Point> stars;
    private ArrayList<Point> diamonds;
    private Point cellLocation;
    private Image lollipop;
    private Image star;
    private Image diamond;
    

    public SnakeEnvironment() {
    }

    @Override
    public void initializeEnvironment() {
        this.setBackground(ResourceTools.loadImageFromResource("resources/sparklez.jpg"));
        this.lollipop = ResourceTools.loadImageFromResource("resources/suckers.png");
        this.star = ResourceTools.loadImageFromResource("resources/star.png");
        this.diamond = ResourceTools.loadImageFromResource("resources/diamond.png");

        this.grid = new Grid();
        this.grid.setColor(Color.BLACK);
        this.grid.setColumns(44);
        this.grid.setRows(25);
        this.grid.setCellHeight(20);
        this.grid.setCellWidth(20);
        this.grid.setPosition(new Point(10, 100));

        //44 x 24
        
        this.lollipops = new ArrayList<Point>();
        for (int j = 0; j < 10; j++) {
            this.lollipops.add(new Point(getRandomGridPoint()));
        }
        
        this.stars = new ArrayList<Point>();
        for (int j = 0; j < 7; j++) {
            this.stars.add(new Point(getRandomGridPoint()));
        }
        
        this.diamonds = new ArrayList<Point>();
        for (int k = 0; k < 5; k++) {
            this.diamonds.add(new Point(getRandomGridPoint()));            
        }
        
        this.bombs = new ArrayList<Point>();
        for (int l = 0; l < 10; l++) {
            this.bombs.add((new Point(getRandomGridPoint())));
        }

        this.snake = new Snake();
        this.snake.getBody().add(new Point(5, 5));
        this.snake.getBody().add(new Point(5, 4));
        this.snake.getBody().add(new Point(5, 3));
        this.snake.getBody().add(new Point(5, 2));
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
                if (snake.selfHitTest()) {
                }
            }
        }
        if (snake.getHead().x < 0) {
            snake.getHead().x = grid.getColumns();
        } else if (snake.getHead().x > grid.getColumns()) {
            snake.getHead().x = 0;
        } else if (snake.getHead().y < 0) {
            snake.getHead().y = grid.getRows();
        } else if (snake.getHead().y > grid.getRows()) {
            snake.getHead().y = 0;
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

            if (gameState == GameState.RUNNING) {
                gameState = GameState.PAUSED;
            } else if (gameState == GameState.PAUSED) {
                gameState = GameState.RUNNING;
            }
            

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
            //grid.paintComponent(graphics);
            if (this.lollipops != null) {
                for (int i = 0; i < this.lollipops.size() - 1; i++) {
                    graphics.drawImage(lollipop, this.grid.getCellPosition(lollipops.get(i)).x, this.grid.getCellPosition(lollipops.get(i)).y, this.grid.getCellWidth() - 6, this.grid.getCellHeight(), this);
                }
            }
            
            if (this.stars != null) {
                for (int j = 0; j < this.stars.size() - 1; j++) {
                    graphics.drawImage(star, this.grid.getCellPosition(stars.get(j)).x, this.grid.getCellPosition(stars.get(j)).y, this.grid.getCellWidth(), this.grid.getCellHeight(), this);
                    
                }
            }
            
            if (this.diamonds != null) {
                for (int k = 0; k < this.diamonds.size() - 1; k++) {
                    graphics.drawImage(diamond, this.grid.getCellPosition(diamonds.get(k)).x, this.grid.getCellPosition(diamonds.get(k)).y, this.grid.getCellWidth(), this.grid.getCellHeight(), this);
                }
            }
            
            if (this.bombs != null) {
                for (int l = 0; l < this.bombs.size() - 1; l++) {
                    GraphicsPalette.drawBomb(graphics, this.grid.getCellPosition(this.bombs.get(l)), this.grid.getCellSize(), Color.BLACK);
                }
            }
            
            
            
            
        }

        //  graphics.drawImage(lollipop, 50, 50, 40, 50, this);


        if (snake != null) {
            for (int i = 0; i < snake.getBody().size(); i++) {
                if (i == 0) {
                    graphics.setColor(new Color(220, 20, 60));
                } else {
                    graphics.setColor(new Color(220, 20, 60, 60));
                }

                cellLocation = grid.getCellPosition(snake.getBody().get(i));

                graphics.fillOval(cellLocation.x, cellLocation.y, grid.getCellWidth(), grid.getCellHeight());

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

        graphics.setColor(Color.BLACK);
        if (snake != null) {
            for (int i = 0; i < snake.getBody().size(); i++) {
                cellLocation = grid.getCellPosition(snake.getBody().get(i));
                graphics.drawOval(cellLocation.x, cellLocation.y, grid.getCellWidth(), grid.getCellHeight());
            }
        }
        if (gameState == GameState.ENDED) {
            graphics.setFont(new Font("Calibri", Font.ITALIC, 100));
            graphics.drawString("Game Over!!!", 50, 50);
        }

        graphics.setColor(Color.BLACK);

        graphics.setFont(
                new Font("CenturyGothic", Font.BOLD, 60));
        graphics.drawString(
                "Your Score: " + this.score, 50, 50);
    }

    private void checkSnakeIntersection() {
        for (int i = 0; i < this.lollipops.size(); i++) {
            if (snake.getHead().equals(this.lollipops.get(i))) {
                this.lollipops.get(i).setLocation(getRandomGridLocation());
                this.snake.addGrowthcounter(moveCounter);
                this.score += 10;
                AudioPlayer.play("/resources/crunching.wav");
                }
        }
        
        for (int j = 0; j < this.stars.size(); j++) {
            if (snake.getHead().equals(this.stars.get(j))) {
                this.stars.get(j).setLocation(getRandomGridLocation());
                this.snake.addGrowthcounter(moveCounter);
                this.score += 20;
                AudioPlayer.play("/resources/sparkling.wav");
                
            }
        }
        
        for (int k = 0; k < this.diamonds.size(); k++) {
            if (snake.getHead().equals(this.diamonds.get(k))) {
                this.diamonds.get(k).setLocation(getRandomGridLocation());
                this.snake.addGrowthcounter(moveCounter);
                this.score += 30;
                AudioPlayer.play("/resources/ding.wav");
                
            }
        }
        
        for (int l = 0; l < this.bombs.size(); l++) {
            if (snake.getHead().equals(this.bombs.get(l))) {
                this.bombs.get(l).setLocation(getRandomGridLocation());
                this.snake.addGrowthcounter(moveCounter);
                this.score -= 30;
                
            }
        }
    }

    private Point getRandomGridPoint() {
        return new Point((int) (Math.random() * this.grid.getColumns()), (int) (Math.random() * this.grid.getRows()));
    }
}
