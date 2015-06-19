package VFS_Visitor_DFS;

import java.util.Arrays;

import org.json.*;

public class File {
	public String Name;
	public int size;
	public int[] allocatedBlocks;
	public boolean deleted;

	public File(String name, int s, int[] allocated) {
		size = s;
		allocatedBlocks = Arrays.copyOf(allocated, allocated.length);
		Name = name;
		deleted = false;
	}

	void freeDisk(Allocation disk) {

		disk.current_Size += size;

		for (int i = 0; i < allocatedBlocks.length; i++) {
			disk.allocatedBlockes[allocatedBlocks[i]] = false;
		}
	}
	
	void freeDisk (DistributedFileSystem dfs){
		dfs.current_Size += size;
	}

	public String toString() {
		return Name;
	}

	public JSONObject toJson() throws JSONException {
		JSONObject ob = new JSONObject();
		ob.put("name", Name);
		ob.put("size", size);
		ob.put("allocatedBlocks", allocatedBlocks);
		return ob;
	}
}
