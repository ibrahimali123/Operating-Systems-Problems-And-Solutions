package VFS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Admin {

	public static Map<String, User> dict = new HashMap<>();

	public static void adminLogin() {
		Scanner in = new Scanner(System.in);
		String name = "", password = "";
		do {
			System.out.println("Enter Your UserName ? ");
			name = in.next();
			System.out.println("Enter Your Password ? ");
			password = in.next();
			if (!name.equals("admin") || !password.equals("admin"))
				System.out.println("Error in userName or Password , try again");

		} while (!name.equals("admin") || !password.equals("admin"));
	}

	public static void adminCommands() {
		System.out.println("***********************************");
		System.out.println("[1]- Create User");
		System.out.println("[2]- Delete User");
		System.out.println("[3]- Show All Users");
		System.out.println("[4]- Grant User");
		System.out.println("[5]- Tell User");
		System.out.println("[6]- login User");
		System.out.println("[7]- Loqout");
		System.out.println("[8]- Save Users");
		System.out.println("[9]- load Users");
		System.out.println("***********************************");
	}

	public static void AdminOperation() throws FileNotFoundException,
			JSONException {
		Scanner x = new Scanner(System.in);
		Allocation i = Allocation.load("hema.txt");
		int op = -1;

		adminCommands();

		while (true) {
			op = x.nextInt();
			if (op == 1) {
				System.out.println("Enter the UserName ? ");
				String name = x.next();
				System.out.println("Enter the password ? ");
				String password = x.next();

				if (createUser(name, password))
					System.out.println("User Created Successfully. ");
				else
					System.out.println("User Already Exists");
			} else if (op == 2) {
				System.out.println("Enter the UserName ? ");
				String name = x.next();

				if (DeleteUser(name))
					System.out.println("User Deleted Successfully. ");
				else
					System.out.println("User Not Exists");
			} else if (op == 3) {
				ShowAllUsers();

			} else if (op == 4) {
				i.DisplayDiskStructure();
				String userName = x.next();
				String path = x.next();
				String command = x.next();
				System.out.println(grantUser(userName, path, command));
			} else if (op == 5) {
				if (User.getCurrentActiveUser() != null)
					System.out.println(User.getCurrentActiveUser().getName());
				else
					System.out.println("Admin");
			} else if (op == 6) {
				System.out.println("Enter the UserName ? ");
				String name = x.next();
				System.out.println("Enter the password ? ");
				String password = x.next();
				System.out.println(login(name, password));

				if (login(name, password).equals("Login Successfully ..")) {
					VFS_Main main = new VFS_Main();
					main.init();
				}
			} else if (op == 7) {
				User.logout();
				break;
			} else if (op == 8) {
				saveUsers();
			} else if (op == 9) {
				loadUsers();
			} else {
				System.out.println("Un know comand!!!");
			}

			adminCommands();
		}
	}

	public static boolean createUser(String name, String pass) {
		if (dict.containsKey(name))
			return false;

		User user = new User(name, pass);
		dict.put(name, user);
		return true;
	}

	public static boolean DeleteUser(String name) {
		if (!dict.containsKey(name))
			return false;

		dict.remove(name);
		return true;
	}

	public static void ShowAllUsers() {
		System.out.println("--------------------------------");
		for (Map.Entry<String, User> entry : dict.entrySet()) {
			User user = entry.getValue();
			System.out.println("User Name : " + user.getName());
			System.out.println("User password : " + user.getPassword());
			System.out.println("Creating grants : ");
			for (int i = 0; i < user.createCapability.size(); i++) {
				System.out
						.println(i + 1 + " : " + user.createCapability.get(i));
			}
			System.out.println("Deleting grants : ");
			for (int i = 0; i < user.deleteCapability.size(); i++) {
				System.out
						.println(i + 1 + " : " + user.deleteCapability.get(i));
			}
			System.out.println("       ------------------");
		}
	}

	public static boolean userISAllowed(String userName, String path,
			String command) {
		if (!dict.containsKey(userName))
			return false;

		User user = dict.get(userName);

		// System.out.println("path : " + path);

		if (command.equals("1")) {
			for (int i = 0; i < user.createCapability.size(); i++) {
				if (path.contains(user.createCapability.get(i)))
					return true;
			}
		} else if (command.equals("2")) {
			for (int i = 0; i < user.deleteCapability.size(); i++) {
				if (path.contains(user.deleteCapability.get(i)))
					return true;
			}
		}
		return false;
	}

	public static String login(String name, String pass) {
		if (!dict.containsKey(name))
			return "User not found";
		User user = dict.get(name);

		if (!pass.equals(user.getPassword()))
			return "Error in Password";

		User.setCurrentActiveUser(user);
		return "Login Successfully ..";
	}

	public static String grantUser(String name, String path, String command) {
		path = path.substring(path.indexOf("/") + 1);
		// System.out.println("path : " + path);
		if (!Allocation.root.isContainDic(path))
			return "PATH ERROR";

		if (!dict.containsKey(name))
			return "User not found";

		User user = new User();
		user = dict.get(name);

		if (command.equals("00")) {
			if (user.createCapability.contains(path))
				user.createCapability.remove(path);

			if (user.deleteCapability.contains(path))
				user.deleteCapability.remove(path);

		} else if (command.equals("01")) {
			if (user.createCapability.contains(path))
				user.createCapability.remove(path);

			if (!user.deleteCapability.contains(path))
				user.deleteCapability.add(path);

		} else if (command.equals("10")) {
			if (user.deleteCapability.contains(path))
				user.deleteCapability.remove(path);

			if (!user.createCapability.contains(path))
				user.createCapability.add(path);
		} else if (command.equals("11")) {

			if (!user.deleteCapability.contains(path))
				user.deleteCapability.add(path);

			if (!user.createCapability.contains(path))
				user.createCapability.add(path);
		}
		dict.put(name, user);
		return "Done Sucessfully";
	}

	static void saveUsers() throws JSONException, FileNotFoundException {

		JSONObject jsonObj = new JSONObject();

		int i = 0;
		jsonObj.put("usersSize", dict.size());
		for (Map.Entry<String, User> entry : dict.entrySet()) {
			jsonObj.put("name" + i, entry.getKey());
			jsonObj.put("password" + i, entry.getValue().getPassword());
			jsonObj.put("createCapability" + i,
					entry.getValue().createCapability);
			// jsonObj.put("noneCapability" + i,
			// entry.getValue().noneCapability);
			jsonObj.put("deleteCapability" + i,
					entry.getValue().deleteCapability);
			++i;
		}
		String temp = jsonObj.toString();

		PrintWriter out = new PrintWriter("users.txt");
		out.write(temp);
		out.close();
	}

	public static void fromJson(JSONObject ob) throws JSONException {
		int fileSize = (int) ob.get("usersSize");

		for (int i = 0; i < fileSize; i++) {
			String n = (String) ob.get("name" + i);
			String p = (String) ob.get("password" + i);
			JSONArray x = (JSONArray) ob.get("createCapability" + i);
			JSONArray y = (JSONArray) ob.get("deleteCapability" + i);
			// JSONArray z = (JSONArray) ob.get("noneCapability" + i);

			User user = new User(n, p);

			for (int j = 0; j < x.length(); j++) {
				user.createCapability.add((String) x.get(j));
			}

			for (int j = 0; j < y.length(); j++) {
				user.deleteCapability.add((String) y.get(j));
			}
			// for (int j = 0; j < z.length(); j++) {
			// user.noneCapability.add((String) z.get(j));
			// }
			dict.put(n, user);
		}
	}

	public static void loadUsers() throws FileNotFoundException, JSONException {
		Scanner input = new Scanner(new File("users.txt"));
		String temp = input.next();
		input.close();

		JSONObject ob = new JSONObject(temp);

		fromJson(ob);
	}
}
