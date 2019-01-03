package cube;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.scene.Node;

public class Piece extends Node {
    Location location;
    Block block;
    Cube cube;

    Piece(Location l, ColorRGBA color, Cube c){
        location = l;
        cube = c;
        Quaternion q = spinToConstructFace();
        block = new Face(cube.materials, Cube.CBase, color, cube.size);
        block.setLocalTranslation(location.toVector3f());
        attachChild(block);
        spinBack(q);
    }

    public Piece(Location l, ColorRGBA color1, ColorRGBA color2, Cube c) {
        location = l;
        cube = c;
        Quaternion q = spinToConstructEdge();
        block = new Edge(cube.materials, Cube.CBase, color1, color2, cube.size);
        block.setLocalTranslation(location.toVector3f());
        attachChild(block);
        spinBack(q);
    }

    public Piece(Location l, ColorRGBA color1, ColorRGBA color2, ColorRGBA color3, Cube c) {
        location = l;
        cube = c;
        Quaternion q = spinToConstructCorner();
        block = new Corner(cube.materials, Cube.CBase, color1, color2, color3,  cube.size);
        block.setLocalTranslation(location.toVector3f());
        attachChild(block);
        spinBack(q);
    }

    void Rotate(Quaternion q){
        location.rotateLocal(q);
        rotate(q);
    }

    Quaternion spinToConstructFace(){
        Quaternion q = Quaternion.IDENTITY;
        if (location.X == -cube.maxDist){
            q = Cube.Y.mult(Cube.Y);
        } else if (location.Z == cube.maxDist) {
            q = Cube.Y;
        }   else if (location.Z == -cube.maxDist) {
            q = Cube.Y_;
        }  else if (location.Y == cube.maxDist) {
            q = Cube.Z_;
        }   else if (location.Y == -cube.maxDist) {
            q = Cube.Z;
        }
        location.rotateLocal(q);
        return q.inverse();
    }

    Quaternion spinToConstructEdge(){
        Quaternion q = Quaternion.IDENTITY;
        if(Math.abs(location.Y)  == cube.maxDist) {
            if (location.X == -cube.maxDist) {
                q = q.mult(Cube.Y).mult(Cube.Y);
            }
            if (location.Z == cube.maxDist) {
                q = q.mult(Cube.Y);
            }
            if (location.Z == -cube.maxDist) {
                q = q.mult(Cube.Y_);
            }
            if (location.Y == -cube.maxDist) {
                q = q.mult(Cube.Z).mult(Cube.Z);
                if (Math.abs(location.X) == cube.maxDist) {
                    q = q.mult(Cube.Y).mult(Cube.Y);
                }
            }
        } else {
            q = q.mult(Cube.X_);
            if(location.X==-cube.maxDist && location.Z == cube.maxDist){
                q = q.mult(Cube.Y);
            } else if(location.X==-cube.maxDist && location.Z == -cube.maxDist){
                q = q.mult(Cube.Y).mult(Cube.Y);
            } else if(location.X==cube.maxDist && location.Z == -cube.maxDist){
                q = q.mult(Cube.Y_);
            }
        }
        location.rotateLocal(q);
        return q.inverse();
    }

    Quaternion spinToConstructCorner(){
        Quaternion q = Quaternion.IDENTITY;
        if (location.Z == -cube.maxDist){
            q = q.mult(Cube.X);
        }
        if (location.X == -cube.maxDist){
            q = q.mult(Cube.Z_);
        }
        if(location.Y  == -cube.maxDist) {
            q = q.mult(Cube.Z).mult(Cube.Z);
            if (location.X == -cube.maxDist){
                q = q.mult(Cube.Z);
            } else {
                q = q.mult(Cube.Z_);
            }
        }
        location.rotateLocal(q);
        return q.inverse();
    }

    void spinBack(Quaternion q){
        Rotate(q);
    }


}
