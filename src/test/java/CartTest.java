import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.time.Duration;

public class CartTest extends BaseTest {

    @Test
    public void addToCartTest() {

        //login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        //driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //wait for page to load then add onesie to cart
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='add-to-cart-sauce-labs-onesie']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(addButton).perform();
        addButton.click();

        //verify badge shows 1
        WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='shopping-cart-badge']")));
        assertEquals("1", badge.getText());

        // Click cart and verify URL
        driver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
        assertTrue(driver.getCurrentUrl().contains("cart.html"));



    }

}