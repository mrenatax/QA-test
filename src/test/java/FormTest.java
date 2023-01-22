import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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
     * Тест с неотмеченными чекбоксами и радиокнопкой
     */
    @Test
    public void formTestNegative2() {
        formPage.clearFields();

        formPage.inputEmail(ConfProperties.getProperty("email"));
        formPage.inputName(ConfProperties.getProperty("name"));
        Select gender = new Select(driver.findElement(By.id("dataGender")));
        gender.selectByVisibleText("Мужской");

        int tableSizeBefore = formPage.getTableSize();

        formPage.clickAddButton();
        formPage.clickOkButton();

        int tableSizeAfter = formPage.getTableSize();

        String i = "[" + formPage.getTableSize() + "]";

        Assert.assertEquals(driver.findElement(By.xpath("(//table[@id = 'dataTable']//tbody/tr/td[1])" + i)).getText(), ConfProperties.getProperty("email"));
        Assert.assertEquals(driver.findElement(By.xpath("(//table[@id = 'dataTable']//tbody/tr/td[2])" + i)).getText(), ConfProperties.getProperty("name"));
        Assert.assertEquals(driver.findElement(By.xpath("(//table[@id = 'dataTable']//tbody/tr/td[3])" + i)).getText(), "Мужской");
        Assert.assertEquals(driver.findElement(By.xpath("(//table[@id = 'dataTable']//tbody/tr/td[4])" + i)).getText(), "Нет");
        Assert.assertEquals(driver.findElement(By.xpath("(//table[@id = 'dataTable']//tbody/tr/td[5])" + i)).getText(), "");
        Assert.assertEquals(tableSizeAfter, tableSizeBefore + 1);

    }

    /**
     * Тест с валидными данными
     */
    @Test
    public void formTestPositive1() {
        formPage.clearFields();

        formPage.inputEmail(ConfProperties.getProperty("email"));
        formPage.inputName(ConfProperties.getProperty("name"));

        Select gender = new Select(driver.findElement(By.id("dataGender")));
        gender.selectByVisibleText("Женский");

        formPage.checkCheckbox1();
        formPage.checkCheckbox2();

        formPage.checkRadioButton1();
        formPage.checkRadioButton2();
        formPage.checkRadioButton3();

        int tableSizeBefore = formPage.getTableSize();

        formPage.clickAddButton();
        formPage.clickOkButton();

        int tableSizeAfter = formPage.getTableSize();

        String i = "[" + formPage.getTableSize() + "]";

        Assert.assertEquals(driver.findElement(By.xpath("(//table[@id = 'dataTable']//tbody/tr/td[1])" + i)).getText(), ConfProperties.getProperty("email"));
        Assert.assertEquals(driver.findElement(By.xpath("(//table[@id = 'dataTable']//tbody/tr/td[2])" + i)).getText(), ConfProperties.getProperty("name"));
        Assert.assertEquals(driver.findElement(By.xpath("(//table[@id = 'dataTable']//tbody/tr/td[3])" + i)).getText(), "Женский");
        Assert.assertEquals(driver.findElement(By.xpath("(//table[@id = 'dataTable']//tbody/tr/td[4])" + i)).getText(), "1.1, 1.2");
        Assert.assertEquals(driver.findElement(By.xpath("(//table[@id = 'dataTable']//tbody/tr/td[5])" + i)).getText(), "2.3");
        Assert.assertEquals(tableSizeAfter, tableSizeBefore + 1);
    }

    /**
     * Тест с валидными данными
     */
    @Test
    public void formTestPositive2() {
        formPage.clearFields();

        formPage.inputEmail(ConfProperties.getProperty("email"));
        formPage.inputName(ConfProperties.getProperty("name"));

        Select gender = new Select(driver.findElement(By.id("dataGender")));
        gender.selectByVisibleText("Мужской");

        formPage.checkCheckbox1();
        formPage.checkCheckbox2();

        formPage.checkRadioButton1();
        int tableSizeBefore = formPage.getTableSize();

        formPage.clickAddButton();
        formPage.clickOkButton();
        int tableSizeAfter = formPage.getTableSize();

        String i = "[" + formPage.getTableSize() + "]";

        Assert.assertEquals(driver.findElement(By.xpath("(//table[@id = 'dataTable']//tbody/tr/td[1])" + i)).getText(), ConfProperties.getProperty("email"));
        Assert.assertEquals(driver.findElement(By.xpath("(//table[@id = 'dataTable']//tbody/tr/td[2])" + i)).getText(), ConfProperties.getProperty("name"));
        Assert.assertEquals(driver.findElement(By.xpath("(//table[@id = 'dataTable']//tbody/tr/td[3])" + i)).getText(), "Мужской");
        Assert.assertEquals(driver.findElement(By.xpath("(//table[@id = 'dataTable']//tbody/tr/td[4])" + i)).getText(), "1.1, 1.2");
        Assert.assertEquals(driver.findElement(By.xpath("(//table[@id = 'dataTable']//tbody/tr/td[5])" + i)).getText(), "2.1");
        Assert.assertEquals(tableSizeAfter, tableSizeBefore + 1);
    }

    /**
     * Тест с некорректным форматом email
     */
    @Test
    public void formTestNegative1() {
        formPage.clearFields();

        formPage.inputEmail(ConfProperties.getProperty("incorrectEmailFormat"));
        formPage.inputName(ConfProperties.getProperty("name"));
        int tableSizeBefore = formPage.getTableSize();
        formPage.clickAddButton();
        int tableSizeAfter = formPage.getTableSize();
        Assert.assertEquals(tableSizeAfter, tableSizeBefore);
        Assert.assertEquals(formPage.getErrorMessage(), "Неверный формат E-Mail");

    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
