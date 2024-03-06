package Vectors;

import java.util.ArrayList;
import Vectors.Vector;
public class Vertex<T extends Number> implements Comparable<Vertex<Float>>{
        public T x,y,z;
        public Vertex(T x,T y, T z){

                this.x = x;
                this.y = y;
                this.z = z;
        }

        public Vertex<Float> addVertex(Vertex<Float> t){
                return  new Vertex<Float>(t.x +x.floatValue(), t.y +y.floatValue(), t.z+z.floatValue());
        }



        @Override
        public int compareTo(Vertex<Float> a) {
                if(a.x == x.floatValue()&&a.y == y.floatValue() && a.z == z.floatValue()){
                        return 0;
                }
                return 1;
        }

        public Vector<T> toVector(){
                return new Vectors.Vector<T>(x,y,z);
        }

        @Override
        public String toString(){

                return "{"+x.floatValue()+", "+y.floatValue()+", "+z.floatValue()+"}";

        }
}

