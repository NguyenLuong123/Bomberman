package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    public boolean dead = false;
    public int time = 0;

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (dead == true) {
            time++;
            if (time == 100) {
                Sound.play("EnermyDie");
                BombermanGame.entities.remove(this);
            }
            this.img = Sprite.oneal_dead.getFxImage();
        }
        if (dead == false) {
            move();
            killBomber();
        }
    }

        @Override
        public void move () {
            Entity bomber = new Bomber(0, 0, Sprite.player_left.getFxImage());
            for (int i = 0; i < BombermanGame.entities.size(); i++) {
                if (BombermanGame.entities.get(i) instanceof Bomber) {
                    bomber = BombermanGame.entities.get(i);
                }
            }
            int distanceX = Math.abs(x - bomber.x);
            int distanceY = Math.abs(y - bomber.y);

            if (direction == LEFT) {
                this.x -= 1;
                if (checkBoundsWall() || checkBoundsBrick() || x % Sprite.SCALED_SIZE == 0) {
                    if (distanceX <= Sprite.SCALED_SIZE * 4 && distanceY <= Sprite.SCALED_SIZE * 4) {
                        this.catchBomber();
                    } else {
                        generateDirection();
                    }
                    if (x % Sprite.SCALED_SIZE != 0) {
                        this.x += 1;
                    }
                }
                img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3,
                        x, 30).getFxImage();

            }
            if (direction == RIGHT) {
                this.x += 1;
                if (checkBoundsWall() || checkBoundsBrick() || x % Sprite.SCALED_SIZE == 0) {
                    if (distanceX <= Sprite.SCALED_SIZE * 4 && distanceY <= Sprite.SCALED_SIZE * 4) {
                        this.catchBomber();
                    } else {
                        generateDirection();
                    }
                    if (x % Sprite.SCALED_SIZE != 0) {
                        this.x -= 1;
                    }
                }
                img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, x, 30).getFxImage();

            }

            if (direction == UP) {
                this.y -= 1;
                if (checkBoundsWall() || checkBoundsBrick() || y % Sprite.SCALED_SIZE == 0) {
                    if (distanceX <= Sprite.SCALED_SIZE * 4 && distanceY <= Sprite.SCALED_SIZE * 4) {
                        this.catchBomber();
                    } else {
                        generateDirection();
                    }
                    if (y % Sprite.SCALED_SIZE != 0) {
                        this.y += 1;
                    }
                }

                img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, y, 30).getFxImage();

            }

            if (direction == DOWN) {
                this.y += 1;
                if (checkBoundsWall() || checkBoundsBrick() || y % Sprite.SCALED_SIZE == 0) {
                    if (distanceX <= Sprite.SCALED_SIZE * 4 && distanceY <= Sprite.SCALED_SIZE * 4) {
                        this.catchBomber();
                    } else {
                        generateDirection();
                    }
                    if (y % Sprite.SCALED_SIZE != 0) {
                        this.y -= 1;
                    }
                }
                img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, y, 30).getFxImage();
            }
        }
}
