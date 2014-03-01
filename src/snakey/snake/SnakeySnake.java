/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakey.snake;

import environment.ApplicationStarter;
import java.awt.Dimension;

/**
 *
 * @author macbook
 */
public class SnakeySnake {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        start();
    }

    private static void start() {
//        ApplicationStarter.run("SnakeySnake", new SnakeEnvironment());
        ApplicationStarter.run(new String[0], "Snakey Snake", new Dimension(925, 655), new SnakeEnvironment());
    }
}
