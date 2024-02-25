package utilities.log;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class LogDeleter {
    public static void deleteDirectory() {
        try {
            FileUtils.deleteDirectory(new File("log"));
            System.out.println("Previous log deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
