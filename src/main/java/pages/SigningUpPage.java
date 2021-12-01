package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.BasePage;

public class SigningUpPage extends BasePage {

    @FindBy(id = "botonCliente")
    WebElement clientButton;

    @FindBy(id = "botonVendedor")
    WebElement sellerButton;

    public SigningUpPage(WebDriver driver) {
        super(driver);
    }

    public boolean validateButtons() {
        if (super.waitVisibilityOf(this.clientButton) && super.waitForElementToBeClickeable(this.clientButton)) {
            if (super.waitVisibilityOf(this.sellerButton) && super.waitForElementToBeClickeable(this.sellerButton)) {
                return true;
            } else {
                super.print("Something went wrong looking for 'vendedor' button.", "ERROR");
                return false;
            }
        } else {
            super.print("Something went wrong looking for 'client' button.", "ERROR");
            return false;
        }
    }

    public UserRegisterPage clickOnClientButton() {
        super.wclick(this.clientButton);
        return PageFactory.initElements(super.driver, UserRegisterPage.class);
    }

    public UserRegisterPage clickOnSellerButton() {
        super.wclick(this.sellerButton);
        return PageFactory.initElements(super.driver, UserRegisterPage.class);
    }

}
