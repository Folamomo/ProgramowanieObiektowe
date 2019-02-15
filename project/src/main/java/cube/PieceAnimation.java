package cube;

import com.jme3.math.Quaternion;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PieceAnimation {
    private ArrayList<Piece> pieces = new ArrayList<>();
    private Quaternion rotation;
    public boolean isFinished;
    private float elapsedTime;
    private static float duration = 0.2f;

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

    public PieceAnimation(Cube cube, Cube.Move move){
        for (Piece piece : cube.pieces) {
            if (move.condition.test(piece)){
                pieces.add(piece);
            }
        }
        rotation = move.rotation;
    }
}
