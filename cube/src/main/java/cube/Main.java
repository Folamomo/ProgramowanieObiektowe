package cube;

import com.jme3.app.SimpleApplication;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.CameraNode;
import com.jme3.scene.control.CameraControl;
import com.jme3.system.AppSettings;

import java.util.ArrayList;

public class Main extends SimpleApplication implements AnalogListener, ActionListener {

    boolean rotate = false;
    CameraNode camNode;

    public static void main(String[] args) {
        Main main = new Main();

        AppSettings appSettings = new AppSettings(true);
        appSettings.setWidth(800);
        appSettings.setHeight(600);
        appSettings.setFrameRate(60);
        main.setSettings(appSettings);
        main.setShowSettings(false);

        main.start();
    }

    @Override
    public void simpleInitApp() {
        viewPort.setBackgroundColor(ColorRGBA.LightGray);
        Materials materials = new Materials(assetManager);

        Face face = new Face(materials, ColorRGBA.Black, ColorRGBA.Red, 1f);
        face.setLocalTranslation(new Vector3f(0f, 0f, 0f));
        rootNode.attachChild(face);

        Edge edge = new Edge(materials, ColorRGBA.Black, ColorRGBA.Red, ColorRGBA.White, 1f);
        edge.setLocalTranslation(0f, 2f, 0f);
        rootNode.attachChild(edge);

        Corner corner = new Corner(materials, ColorRGBA.Black,
                ColorRGBA.Red, ColorRGBA.White, ColorRGBA.Blue,
                1f);
        corner.setLocalTranslation(2f, 2f, 0f);
        rootNode.attachChild(corner);

        inputManager.addMapping("toggleRotate", new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));

        camNode = new CameraNode("CamNode", cam);
        camNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        //attaching the camNode to the teaNode
        rootNode.attachChild(camNode);
        //setting the local translation of the cam node to move it away from the teanNode a bit
        camNode.setLocalTranslation(new Vector3f(0, 0, 10));
        //setting the camNode to look at the teaNode
        camNode.lookAt(rootNode.getLocalTranslation(), Vector3f.UNIT_Y);
        flyCam.setEnabled(false);
    }

    public void onAction(String name, boolean keyPressed, float tpf) {
        //toggling rotation on or off
        if (name.equals("toggleRotate") && keyPressed) {
            rotate = true;
            inputManager.setCursorVisible(false);
        }
        if (name.equals("toggleRotate") && !keyPressed) {
            rotate = false;
            inputManager.setCursorVisible(true);
        }

    }

    @Override
    public void onAnalog(String name, float value, float tpf) {

    }
}
