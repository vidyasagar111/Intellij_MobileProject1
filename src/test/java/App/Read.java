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
import utils.ExcelReader;
import utils.XLUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Base64;

public class Read extends XLUtils{
         public static AppiumDriver driver = null;
         public static ExtentReports extent = new ExtentReports();
        public static ExtentSparkReporter spark = new ExtentSparkReporter("Spark.html");
    String username = String.valueOf(getNumericCellValue("C:\\Users\\lenovo\\Documents\\Sheet.xlsx","Sheet1",1,1));
    String password = getStringCellData("C:\\Users\\lenovo\\Documents\\Sheet.xlsx","Sheet1",1,1);

    public Read() throws IOException {
    }
    @BeforeTest
        public static void Run() throws MalformedURLException {
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("Report");
            extent.attachReporter(spark);
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setDeviceName("emulator-5554")
                    //       .setAutomationName("appium:UiAutomator2")
                    .setChromedriverExecutable("C:\\Users\\lenovo\\Downloads\\chromedriver_win32(3)\\chromedriver.exe")
                    .setNoReset(true).withBrowserName("Chrome");
            driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.get("http://amazon.in");
        }

        @AfterTest
        public static void close()
        {
            extent.flush();
        }

    @Test
    public void Ran() throws InterruptedException, IOException {
        ExtentTest test = extent.createTest("Sample");
        test.info("Open Amazon Site");

        findElement(By.cssSelector("#nav-search-keywords")).sendKeys("oneplus nord ce 2 lite");

        findElement(By.xpath("//input[@type='submit']")).click();

        findElement(By.linkText("OnePlus Nord CE 2 Lite 5G (Black Dusk, 6GB RAM, 128GB Storage)")).click();
        findElement(By.cssSelector("#buy-now-button")).click();
        findElement(By.cssSelector("#ap_email_login")).sendKeys(username);
        findElement(By.xpath("//body/div[@id='a-page']/div[2]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/form[1]/div[3]/div[1]/span[1]/span[1]/input[1]")).click();
        findElement(By.cssSelector("#continue")).click();
        findElement(By.xpath("//input[@type='password']")).sendKeys(password);

        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\lenovo\\Documents\\Sheet.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            Sheet ws = wb.getSheet("Sheet1");
            Row row = ws.getRow(1);
            Cell c1 = row.getCell(0);
            Cell c2 = row.getCell(1);

            username = String.valueOf(c1.getNumericCellValue());
            password = c2.getStringCellValue();

         //   Username	Password	CVV	Result
         //   7670969796	change-123	954


        }catch (Exception e)
        {
            e.printStackTrace();
        }
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


}
