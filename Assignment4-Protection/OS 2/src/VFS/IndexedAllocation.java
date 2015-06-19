package VFS;

public class IndexedAllocation extends Allocation {

	IndexedAllocation(int max) {
		super(max);
		type = Allocation.IndexedAllocation;
	}

	boolean CreateFile(String path, String Name, int size) {
		if (size > current_Size)
			return false;

		int tSize = size;

		current_Size -= size;

		int[] allocated = new int[size];

		for (int i = 0; i < MAX_MEMORY && tSize != 0; i++) {
			if (!allocatedBlockes[i]) {
				allocated[i] = i;
				allocatedBlockes[i] = true;
				tSize--;
			}
		}

		VFS.File file = new File(Name, size, allocated);

		return root.AddFile(path, file);
	}

}
