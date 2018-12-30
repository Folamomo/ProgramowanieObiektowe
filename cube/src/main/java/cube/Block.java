package cube;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

public class Block extends Node {
    Geometry shape;
    Block(Material material, float size){
        shape = new Geometry("Box", new Box(size, size, size));
        shape.setMaterial(material);
        attachChild(shape);
    }
}
