import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUP(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
