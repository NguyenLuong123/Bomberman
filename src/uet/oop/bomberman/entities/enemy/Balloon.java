package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.Random;

public class Balloon extends Enemy {
    public int time = 0;

    public Balloon(int x, int y, Image img) {
        super(x, y, img);

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    @Override
    public void update() {
        if (dead == false) {
            move();
            killBomber();
        }
        if (dead == true) {
            time++;
            if (time == 100) {
                Sound.play("EnermyDie");
                BombermanGame.entities.remove(this);
            }
            this.img = Sprite.balloom_dead.getFxImage();

        }
    }
    @Override
    public void move() {
        if (direction == LEFT) {
            this.x -= 1;
            if (checkBoundsWall() || checkBoundsBrick() || x % Sprite.SCALED_SIZE == 0) {
                generateDirection();
                if (x % Sprite.SCALED_SIZE != 0) {
                    this.x += 1;
                }
            }
            img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3,
                    x, 30).getFxImage();

        }
        if (direction == RIGHT) {
            this.x += 1;
            if (checkBoundsWall() || checkBoundsBrick() || x % Sprite.SCALED_SIZE == 0) {
                generateDirection();
                if (x % Sprite.SCALED_SIZE != 0) {
                    this.x -= 1;
                }
            }

            img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, x, 30).getFxImage();

        }

        if (direction == UP) {
            this.y -= 1;
            if (checkBoundsWall() || checkBoundsBrick() || y % Sprite.SCALED_SIZE == 0) {
                generateDirection();
                if (y % Sprite.SCALED_SIZE != 0) {
                    this.y += 1;
                }
            }

            img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, y, 30).getFxImage();

        }

        if (direction == DOWN) {
            this.y += 1;
            if (checkBoundsWall() || checkBoundsBrick() || y % Sprite.SCALED_SIZE == 0) {
                generateDirection();
                if (y % Sprite.SCALED_SIZE != 0) {
                    this.y -= 1;
                }
            }
            img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, y, 30).getFxImage();
        }
    }
}