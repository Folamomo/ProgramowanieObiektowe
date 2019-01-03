package cube;

import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class Face extends Block{
    Sticker sticker;
    Face(Materials materials, ColorRGBA blockColor, ColorRGBA stickerColor, float size){
        super(materials.getMaterial(blockColor), size);
        sticker = new Sticker(materials.getMaterial(stickerColor), size*0.9f);
        sticker.setLocalTranslation(size,0f, 0f);
        sticker.setLocalRotation(new Quaternion().fromAngleAxis(FastMath.PI/2, new Vector3f(0f, 1f, 0f)));
        attachChild(sticker);
    }
}
