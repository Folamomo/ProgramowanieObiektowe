package cube;

import com.jme3.app.SimpleApplication;
import com.jme3.input.ChaseCamera;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.system.AppSettings;


public class Main extends SimpleApplication{


    private ChaseCamera chaseCam;
    private Cube cube;
    private Controls controls;
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

        cube = new Cube(materials, 3);
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
