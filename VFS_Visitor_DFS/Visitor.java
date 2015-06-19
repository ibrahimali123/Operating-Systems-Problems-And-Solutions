package VFS_Visitor_DFS;

public abstract class Visitor<T> {
	public abstract boolean accept(VFS_Visitor_DFS.Directory di);

	public T ob;
	Allocation disk;
	DistributedFileSystem dfs;

	public Visitor(T o) {
		ob = o;
	}

	public Visitor(T o, Allocation di) {
		ob = o;
		disk = di;
	}
	
	public Visitor(T o, DistributedFileSystem di) {
		ob = o;
		dfs = di;
	}
}
