package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.BasePage;

public class VendorHomePage extends BasePage {

    @FindBy(css = "li[class*='nav-item dropdown']")
    private WebElement categoriasMenu;

    @FindBy(css = "a[href*='Categoria']")
    private WebElement categorias;

    @FindBy(css = "a[href*='Subcategoria']")
    private WebElement subcategorias;

    public VendorHomePage(WebDriver driver) {
        super(driver);
    }

    public CategoriasPage goToCategorias() throws InterruptedException {
        Thread.sleep(2000);
        this.categoriasMenu.click();
        Thread.sleep(1000);
        this.categorias.click();
        Thread.sleep(1000);
        return PageFactory.initElements(super.driver, CategoriasPage.class);
    }



}
