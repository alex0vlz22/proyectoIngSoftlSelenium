package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.BasePage;

public class LoginPage extends BasePage {

    @FindBy(css = "input[type='email']")
    private WebElement emailTextField;

    @FindBy(css = "input[type='password']")
    private WebElement passwordTextField;

    @FindBy(id = "ingresar")
    private WebElement logInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean validateFields() {
        if (super.waitVisibilityOf(this.emailTextField))
            if (super.waitVisibilityOf(this.passwordTextField))
                if (super.waitVisibilityOf(this.logInButton))
                    return true;
        return false;
    }

    public void fillFields(String email, String password){
        this.emailTextField.sendKeys(email);
        this.passwordTextField.sendKeys(password);
    }

    public ClientHomePage clickOnIngresarButtonAsClient(){
        this.logInButton.click();
        return PageFactory.initElements(super.driver, ClientHomePage.class);
    }

    public VendorHomePage clickOnIngresarButtonAsVendor(){
        this.logInButton.click();
        return PageFactory.initElements(super.driver, VendorHomePage.class);
    }


}
