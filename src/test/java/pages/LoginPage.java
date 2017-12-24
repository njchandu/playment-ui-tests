package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.allure.annotations.Step;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@type='email']")
    private WebElement username;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submit;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter username {0}")
    public LoginPage enterUsername(String username) {
        this.username.sendKeys(username);
        return this;
    }

    @Step("Enter password {0}")
    public LoginPage enterPassword(String password) {
        this.password.sendKeys(password);
        return this;
    }

    @Step("Submit")
    public NavigationBarPage submit() {
        this.submit.click();
        return PageFactory.initElements(driver, NavigationBarPage.class);
    }
}
