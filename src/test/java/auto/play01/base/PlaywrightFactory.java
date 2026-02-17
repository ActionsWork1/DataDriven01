package auto.play01.base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {
    private static Playwright playwright;
    private static Browser browser;

    public static BrowserContext initBrowser(String browserType) {
        playwright = Playwright.create();
        switch (browserType.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            default:
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        }
        return browser.newContext();
    }

    public static void close() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
