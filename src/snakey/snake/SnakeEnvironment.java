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
    private int speed = 3;
    private int moveCounter = speed;
    private int i;
    private GameState gameState = GameState.STARTING;
    private ArrayList<Point> bombs;
    private ArrayList<Point> lollipops;
    private ArrayList<Point> stars;
    private ArrayList<Point> diamonds;
    private ArrayList<Point> poisonBottles;
    private ArrayList<Point> waterBuckets;
    private Point cellLocation;
    private Image lollipop;
    private Image star;
    private Image diamond;
    private Image waterBucket;

    public SnakeEnvironment() {
    }

    @Override
    public void initializeEnvironment() {
        gameState = GameState.STARTING;

        this.setBackground(ResourceTools.loadImageFromResource("resources/sparklez.jpg"));
        this.lollipop = ResourceTools.loadImageFromResource("resources/suckers.png");
        this.star = ResourceTools.loadImageFromResource("resources/star.png");
        this.diamond = ResourceTools.loadImageFromResource("resources/diamond.png");
        this.waterBucket = ResourceTools.loadImageFromResource("resources/water.png");

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

        this.poisonBottles = new ArrayList<Point>();
        for (int m = 0; m < 10; m++) {
            this.poisonBottles.add(new Point(getRandomGridPoint()));
        }

        this.waterBuckets = new ArrayList<Point>();
        for (int n = 0; n < 5; n++) {
            this.waterBuckets.add(new Point(getRandomGridPoint()));
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
        if (gameState == GameState.RUNNING) {
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
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            
            if (gameState == GameState.RUNNING) {
                gameState = GameState.PAUSED;
            } else if (gameState == GameState.PAUSED) {
                gameState = GameState.RUNNING;
            } else if (gameState == GameState.STARTING) {
                gameState = GameState.RUNNING;
            } else if (gameState == GameState.INSTRUCTIONS) {
                gameState = GameState.STARTING;
            } else if (gameState == GameState.ENDED) {
                gameState = GameState.STARTING;
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

        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            gameState = GameState.PAUSED;

        } else if (e.getKeyCode() == KeyEvent.VK_I) {
            gameState = GameState.INSTRUCTIONS;
        }


    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        System.out.println("mouse click = " + e.getX() + ", " + e.getY());
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (gameState == GameState.STARTING) {
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 100));
            graphics.drawString("Snakey Snake", 121, 251);

            graphics.setColor(Color.PINK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 100));
            graphics.drawString("Snakey Snake", 125, 255);

            graphics.setColor(Color.BLACK);
            graphics.fillOval(213, 361, 119, 59);

            graphics.setColor(Color.WHITE);
            graphics.fillOval(215, 363, 115, 55);

            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 35));
            graphics.drawString("Start", 225, 400);

            graphics.setColor(Color.PINK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 35));
            graphics.drawString("Start", 227, 402);

            graphics.setColor(Color.BLACK);
            graphics.fillOval(458, 345, 234, 89);

            graphics.setColor(Color.WHITE);
            graphics.fillOval(460, 347, 230, 85);

            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 35));
            graphics.drawString("Instructions", 475, 400);

            graphics.setColor(Color.PINK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 35));
            graphics.drawString("Instructions", 477, 402);

            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("Press the I", 528, 455);

            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("key for", 545, 474);

            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("instructions", 531, 494);

            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("Press the", 232, 440);

            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("spacebar", 232, 460);

            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("to begin!", 232, 480);

        } else if (gameState == GameState.INSTRUCTIONS) {
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 100));
            graphics.drawString("Instructions", 147, 97);

            graphics.setColor(Color.PINK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 100));
            graphics.drawString("Instructions", 150, 100);
            
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("1. Use the up, down, left, and right keys to control your snake.", 50, 140);
            
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("2. Collect lollipops, stars, and diamonds for points.", 50, 170);
            
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("3. Avoid bombs, they will burn you.", 50, 200);
            
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("4. To heal yourself from a burn, eat a water bucket.", 50, 230);
            
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("5. Poison bottles will kill you.", 50, 260);
            
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("6. Eating different things will adjust your speed, so be careful.", 50, 290);
            
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("7. You can go through the walls and appear on the opposite sides.", 50, 320);
            
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("8. Do not hit yourself, you will die.", 50, 350);
            
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("9. Lollipops are worth 10 points.", 50, 380);
            
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("10. Stars are worth 20 points.", 50, 410);
            
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("11. Diamonds are worth 30 points.", 50, 440);
            
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("12. Bombs are worth -30 points. ", 50, 470);
            
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("13. Water buckets do not affect your score.", 50, 500);
            
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("14. If you die, press the spacebar to restart.", 50, 530);
            
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("Press the spacebar to", 720, 595);
            
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("return to the main menu.", 700, 618);
            
} else if (gameState == GameState.ENDED) {
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 100));
            graphics.drawString("GAME OVER", 131, 321);

            graphics.setColor(Color.PINK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 100));
            graphics.drawString("GAME OVER", 135, 325);
            
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 17));
            graphics.drawString("Press spacebar to restart!", 445, 355);

        } else if (gameState == GameState.PAUSED) {
            graphics.setFont(new Font("ComicSansMS", Font.CENTER_BASELINE, 100));
            graphics.drawString("PAUSED", 120, 300);

        } else if (gameState == GameState.RUNNING) {
            graphics.drawRect(this.grid.getPosition().x, this.grid.getPosition().y, (this.grid.getColumns() + 1) * this.grid.getCellSize().x, (this.grid.getRows() + 1) * this.grid.getCellSize().y);


            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("ComicSansMS", Font.BOLD, 60));
            graphics.drawString("Your Score: " + this.score, 52, 67);

            graphics.setColor(Color.PINK);
            graphics.setFont(new Font("ComicSansMS", Font.BOLD, 60));
            graphics.drawString("Your Score: " + this.score, 55, 70);



            if (this.grid != null) {
                //grid.paintComponent(graphics);
                if (this.lollipops != null) {
                    for (int i = 0; i < this.lollipops.size() - 1; i++) {
                        graphics.drawImage(lollipop, this.grid.getCellPosition(lollipops.get(i)).x, this.grid.getCellPosition(lollipops.get(i)).y, this.grid.getCellWidth() - 4, this.grid.getCellHeight() + 4, this);
                    }
                }

                if (this.stars != null) {
                    for (int j = 0; j < this.stars.size() - 1; j++) {
                        graphics.drawImage(star, this.grid.getCellPosition(stars.get(j)).x, this.grid.getCellPosition(stars.get(j)).y, this.grid.getCellWidth(), this.grid.getCellHeight(), this);

                    }
                }

                if (this.diamonds != null) {
                    for (int k = 0; k < this.diamonds.size() - 1; k++) {
                        graphics.drawImage(diamond, this.grid.getCellPosition(diamonds.get(k)).x, this.grid.getCellPosition(diamonds.get(k)).y, this.grid.getCellWidth() + 5, this.grid.getCellHeight(), this);
                    }
                }

                if (this.waterBuckets != null) {
                    for (int n = 0; n < this.waterBuckets.size(); n++) {
                        graphics.drawImage(waterBucket, this.grid.getCellPosition(waterBuckets.get(n)).x, this.grid.getCellPosition(waterBuckets.get(n)).y, this.grid.getCellWidth(), this.grid.getCellHeight(), this);
                    }
                }

                if (this.bombs != null) {
                    for (int l = 0; l < this.bombs.size() - 1; l++) {
                        GraphicsPalette.drawBomb(graphics, this.grid.getCellPosition(this.bombs.get(l)), this.grid.getCellSize(), Color.BLACK);
                    }
                }

                if (this.poisonBottles != null) {
                    for (int m = 0; m < this.poisonBottles.size(); m++) {
                        GraphicsPalette.drawPoisonBottle(graphics, this.grid.getCellPosition(this.poisonBottles.get(m)), this.grid.getCellSize(), Color.yellow);
                    }
                }
            }

            if (snake != null) {
                for (int i = 0; i < snake.getBody().size(); i++) {
                    if (i == 0) {
                        graphics.setColor(snake.getHeadColor());
                    } else {
                        graphics.setColor(snake.getBodyColor());
                    }

                    cellLocation = grid.getCellPosition(snake.getBody().get(i));
                    graphics.fillOval(cellLocation.x, cellLocation.y, grid.getCellWidth(), grid.getCellHeight());
                }
            }

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

        }
    }

    private void checkSnakeIntersection() {
        if (snake.selfHitTest()) {
            this.gameState = GameState.ENDED;
            AudioPlayer.play("/resources/end.wav");
        }

        for (int i = 0; i < this.lollipops.size(); i++) {
            if (snake.getHead().equals(this.lollipops.get(i))) {
                this.lollipops.get(i).setLocation(getRandomGridLocation());
                this.snake.addGrowthcounter(moveCounter);
                this.score += 10;
                this.speed = 3;
                AudioPlayer.play("/resources/crunching.wav");
            }
        }

        for (int j = 0; j < this.stars.size(); j++) {
            if (snake.getHead().equals(this.stars.get(j))) {
                this.stars.get(j).setLocation(getRandomGridLocation());
                this.snake.addGrowthcounter(moveCounter);
                this.score += 20;
                this.speed = 2;
                AudioPlayer.play("/resources/sparkling.wav");

            }
        }

        for (int k = 0; k < this.diamonds.size(); k++) {
            if (snake.getHead().equals(this.diamonds.get(k))) {
                this.diamonds.get(k).setLocation(getRandomGridLocation());
                this.snake.addGrowthcounter(moveCounter);
                this.score += 30;
                this.speed = 1;
                AudioPlayer.play("/resources/ding.wav");

            }
        }

        for (int l = 0; l < this.bombs.size(); l++) {
            if (snake.getHead().equals(this.bombs.get(l))) {
                this.bombs.get(l).setLocation(getRandomGridLocation());
                this.score -= 30;
                this.speed = 4;
                snake.setBodyColor(Color.BLACK);
                AudioPlayer.play("/resources/bomb.wav");
            }
        }

        for (int m = 0; m < this.poisonBottles.size(); m++) {
            if (snake.getHead().equals(this.poisonBottles.get(m))) {
                this.poisonBottles.get(m).setLocation(getRandomGridLocation());
                AudioPlayer.play("/resources/game over.wav");
                this.gameState = GameState.ENDED;

            }
        }

        for (int n = 0; n < this.waterBuckets.size(); n++) {
            if (snake.getHead().equals(this.waterBuckets.get(n))) {
                this.waterBuckets.get(n).setLocation(getRandomGridLocation());
                snake.setBodyColor(Color.PINK);
                AudioPlayer.play("/resources/water.wav");
            }
        }
    }

    private Point getRandomGridPoint() {
        return new Point((int) (Math.random() * this.grid.getColumns()), (int) (Math.random() * this.grid.getRows()));
    }
}
