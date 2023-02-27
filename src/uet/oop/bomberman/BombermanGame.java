package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.enemy.Balloon;
import uet.oop.bomberman.entities.enemy.Kondoria;
import uet.oop.bomberman.entities.enemy.Oneal;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    private static int height;
    private static int width;
//    public int height;
    public int level;

    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static HashSet<String> currentlyActiveKeys;
    public static final List<Flame> flameList = new ArrayList<>();
    public static List<Entity> bombs = new ArrayList<Entity>();
    private static Scene scene ;

    public char[][] map;


    public static void main(String[] args) {
        Sound.play("SoundTrack");
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        loadMap(1);
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * width, Sprite.SCALED_SIZE * height);
        gc = canvas.getGraphicsContext2D();


        // Tao root container
        Group root = new Group();
        scene = new Scene(root);
        root.getChildren().add(canvas);
        prepareActionHandlers();

        // Tao scene


        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                try {
                    update();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();

        createMap();

        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
    }


    public void loadMap(int level) throws FileNotFoundException {
        File fr = new File("res/levels/Level" + level + ".txt");
        Scanner scanner = new Scanner(fr);
        level = scanner.nextInt();
        height = scanner.nextInt();
        width = scanner.nextInt();
        map = new char[height][width];
        scanner.nextLine();
        for (int i = 0; i < height; i++) {
            String dataLine = scanner.nextLine();
            char[] inLine = dataLine.toCharArray();
            for (int j = 0; j < width; j++) {
                map[i][j] = inLine[j];
            }
        }
        scanner.close();
    }

    public void createMap() throws FileNotFoundException {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Entity object;
                Grass n = new Grass(j, i, Sprite.grass.getFxImage());
                stillObjects.add(n);
                if(map[i][j]=='1') entities.add(new Balloon(j, i, Sprite.balloom_left1.getFxImage()));

                else if(map[i][j]=='2') entities.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                else if(map[i][j] == '3') entities.add(new Kondoria(j, i, Sprite.doll_left1.getFxImage()));
                if (map[i][j] == '#') {
                    object = new Wall(j, i, Sprite.wall.getFxImage());
                } else if(map[i][j] == '*'){
                    object = new Brick(j, i, Sprite.brick.getFxImage());
                } else if(map[i][j] == 'b') {
                    object = new Brick(j, i, Sprite.brick.getFxImage(), 'b');
                } else if(map[i][j] == 's') {
                    object = new Brick(j, i, Sprite.brick.getFxImage(), 's');
                } else if(map[i][j] == 'f') {
                    object = new Brick(j, i, Sprite.brick.getFxImage(), 'f');
                } else if(map[i][j] == 'x') {
                    object = new Brick(j, i, Sprite.brick.getFxImage(), 'x');
                }else {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }

    public static void loadLevel() throws FileNotFoundException {
        BombermanGame.entities.clear();
        BombermanGame.stillObjects.clear();
        BombermanGame a = new BombermanGame();
        a.loadMap(1);
        a.createMap();
        Bomber n = new Bomber(1, 1, Sprite.player_right.getFxImage());
        n.speed = 1;
        n.radiusFlame = 1;
        a.entities.add(n);
    }

    public void update() throws FileNotFoundException {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }

        for (int i = 0; i < stillObjects.size(); i++) {
            BombermanGame.stillObjects.get(i).update();
        }

        for(int i = 0; i < flameList.size(); i++) {
            BombermanGame.flameList.get(i).update();
        }
        for(int i = 0; i < bombs.size(); i++) {
            BombermanGame.bombs.get(i).update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        flameList.forEach(g -> g.render(gc));
        bombs.forEach(g -> g.render(gc));
    }

    private static void prepareActionHandlers()
    {
        currentlyActiveKeys = new HashSet<String>();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                currentlyActiveKeys.add(event.getCode().toString());
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                currentlyActiveKeys.remove(event.getCode().toString());
            }
        });
    }
}
