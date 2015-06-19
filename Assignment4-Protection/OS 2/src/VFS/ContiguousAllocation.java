package VFS;

import java.util.ArrayList;
import java.util.Collections;

public class ContiguousAllocation extends Allocation {

	ContiguousAllocation(int max) {
		super(max);
		type = Allocation.ContiguousAllocation;
	}

	boolean CreateFile(String path, String Name, int size) {

		if (size > current_Size)
			return false;

		int temp = 0, index = -1;
		ArrayList<pair> free = new ArrayList<>();

		for (int i = 0; i < allocatedBlockes.length; i++) {

			if (index == -1 && !allocatedBlockes[i]) {
				index = i;
			}

			if (allocatedBlockes[i]) {
				if (index != -1)
					free.add(new pair(temp, index));
				index = -1;
				temp = 0;
			} else
				temp++;
		}
		if (index != -1)
			free.add(new pair(temp, index));

		Collections.sort(free, new pair(0, 0));

		pair p = free.get(free.size() - 1);
		
		if (p.size < size)
			return false;

		current_Size -= size;

		index = p.index;

		int[] allocated = new int[size];

		for (int i = 0; i < size; i++) {
			allocated[i] = index;
			allocatedBlockes[index] = true;
			index++;
		}

		VFS.File file = new File(Name, size, allocated);

		return root.AddFile(path, file);
	}

}
