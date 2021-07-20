package miniDB;

import java.util.Objects;
import java.util.Scanner;

public class miniDB {
	static createXmlFile mainXml = new createXmlFile(constants.ROOT_PATH + "/minidb.xml");

	public static void main(String[] args) {
		System.out.println(constants.HEADING);

		mainXml.newInstance();
		new mainXmlParse(mainXml.getFile());

		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.print(constants.CMD_PREFIX);

			String currentCmd = input.nextLine();

			// break if user wants to exit
			if (Objects.equals(currentCmd, "exit;")) {
				break;
			}

			cli(currentCmd);

		}

		input.close();
	}

	private static void cli(String input) {
		String[] inputCmds = input.split(" ");

		switch (inputCmds[0]) {
			case "newdb": {
				System.out.println(inputCmds[1]);
				mainXml.createNewDatabase(inputCmds[1]);
				break;
			}

			default: {
				System.out.println("UNKNOWN COMMAND: " + inputCmds[0]);
				break;
			}
		}
	}

}
