package VFS;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Directory {

	public String Name;
	public String directoryPath;
	public ArrayList<VFS.File> files;
	public ArrayList<VFS.Directory> subDirectories;
	public boolean deleted = false;

	public Directory(String path, String name) {
		files = new ArrayList<>();
		subDirectories = new ArrayList<>();
		directoryPath = path;
		Name = name;
	}

	boolean AddFile(String path, VFS.File file) {

		if (path.length() == 0) {
			for (int i = 0; i < files.size(); i++) {
				if (files.get(i).Name.equals(file.Name))
					return false;
			}
			files.add(file);
			return true;
		}

		String subPath = path.substring(path.indexOf('/') + 1), name = path
				.substring(0, path.indexOf('/'));

		for (int i = 0; i < subDirectories.size(); i++) {
			if (subDirectories.get(i).Name.equals(name)) {
				return subDirectories.get(i).AddFile(subPath, file);
			}
		}

		return false;
	}

	boolean AddFolder(String path, VFS.Directory folder) {

		if (path.length() == 0) {
			for (int i = 0; i < subDirectories.size(); i++) {
				if (subDirectories.get(i).Name.equals(folder.Name))
					return false;
			}
			subDirectories.add(folder);
			return true;
		}

		String subPath = path.substring(path.indexOf('/') + 1), name = path
				.substring(0, path.indexOf('/'));

		for (int i = 0; i < subDirectories.size(); i++) {
			if (subDirectories.get(i).Name.equals(name)) {
				return subDirectories.get(i).AddFolder(subPath, folder);
			}
		}

		return false;
	}

	boolean DeleteFile(String path, String Name, Allocation disk) {

		if (path.length() == 0) {
			for (int i = 0; i < files.size(); i++) {
				if (files.get(i).Name.equals(Name)) {
					files.get(i).freeDisk(disk);
					files.remove(i);
					return true;
				}
			}
			return false;
		}

		String subPath = path.substring(path.indexOf('/') + 1), name = path
				.substring(0, path.indexOf('/'));
		for (int i = 0; i < subDirectories.size(); i++) {
			if (subDirectories.get(i).Name.equals(name)) {
				return subDirectories.get(i).DeleteFile(subPath, Name, disk);
			}
		}

		return false;
	}

	boolean DeleteFolder(String path, String Name, Allocation disk) {
		if (path.length() == 0) {
			for (int i = 0; i < subDirectories.size(); i++) {
				if (subDirectories.get(i).Name.equals(Name)) {
					subDirectories.get(i).freeDisk(disk);
					subDirectories.remove(i);
					return true;
				}
			}
			return false;
		}

		String subPath = path.substring(path.indexOf('/') + 1), name = path
				.substring(0, path.indexOf('/'));

		for (int i = 0; i < subDirectories.size(); i++) {
			if (subDirectories.get(i).Name.equals(name)) {
				return subDirectories.get(i).DeleteFolder(subPath, Name, disk);
			}
		}

		return false;
	}

	void freeDisk(Allocation disk) {

		for (int i = 0; i < files.size(); i++) {
			files.get(i).freeDisk(disk);
		}

		for (int i = 0; i < subDirectories.size(); i++) {
			subDirectories.get(i).freeDisk(disk);
		}

	}

	void DisplayDiskStructure(String space) {
		System.out.println(space + "+ " + Name);
		for (int i = 0; i < files.size(); i++) {
			System.out.println(space + "\t- " + files.get(i).toString());
		}
		for (int i = 0; i < subDirectories.size(); i++) {
			subDirectories.get(i).DisplayDiskStructure(space + "\t");
		}
	}

	public JSONObject toJson() throws JSONException {
		JSONObject ob = new JSONObject();
		ob.put("name", Name);
		ob.put("path", directoryPath);
		ob.put("fileSize", files.size());
		for (int i = 0; i < files.size(); i++) {
			ob.put("file" + i, files.get(i).toJson());
		}

		ob.put("folderSize", subDirectories.size());
		for (int i = 0; i < subDirectories.size(); i++) {
			ob.put("folder" + i, subDirectories.get(i).toJson());
		}
		return ob;
	}

	public void fromJson(JSONObject ob) throws JSONException {

		Name = (String) ob.get("name");
		directoryPath = (String) ob.get("path");

		int fileSize = (int) ob.get("fileSize");
		for (int i = 0; i < fileSize; i++) {
			JSONObject obb = ob.getJSONObject("file" + i);
			JSONArray x = (JSONArray) obb.get("allocatedBlocks");
			int[] allocated = new int[x.length()];
			for (int j = 0; j < allocated.length; j++) {
				allocated[j] = (int) x.get(j);
			}
			VFS.File file = new File((String) obb.getString("name"),
					(int) obb.get("size"), allocated);
			files.add(file);
		}

		int folderSize = (int) ob.get("folderSize");

		for (int i = 0; i < folderSize; i++) {
			VFS.Directory directory = new Directory("", "");
			subDirectories.add(directory);
			directory.fromJson(ob.getJSONObject("folder" + i));
		}
	}
}
/*
 * boolean isContainDic(String path) {
 * 
 * if (path.length() == 0) return true;
 * 
 * String subPath = path.substring(path.indexOf('/') + 1), name = path
 * .substring(0, path.indexOf('/'));
 * 
 * for (int i = 0; i < subDirectories.size(); i++) { if
 * (subDirectories.get(i).Name.equals(name)) { return
 * subDirectories.get(i).isContainDic(subPath); } }
 * 
 * return false; }
 */