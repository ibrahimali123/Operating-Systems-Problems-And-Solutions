package Banker;
import java.util.Arrays;
import java.util.Scanner;

public class Banker {

	int[] available; // the available amount of each resource
	int[][] maximum; // the maximum demand of each process
	int[][] allocation;// the amount currently allocated to each process
	int[][] need; // the remaining needs of each process
	public int numOfResources, numOfProcess;
	Scanner x = new Scanner(System.in);

	Banker() {
		System.out.println("Enter Number Of Resources");
		numOfResources = x.nextInt();

		available = new int[numOfResources];

		System.out.println("Enter Available of each Resource");
		for (int i = 0; i < available.length; i++) {
			available[i] = x.nextInt();
		}

		System.out.println("Enter Number Of Process");
		numOfProcess = x.nextInt();

		maximum = new int[numOfProcess][numOfResources];
		allocation = new int[numOfProcess][numOfResources];
		need = new int[numOfProcess][numOfResources];

		System.out.println("Enter Maximum Resource of each process");
		for (int i = 0; i < maximum.length; i++) {
			System.out.println("Need of " + (i + 1) + " Process");
			for (int j = 0; j < maximum[0].length; j++) {
				need[i][j] = maximum[i][j] = x.nextInt();
			}
		}

	}

	public void relase(int processNumber, int[] resource) {
		for (int i = 0; i < resource.length; i++) {
			need[processNumber][i] += available[i] += resource[i];
			allocation[processNumber][i] -= resource[i];
		}
	}

	public boolean request(int processNumber, int[] request) {

		for (int i = 0; i < request.length; i++) {
			if (request[i] > need[processNumber][i]
					|| request[i] > available[i])
				return false;
		}

		boolean flag = dp(processNumber, request);

		if (flag) {
			for (int i = 0; i < request.length; i++) {
				need[processNumber][i] -= request[i];
				allocation[processNumber][i] += request[i];
				available[i] -= request[i];
			}
		}

		return flag;
	}

	boolean dp(int processNumber, int[] request) {

		int[] localAvailable = Arrays.copyOf(available, available.length);
		boolean[] finish = new boolean[numOfProcess];

		finish[processNumber] = true;
		for (int i = 0; i < numOfResources; i++) {
			localAvailable[i] += allocation[processNumber][i];
		}

		int i = 0, count = 0;
		while (true) {

			if (!finish[i] && needTolocalAvailable(need[i], localAvailable)) {
				count = -1;
				finish[i] = true;
				for (int j = 0; j < numOfResources; j++) {
					localAvailable[j] += allocation[i][j];
				}
			}

			if (count > numOfProcess) {
				break;
			}

			i = (i + 1) % numOfProcess;
			count = (count + 1);// % numOfProcess;
		}

		boolean flag = true;

		for (int j = 0; j < finish.length && flag; j++)
			flag &= finish[i];

		return flag;
	}

	boolean needTolocalAvailable(int[] needi, int[] localAvailable) {
		for (int i = 0; i < localAvailable.length; i++) {
			if (needi[i] > localAvailable[i])
				return false;
		}
		return true;
	}
}
