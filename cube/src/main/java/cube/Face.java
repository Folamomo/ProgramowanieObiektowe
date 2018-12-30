package cube;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class Face extends Block{
    Sticker sticker;
    Face(Materials materials, ColorRGBA blockColor, ColorRGBA stickerColor, float size){
        super(materials.getMaterial(blockColor), size);
        sticker = new Sticker(materials.getMaterial(stickerColor), size*0.9f);
        sticker.setLocalTranslation(0f,0f, size);
        attachChild(sticker);
    }
}
