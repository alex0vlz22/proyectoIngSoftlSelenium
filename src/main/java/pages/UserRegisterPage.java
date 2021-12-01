package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.BasePage;

public class UserRegisterPage extends BasePage {

    @FindBy(css = "label[for='file-upload']")
    private WebElement fileUploadButton;

    @FindBy(id = "DNI")
    private WebElement dniTextField;

    @FindBy(id = "nombre")
    private WebElement nameTextField;

    @FindBy(id = "apellido")
    private WebElement lastNameTextField;

    @FindBy(id = "telefono")
    private WebElement phoneNumberTextField;

    @FindBy(id = "direccion")
    private WebElement addressTextField;

    @FindBy(id = "correo")
    private WebElement emailTextField;

    @FindBy(id = "contrasena")
    private WebElement passwordTextField;

    @FindBy(id = "file-upload")
    private WebElement image;

    @FindBy(id = "validar")
    private WebElement validateButton;

    @FindBy(id = "codigoEmpresa")
    private WebElement codeTextField;

    public UserRegisterPage(WebDriver driver) {
        super(driver);
    }

    public boolean validateAllFieldsArePresent() {
        if (super.waitVisibilityOf(this.fileUploadButton))
            if (super.waitVisibilityOf(this.dniTextField))
                if (super.waitVisibilityOf(this.nameTextField))
                    if (super.waitVisibilityOf(this.lastNameTextField))
                        if (super.waitVisibilityOf(this.phoneNumberTextField))
                            if (super.waitVisibilityOf(this.addressTextField))
                                if (super.waitVisibilityOf(this.emailTextField))
                                    if (super.waitVisibilityOf(this.passwordTextField))
                                        return true;
        return false;
    }

    public void fillFieldsForClient(String DNI, String name, String lastName, String phoneNumber, String addressField, String email, String password) {
        this.dniTextField.sendKeys(DNI);
        this.nameTextField.sendKeys(name);
        this.lastNameTextField.sendKeys(lastName);
        this.phoneNumberTextField.sendKeys(phoneNumber);
        this.addressTextField.sendKeys(addressField);
        this.emailTextField.sendKeys(email);
        this.passwordTextField.sendKeys(password);
    }

    public void fillFieldsForVendor(String DNI, String name, String lastName, String phoneNumber, String addressField, String email, String password, String code) throws InterruptedException {
        this.dniTextField.sendKeys(DNI);
        this.nameTextField.sendKeys(name);
        this.lastNameTextField.sendKeys(lastName);
        this.phoneNumberTextField.sendKeys(phoneNumber);
        this.addressTextField.sendKeys(addressField);
        this.emailTextField.sendKeys(email);
        this.passwordTextField.sendKeys(password);
        this.codeTextField.sendKeys(code);
        Thread.sleep(2000);
    }

    public IndexPage clickOnValidateButton() throws InterruptedException {
        this.validateButton.click();
        super.waitForElementToNotBePresent(super.driver, By.id("validar"), 15);
        Thread.sleep(3000);
        return PageFactory.initElements(super.driver, IndexPage.class);
    }

}
