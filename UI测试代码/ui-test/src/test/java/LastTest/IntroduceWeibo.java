package LastTest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class IntroduceWeibo {
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
    public void t5() {
        driver.get("http://121.89.166.24:8088/");
        driver.manage().window().setSize(new Dimension(1366, 728));
        driver.findElement(By.cssSelector(".el-form")).click();
        driver.findElement(By.cssSelector(".el-form-item:nth-child(2) .el-input__inner")).sendKeys("123456");
        driver.findElement(By.cssSelector("#button1 > span")).click();
        driver.findElement(By.cssSelector(".el-input--suffix > .el-input__inner")).click();
        driver.findElement(By.cssSelector(".el-input--suffix > .el-input__inner")).sendKeys("LOL");
        driver.findElement(By.cssSelector(".el-icon-search")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".el-button"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector(".author-title:nth-child(4) .el-icon-thumb")).click();
        js.executeScript("window.scrollTo(0,200)");
        js.executeScript("window.scrollTo(0,200)");
    }
}
