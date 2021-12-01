package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.BasePage;

public class IndexPage extends BasePage {

    @FindBy(id = "division1")
    private WebElement signUpButton;

    @FindBy(id = "division2")
    private WebElement convidAndUsButton;

    @FindBy(id = "division3")
    private WebElement soporteButton;

    @FindBy(id = "division4")
    private WebElement ingresarButton;

    public IndexPage(WebDriver driver) {
        super(driver);
    }

    public boolean validateButtonVisibilityByText(String buttonText) {
        switch (buttonText) {
            case "Registrarse":
                if (super.waitForTextToBePresentInElement(this.signUpButton, buttonText))
                    return true;
            case "COVID y nosotros":
                if (super.waitForTextToBePresentInElement(this.convidAndUsButton, buttonText))
                    return true;
            case "Soporte":
                if (super.waitForTextToBePresentInElement(this.soporteButton, buttonText))
                    return true;
            case "Ingresar":
                if (super.waitForTextToBePresentInElement(this.ingresarButton, buttonText))
                    return true;
        }
        return false;
    }

    public SigningUpPage clickOnSignUpButton() {
        this.signUpButton.click();
        super.waitForElementToNotBePresent(super.driver, By.id("division1"), 10);
        return PageFactory.initElements(super.driver, SigningUpPage.class);
    }

    public LoginPage clickOnLogInButton(){
        this.ingresarButton.click();
        super.waitForElementToNotBePresent(super.driver, By.id("division4"), 10);
        return PageFactory.initElements(super.driver, LoginPage.class);
    }

}
