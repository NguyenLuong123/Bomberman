package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.enemy.Balloon;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileNotFoundException;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class Brick extends Entity {
    public char type = 'a';
    public boolean isAlive = true;
    private int animated = 0;
    private int i = 0;
    public Brick(int x, int y, Image img) {
        super(x, y, img);
        this.type = 'a';
    }

    public Brick(int x, int y, Image img, char type) {
        super(x, y, img);
        this.type = type;
    }

    @Override
    public void update() throws FileNotFoundException {
        if (isAlive == false) {
            animated++;
            if (type == 's') {
                this.img = Sprite.powerup_speed.getFxImage();
                for (int i = 0; i < BombermanGame.entities.size(); i++) {
                    if(BombermanGame.entities.get(i) instanceof Bomber && BombermanGame.entities.get(i).intersects(this)){
                        BombermanGame.stillObjects.remove(this);
                        Bomber.speed++;
                        Sound.play("Item");
                    }
                }
            } else if (type == 'f') {
                this.img = Sprite.powerup_flames.getFxImage();
                for (int i = 0; i < BombermanGame.entities.size(); i++) {
                    if(BombermanGame.entities.get(i) instanceof Bomber && BombermanGame.entities.get(i).intersects(this)){
                        BombermanGame.stillObjects.remove(this);
                        Bomber.radiusFlame = 2;
                        Sound.play("Item");
                    }
                }
            } else if (type == 'x') {
                this.img = Sprite.portal.getFxImage();
                for (int i = 0; i < BombermanGame.entities.size(); i++) {
                    Entity entity = BombermanGame.entities.get(i);
                    if (entity instanceof Enemy)return;
                    if (entity instanceof Bomber && entity.intersects(this)) {
                        Sound.play("Victory");
                        BombermanGame.loadLevel();
                    }
                }
            } else if (type == 'b') {
                this.img = Sprite.powerup_bombs.getFxImage();
                for (int i = 0; i < BombermanGame.entities.size(); i++) {
                    if(BombermanGame.entities.get(i) instanceof Bomber && BombermanGame.entities.get(i).intersects(this)){
                        BombermanGame.stillObjects.remove(this);
                        Bomber.numBomb = Bomber.numBomb++;
                        Sound.play("Item");
                    }
                }
            }
            else {
                if (animated == 100) {
                    BombermanGame.stillObjects.remove(this);
                }
                this.img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1
                        , Sprite.brick_exploded2, i++, 35).getFxImage();
            }
        }
    }


}
