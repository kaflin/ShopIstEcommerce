package utilities.allure;

import base.BaseClass;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class AllureResultsDeleter extends BaseClass {
    public static void deleteAllureResultsDirectory(){
        try{
            FileUtils.deleteDirectory(new File("allure-results"));
            log.info("Previous Allure Result Deleted Successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
