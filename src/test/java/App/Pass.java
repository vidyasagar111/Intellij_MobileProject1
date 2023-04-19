package App;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Pass {
    AppiumDriver driver = null;
    @Test
    public void can() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("emulator-5554")
                //       .setAutomationName("appium:UiAutomator2")
                .setChromedriverExecutable("C:\\Users\\lenovo\\Downloads\\chromedriver_win32(3)\\chromedriver.exe")
                .setNoReset(true).withBrowserName("Chrome");
        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("http://amazon.in");
        driver.findElement(By.cssSelector("#nav-search-keywords")).sendKeys("oneplus nord ce 2 lite");

        driver.findElement(By.xpath("//input[@type='submit']")).click();
        driver.findElement(By.linkText("OnePlus Nord CE 2 Lite 5G (Black Dusk, 6GB RAM, 128GB Storage)")).click();
        driver.findElement(By.id("buy-now-button")).click();
        driver.findElement(By.cssSelector("#ap_email_login")).sendKeys("7670969796");
        driver.findElement(By.xpath("//body/div[@id='a-page']/div[2]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/form[1]/div[3]/div[1]/span[1]/span[1]/input[1]")).click();
        driver.findElement(By.cssSelector("#continue")).click();
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("change-123");
        driver.findElement(By.cssSelector("#signInSubmit")).click();
        driver.findElement(By.cssSelector("#a-autoid-0-announce")).click();
        driver.findElement(By.xpath("//body/div[@id='a-page']/div[@id='checkoutDisplayPage']/div[@id='a-page']/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/form[1]/div[1]/div[2]/div[5]/div[5]/div[1]/div[1]/div[1]/div[1]/span[1]/div[1]/label[1]/i[1]")).click();
        driver.findElement(By.xpath("//body/div[@id='a-page']/div[@id='checkoutDisplayPage']/div[@id='a-page']/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/form[1]/div[1]/div[2]/div[5]/div[5]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/span[1]/span[1]/span[1]/span[1]")).click();
        driver.findElement(By.linkText("Add a new card")).click();
        driver.findElement(By.linkText("Enter card details")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Enter card details"))).click();
     //   wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div/div[2]/div/div/div/form/div[5]/div[3]/div/input"))).sendKeys("5241 8100 0000 0000");

        Select month = new Select(driver.findElement(By.name("ppw-expirationDate_month")));
        month.selectByValue("08");
        Select year = new Select(driver.findElement(By.name("ppw-expirationDate_year")));
        year.selectByValue("2026");
        driver.findElement(By.name("ppw-widgetEvent:AddCreditCardEvent")).click();
    }
}
