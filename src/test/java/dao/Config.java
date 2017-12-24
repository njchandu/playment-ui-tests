package dao;

import java.util.Date;
import java.util.Map;

/**
 * Created by chandanjavaregowda on 03/04/17.
 */
public class Config {
    private int timeout;
    private String browser;
    private String nodeUrl;
    private boolean maximize;
    private int timeoutInSeconds;
    private int timeoutForWait;
    private int explicitTimeOut;
    private int pollingDuration;

    public int getPollingDuration() {
        return pollingDuration;
    }

    public void setPollingDuration(int pollingDuration) {
        this.pollingDuration = pollingDuration;
    }

    public int getExplicitTimeOut() {
        return explicitTimeOut;
    }

    public void setExplicitTimeOut(int explicitTimeOut) {
        this.explicitTimeOut = explicitTimeOut;
    }

    public int getTimeoutInSeconds() {
        return timeoutInSeconds;
    }

    public void setTimeoutInSeconds(int timeoutInSeconds) {
        this.timeoutInSeconds = timeoutInSeconds;
    }

    public int getTimeoutForWait() {
        return timeoutForWait;
    }

    public void setTimeoutForWait(int timeoutForWait) {
        this.timeoutForWait = timeoutForWait;
    }

    public String getNodeUrl() { return nodeUrl; }

    public void setNodeUrl(String nodeUrl) { this.nodeUrl = nodeUrl;}

    public boolean isMaximize() {
        return maximize;
    }

    public void setMaximize(boolean maximize) {
        this.maximize = maximize;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
