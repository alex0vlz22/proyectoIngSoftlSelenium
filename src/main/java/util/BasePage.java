package util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;
import java.util.logging.Logger;

public class BasePage {

    private Logger log;
    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final String AJAX_ACTIVE_CONNECTIONS = "var i = 0; if(!(typeof jQuery == 'undefined')) { i += jQuery.active; } if(!(typeof Ajax == 'undefined')) { i += Ajax.activeRequestCount; } return i == 0;";

    private static ExpectedCondition<Boolean> noActiveConnections = new ExpectedCondition<Boolean>() {
        @Override
        public Boolean apply(WebDriver d) {
            JavascriptExecutor jsExec = (JavascriptExecutor) d;
            return (Boolean) jsExec.executeScript(AJAX_ACTIVE_CONNECTIONS);
        }
    };

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 15);
        this.log = Logger.getLogger(this.getClass().getName());
        PageFactory.initElements(driver, this);
    }

    protected void clickByElement(By by) {
        this.driver.findElement(by).click();
    }

    protected void writeOnField(By by, String msg, WebDriver driver) {
        this.driver.findElement(by).sendKeys(msg);
    }

    protected void print(String msg, String option) {
        this.log.info(option + ": " + msg);
    }

    protected boolean waitForTextToBePresentInElement(WebElement webElement, String text) {
        this.print("Looking for an element containing: '" + text + "' as text.", "INFO");
        if (this.wait.until(ExpectedConditions.textToBePresentInElement(webElement, text))) {
            this.print("It was found an element with '" + text + "' as text.", "SUCCESS");
            return true;
        } else {
            this.print("It couldn't be found an element containing '" + text + "' as text.", "ERROR");
            return false;
        }
    }

    protected boolean waitVisibilityOf(WebElement webElement) {
        this.print("Waiting for element visibility...", "INFO");
        if (this.wait.until(ExpectedConditions.visibilityOf(webElement)) != null) {
            this.print("The element has been successfully found.", "SUCCESS");
            return true;
        } else {
            this.print("It hasn't could be found an element.", "ERROR");
            return false;
        }
    }

    protected boolean waitForElementToBeClickeable(WebElement webElement) {
        this.print("Expecting for an element to be clickeable...", "INFO");
        if (this.wait.until(ExpectedConditions.elementToBeClickable(webElement)) != null) {
            this.print("It was found a clickeable element.", "SUCCESS");
            return true;
        } else {
            this.print("It hasn't could be found a clickeable element.", "ERROR");
            return false;
        }
    }

    public void waitForElementToNotBePresent(WebDriver driver, By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)));
    }

    public final void wclick(WebElement webElement) {
        webElement.click();
        this.waitUntilAjaxRequestCompletes(driver, 15, 3000);
    }

    public static boolean waitUntilAjaxRequestCompletes(WebDriver driver,
                                                        int seconds, int sleepInMillis) {
        Boolean status = false;
        try {
            status = new WebDriverWait(driver, seconds, sleepInMillis)
                    .until(noActiveConnections);
        } catch (Exception e) {
            status = false;
        }
        return status;
    }
}
