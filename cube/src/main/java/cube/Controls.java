package cube;

import com.jme3.collision.CollisionResults;
import com.jme3.input.ChaseCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.*;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;


public class Controls {
    Cube cube;
    InputManager manager;
    Camera cam;
    Node rootNode;

    Controls(Cube cube, InputManager manager, Camera cam, Node rootNode){
        this.cube = cube;
        this.manager = manager;
        this.cam = cam;
        this.rootNode = rootNode;

        manager.addMapping("W", new KeyTrigger(KeyInput.KEY_W));
        manager.addMapping("S", new KeyTrigger(KeyInput.KEY_S));
        manager.addMapping("A", new KeyTrigger(KeyInput.KEY_A));
        manager.addMapping("D", new KeyTrigger(KeyInput.KEY_D));
        manager.addMapping("Q", new KeyTrigger(KeyInput.KEY_Q));
        manager.addMapping("E", new KeyTrigger(KeyInput.KEY_E));

        manager.addListener(turnkeyListener, "W", "S", "A", "D", "Q", "E");


        manager.addMapping("U", new KeyTrigger(KeyInput.KEY_U));
        manager.addMapping("I", new KeyTrigger(KeyInput.KEY_I));
        manager.addMapping("O", new KeyTrigger(KeyInput.KEY_O));

        manager.addMapping("J", new KeyTrigger(KeyInput.KEY_J));
        manager.addMapping("K", new KeyTrigger(KeyInput.KEY_K));
        manager.addMapping("L", new KeyTrigger(KeyInput.KEY_L));

        manager.addMapping("M", new KeyTrigger(KeyInput.KEY_M));
        manager.addMapping(",", new KeyTrigger(KeyInput.KEY_COMMA));
        manager.addMapping(".", new KeyTrigger(KeyInput.KEY_PERIOD));

        manager.addMapping("P", new KeyTrigger(KeyInput.KEY_P));
        manager.addMapping("[", new KeyTrigger(KeyInput.KEY_LBRACKET));

        manager.addListener(highlightKeyListener, "U", "I", "O",
                "J", "K", "L",
                "M", ",", ".",
                "P", "[");

        manager.addMapping("LeftClick", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        manager.addListener(leftClickListener, "LeftClick");

        manager.addMapping("R", new KeyTrigger(KeyInput.KEY_R));
        manager.addListener(randomizelistener, "R");
    }

    InputListener turnkeyListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean isPressed, float tpf) {
            if (isPressed) {
                Quaternion direction = new Quaternion();
                switch (name) {
                    case "W":
                        direction = Cube.X_;
                        break;
                    case "S":
                        direction = Cube.X;
                        break;
                    case "A":
                        direction = Cube.Y_;
                        break;
                    case "D":
                        direction = Cube.Y;
                        break;
                    case "E":
                        direction = Cube.Z_;
                        break;
                    case "Q":
                        direction = Cube.Z;
                        break;
                }
                if (cube.highlighted.isEmpty()){
                    cube.rotateEntireCube(direction);
                } else {
                    cube.rotateHighlighted(direction);
                }
            }
        }
    };

    InputListener highlightKeyListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean isPressed, float tpf) {
            Location location = KeyToLocation.get(name);
            cube.setLocationHighlight(location, isPressed);
        }
    };

    InputListener leftClickListener = new ActionListener() {
        Location old = new Location(-1, 0, 0);

        @Override
        public void onAction(String name, boolean isPressed, float tpf) {
            CollisionResults results = new CollisionResults();
            Vector2f click2d = manager.getCursorPosition();
            Vector3f click3d = cam.getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0f).clone();
            Vector3f dir = cam.getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d).normalizeLocal();
            // Aim the ray from the clicked spot forwards.
            Ray ray = new Ray(click3d, dir);
            rootNode.collideWith(ray, results);
            if (results.size() > 0&&
                    (results.getClosestCollision().getGeometry().getName().equals("pieceBase")||
                    results.getClosestCollision().getGeometry().getName().equals("pieceSticker"))) {
                Piece selected = (Piece) results.getClosestCollision().getGeometry().getParent();
                cube.setLocationHighlight(old, false);
                cube.setLocationHighlight(selected.location, true);
                old = selected.location;
            } else {
                cube.setLocationHighlight(old, false);
                old = new Location(-1, 0, 0);
            }
        }
    };
    InputListener randomizelistener = new ActionListener() {
        @Override
        public void onAction(String name, boolean isPressed, float tpf) {
            if (isPressed){
                cube.randomize(20);
            }
        }
    };
}
