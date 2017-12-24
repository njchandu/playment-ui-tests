package listeners;

import base.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;

/**
 * Created by chandanjavaregowda on 27/10/16.
 */
public class TestListener extends TestListenerAdapter {
    WebDriver driver;

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Taking screenshot of failure!");
        String methodName = result.getName().trim();
        takeScreenShot(methodName);
        System.out.println("*********************************************************************************");
        System.out.println("*********************************************************************************");
        System.out.println("****************** Test: " + methodName + " Failed because of Exception " + result.getThrowable() + "! ******************");
        try {
            throw new Exception(result.getThrowable());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("*********************************************************************************");
        System.out.println("*********************************************************************************");

    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] takeScreenShot(String methodName) {
        driver = BaseTest.driver;
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public void onFinish(ITestContext context) {
    }

    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getName().trim();
        takeScreenShot(methodName);
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onStart(ITestContext context) {
    }
}