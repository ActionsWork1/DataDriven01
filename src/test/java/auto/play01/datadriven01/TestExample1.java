package auto.play01.datadriven01;

import auto.play01.base.BaseUiTest;
import org.testng.annotations.*;
import static org.testng.Assert.assertEquals;



public class TestExample1 extends BaseUiTest {


    @Test (groups = {"smoke"})
    void shouldClickButton1() {
        page.navigate("data:text/html,<script>var result;</script><button onclick='result=\"Clicked\"'>Go</button>");
        page.locator("button").click();
        assertEquals(page.evaluate("result"), "Clicked");
    }


}


