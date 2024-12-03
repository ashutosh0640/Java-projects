package core.monitor;


import java.io.*;
import java.nio.file.*;
import java.util.concurrent.Executors;

public class LiveLogMonitor {

    public static void liveLogMonitor() throws IOException {
        // Directories
        String readDirectory = "D:\\Ashutosh\\java-projects\\test\\read"; // Change to your read directory
        String writeDirectory = "D:\\Ashutosh\\java-projects\\test\\write"; // Change to your write directory
        String readFileName = "auth.log.txt"; // File to monitor
        String writeFileName = "authlog.txt"; // File to write data

        File readFile = new File(readDirectory, readFileName);
        File writeFile = new File(writeDirectory, writeFileName);
        System.out.println("readFile: "+readFile);
        System.out.println("writeFile: "+writeFile);
        

        if (!readFile.exists()) {
            System.err.println("The read file does not exist: " + readFile.getAbsolutePath());
            return;
        }

        // Ensure the write file exists
        if (!writeFile.exists()) {
            writeFile.getParentFile().mkdirs(); // Create parent directories if needed
            writeFile.createNewFile();
        }

        // Start the monitoring service
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                monitorFile(readFile, writeFile);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Monitoring started...");
    }

    private static void monitorFile(File readFile, File writeFile) throws IOException, InterruptedException {
        Path readDirPath = readFile.getParentFile().toPath();

        // Use WatchService to monitor directory for changes
        WatchService watchService = FileSystems.getDefault().newWatchService();
        readDirPath.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        // Track last read position in the file
        long lastReadPosition = 0;

        while (true) {
            WatchKey key = watchService.take(); // Block until a file change is detected
            for (WatchEvent<?> event : key.pollEvents()) {
                Path changedFile = (Path) event.context();
                if (changedFile.getFileName().toString().equals(readFile.getName())) {
                    lastReadPosition = processNewLogs(readFile, writeFile, lastReadPosition);
                }
            }
            key.reset(); // Reset the watch key
        }
    }

    private static long processNewLogs(File readFile, File writeFile, long lastReadPosition) throws IOException {
        try (RandomAccessFile reader = new RandomAccessFile(readFile, "r");
             BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile, true))) {

            // Seek to the last known position
            reader.seek(lastReadPosition);

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            // Return the new file pointer position
            return reader.getFilePointer();
        }
    }
}

