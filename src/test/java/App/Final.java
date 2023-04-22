package App;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Base64;
import java.util.UUID;

import static utils.XLUtils.getNumericCellValue;
import static utils.XLUtils.getStringCellData;

public class Final {
    AppiumDriver driver = null;
    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("Spark1.html");
    String url = getStringCellData("Sheet.xlsx","Sheet2",2,0);
    String product = getStringCellData("Sheet.xlsx","Sheet2",2,1);
    String productname = getStringCellData("Sheet.xlsx","Sheet2",2,2);
    String username = getStringCellData("Sheet.xlsx","Sheet2",2,3);
    String password = getStringCellData("Sheet.xlsx","Sheet2",2,4);
    String cvv = getStringCellData("Sheet.xlsx","Sheet2",2,5);

    public Final() throws IOException {
    }

    @BeforeTest
    public void open() throws MalformedURLException {
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
        driver.get(url);
    }

    @Test
    public void case_01() throws IOException {
        ExtentTest test = extent.createTest("Appium testing");
                test.addScreenCaptureFromPath(takeScreenShot(driver))
                .assignAuthor("vidyasagar")
                .assignCategory("Functional testing")
                .assignDevice("Windows");

        findElement(By.cssSelector("#nav-search-keywords")).sendKeys(product);
        test.addScreenCaptureFromPath(takeScreenShot(driver));

        findElement(By.xpath("//input[@type='submit']")).click();
        test.addScreenCaptureFromPath(takeScreenShot(driver));

        findElement(By.linkText(productname)).click();
        test.addScreenCaptureFromPath(takeScreenShot(driver));

        findElement(By.id("buy-now-button")).click();
        test.addScreenCaptureFromPath(takeScreenShot(driver));

        findElement(By.cssSelector("#ap_email_login")).sendKeys(username);
        test.addScreenCaptureFromPath(takeScreenShot(driver));

        findElement(By.xpath("//body/div[@id='a-page']/div[2]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/form[1]/div[3]/div[1]/span[1]/span[1]/input[1]")).click();
        test.addScreenCaptureFromPath(takeScreenShot(driver));

        findElement(By.cssSelector("#continue")).click();
        test.addScreenCaptureFromPath(takeScreenShot(driver));

        findElement(By.cssSelector("#ap_password")).sendKeys(password);
        test.addScreenCaptureFromPath(takeScreenShot(driver));

        findElement(By.cssSelector("#signInSubmit")).click();
        test.addScreenCaptureFromPath(takeScreenShot(driver));

        findElement(By.cssSelector("#a-autoid-0-announce")).click();
        test.addScreenCaptureFromPath(takeScreenShot(driver));

        findElement(By.xpath("//input[@type='tel']")).sendKeys(cvv);
        test.addScreenCaptureFromPath(takeScreenShot(driver));

        findElement(By.xpath("//input[@type='submit']")).click();
        test.addScreenCaptureFromPath(takeScreenShot(driver));

        findElement(By.name("placeYourOrder1")).click();
        test.addScreenCaptureFromPath(takeScreenShot(driver));

     //   driver.findElement(By.name("forcePlaceOrder")).click();

     //   driver.findElement(By.xpath("//input[name='addCreditCardVerificationNumber0']")).sendKeys("738");

     //   driver.findElement(By.xpath("//body/div[@id='a-page']/div[@id='revisePaymentsPageContainer']/div[@id='portalWidgetSection']/div[@id='cpefront-mpo-widget']/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/span[1]/span[1]/input[1]")).click();

        try {
            FileInputStream fis = new FileInputStream("Sheet.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            Sheet ws = wb.getSheet("Sheet2");
            Row row = ws.getRow(1);

            Cell c1 = row.getCell(0);
            Cell c2 = row.getCell(1);
            Cell c3 = row.getCell(2);
            Cell c4 = row.getCell(3);
            Cell c5 = row.getCell(4);
            Cell c6 = row.getCell(5);

            url = c1.getStringCellValue();
            product = c2.getStringCellValue();
            productname = c3.getStringCellValue();
            username = c4.getStringCellValue();
            password = c5.getStringCellValue();
            cvv = c6.getStringCellValue();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
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

    public WebElement findElement(By by)
    {
        WebElement element = driver.findElement(by);
        if(driver instanceof JavascriptExecutor)
        {
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'",element);
        }
        return element;
    }

    @AfterTest
    public void end()
    {

        extent.flush();
    }
}
