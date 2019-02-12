package cube;

import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

import java.util.ArrayList;
import java.util.function.Predicate;

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

    class Move {
        Location location;
        Quaternion rotation;

        public Move(Location location, Quaternion rotation) {
            this.location = location;
            this.rotation = rotation;
        }
    }

    int dimension;
    float pieceSize = 0.5f;

    Materials materials;
    ArrayList<Move> moveQueue = new ArrayList<>();
    PieceAnimation currentAnimation = null;
    Vector3f center;
    ArrayList<Piece> pieces = new ArrayList<>();


    Cube(Materials m, int d) {

        dimension = d;
        materials = m;
        center = new Vector3f((dimension - 1) / 2f, (dimension - 1) / 2f, (dimension - 1) / 2f);

        for (int i = 0; i < dimension; i++) { //faces
            for (int j = 0; j < dimension; j++) {
                pieces.add(new Piece(this, new Location(0, i, j), new Vector3f(1f, 0f, 0f), CFront));
                pieces.add(new Piece(this, new Location(dimension - 1, i, j), new Vector3f(-1f, 0f, 0f), CBack));
                pieces.add(new Piece(this, new Location(i, 0, j), new Vector3f(0f, 1f, 0f), CTop));
                pieces.add(new Piece(this, new Location(i, dimension - 1, j), new Vector3f(0f, -1f, 0f), CBottom));
                pieces.add(new Piece(this, new Location(i, j, 0), new Vector3f(0f, 0f, 1f), CLeft));
                pieces.add(new Piece(this, new Location(i, j, dimension - 1), new Vector3f(0f, 0f, -1f), CRight));
            }
        }

        for (Piece piece : pieces) {
            attachChild(piece);
        }


    }

    ArrayList<Piece> getPiecesIf(Predicate<Piece> check){
        ArrayList<Piece> result = new ArrayList<>(0);
        for (Piece piece: pieces) {
            if (check.test(piece)){
                result.add(piece);
            }
        }
        return result;
    }

    Predicate<Piece> samePlane(Location l, Quaternion rotation){
        if(rotation.equals(X) || rotation.equals(X_)){
            return (p2) -> l.X == p2.location.X;
        }
        if(rotation.equals(Y) || rotation.equals(Y_)){
            return (p2) -> l.Y == p2.location.Y;
        }
        if(rotation.equals(Z) || rotation.equals(Z_)){
            return (p2) -> l.Z == p2.location.Z;
        }
        return (p2) -> false;
    }

    void rotateFace(Move move){
        /*This is like grabing piece and rotating it in rotation direction*/
        ArrayList<Piece> toMove = getPiecesIf(samePlane(move.location, move.rotation));
        for (Piece p : toMove) {
            p.Rotate(move.rotation);
        }
    }

    void update(float tpf){
        if (currentAnimation != null) {
            if (currentAnimation.isFinished){
                currentAnimation = null;
            } else {
                currentAnimation.update(tpf);
            }
        } else if (!moveQueue.isEmpty()){
            currentAnimation = new PieceAnimation(this, moveQueue.get(0));
            moveQueue.remove(0);
            currentAnimation.update(tpf);
        }
    }

    void doThing(){
        moveQueue.add(new Move(new Location(2, 2, 2), Z));
    }

}
