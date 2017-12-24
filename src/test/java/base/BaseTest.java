package base;

import dao.Config;
import dao.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import util.YamlReader;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by chandanjavaregowda on 03/04/17.
 */
public abstract class BaseTest {
    protected static TestData data;
    private static Config config;
    private static DesiredCapabilities capabilities;
    public static WebDriver driver;

    // set up config and data
    static {
        config = new YamlReader("config.yml").readConfig();
        data = new YamlReader("data.yml").readTestData();
    }

    @BeforeSuite
    public void setUp() {
        try {
            setBrowser(config.getBrowser());
            driver = new RemoteWebDriver(new URL(config.getNodeUrl()), capabilities);
            driver.get(data.getProjectExplorerUrl());
            if (config.isMaximize()) {
                maximize();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void handleTestMethodName(Method method) {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Starting test: " + getClass().getSimpleName() + " --> " + method.getName());
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------------------------");
    }

    private void maximize() {
        System.out.println("Maximizing browser...");
        driver.manage().window().maximize();
    }

    private BaseTest setBrowser(String browser) {
        System.out.println("Launching " + config.getBrowser().toUpperCase() + " browser.");
        if (browser.equals("chrome")) {
            capabilities = DesiredCapabilities.chrome();
        }
        return this;
    }

    @AfterMethod
    public void logout() {
        System.out.println("Starting after method...");
    }

    @AfterSuite
    public void tearDown() {
        System.out.println("Starting after suite...");
        driver.quit();
    }
}
