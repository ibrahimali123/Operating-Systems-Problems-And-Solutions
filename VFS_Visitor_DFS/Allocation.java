package VFS_Visitor_DFS;

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

	public int MAX_MEMORY, current_Size, type;
	public VFS_Visitor_DFS.Directory root;
	public boolean[] allocatedBlockes;

	public Allocation(int max) {
		MAX_MEMORY = current_Size = max;
		root = new Directory("root/", "root");
		allocatedBlockes = new boolean[max];
	}

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
		return root.recursev(path, v);// (path, folder);
	}

	boolean DeleteFile(String path, String Name) {

		Visitor<String> v = new Visitor<String>(Name, this) {
			@Override
			public boolean accept(Directory di) {
				for (int i = 0; i < di.files.size(); i++) {
					if (di.files.get(i).Name.equals(ob)) {
						di.files.get(i).freeDisk(disk);
						di.files.remove(i);
						return true;
					}
				}
				return false;
			}
		};
		return root.recursev(path, v);// (path, folder);
	}

	boolean DeleteFolder(String path, String Name) {
		Visitor<String> v = new Visitor<String>(Name, this) {
			@Override
			public boolean accept(Directory di) {
				for (int i = 0; i < di.subDirectories.size(); i++) {
					if (di.subDirectories.get(i).Name.equals(ob)) {
						di.subDirectories.get(i).freeDisk(disk);
						di.subDirectories.remove(i);
						return true;
					}
				}
				return false;
			}
		};
		return root.recursev(path, v);// (path, folder);
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


}
