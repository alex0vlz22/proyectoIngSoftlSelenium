package sanity;

import base.BaseTest;
import com.beust.jcommander.Parameter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.util.logging.Logger;

public final class FunctionalTesting extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();
    private final Logger log = Logger.getLogger(this.getClass().getName());

    // Pages
    private SigningUpPage signingUpPage;
    private UserRegisterPage userRegisterPage;
    private LoginPage loginPage;
    private ClientHomePage clientHomePage;
    private VendorHomePage vendorHomePage;
    private SubcategoriasPage subcategoriasPage;
    private CategoriasPage categoriasPage;
    private BodegaPage bodegaPage;

    // Variables
    private String clientEmail, clientPassword;
    private String sellerEmail, sellerPassword;

    //Tests

    @Test(priority = 0)
    @Parameters("registrarseTextForButton")
    private void indexButtonsValidation(String registrarseTextForButton) {
        softAssert.assertTrue(super.indexPage.validateButtonVisibilityByText(registrarseTextForButton),
                "It wasn't found a button containing: '" + registrarseTextForButton + "'.");
    }

    @Test(priority = 1)
    @Parameters("covidYNosotrosTextForButton")
    private void indexValidateCovidButton(String COVIDynosotrosTextForButton) {
        softAssert.assertTrue(super.indexPage.validateButtonVisibilityByText(COVIDynosotrosTextForButton),
                "It wasn't found a button containing: '" + COVIDynosotrosTextForButton + "'.");
    }

    @Test(priority = 2)
    @Parameters("soporte")
    private void indexValidateSoporteButton(String soporte) {
        softAssert.assertTrue(super.indexPage.validateButtonVisibilityByText(soporte),
                "It wasn't found a button containing: '" + soporte + "'.");
    }

    @Test(priority = 3)
    @Parameters("ingresar")
    private void indexValidateIngresarButton(String ingresar) {
        softAssert.assertTrue(super.indexPage.validateButtonVisibilityByText(ingresar),
                "It wasn't found a button containing: '" + ingresar + "'.");
        this.signingUpPage = super.indexPage.clickOnSignUpButton();
    }

    @Test(priority = 4)
    private void signingUpOptionButtonsValidation() {
        softAssert.assertTrue(this.signingUpPage.validateButtons(), "Something went wrong while expecting for buttons.");
        //this.userRegisterPage = this.signingUpPage.clickOnClientButton();
        this.userRegisterPage = this.signingUpPage.clickOnSellerButton();
    }

    @Test(priority = 5)
    @Parameters({"dniV", "nombreV", "apellidoV", "telefonoV", "direccionV", "correoV", "contrasenaV", "codeV"})
    private void vendorRegisterValidation(String dni, String nombre, String apellido, String telefono, String direccion, String correo, String contrasena, String code) throws InterruptedException {
        this.softAssert.assertTrue(this.userRegisterPage.validateAllFieldsArePresent(), "");
        this.userRegisterPage.fillFieldsForVendor(dni, nombre, apellido, telefono, direccion, correo, contrasena, code);
        this.sellerEmail = correo;
        this.sellerPassword = contrasena;
        super.indexPage = this.userRegisterPage.clickOnValidateButton();
    }

    @Test(priority = 6)
    private void loginAsVendor() {
        this.loginPage = super.indexPage.clickOnLogInButton();
        this.softAssert.assertTrue(this.loginPage.validateFields(), "Something went wrong validating the fields.");
        this.loginPage.fillFields(this.sellerEmail, this.sellerPassword);
        this.vendorHomePage = this.loginPage.clickOnIngresarButtonAsVendor();
    }

    @Test(priority = 7)
    @Parameters("nameCategory")
    private void createCategory(String name) throws InterruptedException {
        this.categoriasPage = this.vendorHomePage.goToCategorias();
        this.softAssert.assertTrue(this.categoriasPage.create(name), "Something went wrong...");
    }

    @Test(priority = 8)
    @Parameters("nameSubcategory")
    private void createSubCategory(String name) throws InterruptedException {
        this.subcategoriasPage = this.categoriasPage.goToSubcategorias();
        this.softAssert.assertTrue(this.subcategoriasPage.create(name), "Something went wrong");
        this.bodegaPage = this.subcategoriasPage.goToBodegaPage();
    }

    @Test(priority = 9)
    @Parameters({"bodegaNombre", "bodegaDireccion", "bodegaCantidad"})
    public void createBodega(String bodegaNombre, String bodegaDireccion, String bodegaCantidad) throws InterruptedException {
        this.softAssert.assertTrue(this.bodegaPage.create(bodegaNombre, bodegaDireccion, bodegaCantidad), "Something went wrong!");
    }

}
