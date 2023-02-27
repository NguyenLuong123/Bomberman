package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileNotFoundException;

public class Bomber extends Entity {
    public int time = 0;
    public static int speed = 1;
    public static int numBomb = 5;
    public static int radiusFlame = 1;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() throws FileNotFoundException {
        if (dead == false) {
            if (BombermanGame.currentlyActiveKeys.contains("RIGHT")) {
                this.x = x + speed;
                if (checkBoundsWall() || checkBoundsBrick()) {
                    this.x = x - speed;
                    if (this.y % Sprite.SCALED_SIZE >= 2 * Sprite.SCALED_SIZE / 3) {
                        this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE) + Sprite.SCALED_SIZE;
                    } else if (this.x % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 3) {
                        this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE);
                    }
                }
                this.img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, x, 30).getFxImage();
            }
            if (BombermanGame.currentlyActiveKeys.contains("LEFT")) {
                this.x = x - speed;
                if (checkBoundsWall() || checkBoundsBrick()) {
                    this.x = x + speed;
                    if (this.y % Sprite.SCALED_SIZE >= 2 * Sprite.SCALED_SIZE / 3) {
                        this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE) + Sprite.SCALED_SIZE;
                    } else if (this.y % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 3) {
                        this.y = Sprite.SCALED_SIZE * (this.y / Sprite.SCALED_SIZE);
                    }
                }
                this.img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, x, 30).getFxImage();
            }
            if (BombermanGame.currentlyActiveKeys.contains("DOWN")) {
                this.y = y + speed;
                if (checkBoundsWall() || checkBoundsBrick()) {
                    this.y = y - speed;
                    if (this.x % Sprite.SCALED_SIZE >= 2 * Sprite.SCALED_SIZE / 3) {
                        this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE) + Sprite.SCALED_SIZE;
                    } else if (this.x % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 3) {
                        this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE);
                    }
                }
                this.img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, y, 30).getFxImage();
            }
            if (BombermanGame.currentlyActiveKeys.contains("UP")) {
                this.y = y - speed;
                if (checkBoundsWall() || checkBoundsBrick()) {
                    this.y = y + speed;
                    if (this.x % Sprite.SCALED_SIZE >= 2 * Sprite.SCALED_SIZE / 3) {
                        this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE) + Sprite.SCALED_SIZE;
                    } else if (this.x % Sprite.SCALED_SIZE <= Sprite.SCALED_SIZE / 3) {
                        this.x = Sprite.SCALED_SIZE * (this.x / Sprite.SCALED_SIZE);
                    }
                }
                this.img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, y, 30).getFxImage();
            }

            if (BombermanGame.currentlyActiveKeys.contains("SPACE")) {
                if (numBomb > 0) {
                    Sound.play("BombSet");
                    Entity bomb = new Bomb(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());
                    BombermanGame.bombs.add(bomb);
                    numBomb--;
                }
            }
        } else {
            time++;
            if (time == 20) {
                Sound.play("EndGame");
            }
            if (time == 200) {
                BombermanGame.loadLevel();
                BombermanGame.entities.remove(this);
            }
            if (time < 20) {
                this.img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, time, 30).getFxImage();
            }
        }
    }

}
