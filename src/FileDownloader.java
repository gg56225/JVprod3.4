import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class FileDownloader {
    private final ExecutorService executor;

    public FileDownloader(int numberOfThreads) {
        this.executor = Executors.newFixedThreadPool(numberOfThreads);
    }

    public void downloadFile(String fileURL, String saveDir) {
        executor.submit(() -> {
            try (BufferedInputStream in = new BufferedInputStream(new URL(fileURL).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(saveDir)) {
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
                System.out.println("Downloaded: " + fileURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
