package Vectors;
import Vectors.Vector;
import static java.lang.Math.*;
public class Force<T extends Number> extends Vector<T> implements Comparable<Force<T>>{

    private double magnitude;
    public Force(T x, T y, T z, double Length) {
        super(x, y, z);
        magnitude = Length;
    }

    public Force() {

    }

    public Force(Vector<T> vector, double Magnitude){

        super(vector.i, vector.j,vector.k);
        magnitude = Magnitude;

    }

    @Override
    public double getLength() {
        return magnitude;
    }

    @Override
    public int compareTo(Force<T> o) {
        return 1;
    }

    @Override
    public  String toString(){

        return "X = " + i.floatValue() +
                "\nY = " + j.floatValue() +
                "\nZ = " + k.floatValue() +
                "\nL = " + (float)getLength();
    }


    public T getYComponent(){
        if(i.getClass() == Double.class) {
            return (T) (new Double(magnitude * cos(getPHI())));
        }
        else if(i.getClass() == Float.class){

            double radianValue = (getPHI());
            System.err.println(StrictMath.cos(radianValue));

            if(abs(cos(radianValue))<= pow(10,-5)) {
                return (T) new Float(0);
            }
            return (T) (new Float(magnitude * cos(radianValue)));

        }
        else if(i.getClass() == Integer.class){

            return (T) (new Integer((int)(magnitude * cos(getPHI()))));
        }
        return null;
    }

}
