/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakey.snake;

import environment.Environment;
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

    private int speed = 5;
    private int moveCounter = speed;
    public SnakeEnvironment() {
    }

    @Override
    public void initializeEnvironment() {
        this.setBackground( ResourceTools.loadImageFromResource("resources/grass.jpg"));
        
        this.grid = new Grid();
        this.grid.setColor(Color.BLACK);
        this.grid.setColumns(44);
        this.grid.setRows(15);
        this.grid.setCellHeight(20);
        this.grid.setCellWidth(20);
        this.grid.setPosition(new Point(10, 100));
        
        this.snake = new Snake();
        this.snake.getBody().add(new Point(5, 5));
        this.snake.getBody().add(new Point(5, 4));
        this.snake.getBody().add(new Point(5, 3));
        this.snake.getBody().add(new Point(4, 3));
       
    }
    

    @Override
    public void timerTaskHandler() {
        System.out.println("Timer");
        if (snake != null){
            snake.move();
            if (moveCounter <= 0){
                snake.move();
                moveCounter = speed;
            } else {
                
            }
            
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            this.score += 100000;
        } else if (e.getKeyCode() == KeyEvent.VK_W){
            snake.setDirection(Direction.UP);
            snake.move();
        } else if (e.getKeyCode() == KeyEvent.VK_A){
            snake.setDirection(Direction.LEFT);
            snake.move();
        } else if (e.getKeyCode() == KeyEvent.VK_S){
            snake.setDirection(Direction.DOWN);
            snake.move();
        } else if (e.getKeyCode() == KeyEvent.VK_D){
            snake.setDirection(Direction.RIGHT);
            snake.move();    
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
            this.grid.paintComponent(graphics);
            
            Point cellLocation;
            graphics.setColor(Color.YELLOW);
            if (snake != null){
                for (int i = 0; i < snake.getBody().size(); i++) {
                    cellLocation = grid.getCellPosition(snake.getBody().get(i));
                    graphics.fillOval(cellLocation.x, cellLocation.y, grid.getCellWidth(), grid.getCellHeight());
                    
                }
            }
        
        }
        
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("CenturyGothic", Font.BOLD, 60));
        graphics.drawString("Score: " + this.score, 50, 50);
        
        }
    
}
