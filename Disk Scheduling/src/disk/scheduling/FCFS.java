
package disk.scheduling;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Ibrahim Ali
 */
public class FCFS extends DiskScheduling_Algorithms{

    public FCFS(ArrayList<Integer> list, int start, int direction, int end) {
        super(list, start, direction, end);
    }

    @Override
    public ArrayList<Integer> sequenceOFHeadMovement() {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(start);
        result.add(list.get(0));
        
        totalHeadMovement += Math.abs(list.get(0) - result.get(0));
        for (int i = 1; i < list.size() ; ++i) {
            result.add(list.get(i));
            totalHeadMovement += Math.abs(list.get(i) - list.get(i-1));
        }
        return result;
    }

    @Override
    public int totalHeadMovement() {
        return totalHeadMovement;
    }
    
}
