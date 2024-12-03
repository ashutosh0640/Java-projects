package core;

import java.io.IOException;

import core.io.ReaderAndWriterClass;
import core.monitor.LiveLogMonitor;

public class Main {

	public static void main(String[] args) throws IOException {
		
//		ReaderAndWriterClass readwrite = new ReaderAndWriterClass();
//		readwrite.readerAndWriter();
		LiveLogMonitor.liveLogMonitor();
	}
}
