package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest extends TestBase {

    @Test
    public void verifyUser() {
        given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/public/v2/users")
                .then()
                .statusCode(200)
                //    1. Verify the if the total record is 20
                .body("id", hasSize(20),
                        //    2. Verify the if the name of id = 5914152 is equal to ”Anshula Joshi”
                        "[3].id", equalTo(5914152),
                        //    3. Check the single ‘Name’ in the Array list (Dinesh Mehrotra)
                        "name", hasItems("Dinesh Mehrotra"),
                        //    4. Check the multiple ‘Names’ in the ArrayList (Avantika Kaur, Avantika Desai Esq., Himadri Banerjee )
                        "name", hasItems("Avantika Kaur", "Avantika Desai Esq.", "Himadri Banerjee"),
                        //    5. Verify the email of userid = 5914150 is equal “avantika_kaur@welch.test”
                        "[5].id", equalTo(5914150),
                        //    6. Verify the status is “Active” of user name is “Ms. Agnimitra Varrier”
                        "[10].status", equalTo("active"),
                        //    7. Verify the Gender = male of user name is “Amb. Dandapaani Pilla”
                        "[14].gender", equalTo("male"));
    }

}
