package auto.play01.datadriven01;

import auto.play01.base.BaseUiTest;
import io.qameta.allure.*;
import org.testng.annotations.*;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.testng.Assert.assertEquals;



public class TestExample1 extends BaseUiTest {


    @Test (groups = {"smoke"})
    @Description("This test attempts to log into the website using a login and a password. Fails if any error happens.\n\nNote that this test does not test 2-Factor Authentication.")
    @Severity(CRITICAL)
    @Owner("John Doe")
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    @Epic("Web interface")
    @Feature("Essential features")
    @Story("Authentication")
    void shouldClickButton1() {
        page.navigate("data:text/html,<script>var result;</script><button onclick='result=\"Clicked\"'>Go</button>");
        page.locator("button").click();
        assertEquals(page.evaluate("result"), "Clicked");
    }


}


