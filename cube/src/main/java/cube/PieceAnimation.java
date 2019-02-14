package cube;

import com.jme3.math.Quaternion;

import java.util.ArrayList;

public class PieceAnimation {
    ArrayList<Piece> pieces;
    Quaternion rotation;
    boolean isFinished;
    private float elapsedTime;
    static float duration = 0.2f;

    void update(float tpf){
        elapsedTime+=tpf;
        if(elapsedTime/duration<1f) {
            for (Piece piece : pieces) {
                piece.rotateWithAnimation(rotation, elapsedTime / duration);
            }
        } else {
            elapsedTime = 0f;
            isFinished = true;
            for (Piece piece : pieces) {
                piece.Rotate(rotation);
            }
        }
    }

    PieceAnimation(ArrayList<Piece> p, Quaternion r){
        pieces = p;
        rotation = r;
    }

    PieceAnimation(Cube cube, Cube.Move move){
        pieces = cube.getPiecesIf(Cube.samePlane(move.location, move.rotation));
        rotation = move.rotation;
    }
}
