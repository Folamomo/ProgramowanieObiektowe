package cube;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;

import static com.jme3.math.FastMath.cos;
import static com.jme3.math.FastMath.sin;

public class Corner extends Block{
    Sticker sticker1, sticker2, sticker3;
    Corner(Materials materials, ColorRGBA blockColor,
         ColorRGBA stickerColor1,
         ColorRGBA stickerColor2,
         ColorRGBA stickerColor3,
         float size){
        super(materials.getMaterial(blockColor), size);

        sticker1 = new Sticker(materials.getMaterial(stickerColor3), size*0.9f);
        sticker1.setLocalTranslation(0f,0f, size);
        attachChild(sticker1);

        sticker2 = new Sticker(materials.getMaterial(stickerColor2), size*0.9f);
        sticker2.setLocalRotation(new Quaternion(1f, 0f, 0f, 1f));
        sticker2.setLocalTranslation(0f, size, 0f);
        attachChild(sticker2);

        sticker3 = new Sticker(materials.getMaterial(stickerColor1), size*0.9f);
        sticker3.setLocalRotation(new Quaternion(1f, 0f, 1f, 0f));
        sticker3.setLocalTranslation(size, 0f, 0f);
        attachChild(sticker3);


    }
}
