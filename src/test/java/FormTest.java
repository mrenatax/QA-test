import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class FormTest {
    public static FormPage formPage;
    public static WebDriver driver;
    public static LoginPage loginPage;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        formPage = new FormPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Path sampleFile = Paths.get(ConfProperties.getProperty("loginpage"));
        driver.get(sampleFile.toUri().toString());

        loginPage = new LoginPage(driver);
        loginPage.inputEmail(ConfProperties.getProperty("email"));
        loginPage.clickLoginButton();
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        loginPage.clickLoginButton();
    }

    /**
     * Тест с валидными данными
     */
    @Test
    public void formTestPositive() {
        formPage.inputEmail(ConfProperties.getProperty("email"));
        formPage.inputName(ConfProperties.getProperty("name"));

        //...

        formPage.clickAddButton();
        formPage.clickOkButton();
    }

    /**
     * Тест с некорректным форматом email
     */
    @Test
    public void formTestNegative1() {
        formPage.inputEmail(ConfProperties.getProperty("incorrectEmailFormat"));
        formPage.inputName(ConfProperties.getProperty("name"));
        //...
        formPage.clickAddButton();
        Assert.assertEquals(formPage.getErrorMessage(), "Неверный формат E-Mail");
    }

    /**
     * Тест с неотмеченными чекбоксами и радикнопкой
     */
    @Test
    public void formTestNegative2() {
        formPage.inputEmail(ConfProperties.getProperty("email"));
        formPage.inputName(ConfProperties.getProperty("name"));
        formPage.clickAddButton();
        formPage.clickOkButton();
    }

    @AfterClass
    public static void tearDown() {
        //driver.quit();
    }
}
