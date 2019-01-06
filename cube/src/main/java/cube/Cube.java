package cube;

import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

import java.util.ArrayList;

public class Cube extends Node {

    static Quaternion X = new Quaternion().fromAngleAxis(FastMath.PI/2, new Vector3f(1f, 0f, 0f));
    static Quaternion X_ = new Quaternion().fromAngleAxis(-FastMath.PI/2, new Vector3f(1f, 0f, 0f));
    static Quaternion Y = new Quaternion().fromAngleAxis(FastMath.PI/2, new Vector3f(0f, 1f, 0f));
    static Quaternion Y_ = new Quaternion().fromAngleAxis(-FastMath.PI/2, new Vector3f(0f, 1f, 0f));
    static Quaternion Z = new Quaternion().fromAngleAxis(FastMath.PI/2, new Vector3f(0f, 0f, 1f));
    static Quaternion Z_ = new Quaternion().fromAngleAxis(-FastMath.PI/2, new Vector3f(0f, 0f, 1f));

    static ColorRGBA CBase = ColorRGBA.Black;
    static ColorRGBA CTop = ColorRGBA.White;
    static ColorRGBA CBottom = ColorRGBA.Yellow;
    static ColorRGBA CLeft = ColorRGBA.Orange;
    static ColorRGBA CRight = ColorRGBA.Red;
    static ColorRGBA CFront = ColorRGBA.Green;
    static ColorRGBA CBack = ColorRGBA.Blue;

    int dimension;
    float size = 0.5f;

    Materials materials;
    ArrayList<AnimationQueueItem> animationQueue = new ArrayList<>();
    AnimationQueueItem currentAnimation = null;
    Vector3f center;
    ArrayList<Piece> pieces= new ArrayList<>();


    Cube(Materials m, int d) {

        dimension = d;
        materials = m;
        center = new Vector3f((dimension - 1) / 2f, (dimension - 1) / 2f, (dimension - 1) / 2f);

        for (int i = 0; i < dimension; i++) { //faces
            for (int j = 0; j < dimension; j++) {
                pieces.add(new Piece(new Location(0, i, j), CFront, this, new Vector3f(1f, 0f, 0f)));
                pieces.add(new Piece(new Location(dimension - 1, i, j), CBack, this, new Vector3f(-1f, 0f, 0f)));
                pieces.add(new Piece(new Location(i, 0, j), CTop, this, new Vector3f(0f, 1f, 0f)));
                pieces.add(new Piece(new Location(i, dimension - 1, j), CBottom, this, new Vector3f(0f, -1f, 0f)));
                pieces.add(new Piece(new Location(i, j, 0), CLeft, this, new Vector3f(0f, 0f, 1f)));
                pieces.add(new Piece(new Location(i, j, dimension - 1), CRight, this, new Vector3f(0f, 0f, -1f)));
            }
        }

        for (Piece piece : pieces) {
            attachChild(piece);
        }
        for (Piece piece : pieces) {
            if (piece.location.Z == 1) {
                piece.Rotate(Cube.Z);
            }
        }
        for (Piece piece : pieces) {
            if (piece.location.Y == 0) {
                piece.RotateWithAnimation(Cube.Y);
            }
        }
    }

    void update(float tpf){
       for (AnimationQueueItem a : animationQueue){
           a.update(tpf);
           if (a.isFinished){
               a.end();
           }
       }
       animationQueue.removeIf((AnimationQueueItem a)-> a.isFinished);
    }

}
