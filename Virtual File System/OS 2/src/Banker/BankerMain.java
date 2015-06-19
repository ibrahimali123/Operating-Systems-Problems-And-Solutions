package Banker;
import java.util.Scanner;

public class BankerMain {

	public static void main(String[] args) {
		System.out.println("Hello in Banker Avoidance algorithms");
		Banker b = new Banker();
		Scanner x = new Scanner(System.in);
		String temp;
		System.out
				.println("RQ to Request Resource\nRL to Relase Resource\nQuit to Exit");
		while (true) {
			temp = x.next();
			if (temp.contains("RL")) {
				System.out.println("Enter The number of process !");
				int processNumber = x.nextInt();
				int[] resource = new int[b.numOfResources];
				System.out.println("Enter the resources");
				for (int i = 0; i < b.numOfResources; i++) {
					resource[i] = x.nextInt();
				}
				b.relase(processNumber, resource);
			} else if (temp.contains("RQ")) {
				System.out.println("Enter The number of process !");
				int processNumber = x.nextInt();
				int[] resource = new int[b.numOfResources];
				System.out.println("Enter the requested resources");
				for (int i = 0; i < b.numOfResources; i++) {
					resource[i] = x.nextInt();
				}
				if (b.request(processNumber, resource)) {
					System.out
							.println("Ok fine You the th requested Resources :D :D :)");
				} else {
					System.out
							.println("Oh !!!! You Request will lead to Problems Sorry for that :(");
				}
			} else if (temp.contains("Quit") || temp.contains("quit")) {
				System.out.println("Bye Bye :D");
				break;
			} else {
				System.out.println("Unkwon Command :P");

			}

		}
	}
}
