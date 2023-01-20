import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormPage {
    public WebDriver driver;

    @FindBy(xpath = "//div[@id='emailFormatError']")
    private WebElement errorMessage;

    public FormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@id = 'dataEmail']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id = 'dataName']")
    private WebElement nameField;

    @FindBy(xpath = "//button[@id = 'dataSend']")
    private WebElement addButton;

    @FindBy(xpath = "//button[text()='Ok']")
    private WebElement okButton;

    public void inputEmail(String email) {
        emailField.sendKeys(email);
    }

    public void inputName(String name) {
        nameField.sendKeys(name);
    }

    public void clickAddButton() {
        addButton.click();
    }

    public void clickOkButton() {
        okButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

}