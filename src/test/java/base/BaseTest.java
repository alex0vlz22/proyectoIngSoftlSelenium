package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.IndexPage;

public class BaseTest {
    protected WebDriver driver;
    protected IndexPage indexPage;

    @Parameters({"url"})
    @BeforeClass
    public void setUp(String url){
        WebDriverManager.firefoxdriver().setup();
        this.driver = new FirefoxDriver();
        this.indexPage = new IndexPage(this.driver);
        this.driver.get(url);
        this.driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown(){
        this.driver.quit();
    }


}
