package VFS_Visitor;

public abstract class Visitor<T> {
	public abstract boolean accept(VFS_Visitor.Directory di);

	public T ob;
	Allocation disk;

	public Visitor(T o) {
		ob = o;
	}

	public Visitor(T o, Allocation di) {
		ob = o;
		disk = di;
	}
}
