package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private By buttonLogin = By.xpath("//a[@href='/sign_in']");
    private By imageGravatar = By.cssSelector("img.gravatar");
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage openLogInPage() {
        driver.findElement(buttonLogin).click();
        return new LoginPage(driver);
    }

    public boolean isAuthorized() {
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(imageGravatar));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
