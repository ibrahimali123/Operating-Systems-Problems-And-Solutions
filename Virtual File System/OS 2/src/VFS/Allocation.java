package VFS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Allocation {
	final public static int ContiguousAllocation = 1;
	final public static int IndexedAllocation = 2;

	public int MAX_MEMORY, current_Size,type;
	public VFS.Directory root;
	public boolean[] allocatedBlockes;

	public Allocation(int max) {
		MAX_MEMORY = current_Size = max;
		root = new Directory("root/", "root");
		allocatedBlockes = new boolean[max];
	}

	boolean CreateFolder(String path, String Name) {
		VFS.Directory folder = new Directory(path, Name);
		return root.AddFolder(path, folder);
	}

	boolean DeleteFile(String path, String Name) {
		return root.DeleteFile(path, Name, this);
	}

	boolean DeleteFolder(String path, String Name) {
		return root.DeleteFolder(path, Name, this);
	}

	void DisplayDiskStatus() {
		System.out.println("Empty space " + current_Size + "\nAllocated space "
				+ (MAX_MEMORY - current_Size));
		System.out.println("Empty Blocks in the Disk");
		for (int i = 0; i < allocatedBlockes.length; i++) {
			if (!allocatedBlockes[i])
				System.out.print(i + " ");
		}

		System.out.println("\nAllocated Blocks in the Disk");
		for (int i = 0; i < allocatedBlockes.length; i++) {
			if (allocatedBlockes[i])
				System.out.print(i + " ");
		}
		System.out.println();
	}

	void DisplayDiskStructure() {
		root.DisplayDiskStructure("");
	}

	void save(String path, int type) throws JSONException,
			FileNotFoundException {

		JSONObject jsonObj = new JSONObject();

		jsonObj.put("type", type);
		jsonObj.put("size", MAX_MEMORY);
		jsonObj.put("current", current_Size);
		jsonObj.put("allocatedBlockes", allocatedBlockes);
		jsonObj.put("root", root.toJson());

		String temp = jsonObj.toString();
		// System.out.println(temp);

		PrintWriter out = new PrintWriter(path);
		out.write(temp);
		out.close();
	}

	public static Allocation load(String path) throws FileNotFoundException,
			JSONException {

		Allocation allocation = null;

		Scanner input = new Scanner(new File(path));
		String temp = input.next();
		input.close();

		JSONObject ob = new JSONObject(temp);
		
		if (ob.getInt("type") == ContiguousAllocation)
			allocation = new ContiguousAllocation(ob.getInt("size"));
		else
			allocation = new IndexedAllocation(ob.getInt("size"));

		allocation.type = ob.getInt("type") ;
		allocation.current_Size = ob.getInt("current");

		JSONArray xJson = (JSONArray) ob.get("allocatedBlockes");

		for (int i = 0; i < xJson.length(); i++) {
			allocation.allocatedBlockes[i] = xJson.getBoolean(i);
		}

		allocation.root.fromJson((JSONObject) ob.get("root"));

		return allocation;
	}
}
