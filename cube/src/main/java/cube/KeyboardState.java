package cube;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.InputListener;
import com.jme3.input.controls.KeyTrigger;

import java.util.HashMap;

public class KeyboardState {
    InputManager manager;
    HashMap<String, KeyState> keys = new HashMap<>();

    public KeyboardState(InputManager manager) {
        this.manager = manager;
        addKey("U", KeyInput.KEY_U);
        addKey("J", KeyInput.KEY_J);
        addKey("M", KeyInput.KEY_M);

        addKey("I", KeyInput.KEY_I);
        addKey("K", KeyInput.KEY_K);
        addKey(",", KeyInput.KEY_COMMA);

        addKey("O", KeyInput.KEY_O);
        addKey("L", KeyInput.KEY_L);
        addKey(".", KeyInput.KEY_PERIOD);

        addKey("P", KeyInput.KEY_P);
        addKey("[", KeyInput.KEY_LBRACKET);
    }


    public KeyboardState(InputManager manager, boolean empty) {
        this.manager = manager;
    }


    public String getFirstPressed(){
        for (KeyState keyState : keys.values()) {
            if (keyState.state) return keyState.name;
        }
        return null;
    }

    public void addKey(String name, int key_id){
        KeyState key = new KeyState(name, key_id);
        keys.put(name, key);
    }

    public boolean checkState(String name){
        return keys.get(name).state;
    }

    class KeyState {
        boolean state = false;
        String name;
        KeyState(String name, int key_id) {
            this.name = name;
            manager.addListener((ActionListener) (name1, keyPressed, tpf) -> {
                if (name1.equals(name)) {
                    state = keyPressed;
                }
            }, this.name);
            manager.addMapping(name, new KeyTrigger(key_id));
        }
    }
}
