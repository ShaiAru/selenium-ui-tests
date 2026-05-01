import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.junit.Assert.assertFalse;
import java.util.concurrent.TimeUnit;


public class LoginTest {
    WebDriver driver;

    @Before
    public void setUP(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void validLoginTest() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("inventory.html"));
    }
    @Test
    public void invalidLogin(){
        driver.findElement(By.id("user-name")).sendKeys("Aru-Shai");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        WebElement errorMessage= driver.findElement(By.cssSelector("[data-test='error']"));
        String errorText = errorMessage.getText();
        assertTrue(errorText.contains("Epic sadface"));

    }
    @Test
    public void productImageDisplayed(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        List<WebElement> images = driver.findElements(By.cssSelector("img.inventory_item_img"));
        for(WebElement image : images){
            String src = image.getAttribute("src");
            assertFalse(src.isEmpty());
        }

    }

}
