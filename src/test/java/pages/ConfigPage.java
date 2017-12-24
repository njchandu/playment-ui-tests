package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfigPage extends BasePage {
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveConfig;

    @FindBy(xpath = "//div[label[text()='isActive']]/span")
    private WebElement toggleActiveStatus;

    public ConfigPage(WebDriver driver) {
        super(driver);
    }

    public ConfigPage isActive() {
        toggleActiveStatus.click();
        return this;
    }

    public ConfigPage saveConfig() {
        waitFor(2000);
        clickOnElement(saveConfig);
        return this;
    }
}
