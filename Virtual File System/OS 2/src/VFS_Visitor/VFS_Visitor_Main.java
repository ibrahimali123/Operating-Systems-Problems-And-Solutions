package VFS_Visitor;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.JSONException;

public class VFS_Visitor_Main {

	public static void operation(Allocation al) {
		Scanner x = new Scanner(System.in);
		String op = "";
		System.out.println("Hello in Disk");
		System.out.println("cFile to Create File");
		System.out.println("CFolder to Create Folder");
		System.out.println("dFile to delete File");
		System.out.println("dFolder to delete Folder");
		System.out.println("DSs to Display Disk Status");
		System.out.println("DDS to Display Disk Structure");
		System.out.println("Save to Save Disk");
		System.out.println("Exit to Close this Disk");
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
				al.DeleteFolder(path, name);

			} else if (op.contains("DSs")) {
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
		}
	}

	public static void main(String[] arr) throws FileNotFoundException,
			JSONException {
		String systemType = "";
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out
					.println("choose 1 for load disk 2 for new disk 3 for exit ");
			int start = input.nextInt();
			if (start == 1) {
				System.out.println("Enter the path of your disk !!!!  ");
				systemType = input.next();
				Allocation i = Allocation.load(systemType);
				System.out.println("Disk loaded Successfully :D");
				operation(i);
			} else if (start == 2) {
				System.out
						.println("chose 1 for disk Contiguous Allocation 2 for Indexed Allocation");
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
}

// ContiguousAllocation x = new ContiguousAllocation(100);
// x.CreateFile("", "moahamed.txt", 1);
// x.CreateFile("", "ahmed.txt", 1);
// x.CreateFile("", "ibrahim.txt", 1);
// x.CreateFolder("", "test");
// x.CreateFolder("test/", "ghosts");
// x.CreateFile("test/", "test.txt", 3);
// x.CreateFile("test/ghosts/", "test.txt", 3);
// x.CreateFolder("test/", "test");
// x.CreateFile("test/test/", "moahmed.txt", 3);
// x.DisplayDiskStructure();
// x.DisplayDiskStatus();
// x.save("filename.txt", 1);
// Allocation i = Allocation.load("filename.txt");
// i.DisplayDiskStructure();
