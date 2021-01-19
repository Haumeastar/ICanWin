import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class ICanWinTest {

    @Test
    public void CreateNewPaste () {

        WebDriver driver = new ChromeDriver();
        driver.get("https://pastebin.com");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("postform-text")));

        WebElement newPaste = waitForElementLocatedBy(driver, By.id("postform-text"));
        newPaste.sendKeys ("Hello from WebDriver");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("select2-postform-expiration-container")));
        WebElement pasteExpiration = driver.findElement(By.id("select2-postform-expiration-container"));
        pasteExpiration.click();
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/span[2]/span/span[2]/ul/li[3]")));
        WebElement pasteExpiration10Min = driver.findElement(By.xpath("/html/body/span[2]/span/span[2]/ul/li[3]"));
        pasteExpiration10Min.click();



        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("postform-name")));

        WebElement pasteNameTitle = waitForElementLocatedBy(driver, By.id("postform-name"));
        pasteNameTitle.sendKeys ("helloweb");



        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(.,'Create New Paste')]")));

        WebElement createNewPasteBtn = waitForElementLocatedBy(driver, By.xpath("//button[contains(.,'Create New Paste')]"));
        createNewPasteBtn.click ();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='notice -success -post-view']")));
        List<WebElement> newPasteCreated = driver.findElements(By.xpath("//div[@class='notice -success -post-view']"));

        Assert.assertTrue(newPasteCreated.size()>0, "New Paste Was Not Created");
        driver.quit();

    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}

