package helpers;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import java.io.*;
import java.util.Set;


public class Helper {
    public static void writeCookiesInFile(WebDriver driver, String email) throws IOException {
        Set<Cookie> cookies = driver.manage().getCookies();
        File file = new File("cookies/" + email + "-cookies.txt");
        if (file.exists()) {
            file.delete();
        }
        for (Cookie loadedCookie : cookies) {
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                bufferedWriter.write(String.format("%s-->%s", loadedCookie.getName(), loadedCookie.getValue()) + "\n");
                bufferedWriter.close();
            }
        }
    }

    public static void readCookiesFromFile(WebDriver driver, String email) throws IOException {
        File file = new File("cookies/" + email + "-cookies.txt");
        FileReader fileReader = new FileReader(file);
        try(BufferedReader bufferReader = new BufferedReader(fileReader)){
            String line = bufferReader.readLine();
            while (line != null) {
                String[] parts = line.split("-->", 20);
                Cookie cookie = new Cookie(parts[0], parts[1]);
                driver.manage().addCookie(cookie);
                line = bufferReader.readLine();
            }
        }
    }

    public static void openBrowserWithURL(String url, WebDriver driver) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public static void login(WebDriver driver, String email, String password) throws IOException {
        File cookiesFile = new File("cookies/" + email + "-cookies.txt");
        if (!cookiesFile.exists()) {
            MainPage mainPage = new MainPage(driver);
            LoginPage loginPage =  mainPage.openLogInPage();
            loginPage.login(email, password);
            writeCookiesInFile(driver, email);
            } else {
            readCookiesFromFile(driver, email);
            driver.navigate().refresh();
        }
    }
}
