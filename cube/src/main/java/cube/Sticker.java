package cube;

import com.jme3.material.Material;
import com.jme3.math.Plane;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class Sticker extends Geometry {
    Sticker(Material material, float size){ //Standard square sticker
        super("Sticker", new Box(size, size, 0.01f));
        super.setMaterial(material);
    }
}
