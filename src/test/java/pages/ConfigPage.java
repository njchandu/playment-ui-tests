package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

public class ConfigPage extends BasePage {
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveConfig;

    @FindBy(xpath = "//div[label[text()='isActive']]/span")
    private WebElement toggleActiveStatus;

    public ConfigPage(WebDriver driver) {
        super(driver);
    }

    @Step("Toggle isActive")
    public ConfigPage isActive() {
        toggleActiveStatus.click();
        return this;
    }

    @Step("Save config")
    public ConfigPage saveConfig() {
        waitFor(2000);
        clickOnElement(saveConfig);
        return this;
    }
}
