package VFS;

import java.util.ArrayList;

public class User {
	private String name;
	private String password;

	public  ArrayList<String> deleteCapability = new ArrayList<String>();
	public  ArrayList<String> createCapability = new ArrayList<String>();
	//public  ArrayList<String> noneCapability = new ArrayList<String>();
	
	private static User currentActiveUser;

	public User() {

	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public static void setCurrentActiveUser(User user) {
		currentActiveUser = user;
	}
	
	public static User getCurrentActiveUser() {
		return currentActiveUser;
	}
	
	public static void logout() {
		currentActiveUser = null;
	}
	
}
