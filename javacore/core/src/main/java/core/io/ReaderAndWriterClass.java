package core.io;

import java.io.File;
import java.io.IOException;

public class ReaderAndWriterClass {
	
	public void readerAndWriter() throws IOException {
		
		File parent = new File("D:\\Ashutosh\\java-projects\\test\\");
		
		File authFile = new File(parent, "auth.log.txt");
		
		File syslogFile = new File(parent, "syslog.txt");
		
		boolean exists1 = authFile.exists();
		System.out.println("exist authFile: "+ exists1);
		
		boolean exists2 = syslogFile.exists();
		System.out.println("exist syslogFile: "+ exists2);
		
		
		File createFile = new File(parent, "new.txt");
		createFile.createNewFile();
		
		File getFile = new File(parent, "new.txt");
		System.out.println("getfile: "+ getFile);
		
		boolean deletefile = getFile.delete();
		if(deletefile) {
			System.out.println("File deleted Successfully.");
		} else {
			System.out.println("File do not exists");
		}
		
		
	}

}
