import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of threads: ");
        int numberOfThreads = scanner.nextInt();
        scanner.nextLine(); // consume newline

        FileDownloader downloader = new FileDownloader(numberOfThreads);

        System.out.print("Enter the URL of the file to download: ");
        String fileURL = scanner.nextLine();

        System.out.print("Enter the directory to save the file: ");
        String saveDir = scanner.nextLine();

        downloader.downloadFile(fileURL, saveDir);

        System.out.println("Downloading...");

        downloader.shutdown();
        System.out.println("Download complete.");
    }
}
