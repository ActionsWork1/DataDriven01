package auto.play01.datadriven01;

import auto.play01.dataobjects.User;
import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Locale;

public class TestDataFaker {

    @Test(dataProvider = "userData")
    public void testRegister(User user) {

        System.out.println("fullName: "+user.getName());
        System.out.println("Email: "+user.getEmail());
        System.out.println("Password: "+user.getPassword());
    }


    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        Faker faker = new Faker(Locale.US);
        Object[][] data = new Object[2][1];

        for (int i = 0; i < 2; i++) {
            User user = new User(
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.internet().password(8,12)
            );
            data[i][0] = user;
        }
        return data;
    }

}
