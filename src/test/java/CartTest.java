
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.JavascriptExecutor;

public class CartTest extends BaseTest{

    @Test
    public void addToCartTest() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        WebElement addButton = driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-onesie']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);
        //driver.findElement(By.cssSelector("[data-test='add-to-cart-sauce-labs-onesie']")).click();
        WebElement badge1 = driver.findElement(By.cssSelector("[data-test='shopping-cart-badge']"));

        String badge1Element = badge1.getText();
        assertEquals("1",badge1Element);

    }

}
