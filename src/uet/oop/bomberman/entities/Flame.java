package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.enemy.Balloon;
import uet.oop.bomberman.entities.enemy.Kondoria;
import uet.oop.bomberman.entities.enemy.Oneal;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    public int direction;
    private int radiusFlame = 2;
    private int time = 0;
    private boolean last = false;
    private boolean test = false;

    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Flame(int x, int y, Image img, int direction) {
        super(x, y, img);
        this.direction = direction;
    }

    public Flame(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {
        if (time++ == 50) {
            BombermanGame.flameList.remove(this);
        }
        collideEntity();
        setImg();
    }

    public  void setImg() {
            switch (direction) {
                case 0:
                    img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1,
                            Sprite.bomb_exploded2, time, 20).getFxImage();
                    break;
                case 1:
                    img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1
                            ,Sprite.explosion_horizontal2,time,20).getFxImage();
                    break;
                case 2:
                    img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1
                            ,Sprite.explosion_horizontal_right_last2, time,20).getFxImage();
                    break;
                case 3:
                    img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1
                            ,Sprite.explosion_horizontal_left_last2, time,20).getFxImage();
                    break;
                case 4:
                    img = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1
                            ,Sprite.explosion_vertical2, time,20).getFxImage();
                    break;
                case 5:
                    img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1
                            ,Sprite.explosion_vertical_top_last2, time,20).getFxImage();
                    break;
                case 6:
                    img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1
                            ,Sprite.explosion_vertical_down_last2, time,20).getFxImage();
                    break;
            }
    }

    public void collideEntity() {
        for (Entity entity:BombermanGame.flameList) {
            for(Entity entity1:BombermanGame.entities) {
                if (entity1 instanceof Balloon && entity1.intersects(entity)) {
                    ((Balloon) entity1).dead = true;
                }

                if (entity1 instanceof Oneal && entity1.intersects(entity)) {
                    ((Oneal) entity1).dead = true;
                }

                if (entity1 instanceof Kondoria && entity1.intersects(entity)) {
                    ((Kondoria) entity1).dead = true;
                }

                if (entity1 instanceof Oneal && entity1.intersects(entity)) {
                    ((Oneal) entity1).dead = true;
                }
                if (entity1 instanceof Bomber && entity1.intersects(entity)) {
                    ((Bomber) entity1).dead = true;
                }
            }
        }
    }


}