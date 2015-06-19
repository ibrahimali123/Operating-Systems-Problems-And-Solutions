package VFS_Visitor_DFS;

public class DistributedFileSystem {
	public int MAX_MEMORY, current_Size;
	IndexedAllocation[] AL;
	Directory root;

	DistributedFileSystem(int max, IndexedAllocation[] AL) {
		MAX_MEMORY = current_Size = max;
		this.AL = AL;
		root = new Directory("root/", "root");
	}

	boolean CreateFile(String path, String Name, int size) {
		if (size > current_Size)
			return false;

		VFS_Visitor_DFS.File file = new File(Name, 0, new int[0]);

		Visitor<VFS_Visitor_DFS.File> v = new Visitor<VFS_Visitor_DFS.File>(
				file) {

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

		boolean flag = root.recursev(path, v);

		if (!flag) {
			return false;
		}

		current_Size -= size;

		for (int i = 0; i < AL.length; i++) {

			if (size == 0)
				break;

			if (AL[i].current_Size >= size) {
				AL[i].CreateFile(path, Name, size);
				break;

			} else {
				size -= AL[i].current_Size;
				AL[i].CreateFile(path, Name, AL[i].current_Size);
			}
		}
		return flag;
	}

	// boolean CheckExistanceFile(String path, String Name) {
	// VFS_Visitor_DFS.Directory folder = new Directory(path, Name);
	//
	// Visitor<VFS_Visitor_DFS.Directory> v = new Visitor<Directory>(folder) {
	//
	// @Override
	// public boolean accept(Directory di) {
	// for (int i = 0; i < di.files.size(); i++) {
	// if (di.files.get(i).Name.equals(ob.Name))
	// return true;
	// }
	// return false;
	// }
	// };
	// return root.recursev(path, v);
	// }

	boolean CreateFolder(String path, String Name) {
		VFS_Visitor_DFS.Directory folder = new Directory(path, Name);

		Visitor<VFS_Visitor_DFS.Directory> v = new Visitor<Directory>(folder) {

			@Override
			public boolean accept(Directory di) {
				for (int i = 0; i < di.subDirectories.size(); i++) {
					if (di.subDirectories.get(i).Name.equals(ob.Name))
						return false;
				}
				di.subDirectories.add(ob);
				return true;
			}
		};

		for (int i = 0; i < AL.length; i++) {
			AL[i].root.recursev(path, v);
		}

		return root.recursev(path, v);// (path, folder);
	}

	void totalCurrentSize() {
		int tempC = 0;
		for (int i = 0; i < AL.length; i++) {
			tempC += AL[i].current_Size;
		}
		current_Size = tempC;
	}

	boolean DeleteFile(String path, String Name) {

		Visitor<String> v = new Visitor<String>(Name, this) {
			@Override
			public boolean accept(Directory di) {
				for (int i = 0; i < di.files.size(); i++) {
					if (di.files.get(i).Name.equals(ob)) {
						// di.files.get(i).freeDisk(disk);
						di.files.remove(i);
						return true;
					}
				}
				return false;
			}
		};
		for (int i = 0; i < AL.length; i++) {
			AL[i].DeleteFile(path, Name);
		}
		totalCurrentSize();
		return root.recursev(path, v);// (path, folder);
	}

	boolean DeleteFolder(String path, String Name) {
		Visitor<String> v = new Visitor<String>(Name, this) {
			@Override
			public boolean accept(Directory di) {
				for (int i = 0; i < di.subDirectories.size(); i++) {
					if (di.subDirectories.get(i).Name.equals(ob)) {
						// di.subDirectories.get(i).freeDisk(disk);
						di.subDirectories.remove(i);
						return true;
					}
				}
				return false;
			}
		};
		for (int i = 0; i < AL.length; i++) {
			AL[i].DeleteFolder(path, Name);
		}
		totalCurrentSize();
		return root.recursev(path, v);// (path, folder);
	}

	void DisplayDiskStatus() {

		System.out.println("Empty space " + current_Size + "\nAllocated space "
				+ (MAX_MEMORY - current_Size));

		for (int i = 0; i < AL.length; i++) {
			System.out.println("-----------------------------------");
			AL[i].DisplayDiskStatus();
		}
		System.out.println("-----------------------------------");
	}

	void DisplayDiskStructure() {
		root.DisplayDiskStructure("");
	}

}
