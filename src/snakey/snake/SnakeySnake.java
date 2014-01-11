/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakey.snake;

import environment.ApplicationStarter;

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
        ApplicationStarter.run("SnakeySnake", new SnakeEnvironment());
    }
}
