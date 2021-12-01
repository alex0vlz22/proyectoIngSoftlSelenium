package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.BasePage;

public class SubcategoriasPage extends BasePage {

    @FindBy(id = "campoNombre")
    private WebElement campoNombre;

    @FindBy(id = "guardar")
    private WebElement guardar;

    @FindBy(css = "a[href*='editarSubcategoria']")
    private WebElement editar;

    @FindBy(css = "a[href*='registroBo']")
    private WebElement bodega;

    public SubcategoriasPage(WebDriver driver) {
        super(driver);
    }

    public boolean create(String name) throws InterruptedException {
        super.waitVisibilityOf(this.campoNombre);
        this.campoNombre.sendKeys(name);
        this.guardar.click();
        Thread.sleep(2000);
        if(super.waitForElementToBeClickeable(this.editar))
            return true;
        else
            return false;
    }

    public BodegaPage goToBodegaPage() throws InterruptedException {
        Thread.sleep(1000);
        this.bodega.click();
        Thread.sleep(1000);
        return PageFactory.initElements(super.driver, BodegaPage.class);
    }

}
