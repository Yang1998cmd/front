package LastTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class lIST {
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
    public void t6() {
        driver.get("http://121.89.166.24:8088/");
        driver.manage().window().setSize(new Dimension(1366, 728));
        driver.findElement(By.cssSelector(".el-form")).click();
        driver.findElement(By.cssSelector(".el-form-item:nth-child(2) .el-input__inner")).sendKeys("123456");
        driver.findElement(By.id("button1")).click();
        driver.findElement(By.cssSelector(".author_div:nth-child(5) > div:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".el-col:nth-child(1) .switch span")).click();
        driver.findElement(By.cssSelector(".author_div:nth-child(6) > div:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".el-button:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".el-button:nth-child(1)")).click();
        js.executeScript("window.scrollTo(0,375)");
        driver.findElement(By.cssSelector(".el-col > .el-row:nth-child(2) > div")).click();
        js.executeScript("window.scrollTo(0,374)");
        driver.findElement(By.cssSelector(".author-title:nth-child(3) > .header-img > img")).click();
        driver.findElement(By.cssSelector(".el-button > span")).click();
        driver.findElement(By.cssSelector(".el-menu-item:nth-child(2)")).click();
        driver.findElement(By.cssSelector(".el-card__body img")).click();
        driver.findElement(By.cssSelector("img")).click();
    }


}
