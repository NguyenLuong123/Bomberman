package uet.oop.bomberman.entities.enemy;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public abstract class Enemy extends Entity {
    public boolean dead = false;
    public int direction;
    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int UP = 3;

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }

    public int sameRow() {
        Entity bomber = new Bomber(0, 0, Sprite.player_left.getFxImage());
        for (int i = 0; i < BombermanGame.entities.size(); i++) {
            if (BombermanGame.entities.get(i) instanceof Bomber) {
                bomber = BombermanGame.entities.get(i);
            }
        }
        if (x > bomber.x) {
            return LEFT;
        } else if (x < bomber.x) {
            return RIGHT;
        }
        return -1;
    }

    public int sameColumn() {
        Entity bomber = new Bomber(0, 0, Sprite.player_left.getFxImage());
        for (int i = 0; i < BombermanGame.entities.size(); i++) {
            if (BombermanGame.entities.get(i) instanceof Bomber) {
                bomber = BombermanGame.entities.get(i);
            }
        }
        if (y > bomber.y) {
            return UP;
        } else if (y < bomber.y) {
            return DOWN;
        }
        return -1;
    }

    public void catchBomber() {
        Entity bomber = new Bomber(0, 0, Sprite.player_left.getFxImage());
        for (int i = 0; i < BombermanGame.entities.size(); i++) {
            if (BombermanGame.entities.get(i) instanceof Bomber) {
                bomber = BombermanGame.entities.get(i);
            }
        }
        if (y == bomber.y) {
            direction = sameRow();
        } else if (x == bomber.x) {
            direction = sameColumn();
        }
    }

    public void generateDirection() {
        Random random = new Random();
        direction = random.nextInt(4);
    }

    public void killBomber() {
        for (int i = 0; i < BombermanGame.entities.size(); i++) {
            if (BombermanGame.entities.get(i).intersects(this) && BombermanGame.entities.get(i) instanceof Bomber ) {
                BombermanGame.entities.get(i).dead = true;
            }
        }
    }

    public void move(){};
}
