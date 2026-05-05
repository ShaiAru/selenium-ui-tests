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

        //wait for page to load then add onesie to cart
        addOnesieToCart();
        //verify badge shows 1
        WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='shopping-cart-badge']")));
        assertEquals("1", badge.getText());

        // Click cart and verify URL
        driver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
        assertTrue(driver.getCurrentUrl().contains("cart.html"));

    }

    @Test
    public void checkoutFormTest() {
        addOnesieToCart();

        WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='shopping-cart-badge']")));
        assertEquals("1", badge.getText());
        driver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
        assertTrue(driver.getCurrentUrl().contains("cart.html"));

        driver.findElement(By.cssSelector("[data-test='checkout']")).click();
        assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"));
        driver.findElement(By.id("first-name")).sendKeys("Aru");
        driver.findElement(By.id("last-name")).sendKeys("Shai");
        driver.findElement(By.id("postal-code")).sendKeys("60622");
        driver.findElement(By.id("continue")).click();
        assertTrue(driver.getCurrentUrl().contains("checkout-step-two.html"));

    }

    @Test
    public void orderCompleteTest(){
        addOnesieToCart();
        WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='shopping-cart-badge']")));
        assertEquals("1", badge.getText());
        driver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
        assertTrue(driver.getCurrentUrl().contains("cart.html"));

        driver.findElement(By.cssSelector("[data-test='checkout']")).click();
        assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"));
        driver.findElement(By.id("first-name")).sendKeys("Aru");
        driver.findElement(By.id("last-name")).sendKeys("Shai");
        driver.findElement(By.id("postal-code")).sendKeys("60622");
        driver.findElement(By.id("continue")).click();
        assertTrue(driver.getCurrentUrl().contains("checkout-step-two.html"));
        driver.findElement(By.id("finish")).click();
        assertTrue(driver.getCurrentUrl().contains("checkout-complete.html"));


    }

    //this is helper method
    private void addOnesieToCart(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='add-to-cart-sauce-labs-onesie']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(addButton).perform();
        addButton.click();

    }
    
}