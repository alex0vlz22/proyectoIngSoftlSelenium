package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.BasePage;

public class BodegaPage extends BasePage {

    @FindBy(css = "input[id='nombre']")
    private WebElement nombre;

    @FindBy(css = "input[id='direccion']")
    private WebElement direccion;

    @FindBy(css = "input[id='capacidad']")
    private WebElement capacidad;

    @FindBy(css = "td[colspan] button[type='submit']")
    private WebElement guardar;

    public BodegaPage(WebDriver driver) {
        super(driver);
    }

    public boolean create(String nombre, String direccion, String capacidad) throws InterruptedException {
        Thread.sleep(2000);
        this.nombre.sendKeys(nombre);
        this.direccion.sendKeys(direccion);
        this.capacidad.sendKeys(capacidad);
        this.guardar.click();
        return true;
    }

}
