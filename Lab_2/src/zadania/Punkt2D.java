package zadania;

public class Punkt2D {
    protected double x, y;
    public Punkt2D (double _x, double _y){
        x = _x;
        y = _y;
    }
    public double GetX (){
        return x;
    }
    public double GetY (){
        return y;
    }
    public void SetX (double _x){
        x = _x;
    }
    public void SetY (double _y){
        y = _y;
    }
    public double distance (Punkt2D other){
        return Math.sqrt(Math.pow(x-other.x, 2)+Math.pow(y-other.y, 2));
    }
}
