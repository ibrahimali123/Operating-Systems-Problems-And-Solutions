package VFS;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.JSONException;

public class VFS_Main {
	public static void FileSystemcommands() {
		System.out.println("-----------------------------------");
		System.out.println("cFile to Create File");
		System.out.println("CFolder to Create Folder");
		System.out.println("dFile to delete File");
		System.out.println("dFolder to delete Folder");
		System.out.println("DSS to Display Disk Status");
		System.out.println("DDS to Display Disk Structure");
		System.out.println("Save to Save Disk");
		System.out.println("Exit to Close this Disk");
		System.out.println("-----------------------------------");
	}

	public static void operation(Allocation al) {
		Scanner x = new Scanner(System.in);
		String op = "";

		FileSystemcommands();
		// Allocation.root.DisplayDiskStructure("");
		// Allocation.p("folder1/subfolder");
		while (true) {

			op = x.next();
			if (op.contains("cFile")) {
				String name = x.next();
				String file[] = name.split("/");

				name = file[file.length - 1];
				String path = "";
				for (int i = 1; i < file.length - 1; i++) {
					path += file[i] + "/";
				}
				int size = x.nextInt();

				if (!Admin.userISAllowed(User.getCurrentActiveUser().getName(),
						path, "1")) {
					System.out.println("you are not allowed to do this");
					continue;
				}

				if (al.type == Allocation.ContiguousAllocation)
					if (((ContiguousAllocation) al)
							.CreateFile(path, name, size))
						System.out.println("File Added Successfully");
					else
						System.out.println("There is an Error ");
				else if (((IndexedAllocation) al).CreateFile(path, name, size))
					System.out.println("File Added Successfully");
				else
					System.out.println("There is an Error ");

			} else if (op.contains("cFolder")) {
				String name = x.next();
				String file[] = name.split("/");

				name = file[file.length - 1];
				String path = "";
				for (int i = 1; i < file.length - 1; i++) {
					path += file[i] + "/";
				}

				if (!Admin.userISAllowed(User.getCurrentActiveUser().getName(),
						path, "1")) {
					System.out.println("you are not allowed to do this");
					continue;
				}

				if (al.CreateFolder(path, name))
					System.out.println("Folder Added Successfully");
				else
					System.out.println("There is an Error ");

			} else if (op.contains("dFile")) {
				String name = x.next();
				String file[] = name.split("/");

				name = file[file.length - 1];
				String path = "";
				for (int i = 1; i < file.length - 1; i++) {
					path += file[i] + "/";
				}
				// System.out.println(path + " " + name);

				if (!Admin.userISAllowed(User.getCurrentActiveUser().getName(),
						path, "2")) {
					System.out.println("you are not allowed to do this");
					continue;
				}

				if (al.DeleteFile(path, name))
					System.out.println("Folder deleted Successfully");
				else
					System.out.println("There is an Error ");

			} else if (op.contains("dFolder")) {
				String name = x.next();
				String file[] = name.split("/");

				name = file[file.length - 1];
				String path = "";
				for (int i = 1; i < file.length - 1; i++) {
					path += file[i] + "/";
				}

				if (!Admin.userISAllowed(User.getCurrentActiveUser().getName(),
						path + "/" + name + "/", "2")) {
					System.out.println("you are not allowed to do this");
					continue;
				}

				al.DeleteFolder(path, name);

			} else if (op.contains("DSS")) {
				al.DisplayDiskStatus();
			} else if (op.contains("DDS")) {
				al.DisplayDiskStructure();
			} else if (op.contains("Exit"))
				break;
			else if (op.contains("Save")) {
				System.out.println("Enter Valoid Path and File Name");
				op = x.next();
				try {
					al.save(op, al.type);
					System.out.println("Disk Saved Successfully");
				} catch (FileNotFoundException | JSONException e) {
					e.printStackTrace();
				}
			} else
				System.out.println("Un know comand!!!");
			// FileSystemcommands();
		}
	}

	public void init() throws FileNotFoundException, JSONException {
		String systemType = "";
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println("[1]load disk [2]new disk  [3]exit ");
			int start = input.nextInt();
			if (start == 1) {
				System.out.println("Enter the path of your disk ? ");
				systemType = input.next();
				Allocation i = Allocation.load(systemType);
				System.out.println("Disk loaded Successfully..");
				operation(i);
			} else if (start == 2) {
				System.out
						.println("[1]Contiguous Allocation [2]Indexed Allocation");
				while (true) {
					start = input.nextInt();
					if (start == 1) {
						System.out.println("Enter Initial Disk Size");
						start = input.nextInt();
						ContiguousAllocation al = new ContiguousAllocation(
								start);
						System.out.println("Disk Created Successfully :D");
						operation(al);
						break;
					} else if (start == 2) {
						System.out.println("Enter Initial Disk Size");
						start = input.nextInt();
						IndexedAllocation al = new IndexedAllocation(start);
						System.out.println("Disk Created Successfully :D");
						operation(al);
						break;
					} else {
						System.out.println("Unkown Command");
					}
				}
			} else if (start == 3)
				break;
			else
				System.out.println("UnKown Command");
		}
	}

	public static void main(String[] arr) throws FileNotFoundException,
			JSONException {

		Admin admin = new Admin();
		admin.adminLogin();
		admin.AdminOperation();
	}
}
