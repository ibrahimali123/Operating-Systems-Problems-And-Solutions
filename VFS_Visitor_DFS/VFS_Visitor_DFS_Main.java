package VFS_Visitor_DFS;

import java.util.Scanner;

public class VFS_Visitor_DFS_Main {

	public static void operation(DistributedFileSystem al) {
		Scanner x = new Scanner(System.in);
		String op = "";
		System.out.println("*****************************");
		System.out.println("Hello in Disk");
		System.out.println("cFile to Create File");
		System.out.println("CFolder to Create Folder");
		System.out.println("dFile to delete File");
		System.out.println("dFolder to delete Folder");
		System.out.println("DSS to Display Disk Status");
		System.out.println("DDS to Display Disk Structure");
		System.out.println("Exit to Close this Disk");
		System.out.println("*****************************");
		
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

				if (al.CreateFile(path, name, size))
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

			} else if (op.contains("DSS")) {
				al.DisplayDiskStatus();
			} else if (op.contains("DDS")) {
				al.DisplayDiskStructure();
			} else if (op.contains("Exit"))
				break;
			else
				System.out.println("Un know comand!!!");
		}
	}

	public static void main(String[] arr) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter # of Disks ? ");
		int num = input.nextInt(), sum = 0;
		IndexedAllocation[] AL = new IndexedAllocation[num];

		for (int i = 0; i < AL.length; i++) {
			System.out.println("Enter Disk # " + i + "size ? ");
			int diskSize = input.nextInt();
			AL[i] = new IndexedAllocation(diskSize);
			sum += diskSize;
		}

		DistributedFileSystem x = new DistributedFileSystem(sum, AL);
		operation(x);

	}
}
