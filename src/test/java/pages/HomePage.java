package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(xpath = "//div[@class='explorer'][1]//ul/div[1]")
    private WebElement firstProject;

    @FindBy(xpath = "//div[@class='explorer'][2]//ul/div[1]")
    private WebElement firstMicrotask;

    @FindBy(xpath = "//span/button")
    private WebElement createMicrotask;

    public HomePage(WebDriver driver) {
        super(driver);
    }


}
