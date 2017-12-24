package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public LoginPage enterUsername(String username) {
        this.username.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        this.password.sendKeys(password);
        return this;
    }

    public NavigationBarPage submit() {
        this.submit.click();
        return PageFactory.initElements(driver, NavigationBarPage.class);
    }
}
