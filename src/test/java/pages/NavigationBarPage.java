package pages;

import base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.allure.annotations.Step;

import static java.awt.SystemColor.window;

public class NavigationBarPage extends BasePage {
    @FindBy(xpath = "//li[text()='Task UI']")
    private WebElement taskUI;

    @FindBy(xpath = "//li[text()='Config']")
    private WebElement config;

    @FindBy(xpath = "//li[text()='Instructions']")
    private WebElement instructions;

    @FindBy(xpath = "//div[@class='explorer'][1]//ul/div[1]/a/li")
    private WebElement firstProject;

    @FindBy(xpath = "//div[@class='explorer'][2]//ul/div[1]")
    private WebElement firstMicrotask;

    @FindBy(xpath = "//span/button")
    private WebElement createMicrotask;

    public NavigationBarPage(WebDriver driver) {
        super(driver);
    }

    @Step("Select first project")
    public NavigationBarPage selectFirstProject() {
        waitFor(1000);
        clickOnElement(firstProject);
        waitFor(1000);
        clickOnElement(firstProject);
        return this;
    }

    @Step("Select first microtask")
    public NavigationBarPage selectFirstMircoTask() {
        clickOnElement(firstMicrotask);
        waitFor(3000);
        return this;
    }

    @Step("Create microtask")
    public ConfigPage createMicrotask() {
        clickOnElement(createMicrotask);
        return PageFactory.initElements(driver, ConfigPage.class);
    }

    @Step("Go to Config page")
    public ConfigPage gotoConfigPage() {
        clickOnElement(config);
        return PageFactory.initElements(driver, ConfigPage.class);
    }

    @Step("Go to TaskUI Page")
    public TaskUIPage gotoTaskUIPage() {
        waitFor(3000);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
        clickOnElement(taskUI);
        return PageFactory.initElements(driver, TaskUIPage.class);
    }

    @Step("Go to instructions page")
    public InstructionsPage gotoInstructionsPage() {
        clickOnElement(instructions);
        return PageFactory.initElements(driver, InstructionsPage.class);
    }
}
