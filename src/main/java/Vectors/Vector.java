package Vectors;

import static java.lang.StrictMath.*;

@SuppressWarnings("unchecked")

public class Vector<T extends Number> implements Cloneable{
    public T i;
    public T j;
    public T k;

    public Vector(T x, T y, T z){
        this.i = x;
        this.j = y;
        this.k = z;

    }

    public Vector(){

        i = (T) Double.valueOf(1);
        j = (T) Double.valueOf(0);
        k = (T) Double.valueOf(0);

    }

    public double getLength(){

        return sqrt(pow( i.doubleValue(),2)+ pow( j.doubleValue(),2)+ pow( k.doubleValue(),2));
    }

    public synchronized double getPHI() {
        //this method returns your XY plane rotation angle.
        // x = p sin()
        // y = p cos()
        // phi = asin(x/p) or acos(y/p)
        //decided not to use rot matrices in order to improve performance and or simplify code.

        double angle = (acos(i.doubleValue() / (getLength() * cos(getTheta()))));

        if (i.doubleValue() < 0) {
            angle = PI - angle;
        }
        return angle;
    }

    public double getTheta() {
        double angle = asin(k.doubleValue() / getLength());
        if (i.doubleValue() < 0) {
            angle = PI - angle;
        }
        return angle;
    }

    public void normalize() {
        double d = getLength();
        if (d == 0) {
          //  System.err.println("Null vector normalization detected.");
            return;
        }
        Class<?> tClass = i.getClass();

        if (tClass == Double.class) {
            i = (T) (Double) (i.doubleValue() / d);
            j = (T) (Double) (j.doubleValue() / d);
            k = (T) (Double) (k.doubleValue() / d);
        } else if (tClass == Integer.class) {
            i = (T) (Integer) (i.intValue() / (int) d);
            j = (T) (Integer) (j.intValue() / (int) d);
            k = (T) (Integer) (k.intValue() / (int) d);
        } else if (tClass == Float.class) {
            i = (T) (Float) (i.floatValue() / (float) d);
            j = (T) (Float) (j.floatValue() / (float) d);
            k = (T) (Float) (k.floatValue() / (float) d);
        }



    }

    public synchronized void rotateXZ(T angle){
        Class<?> cls = i.getClass();
        if(cls == Double.class){
            Double x1 = (i.doubleValue()*cos(angle.doubleValue()) + k.doubleValue()*sin(angle.doubleValue()));
            Double z1 = k.doubleValue()*cos(angle.doubleValue()) - i.doubleValue()*sin(angle.doubleValue());
            i = (T) x1; k = (T) z1;
        }else if(cls == Float.class){
            Float x1 = (float) (i.floatValue()*cos(angle.doubleValue()) + k.floatValue()*sin(angle.doubleValue()));
            Float z1 = (float) (k.floatValue()*cos(angle.doubleValue()) - i.floatValue()*sin(angle.doubleValue()));
            i = (T) x1; k = (T) z1;
        }
        /*
        System.err.println((int) (180*getTheta()/3.14));
        */
    }

    public double convertToPositive(double angle){
        if(angle < 0){
            angle = PI*2 -  angle;
        }
        return angle;
    }

    public void rotateXY(T angle) {
        Class<?> cls = i.getClass();
        if(cls == Double.class){
            Double x1 = (i.doubleValue()*cos(angle.doubleValue()) - j.doubleValue()*sin(angle.doubleValue()));
            Double z1 = i.doubleValue()*sin(angle.doubleValue()) + j.doubleValue()*cos(angle.doubleValue());
            i = (T) x1; j = (T) z1;
        }else if(cls == Float.class){
            Float x1 = (float) (i.doubleValue()*cos(angle.doubleValue()) - j.doubleValue()*sin(angle.doubleValue()));
            Float z1 = (float)  (i.doubleValue()*sin(angle.doubleValue()) + j.doubleValue()*cos(angle.doubleValue()));
            i = (T) x1; j = (T) z1;
        }
    }

    public Vector<Float> reverse() {

            return new Vector<>(-i.floatValue(),-j.floatValue(),-k.floatValue());


    }

    public void add(Vector<T> vector){
        if(i.getClass() == Float.class){
            i = (T) new Float(i.floatValue()+vector.i.floatValue());
            j = (T) new Float(j.floatValue()+vector.j.floatValue());
            k = (T) new Float(k.floatValue()+vector.k.floatValue());
       }
        else if (i.getClass() == Double.class){
           i = (T) new Double(i.doubleValue()+vector.i.doubleValue());
           j = (T) new Double(j.doubleValue()+vector.j.doubleValue());
           k = (T) new Double(k.doubleValue()+vector.k.doubleValue());


       }


    }

    @Override
    public Vector<Float> clone() {
        try {
            Vector<Float> clone = (Vector<Float>) super.clone();
            clone.i = (float) i;
            clone.j = (float) j;
            clone.k = (float) k;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public Vector<T> flipXZ() {


        return new Vector<T>(k,j,i);
    }

    public Vector<Float> numMult(float mult){

        return new Vector<Float>(mult*i.floatValue(), mult*j.floatValue(),mult*k.floatValue());
    }


    @Override
    public String toString(){

        return String.format("vec3{%.2f,%.2f,%.2f}",i.floatValue(),j.floatValue(),k.floatValue());


    }

    public Vectors.Vertex<T> toVertex(){
        return  new Vectors.Vertex<T>(i,j,k);
    }

    public void rotateYZ(T angle) {
        Class<?> cls = i.getClass();
        if(cls == Double.class){
            Double x1 = (i.doubleValue()*cos(angle.doubleValue()) - j.doubleValue()*sin(angle.doubleValue()));
            Double z1 = i.doubleValue()*sin(angle.doubleValue()) + j.doubleValue()*cos(angle.doubleValue());
            i = (T) x1; j = (T) z1;
        }else if(cls == Float.class){
            Float y1 = (float) (j.doubleValue()*cos(angle.doubleValue()) - k.doubleValue()*sin(angle.doubleValue()));
            Float k1 = (float)  (j.doubleValue()*sin(angle.doubleValue()) + k.doubleValue()*cos(angle.doubleValue()));
            k = (T) k1; j = (T) y1;
        }

    }
}
