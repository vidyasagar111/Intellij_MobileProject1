package utils;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;


public class Screenshot {
    public static String fullscreenScreenshot(AppiumDriver driver)
    {
        File screenshotLocation = null;
        try {
            File scrfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String path = "photo/" + UUID.randomUUID() + System.currentTimeMillis()+  ".png";
            screenshotLocation = new File(System.getProperty("user.dir") + "/" + path);
            FileUtils.copyFile(scrfile,screenshotLocation);
         /*   InputStream is = new FileInputStream(path);
            byte[] ssBytes = IOUtils.toByteArray(is);
            String base64 = Base64.getEncoder().encodeToString(ssBytes); */


            System.out.println(screenshotLocation.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
        return screenshotLocation.toString();
    }
}
