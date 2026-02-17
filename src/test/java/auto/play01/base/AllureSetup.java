package auto.play01.base;

import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AllureSetup {

    @BeforeSuite()
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
}
