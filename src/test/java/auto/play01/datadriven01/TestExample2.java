package auto.play01.datadriven01;

import auto.play01.base.BaseUiTest;
import io.qameta.allure.Allure;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;



public class TestExample2 extends BaseUiTest {

    @Test(groups = {"ui"})
    void shouldClickButton2() {
        page.navigate("data:text/html,<script>var result;</script><button onclick='result=\"Clicked\"'>Go</button>");
        page.locator("button").click();
        assertEquals(page.evaluate("result"), "Clicked");
    }

    @Test(groups = {"ui"})
//    @Test()
    void shouldCheckTheBox2() {
        page.setContent("<input id='checkbox' type='checkbox'></input>");
        page.locator("input").check();
        assertTrue((Boolean) page.evaluate("() => window['checkbox'].checked"));
    }

    @Test (groups = {"ui"})
    void shouldSearchWiki2() {
        page.navigate("https://www.wikipedia.org/");
        page.locator("input[name=\"search\"]").click();
        page.locator("input[name=\"search\"]").fill("playwright");
        page.locator("input[name=\"search\"]").press("Enter");
        assertEquals(page.url(), "https://en.wikipedia.org/wiki/Playwright");
    }
}
