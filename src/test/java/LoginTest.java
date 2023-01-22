import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    public static LoginPage loginPage;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Path sampleFile = Paths.get(ConfProperties.getProperty("loginpage"));
        driver.get(sampleFile.toUri().toString());
    }

    /**
     * Тест с неверным email и паролем
     */
    @Test
    public void loginTestNegative() {
        loginPage.inputEmail(ConfProperties.getProperty("incorrectLoginEmail"));
        loginPage.clickLoginButton();
        loginPage.inputPasswd(ConfProperties.getProperty("incorrectPassword"));
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessage(), "Неверный E-Mail или пароль");
    }

    /**
     * Тест с валидными данными
     */
    @Test
    public void loginTestPositive() {
        loginPage.inputEmail(ConfProperties.getProperty("email"));
        loginPage.clickLoginButton();
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        loginPage.clickLoginButton();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}