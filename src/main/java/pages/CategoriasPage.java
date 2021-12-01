package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.BasePage;

public class CategoriasPage extends BasePage {

    @FindBy(id = "campoNombre")
    private WebElement campoNombre;

    @FindBy(id = "Guardar")
    private WebElement guardar;

    @FindBy(css = "a[href*='editarCategoria']")
    private WebElement editarCategoria;

    @FindBy(xpath = "a[href*='Categoria']")
    private WebElement categorias;

    @FindBy(css = "a[href*='Subcategoria']")
    private WebElement subcategorias;

    @FindBy(css = "li[class*='nav-item dropdown']")
    private WebElement categoriasMenu;

    public CategoriasPage(WebDriver driver) {
        super(driver);
    }

    public boolean create(String name) throws InterruptedException {
        this.campoNombre.sendKeys(name);
        this.guardar.click();
        Thread.sleep(2000);
        if(super.waitForElementToBeClickeable(this.editarCategoria))
            return true;
        else
            return false;
    }

    public SubcategoriasPage goToSubcategorias() throws InterruptedException {
        Thread.sleep(2000);
        this.categoriasMenu.click();
        Thread.sleep(1000);
        this.subcategorias.click();
        Thread.sleep(1000);
        return PageFactory.initElements(super.driver, SubcategoriasPage.class);
    }

}
