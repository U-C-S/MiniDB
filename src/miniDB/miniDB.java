package miniDB;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class miniDB {

	public static void main(String[] args) {
		System.out.println("\n" + "\n" +
			"--------------- Welcome to MiniDB ---------------\n" +
			"--------- Made by Chanakya. MIT License ---------\n" +
			"Enter the Commands: (Use 'exit;' to exit the cli)\n"
			);

		createXmlFile mainXml = new createXmlFile("./minidb.xml");
		Scanner input = new Scanner(System.in);

		new mainXmlParse(mainXml.getFile());
		while (true) {
			String currentCmd = input.nextLine();

			// break if user wants to exit
			if (Objects.equals(currentCmd, "exit;")) {
				break;
			}

			cli execute = new cli(currentCmd);

		}
	}

}
