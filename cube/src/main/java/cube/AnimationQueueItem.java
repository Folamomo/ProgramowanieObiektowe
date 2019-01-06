package cube;

import com.jme3.math.Quaternion;

public class AnimationQueueItem {
    Piece piece;
    Quaternion rotation;
    boolean isFinished = false;
    float precentage = 0f;
    Quaternion q = new Quaternion();

    public AnimationQueueItem(Piece piece, Quaternion rotation) {
        this.piece = piece;
        this.rotation = rotation;
    }

    public void start(){

    }
    public void update(float tpf){
        precentage += tpf*2;
        if (precentage >=1f) isFinished = true;
        q.slerp(Quaternion.IDENTITY, rotation, precentage);
        piece.setLocalRotation(q);
    }
    public void end(){
        piece.setLocalRotation(Quaternion.DIRECTION_Z);
        piece.Rotate(rotation);
    }

}
