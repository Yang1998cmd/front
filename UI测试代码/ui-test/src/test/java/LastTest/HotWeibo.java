package LastTest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertTrue;



/**
 * Unit test for simple App.
 */
public class HotWeibo
{
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
    public void t4() {
        driver.get("http://121.89.166.24:8088/");
        driver.manage().window().setSize(new Dimension(1366, 728));
        driver.findElement(By.cssSelector(".el-form")).click();
        driver.findElement(By.cssSelector(".el-form-item:nth-child(2) .el-input__inner")).sendKeys("123456");
        driver.findElement(By.id("button1")).click();
        driver.findElement(By.cssSelector(".el-menu-item:nth-child(4)")).click();
        js.executeScript("window.scrollTo(0,200)");
        driver.findElement(By.cssSelector(".el-card:nth-child(5) span:nth-child(1)")).click();
        js.executeScript("window.scrollTo(0,300)");
        js.executeScript("window.scrollTo(0,0)");
        js.executeScript("window.scrollTo(0,375)");
        js.executeScript("window.scrollTo(0,175)");
    }



}
