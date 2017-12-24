package tests;

import base.BaseTest;
import listeners.TestListener;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.NavigationBarPage;

/**
 * Created by chandanjavaregowda on 23/12/17.
 */
@Listeners({TestListener.class})
public class ProjectManagementTest extends BaseTest {
    @Test
    public void projectManagementTest() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        NavigationBarPage navigationBarPage = PageFactory.initElements(driver, NavigationBarPage.class);
        loginPage
                .enterUsername(data.getUsername())
                .enterPassword(data.getPassword())
                .submit()
                .selectFirstProject()
                .selectFirstMircoTask()
                .createMicrotask();
        driver.navigate().refresh();
        navigationBarPage
                .selectFirstProject()
                .selectFirstMircoTask()
                .createMicrotask()
                .saveConfig();
        navigationBarPage
                .gotoTaskUIPage()
                .addTask()
                .selectNextElement()
                .selectLargeHeader()
                .enterText(data.getSampleText1())
                .addTask()
                .selectNextElement()
                .selectLargeHeader()
                .enterText(data.getSampleText2())
                .save();
        navigationBarPage
                .gotoInstructionsPage()
                .enterDesc(data.getSampleDesc())
                .save();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
