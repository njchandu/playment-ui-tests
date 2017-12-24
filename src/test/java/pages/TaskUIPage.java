package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

public class TaskUIPage extends BasePage {
    @FindBy(xpath = "//button[@class='ui primary button']")
    private WebElement addTask;

    @FindBy(xpath = "(//div[@class='grammarTempContainer'])[last()]/div[1]/div")
    private WebElement selectElement;

    @FindBy(xpath = "(//div[@class='grammarTempContainer'])[last()]/div[2]//input")
    private WebElement text;

    @FindBy(xpath = "(//div[@class='grammarTempContainer'])[last()]/div[1]/div/div/div[text()='Large Header']")
    private WebElement largeHeader;

    @FindBy(xpath = "//button[text()='Save']")
    private WebElement save;

    public TaskUIPage(WebDriver driver) {
        super(driver);
    }

    @Step("Add task")
    public TaskUIPage addTask() {
        clickOnElement(addTask);
        return this;
    }

    @Step("Select next element")
    public TaskUIPage selectNextElement() {
        clickOnElement(selectElement);
        return this;
    }

    @Step("Select large header")
    public TaskUIPage selectLargeHeader() {
        clickOnElement(largeHeader);
        return this;
    }

    @Step("Enter {0}")
    public TaskUIPage enterText(String text) {
        waitForElement(this.text);
        this.text.sendKeys(text);
        return this;
    }

    @Step("Save")
    public TaskUIPage save() {
        clickOnElement(save);
        return this;
    }
}
