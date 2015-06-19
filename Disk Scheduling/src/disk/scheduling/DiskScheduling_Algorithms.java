package disk.scheduling;

import java.util.ArrayList;

/**
 *
 * @author Ibrahim Ali
 */
public abstract class DiskScheduling_Algorithms {
    
    public int start , totalHeadMovement , end , direction;
    public ArrayList<Integer> list = new ArrayList<Integer>();
    public DiskScheduling_Algorithms(ArrayList<Integer> list , int start , int direction , int end) {
        this.list = list;
        this.start = start;
        this.direction = direction;
        this.end = end;
    }
    
    public abstract ArrayList<Integer> sequenceOFHeadMovement();
    public abstract int totalHeadMovement();

}
