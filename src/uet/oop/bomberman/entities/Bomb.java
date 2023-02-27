package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends Entity{

    private int radiusFlame = 1;
    public int timerEx = 0;
    private int timeCounter = 0;
    private static final int size = Sprite.SCALED_SIZE;
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    @Override
    public void update() {
        if (timeCounter ++ == 120) {
            radiusFlame = Bomber.radiusFlame;
            addFlame();
            for(int i = 0; i < BombermanGame.bombs.size(); i++) {
                BombermanGame.bombs.remove(i);
                Bomber.numBomb++;
                Sound.play("ExBomb");
            }
        }

        this.img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, timerEx++, 30).getFxImage();
    }

    public void addFlame() {
        BombermanGame.flameList.add(new Flame(x/Sprite.SCALED_SIZE, y/Sprite.SCALED_SIZE, Sprite.bomb_exploded.getFxImage(),0));
        for(int i = 0; i < radiusFlame; i++){
            Flame e = new Flame((x + size*(i + 1))/size, y/size, Sprite.explosion_horizontal_right_last.getFxImage(),2);
            if (collideFlame(e)) break;
            if(i != radiusFlame - 1) {
                e.img = Sprite.explosion_horizontal.getFxImage();
                e.direction = 1;
            }
            BombermanGame.flameList.add(e);
        }

        for(int i = 0; i < radiusFlame; i++){
            Flame e = new Flame((x - size*(i + 1))/size, y/size, Sprite.explosion_horizontal_left_last.getFxImage(),3);
            if (collideFlame(e)) break;
            if(i != radiusFlame - 1) {
                e.img = Sprite.explosion_horizontal.getFxImage();
                e.direction = 1;
            }
            BombermanGame.flameList.add(e);
        }

        for(int i = 0; i < radiusFlame; i++){
            Flame e = new Flame(x /size, (y - size*(i + 1))/size,Sprite.explosion_vertical_top_last.getFxImage(), 5);
            if (collideFlame(e)) break;
            if(i != radiusFlame - 1) {
                e.img = Sprite.explosion_vertical.getFxImage();
                e.direction = 4;
            }
            BombermanGame.flameList.add(e);
        }

        for(int i = 0; i < radiusFlame; i++){
            Flame e = new Flame(x/size, (y + size*(i + 1))/size, Sprite.explosion_vertical_down_last.getFxImage(), 6);
            if (collideFlame(e)) break;
            if(i != radiusFlame - 1) {
                e.img = Sprite.explosion_vertical.getFxImage();
                e.direction = 4;
            }
            BombermanGame.flameList.add(e);
        }
    }

    public boolean collideFlame(Entity e) {

        for (Entity entity:BombermanGame.stillObjects) {
            if (entity instanceof Brick && e.intersects(entity) ) {
                    BombermanGame.flameList.add((Flame) e);
                    ((Brick) entity).isAlive = false;
                    return true;

            }
            if (entity instanceof Wall) {
                if (e.intersects(entity)) {
                    return true;
                }
            }
        }
        return false;
    }

}

