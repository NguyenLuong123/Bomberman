package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileNotFoundException;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    public int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    public int y;
    public int desX;
    public int desY;

    protected Image img;
    protected int animate;
    public boolean dead = false;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public Entity(int x, int y) {
        this.x = x * Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update() throws FileNotFoundException;

    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    public boolean intersects(Entity e) {
        return e.getBoundary().intersects(this.getBoundary());
    }

    public boolean checkBoundsBrick() {
        for (Entity e : BombermanGame.stillObjects) {
            if (e instanceof Brick && this.intersects(e) && ((Brick) e).isAlive == false && ((Brick) e).type != 'a' ) return false;
            if (e instanceof Brick && this.intersects(e) && ((Brick) e).isAlive == true) return true;
        }
        return false;
    }

    /**public boolean checkBoundsBomb() {
     for (Entity e : EntityArr.bomberman.bombs) {
     if (this.intersects(e)) return true;
     }
     return false;
     }.*/
    public void stay() {}

    public boolean checkBoundsWall() {
        for (Entity e : BombermanGame.stillObjects) {
            if (e instanceof Wall && this.intersects(e)) return true;
        }
        return false;
    }

}
