package miniDB;

import java.io.File;
import java.io.IOException;

public class miniDB {

	public static void main(String[] args) {
		File theDbFile;
		try {
			theDbFile = new File("./minidb.xml");
			if (theDbFile.createNewFile()) {
				System.out.println(theDbFile.getName() + " Not Found! Created new file.");
			} else {
				System.out.println("minidb.xml Found!!");
			}

			new ListDbXmlParse(theDbFile);

		} catch (IOException e) {
			System.out.println("Congratulations! You created a ERROR;;");
			e.printStackTrace();
		}

	}

}
