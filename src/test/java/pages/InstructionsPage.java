package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

public class InstructionsPage extends BasePage {
    @FindBy(xpath = "//textarea[@class='form-control']")
    private WebElement desc;

    @FindBy(xpath = "//button[@class='btn btn-success']")
    private WebElement save;

    public InstructionsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter desc {0}")
    public InstructionsPage enterDesc(String desc) {
        waitForElement(this.desc);
        this.desc.sendKeys(desc);
        return this;
    }

    @Step("Save")
    public InstructionsPage save() {
        clickOnElement(save);
        return this;
    }
}
