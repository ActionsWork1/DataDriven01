package auto.play01.base;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;
import io.qameta.allure.Allure;
import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//import static auto.play01.base.PlaywrightFactory.browser;
//import static auto.play01.base.PlaywrightFactory.playwright;

//public class BaseUiTest extends AllureSetup{
//
//    protected static Page page;
//    protected static BrowserContext context;
//    protected static Browser browser;
//
//    @BeforeMethod(alwaysRun = true)
//    public void createContext() {
////        Browser browser;
//        try (Playwright playwright = Playwright.create()) {
//            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
//        }
//        context = browser.newContext();
//        context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));
//        page = context.newPage();
//    }
//
//
//    @AfterMethod(alwaysRun = true)
//    public void tearDown(ITestResult result) {
//        Path tracePath = Paths.get("target/trace-" + result.getName() + ".zip");
//        context.tracing().stop(new Tracing.StopOptions().setPath(tracePath));
//
//        if (result.getStatus() == ITestResult.FAILURE) {
//            try {
//                Allure.addAttachment("Playwright Trace", new FileInputStream(tracePath.toFile()));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        context.close();
//    }
//}

public class BaseUiTest { //extends AllureSetup{
    protected BrowserContext context;
    protected Page page;
    protected FrameworkConfig config;


    @BeforeSuite(alwaysRun = true)
    public void setupAllure() throws IOException {
        String resultsPath = "./allure-results";
        Files.createDirectories(Paths.get(resultsPath));

        // 1. Environment metadata
        String envXml = "<environment>" +
                "<parameter>" +
                "<key>Browser</key>" +
                "<value>Chromium</value>" +
                "</parameter>" +
                "<parameter>" +
                "<key>OS</key>" +
                "<value>" + System.getProperty("os.name") + "</value>" +
                "</parameter>" +
                "</environment>";
        Files.write(Paths.get(resultsPath, "environment.xml"), envXml.getBytes());

        // 2. Categories for failure grouping
        String categoriesJson = "[{\"name\": \"Infrastructure\", \"matchedStatuses\": [\"broken\"], \"messageRegex\": \".*Timeout.*\"}]";
        Files.write(Paths.get(resultsPath, "categories.json"), categoriesJson.getBytes());
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        config = ConfigFactory.create(FrameworkConfig.class);
        context = PlaywrightFactory.initBrowser(config.browser());
        page = context.newPage();
        //page.navigate(config.baseUrl(),new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (page != null) page.close();
        if (context != null) context.close();
        // Keep PlaywrightFactory.close() for @AfterSuite instead
    }

    @AfterSuite(alwaysRun = true)
    public  void closeAll() {
        //if (browser != null)
        PlaywrightFactory.close();
    }

}
