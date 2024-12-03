package core.io;

import java.io.Console;

public class ConsoleClass {
	
	public void consoleClass() {
		
		Console console = System.console();
		if (console != null) {
		    String name = console.readLine("Enter your name: ");
		    char[] password = console.readPassword("Enter your password: ");
		    console.printf("Welcome, %s!%n", name);
		} else {
		    System.out.println("No console available.");
		}
		
	}

}
