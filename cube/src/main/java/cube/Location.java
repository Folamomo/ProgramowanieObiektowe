package cube;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

public class Location {
    int X;
    int Y;
    int Z;

    Location(int x, int y, int z){
        X = x;
        Y = y;
        Z = z;
    }

    Location (Location l){
        X = l.X;
        Y = l.Y;
        Z = l.Z;
    }

    Location(Vector3f v){
        X = Math.round(v.x);
        Y = Math.round(v.y);
        Z = Math.round(v.z);
    }

    Vector3f toVector3f(){
        return new Vector3f(X, Y, Z);
    }

    void fromVector3f(Vector3f v){
        X = Math.round(v.x);
        Y = Math.round(v.y);
        Z = Math.round(v.z);
    }

    void rotateLocal(Quaternion q, Vector3f center){
        Vector3f v = toVector3f().subtract(center);
        v = q.mult(v);
        v.addLocal(center);
        fromVector3f(v);
    }

    Location rotate(Quaternion q, Vector3f center){
        Location l = new Location(this);
        l.rotateLocal(q, center);
        return l;
    }

    void print(){
        System.out.println("("+X+";"+Y+";"+Z+")");
    }

}
