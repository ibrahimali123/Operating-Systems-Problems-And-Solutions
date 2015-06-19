package disk.scheduling;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Ibrahim Ali
 */
public class LOOK extends DiskScheduling_Algorithms{
    
    public LOOK(ArrayList<Integer> list, int start, int direction, int end) {
        super(list, start, direction, end);
    }

    @Override
    public ArrayList<Integer> sequenceOFHeadMovement() {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> tmp = new ArrayList<>();

        tmp.add(start);

        for (int i = 0; i < list.size(); ++i) {
            tmp.add(list.get(i));
        }

        Collections.sort(tmp);

        if (direction == 2) {
            result = rightDirection(tmp);
        } else {
            result = leftDirection(tmp);
        }

        for (int i = 1; i < result.size(); ++i) {
            totalHeadMovement += Math.abs(result.get(i) - result.get(i - 1));
        }

        return result;
    }

    public ArrayList<Integer> rightDirection(ArrayList<Integer> tmp) {
        int i;
        for (i = 0; i < tmp.size(); ++i) {
            if (tmp.get(i) == start) {
                break;
            }
        }

        i--;

        while (i >= 0) {
            tmp.add(tmp.get(i));
            tmp.remove(i);
            i--;
        }

        return tmp;
    }

    public ArrayList<Integer> leftDirection(ArrayList<Integer> tmp) {
        ArrayList<Integer> res = new ArrayList<>();
        int i;
        for (i = 0; i < tmp.size(); ++i) {
            if (tmp.get(i) == start) {
                break;
            }
        }

        res.add(tmp.get(i));
        i--;

        while (i >= 0) {
            res.add(tmp.get(i));
            i--;
        }

        for (i = 0; i < tmp.size(); ++i) {
            if (tmp.get(i) > start) {
                res.add(tmp.get(i));
            }
        }

        return res;
    }

    @Override
    public int totalHeadMovement() {
        return totalHeadMovement;
    }
}
