/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package disk.scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Ibrahim Ali
 */

class ValueComparator implements Comparator<Integer> {

    Map<Integer, Integer> base;

    public ValueComparator(Map<Integer, Integer> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.    
    public int compare(Integer a, Integer b) {
        if (base.get(a) <= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}
public class SSTF extends DiskScheduling_Algorithms{

    public SSTF(ArrayList<Integer> list, int start, int direction, int end) {
        super(list, start, direction, end);
    }
    
    @Override
    public ArrayList<Integer> sequenceOFHeadMovement() {
        
        Map<Integer,Integer> indx = new HashMap<Integer,Integer>();
        ArrayList<Integer> result = new ArrayList<>();
        result.add(start);
        
        for (int i = 0; i < list.size() ; ++i)
            indx.put( i , Math.abs(start - list.get(i)));
        
        ValueComparator bvc = new ValueComparator(indx);
        TreeMap<Integer, Integer> sorted_map = new TreeMap<>(bvc);
        sorted_map.putAll(indx);

        for (Map.Entry<Integer, Integer> entrySet : sorted_map.entrySet()) 
            result.add(list.get(entrySet.getKey()));
        
        for (int i = 1; i < result.size() ; ++i)
            totalHeadMovement += Math.abs(result.get(i) - result.get(i-1));
  
        return result;
    }

    @Override
    public int totalHeadMovement() {
        return totalHeadMovement;
    }
}
