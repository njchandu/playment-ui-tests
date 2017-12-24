package base;

import dao.Config;
import dao.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Attachment;
import util.YamlReader;

public class BasePage {
    protected WebDriver driver;
    protected TestData data;
    protected Config config;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        saveScreenshot();
        config = new YamlReader("config.yml").readConfig();
        data = new YamlReader("data.yml").readTestData();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    protected byte[] saveScreenshot() {
        driver = BaseTest.driver;
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    protected void waitForLoaderToDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text('step details are being uploaded')]")));
        waitFor(2000); // TODO revisit
    }

    protected void waitForElement(WebElement element) {
        int failureCount = 1;
        int pollingDuration = config.getPollingDuration();
        boolean visible = false;
        for (int i = 0; i < config.getTimeoutForWait(); i++) {
            try {
                visible = element.isDisplayed();
                if (visible) {
                    break;
                }
            } catch (Exception e) {
            }
            waitFor(pollingDuration);
            failureCount++;
        }
        if (!visible) {
            System.out.println("************ Unable to find element \"" + element + "\". Retry count: " + (failureCount - 1));
            throw new NoSuchElementException("Element: \"" + element + "\" not found");
        }
    }

    protected boolean waitForElement(WebElement element, int pollingDuration) {
        boolean visible = false;
        for (int i = 0; i < config.getTimeoutForWait(); i++) {
            try {
                visible = element.isDisplayed();
                break;
            } catch (Exception e) {
            }
            waitFor(pollingDuration);
        }
        return visible;
    }

    protected void waitFor(int pollingDuration) {
        try {
            Thread.sleep(pollingDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected boolean isVisible(WebElement element) {
        int pollingDuration = config.getPollingDuration();
        boolean visible = false;
        for (int i = 0; i < config.getTimeoutForWait(); i++) {
            try {
                if (element.isDisplayed()) {
                    visible = true;
                    break;
                }
            } catch (Exception e) {
            }
            waitFor(pollingDuration);
        }
        return visible;
    }

    protected boolean isNotVisible(WebElement element) {
        int pollingDuration = config.getPollingDuration();
        boolean isNotVisible = false;
        for (int i = 0; i < config.getTimeoutForWait(); i++) {
            try {
                element.isDisplayed();
            } catch (Exception e) {
                isNotVisible = true;
                break;
            }
            waitFor(pollingDuration);
        }
        return isNotVisible;
    }

    protected void waitForElementToDisappear(WebElement element) {
        int pollingDuration = config.getPollingDuration();
        boolean visible = true;
        for (int i = 0; i < config.getTimeoutForWait(); i++) {
            try {
                element.isDisplayed();
            } catch (Exception e) {
                visible = false;
                break;
            }
            waitFor(pollingDuration);
        }
        if (visible) {
            throw new NoSuchElementException("Element: " + element + " found");
        }
    }

    protected void explicitlyWaitForElement(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, config.getExplicitTimeOut());
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected void waitForElementToBeClickable(WebElement element) {
        int pollingDuration = config.getPollingDuration();
        boolean visible = false;
        for (int i = 0; i < config.getTimeoutForWait(); i++) {
            try {
                visible = element.isEnabled();
                break;
            } catch (Exception e) {
            }
            waitFor(pollingDuration);
        }
        if (!visible) {
            throw new NoSuchElementException("Element: " + element + " not clickable");
        }
    }

    protected WebElement findElement(String method, String value) {
        int pollingDuration = config.getPollingDuration();
        WebElement element = null;
        for (int i = 0; i < config.getTimeoutForWait(); i++) {
            try {
                switch (method) {
                    case "id":
                        element = driver.findElement(By.id(value));
                    case "name":
                        element = driver.findElement(By.name(value));
                    case "linkText":
                        element = driver.findElement(By.linkText(value));
                    case "partialLinkText":
                        element = driver.findElement(By.partialLinkText(value));
                    case "xpath":
                        element = driver.findElement(By.xpath(value));
                    case "tagName":
                        element = driver.findElement(By.tagName(value));
                    case "css":
                        element = driver.findElement(By.cssSelector(value));
                    case "className":
                        element = driver.findElement(By.className(value));
                }
            } catch (NoSuchElementException e) {
            }
            waitFor(pollingDuration);
        }
        if (element == null) {
            throw new NoSuchElementException("Element: " + value + " by: " + method + " not found");
        }
        return element;
    }

    protected WebElement findElement(String method, String value, String replacement) {
        int pollingDuration = config.getPollingDuration();
        WebElement element = null;
        for (int i = 0; i < config.getTimeoutForWait(); i++) {
            try {
                element = driver.findElement(By.xpath(value.replace("%", replacement)));
            } catch (NoSuchElementException e) {
            }
            waitFor(pollingDuration);
        }
        if (element == null) {
            throw new NoSuchElementException("Element: " + value + " by: " + method + " not found");
        }
        return element;
    }

    public void clickOnElement(WebElement element) {
        System.out.println("Clicking on element: " + element);
        waitForElement(element);
        element.click();
    }

    public void clickOnStaleElementWithRetry(WebElement element) {
        boolean clicked = false;
        System.out.println("Clicking on element: " + element);
        waitForElement(element);
        for (int i = 0; i < config.getTimeoutForWait(); i++) {
            try {
                element.click();
                clicked = true;
                break;
            } catch (StaleElementReferenceException s) {
            }
            waitFor(config.getPollingDuration());
        }
        if (!clicked) {
            throw new StaleElementReferenceException("Element: " + element + " not clickable");
        }
    }

    public void writeTextInField(WebElement webElement, String text) {
        System.out.println("Typing :" + text + ", on element: " + webElement);
        waitForElement(webElement);
        webElement.sendKeys(text);
    }

    protected void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void explicitlyWaitForFrame(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, config.getExplicitTimeOut());
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }
}
