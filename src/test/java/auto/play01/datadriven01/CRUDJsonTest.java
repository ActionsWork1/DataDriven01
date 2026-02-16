package auto.play01.datadriven01;

import auto.play01.dataclass.JsonDataDriven;
import auto.play01.dataclass.JsonPojoHandler;
import auto.play01.dataobjects.TestDataObjects;
import org.testng.annotations.Test;

import java.io.IOException;

import static auto.play01.dataclass.JsonPojoHandler.saveOrUpdateTestData;

public class CRUDJsonTest {


    @Test
    public void testCRUDJson() throws IOException {

            saveOrUpdateTestData("Test100", "updated_test2@example.com", "999");
            saveOrUpdateTestData("Test200", "new_user@example.com", "888");

    }


    @Test(dataProvider = "userDataProvider", dataProviderClass = JsonDataDriven.class)
    public void loginTest(TestDataObjects user) {
        System.out.println("--- Starting Test for: " + user.getName() + " ---");

        System.out.println("Testing with Email: " + user.getName());
        System.out.println("Testing with Email: " + user.getPassword());
        System.out.println("Testing with Email: " + user.getEmail());
    }




}
