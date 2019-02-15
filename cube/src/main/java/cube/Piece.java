package cube;

import com.jme3.math.*;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

public class Piece extends Node {
    Cube cube;
    ColorRGBA color;
    Location location;
    Matrix3f orientation;
    boolean isHighlighted;

    Piece(Cube parent, Location l, Vector3f orientation, ColorRGBA stickerColor){
        this.cube = parent;
        color = stickerColor;

        Geometry base = new Geometry("pieceBase", new Box(cube.pieceSize, cube.pieceSize, cube.pieceSize));
        base.setMaterial(cube.materials.getMaterial(Cube.CBase));
        base.setLocalRotation(Quaternion.IDENTITY);
        attachChild(base);

        Geometry sticker = new Geometry("pieceSticker", new Box(cube.pieceSize *0.9f, cube.pieceSize *0.9f, 0.01f));
        sticker.setMaterial(cube.materials.getMaterial(color));
        sticker.setLocalTranslation(cube.pieceSize,0f, 0f);
        sticker.setLocalRotation(new Quaternion().fromAngleAxis(FastMath.PI/2, new Vector3f(0f, 1f, 0f)));
        attachChild(sticker);

        this.setLocation(l);
        this.setOrientation(orientation);
    }

    public void setLocation(Location l){
        location = l;
        this.setLocalTranslation(location.toVector3f().subtract(cube.center));
    }

    public void setOrientation(Vector3f o){
        Matrix3f rot = new Matrix3f();
        rot.fromStartEndVectors(new Vector3f(-1f, 0f, 0f), o);
        this.setLocalRotation(rot);
        orientation = rot;
    }
    public void setOrientation(Matrix3f m){
        this.setLocalRotation(m);
        orientation = m;
    }
    void Rotate(Quaternion rotation){
        this.setOrientation(rotation.toRotationMatrix().mult(orientation));
        this.setLocation(location.rotate(rotation, cube.center));
        updateHighlight();
    }

    void updateHighlight(){
        highlight(cube.highlighted.contains(location));
    }

    void rotateWithAnimation(Quaternion rotation, float percentage){
        Quaternion q = new Quaternion().slerp(Quaternion.IDENTITY, rotation, percentage);
        Matrix3f r =  q.toRotationMatrix().mult(orientation);

        Vector3f t = q.mult(location.toVector3f().subtract(cube.center));

        this.setLocalRotation(r);
        this.setLocalTranslation(t);
    }

    void highlight(boolean flag){
        if (flag) {
            getChild("pieceBase").setMaterial(cube.materials.getMaterial(Cube.CHighlight));
        } else {
            getChild("pieceBase").setMaterial(cube.materials.getMaterial(Cube.CBase));
        }
        isHighlighted = flag;
    }
}
