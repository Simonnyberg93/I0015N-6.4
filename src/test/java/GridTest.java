import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class GridTest {

    public static URL url;
    public RemoteWebDriver driver;

    @BeforeAll
    public static void setUp(){
        try {
            url = new URL("http://3.238.176.22:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private DesiredCapabilities setupCapabilitys(String browser){
        return switch (browser) {
            case "firefox" -> DesiredCapabilities.firefox();
            case "chrome" -> DesiredCapabilities.chrome();
            case "edge" -> DesiredCapabilities.edge();
            case "opera" -> DesiredCapabilities.opera();
            default -> throw new IllegalStateException("Unexpected value: " + browser);
        };
    }

    @DisplayName("Google search.")
    @ParameterizedTest(name = "Testing script 1 with {0}")
    @CsvSource({"firefox","chrome","opera","edge"})
    public void script1(String browser){
        DesiredCapabilities cap = setupCapabilitys(browser);
        cap.setPlatform(Platform.LINUX);
        driver = new RemoteWebDriver(url, cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1900, 1000));
        driver.get("https://www.google.com");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       // driver.findElement(By.id("L2AGLb")).click();
        driver.findElement(By.name("q")).sendKeys("Software Testing");
        driver.findElement(By.name("q")).submit();
        driver.quit();
    }

    @DisplayName("Actitime create user.")
    @ParameterizedTest(name = "Testing script 2 with {0}")
    @CsvSource({"firefox","chrome","opera","edge"})
    public void script2(String browser){
        DesiredCapabilities cap = setupCapabilitys(browser);
        cap.setPlatform(Platform.LINUX);
        driver = new RemoteWebDriver(url, cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1900, 1000));
        driver.get("https://www.actitime.com/");

        driver.findElement(By.linkText("Try Free")).click();
        // explicit wait - to wait for the compose button to be click-able
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("email")).sendKeys("JohnDoe@hotmail.com");
        driver.findElement(By.id("company")).sendKeys("Test av IT AB");
        driver.quit();
    }

    @DisplayName("Order book on Adlibris.")
    @ParameterizedTest(name = "Testing script 3 with {0}")
    @CsvSource({"firefox","chrome","opera","edge"})
    public void script3(String browser){
        DesiredCapabilities cap = setupCapabilitys(browser);
        cap.setPlatform(Platform.LINUX);
        driver = new RemoteWebDriver(url, cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1900, 1000));


        driver.get("https://Adlibris.Com/se/");
        driver.findElement(By.id("q")).sendKeys("praktisk mjukvarutestning");
        driver.findElement(By.id("q")).submit();
        driver.findElement(By.linkText("Praktisk mjukvarutestning")).click();
        driver.findElement(By.cssSelector(".product__add-to-cart:nth-child(1)")).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Till kassan")));
        driver.findElement(By.cssSelector(".notifications-bar__added-to-cart__to-checkout")).click();

    }
}
