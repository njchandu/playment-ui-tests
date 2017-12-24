package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public TaskUIPage addTask() {
        clickOnElement(addTask);
        return this;
    }

    public TaskUIPage selectNextElement() {
        clickOnElement(selectElement);
        return this;
    }

    public TaskUIPage selectLargeHeader() {
        clickOnElement(largeHeader);
        return this;
    }

    public TaskUIPage enterText(String text) {
        waitForElement(this.text);
        this.text.sendKeys(text);
        return this;
    }

    public TaskUIPage save() {
        clickOnElement(save);
        return this;
    }
}
