package part02;

import java.awt.Font;

import console.Console;

public class Menu {
	private String items[];
	private String title;
	private Console con;

	public Menu(String title, String data[],Console con) {
		this.title = title;
		this.items = data;
		this.con = con;
	}

	private void display() {
		con.setFont(new Font("Courier", Font.BOLD, 30));
		con.println(title);
		con.setFont(new Font("Courier", Font.PLAIN, 15));
		for (int count = 0; count < (2*title.length())+2; count++) {
			con.print("+");
		}
		
		con.println();
		for (int option = 1; option <= items.length; option++) {
			con.println(option + ". " + items[option - 1]);
		}
		con.println();
	}

	public int getUserChoice() {
		display();
		con.print("Enter Selection: ");
		int value = Integer.parseInt(con.readLn());
		con.clear();
		return value;
	}
}
