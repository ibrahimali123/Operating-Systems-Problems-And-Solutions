package VFS_Visitor_DFS;

import java.util.Comparator;

public class pair implements Comparator<pair> {
	public int size, index;

	pair(int s, int i) {
		size = s;
		index = i;
	}

	@Override
	public int compare(pair arg0, pair arg1) {
		return arg0.size - arg1.size;
	}

}
