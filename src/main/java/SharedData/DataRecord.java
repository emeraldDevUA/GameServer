package SharedData;

import Vectors.Vector;
import Vectors.Vertex;

public class DataRecord {
    private Vertex<Float> pos;
    private Vector<Float> directionVec, turretVector;
    private float gunAngle;

    private int HP = 100;
    public DataRecord(Vertex<Float> pos, Vector<Float> dir, Vector<Float> turret, float gunAngle) {
        this.pos = pos;
        this.directionVec = dir;
        this.turretVector = turret;
        this.gunAngle = gunAngle;
    }



}
