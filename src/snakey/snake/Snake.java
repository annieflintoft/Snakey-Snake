/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakey.snake;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import static snakey.snake.Direction.DOWN;
import static snakey.snake.Direction.UP;

/**
 *
 * @author macbook
 */
public class Snake {
    
    {
        body = new ArrayList<Point>();
    }

    
    //<editor-fold defaultstate="collapsed" desc="Methods">
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
        
        if (growthcounter > 0){
            growthcounter --;
        } else {
            getBody().remove(getBody().size() - 1);
        }
        
    }
    
    public Point getHead(){
        return body.get(0);
    }
    
    public boolean selfHitTest(){
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).equals(getHead())) {
                return true;
            }
            
        }
        
        return false;
    }
    
    public boolean intersects(Point location) {
        for (Point bodyLocation : body) {
            if (bodyLocation.equals(location)) {
                return true;
            }
            
        }
        return false;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    private ArrayList<Point> body;
    private Direction direction = Direction.LEFT;
    private int growthcounter = 0;
    private Color headColor = Color.PINK;
    private Color bodyColor = Color.PINK;
    
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
    public int getGrowthcounter(){
        return growthcounter;
    }
    public void setGrowthcounter(int growthcounter){
        this.growthcounter = growthcounter;
    }
    public void addGrowthcounter(int growthcounter){
        this.growthcounter += growthcounter;
    }
    
    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    void add(Point point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * @return the bodyColor
     */
    public Color getBodyColor() {
        return bodyColor;
    }
    
    /**
     * @param bodyColor the bodyColor to set
     */
    public void setBodyColor(Color bodyColor) {
        this.bodyColor = bodyColor;
    }
    
    /**
     * @return the headColor
     */
    public Color getHeadColor() {
        return headColor;
    }
    
    /**
     * @param headColor the headColor to set
     */
    public void setHeadColor(Color headColor) {
        this.headColor = headColor;
    }
    //</editor-fold>  
    
}
