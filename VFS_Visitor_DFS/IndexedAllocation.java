package VFS_Visitor_DFS;

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

		for (int i = 0, j = 0; i < MAX_MEMORY && tSize != 0; i++) {
			if (!allocatedBlockes[i]) {
				allocated[j] = i;
				allocatedBlockes[i] = true;
				tSize--;
				j++;
			}
		}
		VFS_Visitor_DFS.File file = new File(Name, size, allocated);

		Visitor<VFS_Visitor_DFS.File> v = new Visitor<VFS_Visitor_DFS.File>(file) {
			@Override
			public boolean accept(Directory di) {
				for (int i = 0; i < di.files.size(); i++) {
					if (di.files.get(i).Name.equals(ob.Name))
						return false;
				}
				di.files.add(ob);
				return true;
			}
		};

		return root.recursev(path, v);// (path, file);
	}

}
