package miniDB;

import java.util.Objects;
import java.util.Scanner;

/*
TODO
- Comment the code

*/

public class miniDB {
	static miniDB_data DataFile = new miniDB_data(constants.DATA_XML_PATH);

	public static void main(String[] args) {
		System.out.println(constants.HEADING);

		DataFile.load();
		new database(DataFile.getFile());

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
			case "new": {
				System.out.println(inputCmds[1]);
				DataFile.createNewDatabase(inputCmds[1]);
				break;
			}
			case "use": {

				break;
			}

			case "list": {

				break;
			}

			case "info": {

				break;
			}

			case "schema/update/delete/read": {

				break;
			}

			default: {
				System.out.println("UNKNOWN COMMAND: " + inputCmds[0]);
				break;
			}
		}
	}

}


// update where id=2 name=cow 
