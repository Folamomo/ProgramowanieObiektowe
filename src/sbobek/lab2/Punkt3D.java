package lab2;

import java.lang.Math;

public class Punkt3D extends Punkt2D{
    protected double z;
    protected String name;
    public Punkt3D (double _x, double _y, double _z){
        super (_x, _y);
        z = _z;
    }
    public Punkt3D (double _x, double _y, double _z, String _name){
        super (_x, _y);
        z = _z;
        name = _name;
    }
    public double GetZ (){
        return z;
    }

    public String GetName (){
        return name;
    }

    public void SerName(String _name){
        name = _name;
    }

    public void SetZ (double _z){
        z = _z;
    }
    public double distance (Punkt3D other){
        return Math.sqrt(Math.pow(x-other.x, 2)+Math.pow(y-other.y, 2)+Math.pow(z-other.z, 2));
    }
}
