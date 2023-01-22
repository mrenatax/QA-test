import org.openqa.selenium.By;
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

    @FindBy(xpath = "//input[@id = 'dataCheck11']")
    private WebElement checkbox1;

    @FindBy(xpath = "//input[@id = 'dataCheck12']")
    private WebElement checkbox2;

    @FindBy(xpath = "//input[@id = 'dataSelect21']")
    private WebElement radioButton1;

    @FindBy(xpath = "//input[@id = 'dataSelect22']")
    private WebElement radioButton2;

    @FindBy(xpath = "//input[@id = 'dataSelect23']")
    private WebElement radioButton3;

    private String tableXpath = "//table[@id = 'dataTable']/tbody/tr";

    public void inputEmail(String email) {
        emailField.sendKeys(email);
    }

    public void inputName(String name) {
        nameField.sendKeys(name);
    }

    public void clickAddButton() {
        addButton.click();
    }

    public int getTableSize(){
        return driver.findElements(By.xpath(tableXpath)).size();
    }

    public void clickOkButton() {
        okButton.click();
    }

    public void checkCheckbox1() {
        checkbox1.click();
    }

    public void checkCheckbox2() {
        checkbox2.click();
    }

    public void checkRadioButton1() {
        radioButton1.click();
    }

    public void checkRadioButton2() {
        radioButton2.click();
    }

    public void checkRadioButton3() {
        radioButton3.click();
    }

    public WebElement getCheckBox1() {
        return checkbox1;
    }

    public WebElement getCheckBox2() {
        return checkbox2;
    }

    public void clearFields() {
        emailField.clear();
        nameField.clear();
        if (checkbox1.isSelected()) {
            checkbox1.click();
        }
        if (checkbox2.isSelected()) {
            checkbox2.click();
        }
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

}