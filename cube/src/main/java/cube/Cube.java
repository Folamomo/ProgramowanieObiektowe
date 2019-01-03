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
    int maxDist;
    float size = 0.5f;

    Materials materials;

    ArrayList<Piece> pieces= new ArrayList<>();

    Cube(Materials m){

        dimension = 3;
        maxDist = 1;
        materials = m;

        for (int i = -maxDist+1; i<maxDist; i++){ //faces
            for (int j = -maxDist+1; j<maxDist; j++) {
                pieces.add(new Piece(new Location(maxDist, i, j), CFront, this));
                pieces.add(new Piece(new Location(-maxDist, i, j), CBack, this));
                pieces.add(new Piece(new Location(i, maxDist, j), CTop, this));
                pieces.add(new Piece(new Location(i, -maxDist, j), CBottom, this));
                pieces.add(new Piece(new Location(i, j, maxDist), CLeft, this));
                pieces.add(new Piece(new Location(i, j, -maxDist), CRight, this));
            }
        }

        for (int i = -maxDist+1; i<maxDist; i++) { //edges
            pieces.add(new Piece(new Location(maxDist, maxDist, i), CFront, CTop, this));
            pieces.add(new Piece(new Location(-maxDist, maxDist, i), CBack, CTop, this));
            pieces.add(new Piece(new Location(maxDist, -maxDist, i), CFront, CBottom, this));
            pieces.add(new Piece(new Location(-maxDist, -maxDist, i), CBack, CBottom, this));

            pieces.add(new Piece(new Location(maxDist, i, maxDist), CFront, CLeft, this));
            pieces.add(new Piece(new Location(-maxDist,i, maxDist), CLeft, CBack, this));
            pieces.add(new Piece(new Location(maxDist, i, -maxDist), CRight, CFront, this));
            pieces.add(new Piece(new Location(-maxDist, i, -maxDist), CBack, CRight, this));

            pieces.add(new Piece(new Location(i, maxDist, maxDist),  CLeft, CTop, this));
            pieces.add(new Piece(new Location(i, -maxDist, maxDist), CLeft, CBottom, this));
            pieces.add(new Piece(new Location(i, maxDist, -maxDist), CRight, CTop, this));
            pieces.add(new Piece(new Location(i, -maxDist, -maxDist), CRight, CBottom, this));
        }

        pieces.add(new Piece(new Location(maxDist, maxDist, maxDist), CFront, CTop, CLeft, this));
        pieces.add(new Piece(new Location(-maxDist, maxDist, maxDist), CTop, CBack, CLeft, this));
        pieces.add(new Piece(new Location(maxDist, -maxDist, maxDist), CBottom, CFront, CLeft, this));
        pieces.add(new Piece(new Location(-maxDist, -maxDist, maxDist), CBack, CBottom, CLeft, this));

        pieces.add(new Piece(new Location(maxDist, maxDist, -maxDist), CFront, CRight, CTop, this));
        pieces.add(new Piece(new Location(-maxDist, maxDist, -maxDist), CTop, CRight, CBack, this));
        pieces.add(new Piece(new Location(maxDist, -maxDist, -maxDist), CBottom, CRight, CFront, this));
        pieces.add(new Piece(new Location(-maxDist, -maxDist, -maxDist), CBack, CRight, CBottom, this));

        for (Piece piece: pieces){
            attachChild(piece);
        }
    }

    Cube(Materials m, int d) {

        dimension = d;
        maxDist = (dimension - 1);
        materials = m;
        size *= 2;

        for (int i = -maxDist + 2; i < maxDist - 1; i += 2) { //faces
            for (int j = -maxDist + 2; j < maxDist - 1; j += 2) {
                pieces.add(new Piece(new Location(maxDist, i, j), CFront, this));
                pieces.add(new Piece(new Location(-maxDist, i, j), CBack, this));
                pieces.add(new Piece(new Location(i, maxDist, j), CTop, this));
                pieces.add(new Piece(new Location(i, -maxDist, j), CBottom, this));
                pieces.add(new Piece(new Location(i, j, maxDist), CLeft, this));
                pieces.add(new Piece(new Location(i, j, -maxDist), CRight, this));
            }
        }

        for (int i = -maxDist + 2; i < maxDist - 1; i += 2) { //edges
            pieces.add(new Piece(new Location(maxDist, maxDist, i), CFront, CTop, this));
            pieces.add(new Piece(new Location(-maxDist, maxDist, i), CBack, CTop, this));
            pieces.add(new Piece(new Location(maxDist, -maxDist, i), CFront, CBottom, this));
            pieces.add(new Piece(new Location(-maxDist, -maxDist, i), CBack, CBottom, this));

            pieces.add(new Piece(new Location(maxDist, i, maxDist), CFront, CLeft, this));
            pieces.add(new Piece(new Location(-maxDist, i, maxDist), CLeft, CBack, this));
            pieces.add(new Piece(new Location(maxDist, i, -maxDist), CRight, CFront, this));
            pieces.add(new Piece(new Location(-maxDist, i, -maxDist), CBack, CRight, this));

            pieces.add(new Piece(new Location(i, maxDist, maxDist), CLeft, CTop, this));
            pieces.add(new Piece(new Location(i, -maxDist, maxDist), CLeft, CBottom, this));
            pieces.add(new Piece(new Location(i, maxDist, -maxDist), CRight, CTop, this));
            pieces.add(new Piece(new Location(i, -maxDist, -maxDist), CRight, CBottom, this));
        }

        pieces.add(new Piece(new Location(maxDist, maxDist, maxDist), CFront, CTop, CLeft, this));
        pieces.add(new Piece(new Location(-maxDist, maxDist, maxDist), CTop, CBack, CLeft, this));
        pieces.add(new Piece(new Location(maxDist, -maxDist, maxDist), CBottom, CFront, CLeft, this));
        pieces.add(new Piece(new Location(-maxDist, -maxDist, maxDist), CBack, CBottom, CLeft, this));

        pieces.add(new Piece(new Location(maxDist, maxDist, -maxDist), CFront, CRight, CTop, this));
        pieces.add(new Piece(new Location(-maxDist, maxDist, -maxDist), CTop, CRight, CBack, this));
        pieces.add(new Piece(new Location(maxDist, -maxDist, -maxDist), CBottom, CRight, CFront, this));
        pieces.add(new Piece(new Location(-maxDist, -maxDist, -maxDist), CBack, CRight, CBottom, this));

        for (Piece piece : pieces) {
            attachChild(piece);
        }
    }

    void update(float tpf){
        for (Piece piece : pieces){
//            if (piece.location.Y == maxDist){
                piece.Rotate(new Quaternion().fromAngleAxis(tpf, new Vector3f(0f, 1f, 0f)));
//            }
        }
    }


}
