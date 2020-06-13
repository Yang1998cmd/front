package LastTest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class Home {
    private WebDriver driver;
    JavascriptExecutor js;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver_win32/chromedriver.exe");
        js = (JavascriptExecutor) driver;
        driver = new ChromeDriver();

    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void t2() throws InterruptedException {
        driver.get("http://121.89.166.24:8088/home");
        driver.manage().window().setSize(new Dimension(1366, 728));
        Thread.sleep(1000);
        driver.findElement(By.id("replyInput")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("replyInput")).sendKeys("刘意最帅");
        Thread.sleep(1000);
//        driver.findElement(By.cssSelector(".el-upload")).click();
//        Thread.sleep(1000);
        js.executeScript("window.scrollTo(0,200)");
        Thread.sleep(1000);
        driver.findElement(By.name("file")).sendKeys("photoes/test3.jpg");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".reply-btn-box:nth-child(5) span")).click();
        {
            Thread.sleep(1000);
            WebElement element = driver.findElement(By.cssSelector(".reply-btn-box:nth-child(5) span"));
            Thread.sleep(1000);
            Actions builder = new Actions(driver);
            Thread.sleep(1000);
            builder.moveToElement(element).perform();
            Thread.sleep(1000);
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Thread.sleep(1000);
            Actions builder = new Actions(driver);
            Thread.sleep(1000);
            builder.moveToElement(element, 0, 0).perform();
        }
    }


}
