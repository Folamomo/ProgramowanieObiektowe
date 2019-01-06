package cube;

import com.jme3.math.*;
import com.jme3.scene.Node;

public class Piece extends Node {
    Location location;
    Vector3f direction;
    Block block;
    Cube cube;

    Piece(Location l, ColorRGBA color, Cube c, Vector3f dir){
        location = l;
        cube = c;
        direction = dir;
        block = new Face(cube.materials, Cube.CBase, color, cube.size);
        block.setLocalTranslation(location.toVector3f().subtract(cube.center));
        Matrix3f rot = new Matrix3f();
        rot.fromStartEndVectors(new Vector3f(-1f, 0f, 0f), direction);
        block.setLocalRotation(rot);
        attachChild(block);
    }
    void Rotate(Quaternion rotation){
        location.rotateLocal(rotation, cube.center);
        block.setLocalTranslation(location.toVector3f().subtract(cube.center));

        direction = rotation.mult(direction);
        Matrix3f rot = new Matrix3f();
        rot.fromStartEndVectors(new Vector3f(-1f, 0f, 0f), direction);
        block.setLocalRotation(rot);
    }
    AnimationQueueItem RotateWithAnimation(Quaternion rotation){
        AnimationQueueItem a = new AnimationQueueItem(this, rotation);
        a.start();
        cube.animationQueue.add(a);
        return a;
    }
}
