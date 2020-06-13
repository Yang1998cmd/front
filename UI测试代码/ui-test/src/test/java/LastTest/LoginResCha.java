package LastTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class LoginResCha {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();

    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void t1() throws InterruptedException {
        driver.get("http://121.89.166.24:8088/");
        Thread.sleep(1000);
        driver.manage().window().setSize(new Dimension(1366, 728));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#button2 > span")).click();
        Thread.sleep(1000);
        String current_url = driver.getCurrentUrl();
        driver.navigate().to(current_url);
        driver.findElement(By.cssSelector("#button2 > span")).click();
        Thread.sleep(1000);
        current_url = driver.getCurrentUrl();//选择图片
        driver.navigate().to(current_url);
        driver.findElement(By.cssSelector("#button3 > span")).click();
        Thread.sleep(1000);
        current_url = driver.getCurrentUrl();//选择图片
        driver.navigate().to(current_url);
//        {
//            Thread.sleep(1000);
//            current_url = driver.getCurrentUrl();//选择图片
//            driver.navigate().to(current_url);
//            WebElement element = driver.findElement(By.cssSelector("#button3 > span"));
//            Thread.sleep(1000);
//            current_url = driver.getCurrentUrl();//选择图片
//            driver.navigate().to(current_url);
//            Actions builder = new Actions(driver);
//            Thread.sleep(1000);
//            builder.moveToElement(element).perform();
//            Thread.sleep(1000);
//        }
        driver.findElement(By.cssSelector("#button2 > span")).click();
        current_url = driver.getCurrentUrl();//选择图片
        driver.navigate().to(current_url);
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".el-form-item:nth-child(2)")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".el-form-item:nth-child(2) .el-input__inner")).sendKeys("123456");
        Thread.sleep(1000);
        driver.findElement(By.id("button1")).click();
    }
}
