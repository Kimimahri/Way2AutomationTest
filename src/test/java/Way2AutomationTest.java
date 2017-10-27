import helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pages.MainPage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Way2AutomationTest {
    private WebDriver driver;

    @BeforeTest
    @Parameters({"url", "timeout"})
    public void setUp(String url, int timeout) {
        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        Helper.openBrowserWithURL(url, driver);
    }

    @org.testng.annotations.Test
    @Parameters({"email", "password"})
    private void login(String email, String password) throws IOException {
        MainPage mainPage = new MainPage(driver);

        Helper.login(driver, email, password);

        Assert.assertTrue(mainPage.isAuthorized());
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}