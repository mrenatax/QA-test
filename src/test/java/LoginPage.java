import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@id = 'loginEmail']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id = 'loginPassword']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@id = 'authButton']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@id='invalidEmailPassword']")
    private WebElement errorMessage;

    public void inputEmail(String login) {
        emailField.sendKeys(login);
    }

    public void inputPasswd(String passwd) {
        passwordField.sendKeys(passwd);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}