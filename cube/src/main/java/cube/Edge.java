package cube;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;

public class Edge extends Block {
    Sticker sticker1, sticker2;
    Edge(Materials materials, ColorRGBA blockColor,
         ColorRGBA stickerColor1,
         ColorRGBA stickerColor2,
         float size){
        super(materials.getMaterial(blockColor), size);
        sticker1 = new Sticker(materials.getMaterial(stickerColor1), size*0.9f);
        sticker1.setLocalTranslation(0f,0f, size);
        attachChild(sticker1);
        sticker2 = new Sticker(materials.getMaterial(stickerColor2), size*0.9f);
        sticker2.setLocalRotation(new Quaternion(1f, 0f, 0f, 1f));
        sticker2.setLocalTranslation(0f, size, 0f);
        attachChild(sticker2);
    }
}
