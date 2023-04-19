package App;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.time.Duration;
import java.util.*;

import static utils.ConfigReader.*;
import static utils.XLUtils.getNumericCellValue;
import static utils.XLUtils.getStringCellData;

public class browser{
    AppiumDriver driver = null;
    public static ExtentReports extent = new ExtentReports();
    public static ExtentSparkReporter spark = new ExtentSparkReporter("Spark.html");

    public browser() throws IOException {
    }

    @BeforeTest
    public void nano() throws IOException {
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Report");
        extent.attachReporter(spark);
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("emulator-5554")
                //       .setAutomationName("appium:UiAutomator2")
                .setChromedriverExecutable("C:\\Users\\lenovo\\Downloads\\chromedriver_win32(3)\\chromedriver.exe")
                .setNoReset(true).withBrowserName("Chrome");
        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(getUrl());

    }

    @Test
    public void testCase() throws IOException {
        ExtentTest test = extent.createTest("Mobile test")
                    .log(Status.PASS,"test pass").addScreenCaptureFromBase64String(takeScreenShot(driver));
                    test.info("iam open Amazon Site")
                    .assignAuthor("Vidyasagar")
                    .assignCategory("test case")
                    .assignCategory("Windows");

        driver.findElement(By.cssSelector("#nav-search-keywords")).sendKeys(getProduct());
        test.addScreenCaptureFromPath(takeScreenShot(driver));

        driver.findElement(By.xpath("//input[@type='submit']")).click();
        test.addScreenCaptureFromBase64String(takeScreenShot(driver));

        findElement(By.linkText(getProductname())).click();
        test.addScreenCaptureFromPath(takeScreenShot(driver));
            
        findElement(By.cssSelector("#buy-now-button")).click();
        test.addScreenCaptureFromPath(takeScreenShot(driver));
            
        findElement(By.cssSelector("#ap_email_login")).sendKeys(getUsername());
        test.addScreenCaptureFromBase64String(takeScreenShot(driver));

        findElement(By.xpath("//body/div[@id='a-page']/div[2]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/form[1]/div[3]/div[1]/span[1]/span[1]/input[1]")).click();
        test.addScreenCaptureFromBase64String(takeScreenShot(driver));

        findElement(By.cssSelector("#continue")).click();
        test.addScreenCaptureFromBase64String(takeScreenShot(driver));

        findElement(By.xpath("//input[@type='password']")).sendKeys(getPassword());
        test.addScreenCaptureFromBase64String(takeScreenShot(driver));

        findElement(By.cssSelector("#signInSubmit")).click();
        test.addScreenCaptureFromBase64String(takeScreenShot(driver));

        findElement(By.cssSelector("#a-autoid-0-announce")).click();
        test.addScreenCaptureFromBase64String(takeScreenShot(driver));

        driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/form[1]/div[1]/div[2]/div[4]/div[2]/div[1]/div[1]/div[1]/span[1]/div[1]/label[1]/span[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/input[1]")).sendKeys(getCvv());
        test.addScreenCaptureFromPath(takeScreenShot(driver));

        driver.findElement(By.name("ppw-widgetEvent:SetPaymentPlanSelectContinueEvent")).click();
        test.addScreenCaptureFromPath(takeScreenShot(driver));

        driver.findElement(By.name("placeYourOrder1")).click();
        test.addScreenCaptureFromPath(takeScreenShot(driver));


     //   findElement(By.xpath("//body/div[@id='a-page']/div[@id='checkoutDisplayPage']/div[@id='a-page']/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/form[1]/div[1]/div[2]/div[5]/div[5]/div[1]/div[1]/div[1]/div[1]/span[1]/div[1]/label[1]/i[1]")).click();
     //   test.addScreenCaptureFromBase64String(takeScreenShot(driver));

     //   findElement(By.xpath("//span[contains(text(),'Select EMI options')]")).click();
     //   test.addScreenCaptureFromBase64String(takeScreenShot(driver));

     //   findElement(By.linkText("Add a new card")).click();
     //   test .addScreenCaptureFromBase64String(takeScreenShot(driver));

     //   findElement(By.xpath("//body/div[@id='a-page']/div[@id='checkoutDisplayPage']/div[@id='a-page']/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/form[1]/div[1]/div[2]/div[5]/div[5]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/span[1]/div[1]/a[1]/span[1]")).click();
     //   test.addScreenCaptureFromBase64String(takeScreenShot(driver));



     /*   driver.switchTo().frame(0);
        WebElement myElement = findElement(By.xpath("//*[@type='tel']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].value='5425233430109903'",myElement);
     //   ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid blue'", myElement);

        Select month = new Select(driver.findElement(By.name("ppw-expirationDate_month")));
        month.selectByVisibleText("04");
        
        Select year = new Select(driver.findElement(By.name("ppw-expirationDate_year")));
        year.selectByVisibleText("2026");

        WebElement enterBtn = findElement(By.className("a-button-input"));
        String javascript = "arguments[0].click()";
        ((JavascriptExecutor)driver).executeScript(javascript,enterBtn);   */

    }

    public static String takeScreenShot(AppiumDriver driver) throws IOException {
        String Base64StringOfScreenShot = "";
        File srcfile  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        BufferedImage img = ImageIO.read(srcfile);
        ImageIO.write(img,".png",srcfile);
        String path = System.getProperty("user.dir") + "imagine/" + ".png";
        File dscfile = new File(path);
        FileUtils.copyFile(srcfile,dscfile);

        byte[] fileContent = FileUtils.readFileToByteArray(srcfile);
        Base64StringOfScreenShot = "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);
        return Base64StringOfScreenShot;
    }

    public static byte[] getByteScreenshot(AppiumDriver driver) throws IOException {
        File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        byte[] fileContent = FileUtils.readFileToByteArray(srcfile);
        return fileContent;
    }

    public WebElement findElement(By by)
    {
        WebElement element = driver.findElement(by);
        if(driver instanceof JavascriptExecutor)
        {
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'",element);
        }
        return element;
    }

 //   public String getBase64(AppiumDriver driver)
 //   {
 //       return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
 //   }

 /*   public static String man() throws IOException {
        String data = "C:\\Users\\lenovo\\IdeaProjects\\Mobile\\src\\test\\java\\Sheet.xlsx";
        String sheet = "Sheet1";

        int rowcount = XLUtils.getrowCount(data,sheet);
        String username;
        String password;
        for (int i=1;i<=rowcount;i++)
        {
            username = String.valueOf(XLUtils.getNumericCellValue(data,sheet,i,0));
            password = XLUtils.getStringCellData(data,sheet,i,1);
        }
        return man();
    }  */
    @AfterTest
    public void teardown()
    {
        driver.close();
        extent.flush();
    }


}
