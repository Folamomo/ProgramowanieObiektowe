package cube;

import com.jme3.app.SimpleApplication;
import com.jme3.input.ChaseCamera;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.debug.Arrow;
import com.jme3.system.AppSettings;

import static cube.Cube.X;


public class Main extends SimpleApplication{


    ChaseCamera chaseCam;
    Cube cube;
    Controls controls;
    public static void main(String[] args) {
        Main main = new Main();

        AppSettings appSettings = new AppSettings(true);
        appSettings.setWidth(800);
        appSettings.setHeight(600);
        appSettings.setFrameRate(60);
        appSettings.setSamples(8);
        main.setSettings(appSettings);
        main.setShowSettings(false);

        main.start();
    }

    @Override
    public void simpleInitApp() {
        viewPort.setBackgroundColor(ColorRGBA.LightGray);
        Materials materials = new Materials(assetManager);
        setDisplayStatView(false);

        setupCamera();

        inputManager.addMapping("Rotate", new KeyTrigger(KeyInput.KEY_1));

        inputManager.deleteMapping(SimpleApplication.INPUT_MAPPING_MEMORY);

        cube = new Cube(materials, 5);
        rootNode.attachChild(cube);
        controls = new Controls(cube, inputManager, cam, rootNode);

    }

    private void setupCamera(){
        flyCam.setEnabled(false);
        chaseCam = new ChaseCamera(cam, rootNode, inputManager);
        chaseCam.setInvertVerticalAxis(true);
        chaseCam.setMinVerticalRotation(-FastMath.PI);
        chaseCam.setToggleRotationTrigger(new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        chaseCam.setRotationSpeed(1.5f);
        chaseCam.setZoomSensitivity(0.5f);
    }

    @Override
    public void simpleUpdate(float tpf) {
        cube.update(tpf);
    }

}
