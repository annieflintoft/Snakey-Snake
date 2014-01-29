/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakey.snake;

import java.awt.Point;
import java.util.ArrayList;
import static snakey.snake.Direction.DOWN;
import static snakey.snake.Direction.UP;

/**
 *
 * @author macbook
 */
public class Snake {
    private ArrayList<Point> body;
    private Direction direction = Direction.LEFT;

    
    {
        body = new ArrayList<Point>();
    }

    
    public void move(){
        //Create a new location for the head; using the direction.
        int x = 0;
        int y = 0;
        
        switch (getDirection()){
            case UP:
                x = 0;
                y = -1;
                break;
             
            case DOWN:
                x = 0;
                y = 1;
                break;
                
            case RIGHT:
                x = 1;
                y = 0;
                break;
            
            case LEFT:
                x = -1;
                y = 0;
        }
        
       getBody().add(0, new Point(getHead().x + x, getHead().y + y));
       
       getBody().remove(getBody().size() - 1);
    }
    
    public Point getHead(){
        return body.get(0);
    }
    
    /**
     * @return the body
     */
    public ArrayList<Point> getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(ArrayList<Point> body) {
        this.body = body;
    }

    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
}
