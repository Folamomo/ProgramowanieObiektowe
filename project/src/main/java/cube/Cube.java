package cube;

import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.function.Predicate;

public class Cube extends Node {

    static Quaternion X = new Quaternion().fromAngleAxis(FastMath.PI / 2, new Vector3f(1f, 0f, 0f));
    static Quaternion X_ = new Quaternion().fromAngleAxis(-FastMath.PI / 2, new Vector3f(1f, 0f, 0f));
    static Quaternion Y = new Quaternion().fromAngleAxis(FastMath.PI / 2, new Vector3f(0f, 1f, 0f));
    static Quaternion Y_ = new Quaternion().fromAngleAxis(-FastMath.PI / 2, new Vector3f(0f, 1f, 0f));
    static Quaternion Z = new Quaternion().fromAngleAxis(FastMath.PI / 2, new Vector3f(0f, 0f, 1f));
    static Quaternion Z_ = new Quaternion().fromAngleAxis(-FastMath.PI / 2, new Vector3f(0f, 0f, 1f));

    static ColorRGBA CBase = ColorRGBA.Black;
    static ColorRGBA CHighlight = ColorRGBA.Magenta;

    static ColorRGBA CTop = ColorRGBA.White;
    static ColorRGBA CBottom = ColorRGBA.Yellow;
    static ColorRGBA CLeft = ColorRGBA.Orange;
    static ColorRGBA CRight = ColorRGBA.Red;
    static ColorRGBA CFront = ColorRGBA.Green;
    static ColorRGBA CBack = ColorRGBA.Blue;

    HashSet<Location> highlighted = new HashSet<>();

    int dimension;
    float pieceSize = 0.5f;

    Materials materials;
    private ArrayList<Move> moveQueue = new ArrayList<>();
    private PieceAnimation currentAnimation = null;
    Vector3f center;
    ArrayList<Piece> pieces = new ArrayList<>();

    class Move {
        public final Predicate<Piece> condition;
        public final Quaternion rotation;

        public Move(Predicate<Piece> condition, Quaternion rotation) {
            this.condition = condition;
            this.rotation = rotation;
        }
    }

    Cube(Materials m, int d) {

        this.dimension = d;
        this.materials = m;
        this.center = new Vector3f((dimension - 1) / 2f, (dimension - 1) / 2f, (dimension - 1) / 2f);

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
        Geometry frontSphere = new Geometry("frontSphere", new Sphere(16, 16, 0.1f));
        frontSphere.setMaterial(materials.getMaterial(ColorRGBA.Magenta));
        frontSphere.setLocalTranslation(0, 0, pieceSize*dimension);
        attachChild(frontSphere);
    }

    ArrayList<Piece> getPiecesIf(Predicate<Piece> check) {
        ArrayList<Piece> result = new ArrayList<>(0);
        for (Piece piece : pieces) {
            if (check.test(piece)) {
                result.add(piece);
            }
        }
        return result;
    }

    static Predicate<Piece> samePlane(Location l, Quaternion rotation) {
        if (rotation.equals(X) || rotation.equals(X_)) {
            return (p2) -> l.X == p2.location.X;
        }
        if (rotation.equals(Y) || rotation.equals(Y_)) {
            return (p2) -> l.Y == p2.location.Y;
        }
        if (rotation.equals(Z) || rotation.equals(Z_)) {
            return (p2) -> l.Z == p2.location.Z;
        }
        return (p2) -> false;
    }


    void rotateFaceWithAnimation(Quaternion rotation, Location location) {
        moveQueue.add(new Move(samePlane(location, rotation), rotation));
    }

    void rotateHighlightedPlane(Quaternion rotation) {
        for (Location location : highlighted) {
            rotateFaceWithAnimation(rotation, location);
        }
    }

    void rotateEntireCube(Quaternion rotation){
        moveQueue.add(new Move(piece -> true, rotation));
    }

    void setLocationHighlight(Location location, boolean flag) {
        for (Piece piece : pieces) {
            if (piece.location.equals(location)) piece.highlight(flag);
        }
        if (flag) {
            highlighted.add(location);
        } else {
            highlighted.remove(location);
        }
    }

    void randomize(int moves){
        Random random = new Random();
        for (int i = 0; i < moves; i++) {

            Location l = new Location(random.nextInt(dimension), random.nextInt(dimension), random.nextInt(dimension));
            Quaternion d = Quaternion.IDENTITY;
            switch (random.nextInt(6)) {
                case 0: d = X;  break;
                case 1: d = X_; break;
                case 2: d = Y;  break;
                case 3: d = Y_; break;
                case 4: d = Z;  break;
                case 5: d = Z_; break;
            }
            rotateFaceWithAnimation(d, l);
        }
    }

    void update(float tpf) {
        if (currentAnimation != null) {
            if (currentAnimation.isFinished) {
                currentAnimation = null;
            } else {
                currentAnimation.update(tpf);
            }
        } else if (!moveQueue.isEmpty()) {
            currentAnimation = new PieceAnimation(this, moveQueue.get(0));
            moveQueue.remove(0);
            currentAnimation.update(tpf);
        }
    }
}